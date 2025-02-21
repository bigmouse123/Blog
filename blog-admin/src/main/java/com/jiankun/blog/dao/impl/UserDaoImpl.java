package com.jiankun.blog.dao.impl;

import com.jiankun.blog.dao.IUserDao;
import com.jiankun.blog.pojo.User;
import com.jiankun.blog.utils.JDBCUtil;
import com.jiankun.blog.utils.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/2/7 14:06
 */
public class UserDaoImpl implements IUserDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User login(String username, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT id,name,password from users where name = ? and password = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                user = new User(id, username, password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }
        return user;
    }

    @Override
    public void updatePassword(String name, String newPassword) {
        String sql = "update users set password = ? where name = ?";
        jdbcTemplate.update(sql, newPassword, name);
    }
}
