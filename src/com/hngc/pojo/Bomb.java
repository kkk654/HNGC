package com.hngc.pojo;

import com.hngc.run.GameView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

/**
 * 炸弹类，实现了Serializable接口以支持序列化
 * 用于表示游戏中的炸弹对象，包含炸弹的位置、大小、图片、速度等属性
 * 以及炸弹的绘制、移动等行为方法
 */
public class Bomb implements Serializable
{
    /**
     * 炸弹的x坐标
     */
    private int x;

    /**
     * 炸弹的y坐标
     */
    private int y;

    /**
     * 炸弹图片的宽度
     */
    private int width;

    /**
     * 炸弹图片的高度
     */
    private int height;

    /**
     * 炸弹图片，使用transient关键字修饰，表示序列化时不保存此属性
     */
    private transient Image boomImage;

    /**
     * 炸弹的移动速度
     */
    private int speed;

    /**
     * 炸弹的生成时间
     */
    private int bombTime;

    /**
     * 获取炸弹的x坐标
     *
     * @return 炸弹的x坐标
     */
    public int getX()
    {
        return x;
    }

    /**
     * 设置炸弹的x坐标
     *
     * @param x 炸弹的x坐标
     */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
     * 获取炸弹的y坐标
     *
     * @return 炸弹的y坐标
     */
    public int getY()
    {
        return y;
    }

    /**
     * 设置炸弹的y坐标
     *
     * @param y 炸弹的y坐标
     */
    public void setY(int y)
    {
        this.y = y;
    }

    /**
     * 获取炸弹图片的宽度
     *
     * @return 炸弹图片的宽度
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * 设置炸弹图片的宽度
     *
     * @param width 炸弹图片的宽度
     */
    public void setWidth(int width)
    {
        this.width = width;
    }

    /**
     * 获取炸弹图片的高度
     *
     * @return 炸弹图片的高度
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * 设置炸弹图片的高度
     *
     * @param height 炸弹图片的高度
     */
    public void setHeight(int height)
    {
        this.height = height;
    }

    /**
     * 获取炸弹图片
     *
     * @return 炸弹图片
     */
    public Image getBoomImage()
    {
        return boomImage;
    }

    /**
     * 设置炸弹图片
     *
     * @param boomImage 炸弹图片
     */
    public void setBoomImage(Image boomImage)
    {
        this.boomImage = boomImage;
    }

    /**
     * 获取炸弹的移动速度
     *
     * @return 炸弹的移动速度
     */
    public int getSpeed()
    {
        return speed;
    }

    /**
     * 设置炸弹的移动速度
     *
     * @param speed 炸弹的移动速度
     */
    public void setSpeed(int speed)
    {
        this.speed = speed;
    }

    /**
     * 获取炸弹的生成时间
     *
     * @return 炸弹的生成时间
     */
    public int getBombTime()
    {
        return bombTime;
    }

    /**
     * 设置炸弹的生成时间
     *
     * @param bombTime 炸弹的生成时间
     */
    public void setBombTime(int bombTime)
    {
        this.bombTime = bombTime;
    }

    /**
     * 初始化炸弹图片
     * 从指定路径读取炸弹图片资源
     */
    public void initImage()
    {
        try
        {
            // 从image目录下读取daodan.png图片作为炸弹图片
            boomImage = ImageIO.read(new File("image/daodan.png"));
        }
        catch (IOException e)
        {
            // 捕获IO异常并打印堆栈信息
            e.printStackTrace();
        }
    }

    /**
     * 构造方法，根据游戏难度等级初始化炸弹的属性
     *
     * @param level 游戏难度等级，影响炸弹的大小
     */
    public Bomb(int level)
    {
        // 设置炸弹的初始x坐标为游戏窗口宽度（从右侧进入）
        x = GameView.WIDTH;
        // 设置炸弹的初始y坐标为随机值（在游戏窗口高度范围内）
        y = new Random().nextInt(GameView.HEIGHT) - 180;
        // 根据难度等级设置炸弹的宽度
        width = 50 + level;
        // 根据难度等级设置炸弹的高度
        height = 35 + level;
        // 设置炸弹的移动速度
        speed = 2;
        // 设置炸弹的生成时间
        bombTime = 200;
        // 初始化炸弹图片
        initImage();
    }

    /**
     * 绘制炸弹的方法
     * 使用指定的Graphics对象在屏幕上绘制炸弹
     *
     * @param g Graphics对象，用于绘制炸弹
     */
    public void bombRepaint(Graphics g)
    {
        // 在指定位置绘制炸弹图片，使用当前的宽度和高度
        g.drawImage(boomImage, x, y, width, height, null);
    }

    /**
     * 控制炸弹移动的方法
     * 根据炸弹的速度更新炸弹的位置
     */
    public void step()
    {
        // 炸弹向左移动，x坐标减少speed值
        x -= speed;
    }
}