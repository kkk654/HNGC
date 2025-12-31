package com.qf.pojo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import static com.qf.run.GameView.HEIGHT;
import static com.qf.run.GameView.WIDTH;

/**
 * 人物角色实体
 *      1.声明各种属性
 *      2.生成set/get方法
 *      3.设置角色图片
 *      4.绘制角色
 *      5.让角色动起来
 *      6.绘制血条
 *      7.设置角色自动掉落
 *      8.设置角色移动
 */
public class Person implements Serializable {

    //人物角色速度
    private  int speed=16;
    //水平变量x坐标
    private int  x= 20;
    //垂直坐标变量
    private  int y=325;
    //图像宽度变量
    private int width=110;
    //图像高度
    private int height=110;
    //显示的图像对象
    private transient Image personImage;
    //声明BufferedImage数组，用来存储多帧图像数据，初始值为空
//    private BufferedImage personBufferedImage[]=new BufferedImage[]{};
    private  transient BufferedImage[] personBufferedImage={};
    //得分变量
    private  int score;
    //角色死亡变量
    private  boolean die;
    //最高得分变量
    private int maxScore;
    //添加血条属性
    private  int health=100;
    //追踪显示当前动画帧的索引
    int peronIndex=0;


    /**
     * 构造方法
     */
    public Person() {
        try {
            personBufferedImage= new BufferedImage[]{
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * 绘制角色
     * @return
     */
    public void personPaint(Graphics g){
        //绘制人物角色
        g.drawImage(personImage,x,y,width,height,null);

        //绘制血条
        drawHealthBar(g);
    }


    /**
     * 让角色动起来
     */

    //控制动画的步进（即动画帧切换）
    public void step(){
        //避免整数溢出，达到一个延迟的效果
        if (peronIndex==1000){
            peronIndex=0;
        }
        //通过speed变量控制动画的播放速度，speed的值越大吗，帧之间的切换间隔时间约长，动画播放越慢
        //取结果对数组长度取模，确保索引值不会超出范围，实现在数组中的内循环
        personImage = personBufferedImage[peronIndex++ / speed % personBufferedImage.length];

    }




    /**
     * 绘制血条
     * @return
     */
    public void drawHealthBar(Graphics g){
        //血条宽度
        int  barWidth=100;
        //血条高度
        int  barHeight=10;
        //血条X坐标
        int  healthBarX=x;
        //血条Y坐标
        int  healthBarY=y-15;

        //绘制血条背景
        g.setColor(Color.RED);
        g.fillRect(healthBarX,healthBarY,barWidth,barHeight);

        //计算当前血量比例并绘制
        g.setColor(Color.GREEN);
        g.fillRect(healthBarX,healthBarY,(int) (barWidth*(health/100.0)),barHeight);

    }

    /**
     * 角色移动
     */

    // 向上移动
    public void moveUp() {
        if (y > 0) {
            setY(Math.max(0, y - speed)); // 保证角色不超出窗口顶部
        }
    }

    // 向下移动
    public void moveDown() {
        setY(Math.min(HEIGHT - height, y + speed)); // 保证角色不超出窗口底部
    }

    // 向左移动
    public void moveLeft() {
        if (x > 0) {
            setX(Math.max(0, x - speed)); // 保证角色不超出窗口左侧
        }
    }

    // 向右移动
    public void moveRight() {
        setX(Math.min(WIDTH - width, x + speed)); // 保证角色不超出窗口右侧
    }


    /**
     * 角色降落
     */
    // 角色降落
    public void low() {
        // 增加下降速度，设定每次下降 5 像素
        if (getY() < 310) {
            setY(getY() + 3); // 增加为 5，以加快下降速度
        }
    }


    /**
     * 人物扣血
     */
    public  void reduceHealth(int damage){

        this.health-=damage;

        if (this.health<=0){
            this.health=0;
            this.setDie(true);
        }
    }

    /**
     * 人物死亡方法
     * @return
     */
    public boolean isDie() {

        return die;
    }



    //set/get方法
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

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

    public Image getPersonImage() {
        return personImage;
    }

    public void setPersonImage(Image personImage) {
        this.personImage = personImage;
    }

    public BufferedImage[] getPersonBufferedImage() {
        return personBufferedImage;
    }

    public void setPersonBufferedImage(BufferedImage[] personBufferedImage) {
        this.personBufferedImage = personBufferedImage;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setDie(boolean die) {
        this.die = die;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getPeronIndex() {
        return peronIndex;
    }

    public void setPeronIndex(int peronIndex) {
        this.peronIndex = peronIndex;
    }
}
