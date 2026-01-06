package com.hngc.pojo;

import com.hngc.run.GameView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

public class Bomb implements Serializable
{
    //定义炸弹的x坐标
    private int x;
    //定义炸弹的y坐标
    private int y;
    //定义炸弹图片的宽度
    private int width;
    //定义炸弹图片的高度
    private int height;
    //定义炸弹图片，使用transient关键字修饰该属性
    private transient Image boomImage;
    //定义炸弹的移动速度
    private int speed;
    //定义炸弹的生成时间
    private int bombTime;

    //get,set方法


    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public Image getBoomImage()
    {
        return boomImage;
    }

    public void setBoomImage(Image boomImage)
    {
        this.boomImage = boomImage;
    }

    public int getSpeed()
    {
        return speed;
    }

    public void setSpeed(int speed)
    {
        this.speed = speed;
    }

    public int getBombTime()
    {
        return bombTime;
    }

    public void setBombTime(int bombTime)
    {
        this.bombTime = bombTime;
    }


    //初始化炸弹图片
    public void initImage()
    {
        try
        {
            boomImage = ImageIO.read(new File("image/daodan.png"));
        }
        catch (IOException e)
        {
            //捕获异常并打印信息
            e.printStackTrace();
        }
    }

    //构造方法，用来初始化炸弹的属性
    public Bomb(int level)
    {
        //设置炸弹的x坐标
        x = GameView.WIDTH;
        //设置炸弹的y坐标
        y = new Random().nextInt(GameView.HEIGHT) - 180;
        //设置炸弹的宽度
        width = 50 + level;
        //设置炸弹高度
        height = 35 + level;
        //设置炸弹的移动速度
        speed = 2;
        //设置炸弹的生成时间
        bombTime = 200;
        //初始化炸弹图片
        initImage();
    }

    //绘制炸弹的方法
    public void bombRepaint(Graphics g)
    {
        //绘制炸弹图片
        g.drawImage(boomImage, x, y, width, height, null);
    }

    //控制炸弹移动方法
    public void step()
    {
        //控制炸弹的x坐标
        x -= speed;
    }


}
