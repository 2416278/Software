package com.hxx.sys.utils;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;

/**
 * 操作数据库的工具类
 */
public class MyDbUtils {
    //定义数据源
    private static MysqlDataSource dataSource;
    //数据源的初始化
    static{
        dataSource=new MysqlConnectionPoolDataSource();

        dataSource.setUrl("jdbc:mysql://localhost:3306/store?serverTimezone=GMT%2b8");
        dataSource.setUser("root");
        dataSource.setPassword("04082012");
    }
    public static QueryRunner getQueryRunner(){
        return new QueryRunner(dataSource);
    }
}
