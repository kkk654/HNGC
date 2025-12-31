package com.hngc.run;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame implements ActionListener
{

    //邮箱标签
    JLabel emailJLabel = null;
    //密码标签
    JLabel pwdJLabel = null;


    //邮箱输入框
    JTextField emailJTextField = null;
    //密码输入框
    JPasswordField pwdJPwdField = null;


    //登录按钮
    JButton loginButton = null;
    //取消按钮
    JButton escButton = null;

    public LoginView()
    {
        super();
        //===========绘制背景音乐==============
        BackGroundMusic bgm = new BackGroundMusic("sound/main.wav");
        bgm.setVolume(0.7f); // 设置音量
        bgm.loop(); // 循环播放

        //==============绘制标签===============
        //邮箱
        emailJLabel = new JLabel();
        emailJLabel.setText("邮  箱: ");
        emailJLabel.setLocation(30,150);
        emailJLabel.setSize(50,20);
        emailJLabel.setForeground(Color.blue);
        super.add(emailJLabel);
        //密码
        pwdJLabel = new JLabel();
        pwdJLabel.setText("密    码:");
        pwdJLabel.setLocation(30,200);
        pwdJLabel.setSize(50,20);
        pwdJLabel.setForeground(Color.blue);
        super.add(pwdJLabel);

        //==================================绘制输入框========================================
        //设置邮箱输入框参数
        emailJTextField = new JTextField();
        emailJTextField.setLocation(90,150);
        emailJTextField.setSize(100,20);
        super.add(emailJTextField);

        //设置密码输入框参数
        pwdJPwdField = new JPasswordField();
        pwdJPwdField.setLocation(90,200);
        pwdJPwdField.setSize(100,20);
        super.add(pwdJPwdField);

        //==================================绘制按钮========================================
        //设置登录按钮的参数
        loginButton = new JButton();
        //设置图片
        loginButton.setIcon(new ImageIcon("image/denglu.gif"));
        loginButton.setLocation(30,250);
        loginButton.setSize(68,20);
        loginButton.addActionListener(this);
        super.add(loginButton);

        //设置登录取消的参数
        escButton = new JButton();
        //设置图片
        escButton.setIcon(new ImageIcon("image/quxiao.gif"));
        escButton.setLocation(156,250);
        escButton.setSize(68,20);
        escButton.addActionListener(this);
        super.add(escButton);

        //==============绘制背景===============
        BackGroundImage backGroundImage = new BackGroundImage("image/login.jpg");
        backGroundImage.setSize(599, 330);
        super.add(backGroundImage);

        //==============添加背景到窗体===========
        super.add(backGroundImage);

        //设置窗体大小
        super.setSize(599, 370);
        //设置窗体位置
        super.setLocation(0, 0);
        //设置窗体可见
        super.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {

    }
}
