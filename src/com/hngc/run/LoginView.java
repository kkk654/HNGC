package com.hngc.run;

import com.hngc.dao.UserDao;
import com.hngc.dao.UserDaoImpl;
import com.hngc.pojo.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 登录窗口类
 * 继承自JFrame，实现ActionListener接口
 * 用于显示登录界面，处理用户登录验证逻辑
 */
public class LoginView extends JFrame implements ActionListener
{

    /**
     * 邮箱标签，用于提示用户输入邮箱
     */
    JLabel emailJLabel = null;

    /**
     * 密码标签，用于提示用户输入密码
     */
    JLabel pwdJLabel = null;

    /**
     * 邮箱输入框，用于用户输入登录邮箱
     */
    JTextField emailJTextField = null;

    /**
     * 密码输入框，用于用户输入登录密码
     */
    JPasswordField pwdJPwdField = null;

    /**
     * 登录按钮，触发登录验证逻辑
     */
    JButton loginButton = null;

    /**
     * 取消按钮，用于退出登录窗口
     */
    JButton escButton = null;

    /**
     * 构造方法
     * 初始化登录界面的所有组件，包括标签、输入框、按钮和背景
     */
    public LoginView()
    {
        super();

        //===========播放背景音乐==============
        try
        {
            // 播放登录界面的背景音乐
            BackGroundMusic.playMusic("sound/hjm1.wav");
        }
        catch (Exception e)
        {
            // 捕获并打印音乐播放异常
            e.printStackTrace();
        }

        //==============绘制标签===============
        // 初始化邮箱标签
        emailJLabel = new JLabel();
        emailJLabel.setText("邮   箱: ");
        emailJLabel.setLocation(30, 150);
        emailJLabel.setSize(50, 20);
        emailJLabel.setForeground(Color.blue);
        super.add(emailJLabel);

        // 初始化密码标签
        pwdJLabel = new JLabel();
        pwdJLabel.setText("密   码: ");
        pwdJLabel.setLocation(30, 200);
        pwdJLabel.setSize(50, 20);
        pwdJLabel.setForeground(Color.blue);
        super.add(pwdJLabel);

        //==================================绘制输入框========================================
        // 初始化邮箱输入框，默认值为"123"
        emailJTextField = new JTextField("123");
        emailJTextField.setLocation(90, 150);
        emailJTextField.setSize(100, 20);
        super.add(emailJTextField);

        // 初始化密码输入框，默认值为"1234"
        pwdJPwdField = new JPasswordField("1234");
        pwdJPwdField.setLocation(90, 200);
        pwdJPwdField.setSize(100, 20);
        super.add(pwdJPwdField);

        //==================================绘制按钮========================================
        // 初始化登录按钮
        loginButton = new JButton();
        // 设置登录按钮图片
        loginButton.setIcon(new ImageIcon("image/denglu.gif"));
        loginButton.setLocation(30, 250);
        loginButton.setSize(68, 20);
        // 添加点击事件监听器
        loginButton.addActionListener(this);
        super.add(loginButton);

        // 初始化取消按钮
        escButton = new JButton();
        // 设置取消按钮图片
        escButton.setIcon(new ImageIcon("image/quxiao.gif"));
        escButton.setLocation(156, 250);
        escButton.setSize(68, 20);
        // 添加点击事件监听器
        escButton.addActionListener(this);
        super.add(escButton);

        //==============绘制背景===============
        // 初始化背景图片
        BackGroundImage backGroundImage = new BackGroundImage("image/login.jpg");
        backGroundImage.setSize(599, 330);
        super.add(backGroundImage);

        //==============添加背景到窗体===========
        super.add(backGroundImage);

        // 设置窗体大小
        super.setSize(599, 370);
        // 设置窗体位置
        super.setLocation(0, 0);
        // 设置窗体可见
        super.setVisible(true);
    }

    /**
     * 按钮点击事件处理方法
     *
     * @param e 事件对象
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // 获取用户输入的邮箱
        String email = emailJTextField.getText();
        // 获取用户输入的密码
        char[] passwordCharArray = pwdJPwdField.getPassword();
        String pwd = String.valueOf(passwordCharArray);

        // 处理登录按钮点击事件
        if (e.getSource().equals(loginButton))
        {
            // 创建用户DAO对象，用于数据库操作
            UserDao userDao = new UserDaoImpl();

            // 调用登录方法验证用户信息
            User user = userDao.login(email, pwd);
            if (user != null)
            {
                // 重点：登录成功，显示欢迎消息对话框
                JOptionPane.showMessageDialog(null, "欢迎\t" + user.getUsername() + "来到天天酷跑小游戏");

                // 关闭当前登录窗口
                this.dispose();

                // 打开游戏选择菜单界面
                new ChooseView();
            }
        }
        // 注：此处存在逻辑错误，两个条件判断完全相同，第二个条件永远不会执行
        else if (e.getSource().equals(loginButton))
        {
            // 登录失败提示信息
            JOptionPane.showMessageDialog(null, "登陆失败，请检查用户名和密码~");
        }
        // 处理取消按钮点击事件
        else
        {
            // 退出应用程序
            System.exit(0);
        }
    }
}