package com.jiankun.blog.dao.impl;

import com.jiankun.blog.dao.ITypeDao;
import com.jiankun.blog.pojo.Type;
import com.jiankun.blog.query.TypeQuery;
import com.jiankun.blog.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/2/17 22:08
 */
public class TypeDaoImpl implements ITypeDao {
    @Override
    public List<Type> selectByPage(TypeQuery typeQuery) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Type> list = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "SELECT * from type WHERE 1=1";
            List<Object> params = new ArrayList<>();
            String queryName = typeQuery.getName();
            if (queryName != null && !"".equals(queryName)) {
                sql += " AND name LIKE ?";
                params.add("%" + queryName + "%");
            }
            sql += " LIMIT ?,?";
            int offset = (typeQuery.getPage() - 1) * typeQuery.getLimit();
            params.add(offset);
            params.add(typeQuery.getLimit());
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }
            resultSet = statement.executeQuery();
            list = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                boolean deleted = resultSet.getBoolean("is_deleted");
                Date createTime = resultSet.getTimestamp("create_time");
                Date updateTime = resultSet.getTimestamp("update_time");
                Type type = new Type(id, name, deleted, createTime, updateTime);
                list.add(type);
            }
            for (Type type : list) {
                System.out.println(type);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(connection, statement, resultSet);
        }
        return list;
    }

    @Override
    public int selectTotalCount(TypeQuery typeQuery) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int totalCount = 0;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "SELECT COUNT(*) FROM type WHERE 1=1";
            List<Object> params = new ArrayList<>();
            String queryName = typeQuery.getName();
            if (queryName != null && !"".equals(queryName)) {
                sql += " AND name LIKE ?";
                params.add("%" + queryName + "%");
            }
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                totalCount = resultSet.getInt(1);
                System.out.println("totalCount=" + totalCount);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(connection, statement, resultSet);
        }
        return totalCount;
    }

    @Override
    public void add(Type type) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "insert into type(name) values(?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, type.getName());
            System.out.println(statement);
            int count = statement.executeUpdate();
            System.out.println(statement);
            System.out.println(count);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(connection, statement, null);
        }
    }

    @Override
    public List<Type> selectAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Type> list = new ArrayList<>();
        try {
            connection = JDBCUtils.getConnection();
            String sql = "SELECT * FROM type";
            statement = connection.prepareStatement(sql);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {//判断下一个有没有，没有返回false，如果有返回true，并且指向下一行
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                boolean deleted = resultSet.getBoolean("is_deleted");
                Date createTime = resultSet.getTimestamp("create_time");
                Date updateTime = resultSet.getTimestamp("update_time");
                Type type = new Type(id, name, deleted, createTime, updateTime);
                list.add(type);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(connection, statement, resultSet);
        }
        return list;
    }
}
