package com.qf.pojo;

import com.qf.run.GameView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

/**
*@Author: miaokaidi
*@CreateTime: 2025-09-04 21:57:13
*@Description: 炸弹实体
 *              步骤：1.声明属性
 *                   2.set/get方法
 *                   3.绘制炸弹图像
 *                   4.构造方法初始化属性和图像
 */


public class Bomb implements Serializable {
    // 定义炸弹的x坐标
    int x;
    // 定义炸弹的y坐标
    int y;
    // 定义炸弹的宽度
    int width;
    // 定义炸弹的高度
    int height;
    // 定义炸弹的图片，使用transient关键字表示该变量不会序列化
    transient Image boomImage;
    // 定义炸弹的移动速度
    int speed;
    // 定义炸弹的生成时间
    int bombTime;

    // 初始化炸弹图片
    public void initImage() {
        try {
            // 从指定文件路径加载炸弹图片
            boomImage = ImageIO.read(new File("image/daodan.png"));
        } catch (IOException e) {
            e.printStackTrace(); // 捕获异常并打印堆栈跟踪
        }
    }

    // 炸弹的构造方法，初始化炸弹的属性
    public Bomb(int level) {
        // 设置炸弹的x坐标为游戏界面的宽度
        x = GameView.WIDTH;
        // 随机生成y坐标，确保炸弹不超出游戏界面的范围（减去180像素的高度）
        y = new Random().nextInt(GameView.HEIGHT) - 180;
        // 设置炸弹的宽度为25像素
        width = 50+level;
        // 设置炸弹的高度为27像素
        height = 35+level;
        // 设置炸弹的移动速度为3
        speed = 2;
        // 设置炸弹的爆炸生成为130
        bombTime = 200;
        // 调用initImage()方法，初始化炸弹的图片
        initImage();
    }

    // 绘制炸弹的方法，传入Graphics对象g
    public void bombRepaint(Graphics g) {
        // 使用传入的Graphics对象绘制炸弹的图片，坐标为x, y，宽度和高度为width, height
        g.drawImage(boomImage, x, y, width, height, null);
    }

    // 控制炸弹移动的步进方法
    public void step() {
        // 每调用一次方法，x坐标减去速度，使炸弹向左移动
        x -= speed;
    }







    //set/get方法
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Image getBoomImage() {
        return boomImage;
    }

    public void setBoomImage(Image boomImage) {
        this.boomImage = boomImage;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getBombTime() {
        return bombTime;
    }

    public void setBombTime(int bombTime) {
        this.bombTime = bombTime;
    }
}
