package com.hngc.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

public class DataSourceUtil
{
    private static DruidDataSource ds;

    static
    {
        Properties properties = new Properties();

        try
        {
            InputStream inputStream = DataSourceUtil.class.getResourceAsStream("/database.properties");

            properties.load(inputStream);

            ds = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource()
    {
        return ds;
    }
}
