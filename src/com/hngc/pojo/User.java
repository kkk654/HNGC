package com.hngc.pojo;

/**
 * 用户实体类
 * 用于表示系统中的用户信息，包含用户的基本属性
 */
public class User
{

    /**
     * 用户唯一标识符
     */
    private int id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 默认构造方法
     */
    public User()
    {
    }

    /**
     * 带参数的构造方法
     *
     * @param id       用户ID
     * @param username 用户名
     * @param email    用户邮箱
     * @param password 用户密码
     */
    public User(int id, String username, String email, String password)
    {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /**
     * 获取用户ID
     *
     * @return 用户ID
     */
    public int getId()
    {
        return id;
    }

    /**
     * 设置用户ID
     *
     * @param id 用户ID
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return 用户名
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * 获取用户邮箱
     *
     * @return 用户邮箱
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * 设置用户邮箱
     *
     * @param email 用户邮箱
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * 获取用户密码
     *
     * @return 用户密码
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * 设置用户密码
     *
     * @param password 用户密码
     */
    public void setPassword(String password)
    {
        this.password = password;
    }
}