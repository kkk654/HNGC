package com.hngc.run;

import com.hngc.util.GamePersistenceUtil;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 游戏选择窗口类
 * 继承自JFrame，实现MouseListener接口
 * 用于显示游戏主菜单，包括开始游戏、继续游戏、帮助和退出功能
 */
public class ChooseView extends JFrame implements MouseListener
{

    /**
     * 开始游戏标签按钮
     */
    JLabel startGameJLabel = null;

    /**
     * 继续游戏标签按钮
     */
    JLabel continueJLabel = null;

    /**
     * 帮助游戏标签按钮
     */
    JLabel helpGameJLabel = null;

    /**
     * 退出游戏标签按钮
     */
    JLabel exitJLabel = null;

    /**
     * 构造方法：初始化游戏选择窗口
     */
    public ChooseView()
    {
        // 初始化开始游戏标签
        startGameJLabel = new JLabel();
        startGameJLabel.setLocation(360, 280); // 设置位置
        startGameJLabel.setSize(150, 40); // 设置大小
        startGameJLabel.setIcon(new ImageIcon("image/hh1.png")); // 设置图片
        startGameJLabel.setEnabled(false); // 默认禁用
        startGameJLabel.addMouseListener(this); // 添加鼠标监听器
        this.add(startGameJLabel); // 将标签添加到窗口

        // 初始化继续游戏标签
        continueJLabel = new JLabel();
        continueJLabel.setLocation(360, 350); // 设置位置
        continueJLabel.setSize(150, 40); // 设置大小
        continueJLabel.setIcon(new ImageIcon("image/hh43.png")); // 设置图片
        continueJLabel.setEnabled(false); // 默认禁用
        continueJLabel.addMouseListener(this); // 添加鼠标监听器
        this.add(continueJLabel); // 将标签添加到窗口

        // 初始化帮助游戏标签
        helpGameJLabel = new JLabel();
        helpGameJLabel.setLocation(360, 420); // 设置位置
        helpGameJLabel.setSize(150, 40); // 设置大小
        helpGameJLabel.setIcon(new ImageIcon("image/hh2.png")); // 设置图片
        helpGameJLabel.setEnabled(false); // 默认禁用
        helpGameJLabel.addMouseListener(this); // 添加鼠标监听器
        this.add(helpGameJLabel); // 将标签添加到窗口

        // 初始化退出游戏标签
        exitJLabel = new JLabel();
        exitJLabel.setLocation(360, 490); // 设置位置
        exitJLabel.setSize(150, 40); // 设置大小
        exitJLabel.setIcon(new ImageIcon("image/hh3.png")); // 设置图片
        exitJLabel.setEnabled(false); // 默认禁用
        exitJLabel.addMouseListener(this); // 添加鼠标监听器
        this.add(exitJLabel); // 将标签添加到窗口

        // 设置窗口背景图片
        BackGroundImage backGroundImage = new BackGroundImage("image/main.png");
        this.add(backGroundImage);

        // 设置窗口属性
        this.setSize(1136, 640); // 设置窗口大小
        this.setResizable(false); // 设置大小固定
        this.setLocationRelativeTo(null); // 设置窗口居中
        this.setVisible(true); // 设置窗口可见
    }

    /**
     * 鼠标点击事件处理
     *
     * @param e 鼠标事件对象
     */
    @Override
    public void mouseClicked(MouseEvent e)
    {
        // 处理开始游戏按钮点击
        if (e.getSource().equals(startGameJLabel))
        {
            this.dispose(); // 关闭当前窗口
            Thread thread = new Thread(new LoadingView()); // 创建加载窗口线程
            thread.start(); // 启动线程
        }

        // 处理继续游戏按钮点击
        if (e.getSource().equals(continueJLabel))
        {
            try
            {
                // 判断是否有存档数据
                if (GamePersistenceUtil.getGameData() == null)
                {
                    JOptionPane.showMessageDialog(null, "没有存档数据"); // 提示没有存档
                }
                else
                {
                    this.dispose(); // 关闭当前窗口
                    new GameView(true); // 创建游戏视图并加载存档
                }
            }
            catch (Throwable e1)
            {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "没有存档数据"); // 异常时提示没有存档
            }
        }

        // 处理帮助按钮点击
        if (e.getSource().equals(helpGameJLabel))
        {
            JOptionPane.showMessageDialog(null, "请联系开发人员"); // 显示帮助信息
        }

        // 处理退出按钮点击
        if (e.getSource().equals(exitJLabel))
        {
            // 弹出确认对话框
            int confirm = JOptionPane.showConfirmDialog(null, "确定要退出吗", "确认退出", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION)
            {
                System.exit(0); // 用户确认后退出程序
            }
        }
    }

    /**
     * 鼠标按下事件处理
     *
     * @param e 鼠标事件对象
     */
    @Override
    public void mousePressed(MouseEvent e)
    {
        // 此方法未实现具体功能
    }

    /**
     * 鼠标释放事件处理
     *
     * @param e 鼠标事件对象
     */
    @Override
    public void mouseReleased(MouseEvent e)
    {
        // 此方法未实现具体功能
    }

    /**
     * 鼠标进入组件事件处理
     *
     * @param e 鼠标事件对象
     */
    @Override
    public void mouseEntered(MouseEvent e)
    {
        // 鼠标进入开始游戏按钮
        if (e.getSource().equals(startGameJLabel))
        {
            startGameJLabel.setEnabled(true); // 启用按钮
        }

        // 鼠标进入继续游戏按钮
        if (e.getSource().equals(continueJLabel))
        {
            continueJLabel.setEnabled(true); // 启用按钮
        }

        // 鼠标进入帮助游戏按钮
        if (e.getSource().equals(helpGameJLabel))
        {
            helpGameJLabel.setEnabled(true); // 启用按钮
        }

        // 鼠标进入退出游戏按钮
        if (e.getSource().equals(exitJLabel))
        {
            exitJLabel.setEnabled(true); // 启用按钮
        }
    }

    /**
     * 鼠标离开组件事件处理
     *
     * @param e 鼠标事件对象
     */
    @Override
    public void mouseExited(MouseEvent e)
    {
        // 鼠标离开开始游戏按钮
        if (e.getSource().equals(startGameJLabel))
        {
            startGameJLabel.setEnabled(false); // 禁用按钮
        }

        // 鼠标离开继续游戏按钮
        if (e.getSource().equals(continueJLabel))
        {
            continueJLabel.setEnabled(false); // 禁用按钮
        }

        // 鼠标离开帮助游戏按钮
        if (e.getSource().equals(helpGameJLabel))
        {
            helpGameJLabel.setEnabled(false); // 禁用按钮
        }

        // 鼠标离开退出游戏按钮
        if (e.getSource().equals(exitJLabel))
        {
            exitJLabel.setEnabled(false); // 禁用按钮
        }
    }
}