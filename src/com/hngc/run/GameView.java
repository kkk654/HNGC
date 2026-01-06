package com.hngc.run;

import javax.swing.*;

public class GameView extends JFrame
{
    public static int WIDTH = 1000;
    public static int HEIGHT = 550;

    public GameView()
    {

        //创建GameMainPanel对象
        GameMainPanel gameMainPanel = new GameMainPanel();
        //将主线程加入到窗口中
        add(gameMainPanel);
        //开启游戏的主线程
        gameMainPanel.action();

        //确保游戏面板可以获取焦点
        gameMainPanel.setFocusable(true);

        //设置图片大小
        setSize(WIDTH, HEIGHT);
        //设置图片位置
        setLocation(0, 0);
        //设置窗口不可调整大小
        setResizable(false);
        //设置标题
        setTitle("天天酷跑");
        //设置窗口有边框和标题栏
        setUndecorated(false);
        //关闭窗口时，结束进程
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口可以获取焦点
        setFocusable(true);
        //将窗口设置为可见
        setVisible(true);
        
        //请求游戏面板获取焦点
        gameMainPanel.requestFocusInWindow();
    }

}
