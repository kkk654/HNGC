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

public class GamePersistenceUtil {


    //将游戏数据保存到文件中
    public static void save(Person person, List<Gold> goldList , List<Bomb> bombList){
        //文件的输出流
        FileOutputStream fileOutputStream = null;
        //定义对象的输出流
        ObjectOutputStream objectOutputStream = null;
        try {
            //创建文件的输出流,指定文档的文件名
            fileOutputStream = new FileOutputStream("keep_file.bin");
            //创建对象的输出流
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            //创建游戏数据对象,设置其属性
            ArchiveGameData gameData = new ArchiveGameData();
            //设置存档中的角色对象
            gameData.setPerson(person);
            //设置存档中的金币对象列表
            gameData.setGoldList(goldList);
            //设置存档中的炸弹对象列表
            gameData.setBombList(bombList);
            //将游戏数据对象写入到文件中
            objectOutputStream.writeObject(gameData);
            //控制台打印
            System.out.println("存档成功！");
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                //判断对象输出流是否为空
                if (objectOutputStream != null){
                    //关闭对象输出流
                    objectOutputStream.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
    }

    //从文件中读取游戏数据
    public static ArchiveGameData getGameData ()  throws Throwable{
        //文件的输入流
        FileInputStream fileInputStream = null;
        //对象的输入流
        ObjectInputStream objectInputStream = null;
        //声明游戏数据对象
        ArchiveGameData gameData = null;
        try {
            //创建文件的输入流,指定文档的文件名
            fileInputStream = new FileInputStream("keep_file.bin");
            //创建对象的输入流
            objectInputStream = new ObjectInputStream(fileInputStream);
            //从文件中读取游戏数据对象
            gameData = (ArchiveGameData) objectInputStream.readObject();
            //初始化角色对象
            gameData.getPerson().init();
            //初始化金币对象列表
            //增强for循环遍历金币对象列表
            for (Gold gold :gameData.getGoldList()){
                gold.initImage();
            }
            //初始化炸弹对象列表
            for (Bomb bomb : gameData.getBombList()){
                bomb.initImage();
            }
        }finally {
            try {
                //判断对象输入流是否为空
                if (objectInputStream != null){
                    //尝试关闭对象输入流
                    objectInputStream.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        //返回游戏数据对象
        return gameData;

    }

}
