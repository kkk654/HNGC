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

public class GameMainPanel extends JPanel implements KeyListener
{

    //游戏背景
    Image backGroundImage = null;


    //图片水平位置变量
    int backIndex = 0;

    //分数显示
    Image scoreImage = null;

    //游戏角色
    Person person = new Person();

    //添加按键状态
    private boolean moveUp = false;
    private boolean moveDown = false;
    private boolean moveLeft = false;
    private boolean moveRight = false;
    //用于控制游戏角色移动的定时器
    private Timer moveTimer;


    //暂停游戏图片
    Image stopImage = null;

    //停止变量
    boolean stop;
    java.util.List<Gold> goldList = new java.util.LinkedList<Gold>();

    //控制游戏金币位置的变量
    int goldIndex = 0;

    //炸弹列表
    java.util.List<Bomb> bombList = new java.util.LinkedList<Bomb>();

    //定义控制炸弹位置的变量
    int bombIndex = 0;

    //炸弹变大的变量
    int level = 1;

    //游戏存档实体类
    ArchiveGameData archiveGameData;

    //游戏主面板
    public GameMainPanel(boolean continueGame)
    {
        //创建游戏存档
        ArchiveGameData gameData = new ArchiveGameData();
        //判断用户是否继续游戏
        if (continueGame)
        {
            try
            {
                //从文件中读取存档数据
                gameData = GamePersistenceUtil.getGameData();
            }
            catch (Throwable e)
            {
                e.printStackTrace();
            }
            //如果存档数据不为空，则将数据赋值给游戏角色、金币列表、炸弹列表赋值给对应的属性
            if (gameData != null)
            {
                person = gameData.getPerson();
                goldList = gameData.getGoldList();
                bombList = gameData.getBombList();
            }
        }


        //停止当前正在播放的背景音乐
        BackGroundMusic.stopMusic();

        try
        {
            //创建BackGroundMusic对象，指定我们播放的背景音乐
            new BackGroundMusic("sound/hjm2.wav");
        }
        catch (Exception e)
        {
            //加载或者初始化音频时发生任何异常（文件不存在，格式不支持）
            e.printStackTrace();
        }

        //设置背景图片
        try
        {
            backGroundImage = ImageIO.read(new File("image/cc.png"));
            scoreImage = ImageIO.read(new File("image/a12.png"));
            //暂停
            stopImage = ImageIO.read(new File("image/a9.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        //在构造方法中初始化一个定时器
        moveTimer = new Timer(20, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //按键按住时移动角色
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

                //重新绘制界面
                repaint();
            }
        });
        //启动定时器
        moveTimer.start();

        //注册键盘监听器
        this.addKeyListener(this);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        g.drawImage(backGroundImage, 0, 0, GameView.WIDTH, GameView.HEIGHT, null);
        //绘制滚动的背景图片的第二部分
        //确保当第一张图片完全滚动出屏幕左侧时，第二张图片正好填满整个屏幕
        g.drawImage(backGroundImage, GameView.WIDTH + backIndex, 0, GameView.WIDTH, GameView.HEIGHT, null);
        //判断图片是否已经超过屏幕左侧
        if (backIndex == -GameView.WIDTH)
        {
            //如果是，则将backIndex变量手动设置为0
            backIndex = 0;
        }

        //绘制分数
        g.drawImage(scoreImage, 80, 40, 259, 64, null);
        //设置颜色为橘色
        g.setColor(Color.ORANGE);
        //设置字体
        g.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        //绘制文本
        g.drawString("您当前的分数 : " + person.getScore(), 140, 80);

        //绘制游戏角色
        person.step();

        //绘制角色图像
        person.personPaint(g);

        //角色行动方法
        person.moveUp();
        person.moveDown();
        person.moveLeft();
        person.moveRight();
        //人物自由落体
        person.low();

        //绘制停止图片
        if (stop)
        {
            g.drawImage(stopImage, 0, 0, 76, 78, null);
        }
        //绘制金币
        //创建金币的实体类
        Gold gold = new Gold();
        //判断是否需要添加新的金币到列表中
        if (goldIndex++ % gold.getNewGoldTime() == 0)
        {
            //添加金币对象
            goldList.add(gold);
        }
        //绘制金币
        //增强for循环
        for (Gold gold2 : goldList)
        {
            //遍历金币列表，调用金币对象的goldPaint方法绘制金币
            gold2.goldPaint(g);
            //调用gold对象的step方法，使金币移动
            gold2.step();
        }

        //创建列表的迭代器
        Iterator<Gold> goldIterator = goldList.iterator();
        //检查迭代器中是否有下一个金币对象
        //hasNext方法判断迭代器中是否有下一个元素
        while (goldIterator.hasNext())
        {
            //获取下一个金币对象
            Gold golds = (Gold) goldIterator.next();
            //判断角色是否和金币发生碰撞（角色的右边界大于金币的左边界）
            if (person.getX() + person.getWidth() > golds.getX()
                //判断左边界是否小于金币的右边界
                && person.getX() < golds.getX() + golds.getWidth()
                //检查角色的底部是否高于金币的顶部
                && person.getY() + person.getHeight() > golds.getY()
                //检查角色的顶部是否低于金币的底部
                && person.getY() < golds.getY() + golds.getHeight())
            {
                //如果角色与金币发生碰撞，则移除金币对象
                goldIterator.remove();
                //增加角色的分数，分数增长的值为当前金币的价格
                person.setScore(person.getScore() + gold.getGoldPrice());
                //判断玩家的得分是否是100的倍数
                if (person.getScore() % 100 == 0)
                {
                    //提升游戏困难等级
                    level++;
                }
            }
            //判断金币是否超出屏幕左侧范围
            if (golds.getX() <= -golds.getWidth())
            {
                //如果金币超出范围，打印金币移除信息
                System.out.println("清理金币" + golds);
                //移除金币对象
                goldIterator.remove();
            }
        }


        //绘制炸弹
        //创建炸弹的实体类
        Bomb bomb = new Bomb(level);
        //判断bombIndex是否满足创建新炸弹的条件
        //如果说bombIndex递增后和爆炸事件取余为0，表示创建炸弹
        if (bombIndex++ % bomb.getBombTime() == 0)
        {
            //将新的炸弹对象添加到炸弹列表中
            bombList.add(bomb);
        }
        //遍历炸弹列表中每一个炸弹
        //增强for循环
        for (Bomb bomb2 : bombList)
        {
            //调用bombRepaint方法，将炸弹绘制到窗口中
            bomb2.bombRepaint(g);
            //调用step方法，更新炸弹位置
            bomb2.step();
        }

        //创建炸弹的迭代器
        Iterator<Bomb> iterator = bombList.iterator();
        //检查迭代器中是否有下一个炸弹对象
        while (iterator.hasNext())
        {
            //获取下一个炸弹对象
            Bomb bomb2 = (Bomb) iterator.next();
            //检查角色是否与炸弹对象发生碰撞
            if (person.getX() + person.getWidth() > bomb2.getX()
                && person.getX() < bomb2.getX() + bomb2.getWidth()
                && person.getY() + person.getHeight() > bomb2.getY()
                && person.getY() < bomb2.getY() + bomb2.getHeight())
            {
                //角色与炸弹发生碰撞，移除炸弹对象
                iterator.remove();
                //每次扣除10点血量
                person.reduceHealth(10);
                //判断人物是否死亡
                if (person.isDie())
                {
                    GameView.diestory();
                    new GameOverView(person);
                }

            }
            //判断炸弹是否超出屏幕左侧
            if (bomb2.getX() <= -bomb2.getWidth())
            {
                //在控制台打印炸弹清楚
                System.out.println("炸弹清除");
                //移除已经引爆的炸弹对象
                iterator.remove();
            }
        }
    }

    //控制游戏画面的帧率，实现动画效果
    public void action()
    {
        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                while (!person.isDie())
                {
                    try
                    {
                        //首先将当前线程暂停10毫秒
                        Thread.sleep(10);
                        //调用repaint方法，用于刷新图片页面
                        if(!stop)
                            repaint();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                }
                GameView.diestory();
                new GameOverView(person);
            }
        };
        //启动新创建的线程
        thread.start();
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {

        //获取按键的键码
        int keyCode = e.getKeyCode();
        //根据不同按键设置相应的移动状态
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
        //按下空格暂停游戏
        if (keyCode == KeyEvent.VK_SPACE)
        {
            stop = !stop;
            if (stop)
            {
                //停止定时器
                moveTimer.stop();
            }
            else
            {
                moveTimer.start();
            }
            repaint();
            GamePersistenceUtil.save(person, goldList, bombList);
        }

    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        //获取按键的键码
        int keyCode = e.getKeyCode();
        //根据不同按键设置相应的移动状态
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