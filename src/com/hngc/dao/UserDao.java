package com.hngc.dao;

import com.hngc.pojo.User;

public interface UserDao
{
    public abstract User login(String email, String password);
}
