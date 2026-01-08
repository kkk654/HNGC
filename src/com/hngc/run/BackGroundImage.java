package com.hngc.run;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * 背景图片面板类
 * 继承自JPanel，用于在界面上显示背景图片
 */
public class BackGroundImage extends JPanel
{

    /**
     * 用于存储背景图片的Image对象
     */
    Image image = null;

    /**
     * 构造方法，通过图片路径加载背景图片
     *
     * @param path 背景图片的文件路径
     */
    public BackGroundImage(String path)
    {
        try
        {
            // 通过ImageIO读取指定路径的图片文件并转换为Image对象
            image = ImageIO.read(new File(path).toURI().toURL());
        }
        catch (Exception e)
        {
            // 打印异常信息，便于调试图片加载失败的问题
            e.printStackTrace();
        }
    }

    /**
     * 重写JPanel的paint方法，用于绘制背景图片
     *
     * @param g 绘图上下文对象，用于执行绘图操作
     */
    @Override
    public void paint(Graphics g)
    {
        // 在面板的左上角(0, 0)位置绘制背景图片，图片大小自适应面板大小
        g.drawImage(image, 0, 0, null);
    }
}