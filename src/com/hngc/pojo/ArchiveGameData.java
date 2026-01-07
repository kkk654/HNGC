package com.hngc.pojo;

import java.io.Serializable;
import java.util.List;

public class ArchiveGameData implements Serializable
{
    //定义用于存储角色对象
    private Person person;
    //定义存储金币列表的集合
    private List<Gold> goldList;
    //定义存储炸弹列表的集合
    private List<Bomb> BombList;

    public Person getPerson()
    {
        return person;
    }

    public void setPerson(Person person)
    {
        this.person = person;
    }

    public List<Gold> getGoldList()
    {
        return goldList;
    }

    public void setGoldList(List<Gold> goldList)
    {
        this.goldList = goldList;
    }

    public List<Bomb> getBombList()
    {
        return BombList;
    }

    public void setBombList(List<Bomb> bombList)
    {
        BombList = bombList;
    }
}
