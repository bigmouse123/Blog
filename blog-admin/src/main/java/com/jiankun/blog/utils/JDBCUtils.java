package com.jiankun.blog.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/*
1. 声明静态数据源成员变量
2. 创建连接池对象
3. 定义公有的得到数据源的方法
4. 定义得到连接对象的方法
5. 定义关闭资源的方法
 */
public class JDBCUtils {
    // 1.   声明静态数据源成员变量
    private static DataSource dataSource;

    // 2. 创建连接池对象
    static {
        // 加载配置文件中的数据
        InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            // 创建连接池，使用配置文件中的参数
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 3. 定义公有的得到数据源的方法
    public static DataSource getDataSource() {
        return dataSource;
    }

    // 4. 定义得到连接对象的方法
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // 5.定义关闭资源的方法
    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }

    // 6.重载关闭方法
    public static void close(Connection conn, Statement stmt) {
        close(conn, stmt, null);
    }
}
