package com.hngc.dao;

import com.hngc.pojo.User;
import com.hngc.util.DataSourceUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao
{

    //查询对象
    private static QueryRunner queryRunner = new QueryRunner(DataSourceUtil.getDataSource());

    @Override
    public User login(String email, String password)
    {


        String sql = "SELECT id,username,password,email FROM user where email =?   and  password =? ";

        try
        {
            //根据邮箱和密码查询用户对象
            //参数一：sql 语句 ，参数二：实例拦截对象 ，参数三和参数四：传递的参数的值
            User user = queryRunner.query(sql,new BeanHandler<User>(User.class),email,password);

            //非空判断
            if (user !=null)
            {
                return user;
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
