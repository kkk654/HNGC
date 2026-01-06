package com.hngc.pojo;

import com.hngc.run.GameView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

public class Gold implements Serializable
{

    //金币的x轴坐标
    private int x;
    //金币的y轴坐标
    private int y;
    //金币图像的宽度
    private int width = 40;
    //金币图像的高度
    private int height = 40;
    //金币的图像,使用transient关键字修饰属性，不参与序列化
    private transient Image goldImg = null;
    //金币的移动速度
    private int speed = 2;
    //生成新的金币的时间间隔
    private int newGoldTime = 10;
    //收集到的金币价值
    private int goldPrice = 10;
    //数组，用来存储金币图片
    private int[] ImageIndex = new int[100];
    //图像索引数组的当前长度
    private int ImageIndexLength;

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

    public Image getGoldImg()
    {
        return goldImg;
    }

    public void setGoldImg(Image goldImg)
    {
        this.goldImg = goldImg;
    }

    public int getSpeed()
    {
        return speed;
    }

    public void setSpeed(int speed)
    {
        this.speed = speed;
    }

    public int getNewGoldTime()
    {
        return newGoldTime;
    }

    public void setNewGoldTime(int newGoldTime)
    {
        this.newGoldTime = newGoldTime;
    }

    public int getGoldPrice()
    {
        return goldPrice;
    }

    public void setGoldPrice(int goldPrice)
    {
        this.goldPrice = goldPrice;
    }

    public int[] getImageIndex()
    {
        return ImageIndex;
    }

    public void setImageIndex(int[] imageIndex)
    {
        ImageIndex = imageIndex;
    }

    public int getImageIndexLength()
    {
        return ImageIndexLength;
    }

    public void setImageIndexLength(int imageIndexLength)
    {
        ImageIndexLength = imageIndexLength;
    }


    //初始化金币的图像，根据当前索引
    public void initImage()
    {
        //根据当前索引获取金币图像
        try
        {
            goldImg = ImageIO.read(new File("image/" + ImageIndex[ImageIndexLength] + ".png"));
        }
        catch (IOException e)
        {
            //如果说出现异常，则捕获异常并打印异常信息
            e.printStackTrace();
        }
    }

    //用来初始化属性并加载图像
    public Gold()
    {
        //增加索引的长度 ，获取新的图像索引
        ImageIndexLength++;
        //随机生成一个介于21-26之间的图像索引
        ImageIndex[ImageIndexLength] = new Random().nextInt(6) + 21;
        //随机生成金币的水平位置
        x = new Random().nextInt(GameView.WIDTH);
        //随机生成金币的垂直位置
        y = new Random().nextInt(GameView.HEIGHT) - 180;
        //初始化金币的图像
        initImage();
    }

    //绘制金币
    public void goldPaint(Graphics g)
    {
        //在当前位置绘制金币的图像
        g.drawImage(goldImg, x, y, width, height, null);

    }


    //更新金币的位置
    public void step()
    {
        setX(getX() - speed);
    }
}
