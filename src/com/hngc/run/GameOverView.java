package com.hngc.run;

import com.hngc.pojo.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 游戏结束视图类
 * 继承自JFrame，实现ActionListener接口
 * 用于显示游戏结束界面，包括最终得分、再来一次和退出游戏选项
 */
public class GameOverView extends JFrame implements ActionListener
{

    /**
     * 用于展示最终分数的标签
     */
    JLabel scoreLabel = null;

    /**
     * "再来一次"按钮，用于重新开始游戏
     */
    JButton againButton = null;

    /**
     * "退出游戏"按钮，用于退出程序
     */
    JButton exitButton = null;

    /**
     * 构造方法
     *
     * @param person 游戏角色对象，用于获取最终得分
     */
    public GameOverView(Person person)
    {
        // 初始化分数标签
        scoreLabel = new JLabel();
        scoreLabel.setText("本次获得的金币分数为：\t" + person.getScore()); // 设置显示文本
        scoreLabel.setForeground(Color.ORANGE); // 设置文本颜色为橘色
        scoreLabel.setSize(200, 20); // 设置标签大小
        scoreLabel.setLocation(120, 30); // 设置标签位置
        add(scoreLabel); // 将标签添加到窗口

        // 初始化"再来一次"按钮
        againButton = new JButton("再来一次");
        againButton.setSize(100, 20); // 设置按钮大小
        againButton.setLocation(80, 150); // 设置按钮位置
        againButton.addActionListener(this); // 添加事件监听器
        add(againButton); // 将按钮添加到窗口

        // 初始化"退出游戏"按钮
        exitButton = new JButton("退出游戏");
        exitButton.setSize(100, 20); // 设置按钮大小
        exitButton.setLocation(240, 150); // 设置按钮位置
        exitButton.addActionListener(this); // 添加事件监听器
        add(exitButton); // 将按钮添加到窗口

        // 创建并设置背景图片
        BackGroundImage backGroundImage = new BackGroundImage("image/pp.png");
        backGroundImage.setSize(393, 208); // 设置背景图片大小
        this.add(backGroundImage); // 将背景图片添加到窗口

        // 设置窗口属性
        this.setSize(393, 208); // 设置窗口大小
        this.setLocation(750, 300); // 设置窗口位置
        this.setUndecorated(true); // 设置窗口为无边框窗口
        this.setVisible(true); // 设置窗口可见
    }

    /**
     * 按钮点击事件处理方法
     *
     * @param e 事件对象
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // 处理"再来一次"按钮点击事件
        if (e.getSource().equals(againButton))
        {
            this.setVisible(false); // 隐藏当前窗口
            this.dispose(); // 释放窗口资源
            Thread thread = new Thread(new LoadingView()); // 创建加载窗口线程
            thread.start(); // 启动线程，重新开始游戏
        }

        // 处理"退出游戏"按钮点击事件
        if (e.getSource().equals(exitButton))
        {
            System.exit(0); // 退出程序
        }
    }
}