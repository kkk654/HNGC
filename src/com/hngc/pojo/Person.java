package com.hngc.pojo;

import com.hngc.run.GameView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import static com.hngc.run.GameView.HEIGHT;
import static com.hngc.run.GameView.WIDTH;
//import static java.awt.image.ImageObserver.WIDTH;

public class Person implements Serializable
{

    //人物角色速度
    private int speed = 16;
    //水平变量x坐标
    private int x = 20;
    //垂直变量y坐标
    private int y = 325;
    //图像宽度
    private int width = 110;
    //图像高度
    private int height = 110;
    //显示的图像
    private transient Image personImage;
    //声明一个数组,用于存储图像数据的
    private transient BufferedImage[] personBufferedImage = {};
    //得分变量
    private int score;
    //角色死亡变量
    private boolean die;
    //最高得分变量
    private int maxScore;
    //血条属性
    private int health = 100;
    //当前动画图片的帧率
    int personIndex = 0;

    public int getSpeed()
    {
        return speed;
    }

    public void setSpeed(int speed)
    {
        // 确保speed不会被设置为0或负数，避免除以零错误
        this.speed = Math.max(1, speed);
    }

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

    public Image getPersonImage()
    {
        return personImage;
    }

    public void setPersonImage(Image personImage)
    {
        this.personImage = personImage;
    }

    public BufferedImage[] getPersonBufferedImage()
    {
        return personBufferedImage;
    }

    public void setPersonBufferedImage(BufferedImage[] personBufferedImage)
    {
        this.personBufferedImage = personBufferedImage;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public boolean isDie()
    {
        return die;
    }

    public void setDie(boolean die)
    {
        this.die = die;
    }

    public int getMaxScore()
    {
        return maxScore;
    }

    public void setMaxScore(int maxScore)
    {
        this.maxScore = maxScore;
    }

    public int getHealth()
    {
        return health;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }

    public int getPersonIndex()
    {
        return personIndex;
    }

    public void setPersonIndex(int personIndex)
    {
        this.personIndex = personIndex;
    }


    public void init()
    {
        // 尝试加载图像资源，并捕获可能的IOException异常
        try
        {
            // 读取并存储的图像文件到personBufferedImage数组中
            personBufferedImage = new BufferedImage[]{
                    // 读取"images/1.png"文件
                    ImageIO.read(new File("image/1.png")),
                    // 读取"images/2.png"文件
                    ImageIO.read(new File("image/2.png")),
                    // 读取"images/3.png"文件
                    ImageIO.read(new File("image/3.png")),
                    // 读取"images/4.png"文件
                    ImageIO.read(new File("image/4.png")),
                    // 读取"images/5.png"文件
                    ImageIO.read(new File("image/5.png")),
                    // 读取"images/6.png"文件
                    ImageIO.read(new File("image/6.png")),
                    // 读取"images/7.png"文件
                    ImageIO.read(new File("image/7.png")),
                    // 读取"images/8.png"文件
                    ImageIO.read(new File("image/8.png")),
                    // 读取"images/9.png"文件
                    ImageIO.read(new File("image/9.png"))
            };
            // 捕获并处理可能的IOException异常
        }
        catch (IOException e)
        {
            // 打印异常堆栈跟踪信息
            e.printStackTrace();
        }
    }

    public Person()
    {
        //尝试加载图像资源
        try
        {
            //读取存储的图像文件到personBufferedImage数组中
            personBufferedImage = new BufferedImage[]{
                    //读取image目录下存在的图片文件
                    ImageIO.read(new File("image/1.png")),
                    ImageIO.read(new File("image/2.png")),
                    ImageIO.read(new File("image/3.png")),
                    ImageIO.read(new File("image/4.png")),
                    ImageIO.read(new File("image/5.png")),
                    ImageIO.read(new File("image/6.png")),
                    ImageIO.read(new File("image/7.png")),
                    ImageIO.read(new File("image/8.png")),
                    ImageIO.read(new File("image/9.png"))
            };
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void personPaint(Graphics g)
    {
        //绘制任务角色
        g.drawImage(personImage, x, y, width, height, null);
        //绘制血条
        drawHealthBar(g);

    }

    //角色移动方法
    public void step()
    {
        if (personIndex == 1000)
        {
            personIndex = 0;
        }
        // 确保speed不为0，避免除以零错误
        int safeSpeed = Math.max(1, speed);
        // 确保personBufferedImage数组不为空，避免模运算除以零错误
        if (personBufferedImage.length > 0)
        {
            personImage = personBufferedImage[personIndex++ / safeSpeed % personBufferedImage.length];
        }
    }

    //绘制血条
    public void drawHealthBar(Graphics g)
    {
        //血条的宽度
        int barWidth = 100;
        //血条的高度
        int barHeight = 10;
        //血条的x坐标
        int healthBarX = x;
        //血条的y坐标
        int healthBarY = y - 15;
        //血条的颜色
        g.setColor(Color.RED);
        //在屏幕上绘制矩形
        g.fillRect(healthBarX, healthBarY, barWidth, barHeight);
        //计算当前血量比例,并绘制血量
        g.setColor(Color.GREEN);
        //计算当前血量比例
        g.fillRect(healthBarX, healthBarY, (int) (barWidth * (health / 100.0)), barHeight);
    }


    //角色移动方法
    //向上移动
    public void moveUp()
    {
        //检查角色在窗口顶部以上
        if (y > 0)
        {
            //设置我们角色的y坐标
            //Math.max(0,y-speed) 用于返回两个参数中最大的值
            setY(Math.max(0, y - speed));
        }
    }

    //向下移动
    public void moveDown()
    {
        //设置我们角色的y坐标
        setY(Math.min(HEIGHT - height, y + speed));
    }

    //向左移动
    public void moveLeft()
    {
        //检查角色在窗口顶部以上
        if (x > 0)
        {
            //设置我们角色的y坐标
            //Math.max(0,y-speed) 用于返回两个参数中最大的值
            setX(Math.max(0, x - speed));
        }
    }

    //向右移动
    public void moveRight()
    {
        //检查角色在窗口顶部以上
        setX(Math.min(WIDTH - width, x + speed));
    }

    //自由落体方法
    public void low()
    {
        //设置下降速度
        if (getY() < 325)
        {
            //增加3的下降速度
            setY(getY() + 3);
        }
    }

    //人物扣血方法
    public void reduceHealth(int damage)
    {
        //减少角色的生命值
        this.health -= damage;
        //如果生命值小于等于0,则角色死亡
        if (this.health <= 0)
        {
            this.health = 0;
            //设置角色的死亡状态为true
            this.setDie(true);
        }

    }

}
