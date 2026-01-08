package com.hngc.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

/**
 * 数据库连接池工具类
 * 使用Druid数据源管理数据库连接，提供获取数据源的静态方法
 */
public class DataSourceUtil
{

    /**
     * Druid数据源实例，用于管理数据库连接池
     */
    private static DruidDataSource ds;

    /**
     * 静态代码块，用于初始化Druid数据源
     * 在类加载时执行，加载数据库配置文件并创建数据源实例
     */
    static
    {
        // 创建Properties对象，用于加载配置文件
        Properties properties = new Properties();

        try
        {
            // 通过类加载器获取数据库配置文件的输入流
            InputStream inputStream = DataSourceUtil.class.getResourceAsStream("/database.properties");

            // 加载配置文件内容
            properties.load(inputStream);

            // 使用Druid数据源工厂创建并初始化数据源
            ds = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        }
        catch (Exception e)
        {
            // 捕获并打印初始化过程中的异常
            e.printStackTrace();
        }
    }

    /**
     * 获取Druid数据源实例的静态方法
     *
     * @return DataSource 数据库连接池的数据源实例
     */
    public static DataSource getDataSource()
    {
        // 返回初始化好的Druid数据源实例
        return ds;
    }
}