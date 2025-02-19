package com.jiankun.blog.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/1/21 11:46
 */

// &allowPublicKeyRetrieval=true
public class JDBCUtil {

    public static String driver;
    public static String url;
    public static String username;
    public static String password;

    static {
        try {
            ClassLoader classLoader = JDBCUtil.class.getClassLoader();
            System.out.println(classLoader);
            InputStream inputStream = classLoader.getResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            Class.forName(driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection
                (url, username, password);
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
