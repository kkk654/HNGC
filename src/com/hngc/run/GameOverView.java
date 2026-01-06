package com.hngc.run;

import com.hngc.pojo.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverView extends JFrame implements ActionListener
{

    //展示分数
    JLabel scoreLabel = null;
    //再来一次按钮
    JButton againButton = null;
    //退出游戏按钮
    JButton exitButton = null;


    public GameOverView(Person person)
    {

        //展示分数
        //创建JLabel对象来展示得分
        scoreLabel = new JLabel();
        //设置文本
        scoreLabel.setText("本次获得的金币分数为：\t" + person.getScore());
        //设置标签颜色为橘色
        scoreLabel.setForeground(Color.ORANGE);
        //设置标签大小
        scoreLabel.setSize(200, 20);
        //设置标签位置
        scoreLabel.setLocation(120, 30);
        //将标签添加到窗口中
        add(scoreLabel);


        //再来一次按钮
        //创建JButton对象
        againButton = new JButton("再来一次");
        //设置按钮大小
        againButton.setSize(100, 20);
        //设置按钮位置
        againButton.setLocation(80, 150);
        //为按钮添加事件的监听器
        againButton.addActionListener(this);
        //加入窗口中
        add(againButton);

        //退出按钮
        exitButton = new JButton("退出游戏");
        exitButton.setSize(100, 20);
        exitButton.setLocation(240, 150);
        exitButton.addActionListener(this);
        add(exitButton);


        //创建人物死亡的背景图片
        BackGroundImage backGroundImage = new BackGroundImage("image/pp.png");
        //设置背景图片的大小
        backGroundImage.setSize(393, 208);
        //将背景图片添加到窗口中
        this.add(backGroundImage);
        //设置窗口大小
        this.setSize(393, 208);
        //设置窗口位置
        this.setLocation(750, 300);
        //设置窗口为无边窗口
        this.setUndecorated(true);
        //设置窗口可见
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //检查事件源是否是“再来一次按钮”
        if (e.getSource().equals(againButton))
        {
            //隐藏当前游戏结束窗口
            this.setVisible(false);
            //释放当前窗口
            this.dispose();
            //创建新的线程来启动游戏界面
            Thread thread = new Thread(new LoadingView());
            //启动线程
            thread.start();
        }
        //检查事件源是否是“退出游戏按钮”
        if (e.getSource().equals(exitButton))
        {
            System.exit(0);
        }

    }
}
