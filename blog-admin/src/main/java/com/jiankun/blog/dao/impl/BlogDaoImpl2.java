/*
package com.jiankun.blog.dao.impl;

import com.jiankun.blog.dao.IBlogDao;
import com.jiankun.blog.pojo.Blog;
import com.jiankun.blog.pojo.vo.BlogVO;
import com.jiankun.blog.query.BlogQuery;
import com.jiankun.blog.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

*/
/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/2/17 22:08
 *//*

public class BlogDaoImpl2 implements IBlogDao {
    @Override
    public List<BlogVO> selectByPage(BlogQuery blogQuery) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<BlogVO> list = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT b.*,t.`name` AS typeName FROM blog b INNER JOIN type t ON b.type_id = t.id WHERE 1=1";
            List<Object> params = new ArrayList<>();
            String queryName = blogQuery.getName();
            if (queryName != null && !"".equals(queryName)) {
                sql += " AND b.name LIKE ?";
                params.add("%" + queryName + "%");
            }
            Integer queryTypeId = blogQuery.getTypeId();
            if (queryTypeId != null && !"".equals(queryTypeId)) {
                sql += " AND type_id = ?";
                params.add(queryTypeId);
            }
//            String queryTypeName = blogQuery.getTypeName();
//            if (queryTypeName != null && !"".equals(queryTypeName)) {
//                sql += " AND t.name = ?";
//                params.add(queryTypeName);
//            }
            sql += " LIMIT ?,?";
            int offset = (blogQuery.getPage() - 1) * blogQuery.getLimit();
            params.add(offset);
            params.add(blogQuery.getLimit());
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }
            resultSet = statement.executeQuery();
            list = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String image = resultSet.getString("image");
                int clickCount = resultSet.getInt("click_count");
                String content = resultSet.getString("content");
                int typeId = resultSet.getInt("type_id");
                int status = resultSet.getInt("status");
                boolean isDeleted = resultSet.getBoolean("is_deleted");
                Date createTime = resultSet.getTimestamp("create_time");
                Date updateTime = resultSet.getTimestamp("update_time");
                String typeName = resultSet.getString("typeName");
                BlogVO blogVO = new BlogVO(id, name, image, clickCount, content, typeId, status, isDeleted, createTime, updateTime, typeName);
                list.add(blogVO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }
        return list;
    }

    @Override
    public int selectTotalCount(BlogQuery blogQuery) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int totalCount = 0;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT COUNT(*) FROM blog WHERE 1=1";
            List<Object> params = new ArrayList<>();
            String queryName = blogQuery.getName();
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
            JDBCUtil.close(connection, statement, resultSet);
        }
        return totalCount;
    }

    @Override
    public void add(Blog blog) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "insert into blog(name,image,content,type_id) values(?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, blog.getName());
            statement.setString(2, blog.getImage());
            statement.setString(3, blog.getContent());
            statement.setInt(4, blog.getTypeId());
            System.out.println(statement);
            int count = statement.executeUpdate();
            System.out.println(statement);
            System.out.println(count);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(connection, statement, null);
        }
    }

    @Override
    public Blog selectById(String id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Blog blog = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT * FROM blog WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String image = resultSet.getString("image");
                int clickCount = resultSet.getInt("click_count");
                String content = resultSet.getString("content");
                int typeId = resultSet.getInt("type_id");
                int status = resultSet.getInt("status");
                boolean isDeleted = resultSet.getBoolean("is_deleted");
                Date createTime = resultSet.getTimestamp("create_time");
                Date updateTime = resultSet.getTimestamp("update_time");
                blog = new Blog(Integer.parseInt(id), name, image, clickCount, content, typeId, status, isDeleted, createTime, updateTime);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }
        return blog;
    }

    @Override
    public void update(Blog blog) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "update blog set name=?,image=?,content=?,type_id=? where id=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, blog.getName());
            statement.setString(2, blog.getImage());
            statement.setString(3, blog.getContent());
            statement.setInt(4, blog.getTypeId());
            statement.setInt(5, blog.getId());
            System.out.println(statement);
            int count = statement.executeUpdate();
            System.out.println(statement);
            System.out.println(count);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(connection, statement, null);
        }
    }
}
*/
