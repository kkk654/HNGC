package com.hngc.run;

import com.hngc.pojo.Person;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

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


    //游戏主面板
    public GameMainPanel()
    {
        //停止当前正在播放的背景音乐
        BackGroundMusic.stopMusic();

        try
        {
            //创建BackGroundMusic对象，指定我们播放的背景音乐
            new BackGroundMusic("sound/game.wav");
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
        g.drawString("您当前的分数 : 999 分", 140, 80);

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
    }

    //控制游戏画面的帧率，实现动画效果
    public void action()
    {
        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                while (true)
                {
                    try
                    {
                        //首先将当前线程暂停10毫秒
                        Thread.sleep(10);
                        //调用repaint方法，用于刷新图片页面
                        repaint();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
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
