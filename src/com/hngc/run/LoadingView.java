package com.hngc.run;

import javax.swing.*;
import java.awt.*;

/**
 * 游戏加载界面类
 * 继承自JFrame，实现Runnable接口
 * 用于在游戏启动时显示加载进度条和背景图片
 */
public class LoadingView extends JFrame implements Runnable
{

    /**
     * 背景图片标签，用于显示加载界面的背景图像
     */
    JLabel backGroundImage = null;

    /**
     * 进度条组件，用于显示游戏资源的加载进度
     */
    JProgressBar jProgressBar = null;

    /**
     * 构造方法
     * 初始化加载界面的背景图片和进度条
     */
    public LoadingView()
    {
        //--------设置背景图片--------------
        backGroundImage = new JLabel();
        // 设置背景图片图标
        backGroundImage.setIcon(new ImageIcon("image/hbg.jpg"));
        // 设置背景图片大小
        backGroundImage.setSize(568, 320);
        // 添加到窗口顶部区域
        add(backGroundImage, BorderLayout.NORTH);

        //-------设置进度条------------------
        jProgressBar = new JProgressBar();
        // 设置进度条背景颜色为橘色
        jProgressBar.setBackground(Color.ORANGE);
        // 启用进度条显示百分比字符串
        jProgressBar.setStringPainted(true);
        // 添加进度条到窗口
        add(jProgressBar);

        //----------设置图标窗口------------
        // 设置窗口图标
        setIconImage(new ImageIcon("image/115.png").getImage());
        //----------设置窗口的其他属性----------
        // 设置窗口不获取焦点
        setFocusable(false);
        // 设置窗口大小不可调整
        setResizable(false);
        // 设置窗口标题为"天天酷跑"
        setTitle("天天酷跑");
        // 设置窗口大小
        setSize(568, 420);
        // 设置窗口位置
        setLocation(0, 0);
        // 设置窗口可见
        setVisible(true);
    }


    /**
     * 实现Runnable接口的run方法
     * 用于模拟游戏资源加载过程，更新进度条，并在加载完成后启动游戏主界面
     */
    @Override
    public void run()
    {
        // 定义加载进度数组，模拟不同阶段的加载进度
        int[] loadingNumber = {0, 5, 10, 20, 30, 40, 50, 60, 70, 80, 90, 95, 99, 100};

        // 使用增强for循环遍历进度数组，模拟加载过程
        for (int i : loadingNumber)
        {
            try
            {
                // 线程暂停100毫秒，模拟加载过程的延迟
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                // 捕获并打印线程中断异常
                e.printStackTrace();
            }

            // 更新进度条的当前值
            jProgressBar.setValue(i);

            // 重点：判断加载进度是否达到100%
            if (i == 100)
            {
                // 加载完成，关闭当前加载窗口
                this.dispose();
                // 创建并启动游戏主界面，参数false表示开始新游戏
                new GameView(false);
            }
        }
    }
}