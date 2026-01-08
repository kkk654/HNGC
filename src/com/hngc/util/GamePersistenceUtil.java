package com.hngc.util;


import com.hngc.pojo.ArchiveGameData;
import com.hngc.pojo.Bomb;
import com.hngc.pojo.Gold;
import com.hngc.pojo.Person;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * 游戏数据持久化工具类
 * 用于实现游戏数据的保存和读取功能
 */
public class GamePersistenceUtil
{

    /**
     * 将游戏数据保存到文件中
     *
     * @param person   游戏角色对象
     * @param goldList 金币对象列表
     * @param bombList 炸弹对象列表
     */
    public static void save(Person person, List<Gold> goldList, List<Bomb> bombList)
    {
        // 文件输出流，用于将数据写入文件
        FileOutputStream fileOutputStream = null;
        // 对象输出流，用于序列化游戏数据对象
        ObjectOutputStream objectOutputStream = null;
        try
        {
            // 创建文件输出流，指定存档文件名为"keep_file.bin"
            fileOutputStream = new FileOutputStream("keep_file.bin");
            // 创建对象输出流
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            // 创建游戏数据存档对象
            ArchiveGameData gameData = new ArchiveGameData();
            // 设置存档中的角色对象
            gameData.setPerson(person);
            // 设置存档中的金币对象列表
            gameData.setGoldList(goldList);
            // 设置存档中的炸弹对象列表
            gameData.setBombList(bombList);
            // 将游戏数据对象序列化并写入到文件中
            objectOutputStream.writeObject(gameData);
            // 控制台打印存档成功消息
            System.out.println("存档成功！");
        }
        catch (Exception e)
        {
            // 捕获并打印异常
            e.printStackTrace();
        }
        finally
        {
            try
            {
                // 判断对象输出流是否为空
                if (objectOutputStream != null)
                {
                    // 关闭对象输出流
                    objectOutputStream.close();
                }
            }
            catch (Exception e2)
            {
                // 捕获并打印关闭流时的异常
                e2.printStackTrace();
            }
        }
    }

    /**
     * 从文件中读取游戏数据
     *
     * @return 存档的游戏数据对象
     * @throws Throwable 可能抛出的异常
     */
    public static ArchiveGameData getGameData() throws Throwable
    {
        // 文件输入流，用于从文件中读取数据
        FileInputStream fileInputStream = null;
        // 对象输入流，用于反序列化游戏数据对象
        ObjectInputStream objectInputStream = null;
        // 声明游戏数据对象
        ArchiveGameData gameData = null;
        try
        {
            // 创建文件输入流，指定存档文件名为"keep_file.bin"
            fileInputStream = new FileInputStream("keep_file.bin");
            // 创建对象输入流
            objectInputStream = new ObjectInputStream(fileInputStream);
            // 从文件中读取并反序列化游戏数据对象
            gameData = (ArchiveGameData) objectInputStream.readObject();

            // 重点：初始化角色对象，恢复角色的初始状态
            gameData.getPerson().init();

            // 初始化金币对象列表
            // 增强for循环遍历金币对象列表
            for (Gold gold : gameData.getGoldList())
            {
                // 初始化每个金币的图片资源
                gold.initImage();
            }
            // 初始化炸弹对象列表
            for (Bomb bomb : gameData.getBombList())
            {
                // 初始化每个炸弹的图片资源
                bomb.initImage();
            }
        }
        finally
        {
            try
            {
                // 判断对象输入流是否为空
                if (objectInputStream != null)
                {
                    // 关闭对象输入流
                    objectInputStream.close();
                }
            }
            catch (Exception e)
            {
                // 捕获并打印关闭流时的异常
                e.printStackTrace();
            }
        }

        // 返回读取到的游戏数据对象
        return gameData;
    }
}