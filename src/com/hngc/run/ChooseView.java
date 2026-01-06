package com.hngc.run;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 选择窗口
 */

public class ChooseView  extends JFrame implements MouseListener {

    //开始游戏标签
    JLabel startGameJLabel = null;
    //继续游戏标签
    JLabel continueJLabel = null;
    //帮助游戏标签
    JLabel helpGameJLabel = null;
    //推出游戏标签
    JLabel exitJLabel = null;


    /**
     * 构造方法：初始化窗口
     */
    public ChooseView(){

        //--------开始游戏标签----------
        startGameJLabel = new JLabel();
        //设置标签位置
        startGameJLabel.setLocation(360,280);
        //设置标签大小
        startGameJLabel.setSize(150,40);
        //设置标签图片
        startGameJLabel.setIcon(new ImageIcon("image/hh1.png"));
        //默认禁用
        startGameJLabel.setEnabled(false);
        //添加鼠标监听器
        startGameJLabel.addMouseListener(this);
        //将开始游戏标签加入到窗口中
        this.add(startGameJLabel);


        //-----------继续游戏标签----------------
        continueJLabel = new JLabel();
        continueJLabel.setLocation(360,350);
        continueJLabel.setSize(150,40);
        //设置标签图标
        continueJLabel.setIcon(new ImageIcon("image/hh43.png"));
        //默认禁用
        continueJLabel.setEnabled(false);
        //添加鼠标监听器
        continueJLabel.addMouseListener(this);
        //添加标签到窗口中
        this.add(continueJLabel);

        //-------------帮助按钮-------------------
        helpGameJLabel = new JLabel();
        helpGameJLabel.setLocation(360,420);
        helpGameJLabel.setSize(150,40);
        //设置标签图标
        helpGameJLabel.setIcon(new ImageIcon("image/hh2.png"));
        //默认禁用
        helpGameJLabel.setEnabled(false);
        //添加鼠标监听器
        helpGameJLabel.addMouseListener(this);
        //添加标签到窗口中
        this.add(helpGameJLabel);

        //-------------退出按钮--------------
        exitJLabel = new JLabel();
        exitJLabel.setLocation(360,490);
        exitJLabel.setSize(150,40);
        //设置标签图标
        exitJLabel.setIcon(new ImageIcon("image/hh3.png"));
        //默认禁用
        exitJLabel.setEnabled(false);
        //添加鼠标监听器
        exitJLabel.addMouseListener(this);
        //添加标签到窗口中
        this.add(exitJLabel);




        //设置窗体背景
        BackGroundImage backGroundImage = new BackGroundImage("image/main.png");
        this.add(backGroundImage);
        //设置窗体大小
        this.setSize(1136,640);
        //设置大小固定
        this.setResizable(false);
        //设置居中
        this.setLocationRelativeTo(null);
        //设置可见
        this.setVisible(true);

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        //检查触发事件的原组件（看你是否点击了开始游戏按钮）\
        //equals()是字符串比较方法，作用是判断两个字符串值是否一致
        if (e.getSource().equals(startGameJLabel)){
//            //如果是，弹出一个对话框，提示正在载入
//            JOptionPane.showMessageDialog(null,"游戏正在载入");

            this.dispose();
            Thread thread = new Thread(new LoadingView());
            //启动线程
            thread.start();

        }

        //检查触发事件的原组件（看你是否点击了继续游戏按钮）\
        //equals()是字符串比较方法，作用是判断两个字符串值是否一致
        if (e.getSource().equals(continueJLabel)){
            //如果是，弹出一个对话框，提示正在载入
            JOptionPane.showMessageDialog(null,"无存档数据");
        }

        //检查触发事件的原组件（看你是否点击了帮助按钮）\
        //equals()是字符串比较方法，作用是判断两个字符串值是否一致
        if (e.getSource().equals(helpGameJLabel)){
            //如果是，弹出一个对话框，提示正在载入
            JOptionPane.showMessageDialog(null,"请联系开发人员");
        }


        //检查触发事件的原组件（看你是否点击了退出游戏按钮）\
        //equals()是字符串比较方法，作用是判断两个字符串值是否一致
        if (e.getSource().equals(exitJLabel)){
            //弹出一个确认对话框，询问用户是否确定要退出
            int confirm = JOptionPane.showConfirmDialog(null,"确定要退出吗","确认退出",JOptionPane.YES_NO_OPTION);
            //判断用户是否点击了”是“选项
            if (confirm == JOptionPane.YES_OPTION){
                //如果用户点击了确认按钮
                System.exit(0);
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    //鼠标进入
    @Override
    public void mouseEntered(MouseEvent e) {
        //检查触发事件的原组件（鼠标是否移动到开始游戏标签上）
        if (e.getSource().equals(startGameJLabel)){
            //如果是，将开始游戏标签设置为启用状态
            startGameJLabel.setEnabled(true);
        }

        //检查触发事件的原组件（鼠标是否移动到继续游戏标签上）
        if (e.getSource().equals(continueJLabel)){
            //如果是，将开始游戏标签设置为启用状态
            continueJLabel.setEnabled(true);
        }

        //检查触发事件的原组件（鼠标是否移动到帮助游戏标签上）
        if (e.getSource().equals(helpGameJLabel)){
            //如果是，将开始游戏标签设置为启用状态
            helpGameJLabel.setEnabled(true);
        }

        //检查触发事件的原组件（鼠标是否移动到退出游戏标签上）
        if (e.getSource().equals(exitJLabel)){
            //如果是，将开始游戏标签设置为启用状态
            exitJLabel.setEnabled(true);
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        //检查触发事件的原组件（鼠标是否移动到开始游戏标签上）
        if (e.getSource().equals(startGameJLabel)){
            //如果是，将开始游戏标签设置为启用状态
            startGameJLabel.setEnabled(false);
        }

        //检查触发事件的原组件（鼠标是否移动到继续游戏标签上）
        if (e.getSource().equals(continueJLabel)){
            //如果是，将开始游戏标签设置为启用状态
            continueJLabel.setEnabled(false);
        }

        //检查触发事件的原组件（鼠标是否移动到帮助游戏标签上）
        if (e.getSource().equals(helpGameJLabel)){
            //如果是，将开始游戏标签设置为启用状态
            helpGameJLabel.setEnabled(false);
        }

        //检查触发事件的原组件（鼠标是否移动到退出游戏标签上）
        if (e.getSource().equals(exitJLabel)){
            //如果是，将开始游戏标签设置为启用状态
            exitJLabel.setEnabled(false);
        }

    }
}
