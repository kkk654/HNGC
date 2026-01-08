package com.hngc.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 游戏存档数据类
 * 用于序列化和存储游戏中的角色、金币和炸弹信息
 */
public class ArchiveGameData implements Serializable
{
    /**
     * 游戏角色对象
     */
    private Person person;

    /**
     * 金币列表集合
     */
    private List<Gold> goldList;

    /**
     * 炸弹列表集合
     */
    private List<Bomb> BombList;

    /**
     * 获取游戏角色对象
     *
     * @return 角色对象
     */
    public Person getPerson()
    {
        return person;
    }

    /**
     * 设置游戏角色对象
     *
     * @param person 角色对象
     */
    public void setPerson(Person person)
    {
        this.person = person;
    }

    /**
     * 获取金币列表
     *
     * @return 金币列表集合
     */
    public List<Gold> getGoldList()
    {
        return goldList;
    }

    /**
     * 设置金币列表
     *
     * @param goldList 金币列表集合
     */
    public void setGoldList(List<Gold> goldList)
    {
        this.goldList = goldList;
    }

    /**
     * 获取炸弹列表
     *
     * @return 炸弹列表集合
     */
    public List<Bomb> getBombList()
    {
        return BombList;
    }

    /**
     * 设置炸弹列表
     *
     * @param bombList 炸弹列表集合
     */
    public void setBombList(List<Bomb> bombList)
    {
        BombList = bombList;
    }
}