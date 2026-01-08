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

/**
 * 游戏角色类，实现了Serializable接口以支持序列化
 * 用于表示游戏中的玩家角色，包含角色的位置、速度、图像、生命值等属性
 * 以及角色的绘制、移动、动画更新等行为方法
 */
public class Person implements Serializable
{

    /**
     * 人物角色移动速度，默认值为16
     */
    private int speed = 16;

    /**
     * 角色的水平坐标x，默认初始值为20
     */
    private int x = 20;

    /**
     * 角色的垂直坐标y，默认初始值为325
     */
    private int y = 325;

    /**
     * 角色图像的宽度，默认值为110
     */
    private int width = 110;

    /**
     * 角色图像的高度，默认值为110
     */
    private int height = 110;

    /**
     * 当前显示的角色图像
     * 使用transient关键字修饰，不参与序列化过程
     */
    private transient Image personImage;

    /**
     * 存储角色动画帧的缓冲图像数组
     * 使用transient关键字修饰，不参与序列化过程
     */
    private transient BufferedImage[] personBufferedImage = {};

    /**
     * 角色当前得分
     */
    private int score;

    /**
     * 角色死亡状态标志
     */
    private boolean die;

    /**
     * 角色最高得分记录
     */
    private int maxScore;

    /**
     * 角色当前生命值，默认值为100
     */
    private int health = 100;

    /**
     * 当前动画图片的帧索引，用于控制角色动画播放
     */
    int personIndex = 0;

    /**
     * 获取角色移动速度
     *
     * @return 角色移动速度
     */
    public int getSpeed()
    {
        return speed;
    }

    /**
     * 设置角色移动速度
     *
     * @param speed 角色移动速度（确保不小于1，避免除以零错误）
     */
    public void setSpeed(int speed)
    {
        // 确保speed不会被设置为0或负数，避免除以零错误
        this.speed = Math.max(1, speed);
    }

    /**
     * 获取角色的水平坐标x
     *
     * @return 角色的水平坐标x
     */
    public int getX()
    {
        return x;
    }

    /**
     * 设置角色的水平坐标x
     *
     * @param x 角色的水平坐标x
     */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
     * 获取角色的垂直坐标y
     *
     * @return 角色的垂直坐标y
     */
    public int getY()
    {
        return y;
    }

    /**
     * 设置角色的垂直坐标y
     *
     * @param y 角色的垂直坐标y
     */
    public void setY(int y)
    {
        this.y = y;
    }

    /**
     * 获取角色图像的宽度
     *
     * @return 角色图像的宽度
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * 设置角色图像的宽度
     *
     * @param width 角色图像的宽度
     */
    public void setWidth(int width)
    {
        this.width = width;
    }

    /**
     * 获取角色图像的高度
     *
     * @return 角色图像的高度
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * 设置角色图像的高度
     *
     * @param height 角色图像的高度
     */
    public void setHeight(int height)
    {
        this.height = height;
    }

    /**
     * 获取当前显示的角色图像
     *
     * @return 当前显示的角色图像
     */
    public Image getPersonImage()
    {
        return personImage;
    }

    /**
     * 设置当前显示的角色图像
     *
     * @param personImage 当前显示的角色图像
     */
    public void setPersonImage(Image personImage)
    {
        this.personImage = personImage;
    }

    /**
     * 获取存储角色动画帧的缓冲图像数组
     *
     * @return 存储角色动画帧的缓冲图像数组
     */
    public BufferedImage[] getPersonBufferedImage()
    {
        return personBufferedImage;
    }

    /**
     * 设置存储角色动画帧的缓冲图像数组
     *
     * @param personBufferedImage 存储角色动画帧的缓冲图像数组
     */
    public void setPersonBufferedImage(BufferedImage[] personBufferedImage)
    {
        this.personBufferedImage = personBufferedImage;
    }

    /**
     * 获取角色当前得分
     *
     * @return 角色当前得分
     */
    public int getScore()
    {
        return score;
    }

    /**
     * 设置角色当前得分
     *
     * @param score 角色当前得分
     */
    public void setScore(int score)
    {
        this.score = score;
    }

    /**
     * 获取角色死亡状态
     *
     * @return 角色死亡状态（true表示死亡，false表示存活）
     */
    public boolean isDie()
    {
        return die;
    }

    /**
     * 设置角色死亡状态
     *
     * @param die 角色死亡状态（true表示死亡，false表示存活）
     */
    public void setDie(boolean die)
    {
        this.die = die;
    }

    /**
     * 获取角色最高得分记录
     *
     * @return 角色最高得分记录
     */
    public int getMaxScore()
    {
        return maxScore;
    }

    /**
     * 设置角色最高得分记录
     *
     * @param maxScore 角色最高得分记录
     */
    public void setMaxScore(int maxScore)
    {
        this.maxScore = maxScore;
    }

    /**
     * 获取角色当前生命值
     *
     * @return 角色当前生命值（0-100之间）
     */
    public int getHealth()
    {
        return health;
    }

    /**
     * 设置角色当前生命值
     *
     * @param health 角色当前生命值
     */
    public void setHealth(int health)
    {
        this.health = health;
    }

    /**
     * 获取当前动画图片的帧索引
     *
     * @return 当前动画图片的帧索引
     */
    public int getPersonIndex()
    {
        return personIndex;
    }

    /**
     * 设置当前动画图片的帧索引
     *
     * @param personIndex 当前动画图片的帧索引
     */
    public void setPersonIndex(int personIndex)
    {
        this.personIndex = personIndex;
    }

    /**
     * 初始化角色动画帧
     * 从image目录下加载角色动画所需的图像资源
     */
    public void init()
    {
        // 尝试加载图像资源，并捕获可能的IOException异常
        try
        {
            // 读取并存储的图像文件到personBufferedImage数组中
            personBufferedImage = new BufferedImage[]{
                    // 读取"image/1.png"文件（角色动画帧1）
                    ImageIO.read(new File("image/1.png")),
                    // 读取"image/2.png"文件（角色动画帧2）
                    ImageIO.read(new File("image/2.png")),
                    // 读取"image/3.png"文件（角色动画帧3）
                    ImageIO.read(new File("image/3.png")),
                    // 读取"image/4.png"文件（角色动画帧4）
                    ImageIO.read(new File("image/4.png")),
                    // 读取"image/5.png"文件（角色动画帧5）
                    ImageIO.read(new File("image/5.png")),
                    // 读取"image/6.png"文件（角色动画帧6）
                    ImageIO.read(new File("image/6.png")),
                    // 读取"image/7.png"文件（角色动画帧7）
                    ImageIO.read(new File("image/7.png")),
                    // 读取"image/8.png"文件（角色动画帧8）
                    ImageIO.read(new File("image/8.png")),
                    // 读取"image/9.png"文件（角色动画帧9）
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

    /**
     * 构造方法，用于初始化角色对象并加载动画帧
     */
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

    /**
     * 绘制角色的方法
     * 使用指定的Graphics对象在屏幕上绘制角色和血条
     *
     * @param g Graphics对象，用于绘制角色
     */
    public void personPaint(Graphics g)
    {
        //绘制角色图像
        g.drawImage(personImage, x, y, width, height, null);
        //绘制角色血条
        drawHealthBar(g);
    }

    /**
     * 角色移动和动画更新方法
     * 更新角色的动画帧，实现角色的行走动画效果
     */
    public void step()
    {
        // 重置帧索引，避免数值过大
        if (personIndex == 1000)
        {
            personIndex = 0;
        }
        // 确保speed不为0，避免除以零错误
        int safeSpeed = Math.max(1, speed);
        // 确保personBufferedImage数组不为空，避免模运算除以零错误
        if (personBufferedImage.length > 0)
        {
            // 根据当前帧索引和速度计算当前应该显示的动画帧
            personImage = personBufferedImage[personIndex++ / safeSpeed % personBufferedImage.length];
        }
    }

    /**
     * 绘制角色血条的方法
     * 使用指定的Graphics对象在角色上方绘制血条
     *
     * @param g Graphics对象，用于绘制血条
     */
    public void drawHealthBar(Graphics g)
    {
        //血条的宽度
        int barWidth = 100;
        //血条的高度
        int barHeight = 10;
        //血条的x坐标（与角色x坐标相同）
        int healthBarX = x;
        //血条的y坐标（在角色上方15像素处）
        int healthBarY = y - 15;

        //绘制血条背景（红色）
        g.setColor(Color.RED);
        g.fillRect(healthBarX, healthBarY, barWidth, barHeight);

        //绘制当前血量（绿色）
        g.setColor(Color.GREEN);
        //根据当前生命值计算血量条的宽度
        g.fillRect(healthBarX, healthBarY, (int) (barWidth * (health / 100.0)), barHeight);
    }

    /**
     * 角色向上移动的方法
     * 控制角色向上移动，确保不超出窗口顶部
     */
    public void moveUp()
    {
        //检查角色是否在窗口顶部以上
        if (y > 0)
        {
            //设置角色的y坐标，确保不小于0
            setY(Math.max(0, y - speed));
        }
    }

    /**
     * 角色向下移动的方法
     * 控制角色向下移动，确保不超出窗口底部
     */
    public void moveDown()
    {
        //设置角色的y坐标，确保不超出窗口底部
        setY(Math.min(HEIGHT - height, y + speed));
    }

    /**
     * 角色向左移动的方法
     * 控制角色向左移动，确保不超出窗口左侧
     */
    public void moveLeft()
    {
        //检查角色是否在窗口左侧边界内
        if (x > 0)
        {
            //设置角色的x坐标，确保不小于0
            setX(Math.max(0, x - speed));
        }
    }

    /**
     * 角色向右移动的方法
     * 控制角色向右移动，确保不超出窗口右侧
     */
    public void moveRight()
    {
        //设置角色的x坐标，确保不超出窗口右侧
        setX(Math.min(WIDTH - width, x + speed));
    }

    /**
     * 角色自由落体方法
     * 控制角色从空中下落，直到达到地面高度（y=325）
     */
    public void low()
    {
        //检查角色是否高于地面高度
        if (getY() < 325)
        {
            //增加3的下降速度，实现自由落体效果
            setY(getY() + 3);
        }
    }

    /**
     * 角色扣血方法
     * 减少角色的生命值，当生命值小于等于0时，设置角色死亡状态
     *
     * @param damage 扣血数值
     */
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