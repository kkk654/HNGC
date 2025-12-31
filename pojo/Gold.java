package com.qf.pojo;

import com.qf.run.GameView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

/**
 * @Author: miaokaidi
 * @CreateTime: 2025-09-02 17:18:36
 * @desc 金币实体
 *              步骤：
 *                  (1). 定义属性
 *                  (2). set/get方法
 *                  (3). 构造方法中进行金币属性初始化并加载图像。
 *                  (4). 金币绘制方法
 *                  (5). 金币更新移动方法
 */
public class Gold implements Serializable {
    private int x; // 金币的X坐标
    private int y; // 金币的Y坐标
    private int width = 40; // 金币图像的宽度
    private int height = 40; // 金币图像的高度
    private transient Image goldImg = null; // 金币的图像，标记为transient，不参与序列化
    private int speed = 2; // 金币移动的速度
    private int newGoldTime = 10; // 生成新金币的时间间隔
    private int goldPrice = 10; // 收集到金币的价值
    private int[] ImageIndex = new int[100]; // 用于存储图像索引的数组，增加金币的多样性
    private int ImageIndexLength; // 图像索引数组的当前长度

    /**
     * 初始化金币的图像，根据当前索引。
     */
    public void initImage() {
        // 根据当前索引加载金币图像
        try {
            goldImg = ImageIO.read(new File("image/" + ImageIndex[ImageIndexLength] + ".png"));
        } catch (IOException e) {
            e.printStackTrace(); // 如果发生错误，打印堆栈追踪信息
        }
    }

    /**
     * Gold 类的构造函数，初始化属性并加载图像。
     */
    public Gold() {
        ImageIndexLength++; // 增加图像索引长度，以获取新的图像索引
        ImageIndex[ImageIndexLength] = new Random().nextInt(6) + 21; // 随机生成一个介于21到26之间的图像索引
        x = new Random().nextInt(GameView.WIDTH); // 随机设置金币的水平位置，位于游戏视图内
        y = new Random().nextInt(GameView.HEIGHT) - 180; // 随机设置金币的垂直位置，位于游戏视图内，并向上调整180

        // 初始化金币图像
//            goldImg = ImageIO.read(new File("image/" + ImageIndex[ImageIndexLength] + ".png"));
        initImage();

    }


    /**
     * 在游戏面板上绘制金币。
     * @param graphics 用于绘图的图形上下文。
     */
    public void goldPaint(Graphics graphics) {
        // 在当前位置绘制金币图像，指定宽度和高度
        graphics.drawImage(goldImg, x, y, width, height, null);
    }


    /**
     * 根据速度更新金币的位置。
     */
    public void step() {
        setX(getX() - speed);
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

    public Image getGoldImg() {
        return goldImg;
    }

    public void setGoldImg(Image goldImg) {
        this.goldImg = goldImg;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getNewGoldTime() {
        return newGoldTime;
    }

    public void setNewGoldTime(int newGoldTime) {
        this.newGoldTime = newGoldTime;
    }

    public int getGoldPrice() {
        return goldPrice;
    }

    public void setGoldPrice(int goldPrice) {
        this.goldPrice = goldPrice;
    }

    public int[] getImageIndex() {
        return ImageIndex;
    }

    public void setImageIndex(int[] imageIndex) {
        ImageIndex = imageIndex;
    }

    public int getImageIndexLength() {
        return ImageIndexLength;
    }

    public void setImageIndexLength(int imageIndexLength) {
        ImageIndexLength = imageIndexLength;
    }
}
