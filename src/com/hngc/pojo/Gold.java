package com.hngc.pojo;

import com.hngc.run.GameView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

/**
 * 金币类，实现了Serializable接口以支持序列化
 * 用于表示游戏中的金币对象，包含金币的位置、大小、图片、速度等属性
 * 以及金币的绘制、移动等行为方法
 */
public class Gold implements Serializable
{

    /**
     * 金币的x轴坐标
     */
    private int x;

    /**
     * 金币的y轴坐标
     */
    private int y;

    /**
     * 金币图像的宽度，默认值为40
     */
    private int width = 40;

    /**
     * 金币图像的高度，默认值为40
     */
    private int height = 40;

    /**
     * 金币的图像
     * 使用transient关键字修饰，不参与序列化过程
     */
    private transient Image goldImg = null;

    /**
     * 金币的移动速度，默认值为2
     */
    private int speed = 2;

    /**
     * 生成新的金币的时间间隔，默认值为10
     */
    private int newGoldTime = 10;

    /**
     * 收集到的金币价值，默认值为10
     */
    private int goldPrice = 10;

    /**
     * 数组，用来存储金币图片的索引
     * 初始容量为100
     */
    private int[] ImageIndex = new int[100];

    /**
     * 图像索引数组的当前长度
     */
    private int ImageIndexLength;

    /**
     * 获取金币的x轴坐标
     *
     * @return 金币的x轴坐标
     */
    public int getX()
    {
        return x;
    }

    /**
     * 设置金币的x轴坐标
     *
     * @param x 金币的x轴坐标
     */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
     * 获取金币的y轴坐标
     *
     * @return 金币的y轴坐标
     */
    public int getY()
    {
        return y;
    }

    /**
     * 设置金币的y轴坐标
     *
     * @param y 金币的y轴坐标
     */
    public void setY(int y)
    {
        this.y = y;
    }

    /**
     * 获取金币图像的宽度
     *
     * @return 金币图像的宽度
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * 设置金币图像的宽度
     *
     * @param width 金币图像的宽度
     */
    public void setWidth(int width)
    {
        this.width = width;
    }

    /**
     * 获取金币图像的高度
     *
     * @return 金币图像的高度
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * 设置金币图像的高度
     *
     * @param height 金币图像的高度
     */
    public void setHeight(int height)
    {
        this.height = height;
    }

    /**
     * 获取金币的图像
     *
     * @return 金币的图像
     */
    public Image getGoldImg()
    {
        return goldImg;
    }

    /**
     * 设置金币的图像
     *
     * @param goldImg 金币的图像
     */
    public void setGoldImg(Image goldImg)
    {
        this.goldImg = goldImg;
    }

    /**
     * 获取金币的移动速度
     *
     * @return 金币的移动速度
     */
    public int getSpeed()
    {
        return speed;
    }

    /**
     * 设置金币的移动速度
     *
     * @param speed 金币的移动速度
     */
    public void setSpeed(int speed)
    {
        this.speed = speed;
    }

    /**
     * 获取生成新的金币的时间间隔
     *
     * @return 生成新的金币的时间间隔
     */
    public int getNewGoldTime()
    {
        return newGoldTime;
    }

    /**
     * 设置生成新的金币的时间间隔
     *
     * @param newGoldTime 生成新的金币的时间间隔
     */
    public void setNewGoldTime(int newGoldTime)
    {
        this.newGoldTime = newGoldTime;
    }

    /**
     * 获取收集到的金币价值
     *
     * @return 收集到的金币价值
     */
    public int getGoldPrice()
    {
        return goldPrice;
    }

    /**
     * 设置收集到的金币价值
     *
     * @param goldPrice 收集到的金币价值
     */
    public void setGoldPrice(int goldPrice)
    {
        this.goldPrice = goldPrice;
    }

    /**
     * 获取金币图片索引数组
     *
     * @return 金币图片索引数组
     */
    public int[] getImageIndex()
    {
        return ImageIndex;
    }

    /**
     * 设置金币图片索引数组
     *
     * @param imageIndex 金币图片索引数组
     */
    public void setImageIndex(int[] imageIndex)
    {
        ImageIndex = imageIndex;
    }

    /**
     * 获取图像索引数组的当前长度
     *
     * @return 图像索引数组的当前长度
     */
    public int getImageIndexLength()
    {
        return ImageIndexLength;
    }

    /**
     * 设置图像索引数组的当前长度
     *
     * @param imageIndexLength 图像索引数组的当前长度
     */
    public void setImageIndexLength(int imageIndexLength)
    {
        ImageIndexLength = imageIndexLength;
    }

    /**
     * 初始化金币的图像
     * 根据当前图像索引从指定路径读取金币图片资源
     */
    public void initImage()
    {
        try
        {
            // 根据当前索引获取金币图像，图像文件位于image目录下
            goldImg = ImageIO.read(new File("image/" + ImageIndex[ImageIndexLength] + ".png"));
        }
        catch (IOException e)
        {
            // 捕获IO异常并打印堆栈信息
            e.printStackTrace();
        }
    }

    /**
     * 构造方法，用于初始化金币的属性并加载图像
     */
    public Gold()
    {
        // 增加图像索引数组的当前长度
        ImageIndexLength++;
        // 随机生成一个介于21-26之间的图像索引（对应不同样式的金币图片）
        ImageIndex[ImageIndexLength] = new Random().nextInt(6) + 21;
        // 随机生成金币的水平位置（在游戏窗口宽度范围内）
        x = new Random().nextInt(GameView.WIDTH);
        // 随机生成金币的垂直位置（在游戏窗口高度范围内，向下偏移180像素）
        y = new Random().nextInt(GameView.HEIGHT) - 180;
        // 初始化金币的图像
        initImage();
    }

    /**
     * 绘制金币的方法
     * 使用指定的Graphics对象在屏幕上绘制金币
     *
     * @param g Graphics对象，用于绘制金币
     */
    public void goldPaint(Graphics g)
    {
        // 在当前位置绘制金币图片，使用当前的宽度和高度
        g.drawImage(goldImg, x, y, width, height, null);
    }

    /**
     * 更新金币位置的方法
     * 根据金币的速度更新金币的水平位置
     */
    public void step()
    {
        // 金币向左移动，x坐标减少speed值
        setX(getX() - speed);
    }
}