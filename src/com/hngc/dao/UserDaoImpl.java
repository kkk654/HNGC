package com.hngc.dao;

import com.hngc.pojo.User;
import com.hngc.util.DataSourceUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * UserDao接口的实现类，负责用户相关的数据库操作
 */
public class UserDaoImpl implements UserDao
{

    /**
     * QueryRunner对象，用于执行数据库查询操作
     * 使用静态变量确保全局共享一个QueryRunner实例
     */
    private static QueryRunner queryRunner = new QueryRunner(DataSourceUtil.getDataSource());

    /**
     * 用户登录方法
     * 根据用户提供的邮箱和密码查询数据库，验证用户身份
     *
     * @param email    用户邮箱
     * @param password 用户密码
     * @return 登录成功返回User对象，包含用户信息；登录失败返回null
     */
    @Override
    public User login(String email, String password)
    {
        // SQL查询语句，根据邮箱和密码查询用户信息
        String sql = "SELECT id,username,password,email FROM user where email =? and password =? ";

        try
        {
            // 执行查询操作
            // 参数1：SQL语句
            // 参数2：结果集处理器，将查询结果转换为User对象
            // 参数3、4：SQL语句中的占位符参数值（邮箱和密码）
            User user = queryRunner.query(sql, new BeanHandler<User>(User.class), email, password);

            // 如果查询到用户对象，则返回该对象
            if (user != null)
            {
                return user;
            }

        }
        catch (SQLException e)
        {
            // 打印数据库操作异常信息
            e.printStackTrace();
        }
        // 登录失败返回null
        return null;
    }
}