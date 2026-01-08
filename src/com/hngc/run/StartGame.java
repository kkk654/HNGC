package com.hngc.run;

/**
 * 天天酷跑游戏的入口类
 * 包含程序的main方法，用于启动游戏
 */
public class StartGame
{

    /**
     * 程序的主入口方法
     *
     * @param args 命令行参数（未使用）
     */
    public static void main(String[] args)
    {
        // 重点：创建登录界面实例，启动游戏的登录流程
        new LoginView();
    }
}