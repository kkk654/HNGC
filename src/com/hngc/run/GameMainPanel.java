package com.hngc.run;

import com.hngc.pojo.ArchiveGameData;
import com.hngc.pojo.Bomb;
import com.hngc.pojo.Gold;
import com.hngc.pojo.Person;
import com.hngc.util.GamePersistenceUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * 游戏主面板类
 * 继承自JPanel，实现KeyListener接口
 * 负责游戏的主要逻辑和渲染，包括角色控制、金币和炸弹生成、碰撞检测等
 */
public class GameMainPanel extends JPanel implements KeyListener
{

    /**
     * 游戏背景图片
     */
    Image backGroundImage = null;

    /**
     * 背景图片水平位置变量，用于实现背景滚动效果
     */
    int backIndex = 0;

    /**
     * 分数显示图片
     */
    Image scoreImage = null;

    /**
     * 游戏角色对象
     */
    Person person = new Person();

    /**
     * 上移按键状态
     */
    private boolean moveUp = false;

    /**
     * 下移按键状态
     */
    private boolean moveDown = false;

    /**
     * 左移按键状态
     */
    private boolean moveLeft = false;

    /**
     * 右移按键状态
     */
    private boolean moveRight = false;

    /**
     * 用于控制游戏角色移动的定时器
     */
    private Timer moveTimer;

    /**
     * 暂停游戏图片
     */
    Image stopImage = null;

    /**
     * 暂停状态变量
     */
    boolean stop;

    /**
     * 金币列表，存储当前游戏中的所有金币对象
     */
    java.util.List<Gold> goldList = new java.util.LinkedList<Gold>();

    /**
     * 控制游戏金币位置的变量，用于控制金币生成频率
     */
    int goldIndex = 0;

    /**
     * 炸弹列表，存储当前游戏中的所有炸弹对象
     */
    java.util.List<Bomb> bombList = new java.util.LinkedList<Bomb>();

    /**
     * 控制炸弹位置的变量，用于控制炸弹生成频率
     */
    int bombIndex = 0;

    /**
     * 游戏难度等级，影响炸弹大小和生成频率
     */
    int level = 1;

    /**
     * 游戏存档实体类
     */
    ArchiveGameData archiveGameData;

    /**
     * 构造方法
     *
     * @param continueGame 是否继续游戏，true表示继续之前的存档
     */
    public GameMainPanel(boolean continueGame)
    {
        // 创建游戏存档对象
        ArchiveGameData gameData = new ArchiveGameData();

        // 判断用户是否继续游戏
        if (continueGame)
        {
            try
            {
                // 从文件中读取存档数据
                gameData = GamePersistenceUtil.getGameData();
            }
            catch (Throwable e)
            {
                e.printStackTrace();
            }

            // 如果存档数据不为空，则将数据赋值给游戏角色、金币列表、炸弹列表
            if (gameData != null)
            {
                person = gameData.getPerson();
                goldList = gameData.getGoldList();
                bombList = gameData.getBombList();
            }
        }

        // 停止当前正在播放的背景音乐
        BackGroundMusic.stopMusic();

        try
        {
            // 创建BackGroundMusic对象，指定播放的背景音乐
            new BackGroundMusic("sound/hjm2.wav");
        }
        catch (Exception e)
        {
            // 加载或者初始化音频时发生任何异常（文件不存在，格式不支持）
            e.printStackTrace();
        }

        // 设置背景图片
        try
        {
            backGroundImage = ImageIO.read(new File("image/cc.png"));
            scoreImage = ImageIO.read(new File("image/a12.png"));
            stopImage = ImageIO.read(new File("image/a9.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        // 初始化控制角色移动的定时器
        moveTimer = new Timer(20, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // 根据按键状态移动角色
                if (moveRight)
                {
                    person.setX(person.getX() + person.getSpeed());
                }
                if (moveUp)
                {
                    person.setY(person.getY() - person.getSpeed());
                }
                if (moveLeft)
                {
                    person.setX(person.getX() - person.getSpeed());
                }
                if (moveDown)
                {
                    person.setY(person.getY() + person.getSpeed());
                }

                // 重新绘制界面
                repaint();
            }
        });

        // 启动定时器
        moveTimer.start();

        // 注册键盘监听器
        this.addKeyListener(this);
    }

    /**
     * 重写paint方法，绘制游戏界面
     *
     * @param g 绘图上下文对象
     */
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        // 绘制背景图片
        g.drawImage(backGroundImage, 0, 0, GameView.WIDTH, GameView.HEIGHT, null);

        // 绘制滚动的背景图片的第二部分，实现无缝滚动效果
        g.drawImage(backGroundImage, GameView.WIDTH + backIndex, 0, GameView.WIDTH, GameView.HEIGHT, null);

        // 判断图片是否已经完全滚动出屏幕左侧
        if (backIndex == -GameView.WIDTH)
        {
            // 如果是，则重置背景位置
            backIndex = 0;
        }

        // 绘制分数显示图片
        g.drawImage(scoreImage, 80, 40, 259, 64, null);

        // 设置分数文字颜色为橘色
        g.setColor(Color.ORANGE);

        // 设置分数文字字体
        g.setFont(new Font("微软雅黑", Font.PLAIN, 15));

        // 绘制当前分数
        g.drawString("您当前的分数 : " + person.getScore(), 140, 80);

        // 更新角色动画帧
        person.step();

        // 绘制角色图像
        person.personPaint(g);

        // 调用角色移动方法
        person.moveUp();
        person.moveDown();
        person.moveLeft();
        person.moveRight();

        // 角色自由落体效果
        person.low();

        // 如果游戏暂停，绘制暂停图片
        if (stop)
        {
            g.drawImage(stopImage, 0, 0, 76, 78, null);
        }

        // 金币相关处理
        Gold gold = new Gold();

        // 判断是否需要添加新的金币到列表中
        if (goldIndex++ % gold.getNewGoldTime() == 0)
        {
            goldList.add(gold);
        }

        // 绘制所有金币并更新位置
        for (Gold gold2 : goldList)
        {
            gold2.goldPaint(g); // 绘制金币
            gold2.step(); // 更新金币位置
        }

        // 创建金币迭代器，用于安全地遍历和修改金币列表
        Iterator<Gold> goldIterator = goldList.iterator();

        // 遍历所有金币
        while (goldIterator.hasNext())
        {
            Gold golds = goldIterator.next();

            // 碰撞检测：判断角色是否与金币发生碰撞
            if (person.getX() + person.getWidth() > golds.getX() &&
                person.getX() < golds.getX() + golds.getWidth() &&
                person.getY() + person.getHeight() > golds.getY() &&
                person.getY() < golds.getY() + golds.getHeight())
            {

                goldIterator.remove(); // 移除被收集的金币
                person.setScore(person.getScore() + gold.getGoldPrice()); // 增加角色分数

                // 每100分提升一次游戏难度
                if (person.getScore() % 100 == 0)
                {
                    level++;
                }
            }

            // 判断金币是否超出屏幕左侧范围
            if (golds.getX() <= -golds.getWidth())
            {
                System.out.println("清理金币" + golds); // 打印清理信息
                goldIterator.remove(); // 移除超出范围的金币
            }
        }

        // 炸弹相关处理
        Bomb bomb = new Bomb(level); // 根据当前难度创建炸弹

        // 判断是否需要添加新的炸弹到列表中
        if (bombIndex++ % bomb.getBombTime() == 0)
        {
            bombList.add(bomb);
        }

        // 绘制所有炸弹并更新位置
        for (Bomb bomb2 : bombList)
        {
            bomb2.bombRepaint(g); // 绘制炸弹
            bomb2.step(); // 更新炸弹位置
        }

        // 创建炸弹迭代器，用于安全地遍历和修改炸弹列表
        Iterator<Bomb> iterator = bombList.iterator();

        // 遍历所有炸弹
        while (iterator.hasNext())
        {
            Bomb bomb2 = iterator.next();

            // 碰撞检测：判断角色是否与炸弹发生碰撞
            if (person.getX() + person.getWidth() > bomb2.getX() &&
                person.getX() < bomb2.getX() + bomb2.getWidth() &&
                person.getY() + person.getHeight() > bomb2.getY() &&
                person.getY() < bomb2.getY() + bomb2.getHeight())
            {

                iterator.remove(); // 移除碰撞的炸弹
                person.reduceHealth(10); // 角色扣血

                // 判断角色是否死亡
                if (person.isDie())
                {
                    GameView.diestory(); // 销毁游戏视图
                    new GameOverView(person); // 创建游戏结束视图
                }
            }

            // 判断炸弹是否超出屏幕左侧
            if (bomb2.getX() <= -bomb2.getWidth())
            {
                System.out.println("炸弹清除"); // 打印清理信息
                iterator.remove(); // 移除超出范围的炸弹
            }
        }
    }

    /**
     * 控制游戏画面的帧率，实现动画效果
     */
    public void action()
    {
        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                // 游戏主循环，直到角色死亡
                while (!person.isDie())
                {
                    try
                    {
                        Thread.sleep(10); // 控制帧率

                        // 如果游戏未暂停，则刷新画面
                        if (!stop)
                        {
                            repaint();
                        }
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }

                // 游戏结束处理
                GameView.diestory();
                new GameOverView(person);
            }
        };

        // 启动游戏线程
        thread.start();
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        // 未实现此方法
    }

    /**
     * 键盘按键按下事件处理
     *
     * @param e 键盘事件对象
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        // 获取按键的键码
        int keyCode = e.getKeyCode();

        // 根据不同按键设置相应的移动状态
        if (keyCode == KeyEvent.VK_D)
        {
            moveRight = true;
        }
        if (keyCode == KeyEvent.VK_A)
        {
            moveLeft = true;
        }
        if (keyCode == KeyEvent.VK_W)
        {
            moveUp = true;
        }
        if (keyCode == KeyEvent.VK_S)
        {
            moveDown = true;
        }

        // 按下空格键暂停/继续游戏
        if (keyCode == KeyEvent.VK_SPACE)
        {
            stop = !stop; // 切换暂停状态

            // 根据暂停状态控制移动定时器
            if (stop)
            {
                moveTimer.stop(); // 停止定时器
            }
            else
            {
                moveTimer.start(); // 启动定时器
            }

            repaint(); // 刷新画面
            GamePersistenceUtil.save(person, goldList, bombList); // 保存游戏进度
        }
    }

    /**
     * 键盘按键释放事件处理
     *
     * @param e 键盘事件对象
     */
    @Override
    public void keyReleased(KeyEvent e)
    {
        // 获取按键的键码
        int keyCode = e.getKeyCode();

        // 根据不同按键设置相应的移动状态
        if (keyCode == KeyEvent.VK_D)
        {
            moveRight = false;
        }
        if (keyCode == KeyEvent.VK_A)
        {
            moveLeft = false;
        }
        if (keyCode == KeyEvent.VK_W)
        {
            moveUp = false;
        }
        if (keyCode == KeyEvent.VK_S)
        {
            moveDown = false;
        }
    }
}