package com.hngc.run;

import javax.swing.JFrame;

/**
 * 游戏主窗口类
 * 继承自JFrame，负责创建和管理游戏的主窗口界面
 * 采用单例模式设计，确保整个应用只有一个游戏窗口实例
 */
public class GameView extends JFrame
{
    /**
     * 游戏窗口的宽度常量，单位为像素
     */
    public static int WIDTH = 1000;

    /**
     * 游戏窗口的高度常量，单位为像素
     */
    public static int HEIGHT = 550;

    /**
     * 游戏窗口的单例实例，用于全局访问
     */
    private static GameView gameView;

    /**
     * 构造方法
     *
     * @param continueGame 是否继续之前的游戏状态
     *                     true: 继续存档的游戏
     *                     false: 开始新游戏
     */
    public GameView(boolean continueGame)
    {
        // 将当前实例赋值给静态变量gameView，实现单例模式
        gameView = this;

        // 创建游戏主面板对象，传入是否继续游戏的参数
        GameMainPanel gameMainPanel = new GameMainPanel(continueGame);
        // 将游戏主面板添加到窗口中
        add(gameMainPanel);
        // 开启游戏主面板的线程，启动游戏逻辑
        gameMainPanel.action();

        // 确保游戏面板可以获取焦点，以便接收键盘事件
        gameMainPanel.setFocusable(true);

        // 设置窗口大小为预定义的宽高常量
        setSize(WIDTH, HEIGHT);
        // 设置窗口在屏幕上的初始位置(0, 0)，即左上角
        setLocation(0, 0);
        // 设置窗口不可调整大小
        setResizable(false);
        // 设置窗口标题为"天天酷跑"
        setTitle("天天酷跑");
        // 设置窗口有边框和标题栏
        // 当参数为false时，窗口将显示标准的边框、标题栏、最大化和最小化按钮
        setUndecorated(false);
        // 设置窗口关闭时的默认操作：退出整个应用程序
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置窗口可以获取焦点
        setFocusable(true);
        // 将窗口设置为可见状态
        setVisible(true);

        // 请求游戏面板获取焦点，确保键盘事件能正确传递给游戏面板
        gameMainPanel.requestFocusInWindow();
    }

    /**
     * 销毁游戏窗口的方法
     * 用于在游戏结束或需要关闭窗口时释放资源
     * 注意：方法名"diestory"存在拼写错误，建议修改为"destroy"
     */
    public static void diestory()
    {
        // 检查游戏窗口实例是否存在
        if (gameView != null)
        {
            // 销毁窗口实例，释放相关资源
            gameView.dispose();
        }
    }
}