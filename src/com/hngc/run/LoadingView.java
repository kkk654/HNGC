package com.hngc.run;

import javax.swing.*;
import java.awt.*;

//实现Runnable要重写里面的run抽象方法
//线程
public class LoadingView extends JFrame implements Runnable
{

    //背景图片
    JLabel backGroundImage = null;
    //进度条，用于显示加载进度的
    JProgressBar jProgressBar = null;

    public LoadingView()
    {
        //--------设置背景图片--------------
        backGroundImage = new JLabel();
        //设置图标
        backGroundImage.setIcon(new ImageIcon("image/hbg.jpg"));
        //设置图片大小
        backGroundImage.setSize(568, 320);
        //添加到顶部区域
        add(backGroundImage, BorderLayout.NORTH);

        //-------设置进度条------------------
        jProgressBar = new JProgressBar();
        //设置进度条的背景颜色为橘色
        jProgressBar.setBackground(Color.ORANGE);
        //启用进度条支持字符串功能
        jProgressBar.setStringPainted(true);
        //添加到窗口中
        add(jProgressBar);

        //----------设置图标窗口------------
        setIconImage(new ImageIcon("image/115.png").getImage());
        //----------设置窗口的其他属性----------
        //设置窗口不获取焦点
        setFocusable(false);
        //设置窗口大小不可调整
        setResizable(false);
        //设置窗口标题为”天天酷跑“
        setTitle("天天酷跑");
        //设置窗口大小
        setSize(568, 420);
        //设置位置
        setLocation(0, 0);
        //设置窗口可见
        setVisible(true);

    }


    @Override
    public void run()
    {

        //定义一个数组,用来表示进度条的百分比,模拟进度条的更新
        int[] loadingNumber = {0, 5, 10, 20, 30, 40, 50, 60, 70, 80, 90, 95, 99, 100};
        //使用增强for循环遍历数组中的每个值，模拟加载过程的延迟，每次更新进度条之后暂停一段事件
        for (int i : loadingNumber)
        {
            try
            {
                //线程暂停100毫秒,模拟加载过程的延迟
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                //如果线程在睡眠时期间被中断，捕获并打印异常
                e.printStackTrace();
            }
            //设置进度条的当前值，将当前循环中的”i“设置为进度条的进度值
            jProgressBar.setValue(i);
            //当进度值达到100时
            if (i == 100)
            {
                //关闭加载的当前窗口
                this.dispose();
                //创建并启动游戏的页面
                new GameView(false);
            }
        }

    }
}
