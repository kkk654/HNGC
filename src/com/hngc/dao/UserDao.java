package com.hngc.dao;

import com.hngc.pojo.User;

public interface UserDao
{
    /**
     * 用户登录方法
     *
     * @param email    用户邮箱
     * @param password 用户密码
     * @return 登录成功返回用户对象，登录失败返回null
     */
    public abstract User login(String email, String password);
}