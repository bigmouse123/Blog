package com.jiankun.blog.dao.impl;

import com.jiankun.blog.dao.IBlogDao;
import com.jiankun.blog.pojo.Blog;
import com.jiankun.blog.pojo.query.BlogQuery;
import com.jiankun.blog.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/2/21 14:23
 */
public class BlogDaoImpl implements IBlogDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Blog> list(BlogQuery blogQuery) {
        String sql = "SELECT * FROM blog WHERE 1=1";
        String queryName = blogQuery.getName();
        List<Object> params = new ArrayList<>();
        if (queryName != null && !"".equals(queryName)) {
            sql += " AND name like '%" + queryName + "%'";
            params.add(queryName);
        }
        Integer queryTypeId = blogQuery.getTypeId();
        if (queryTypeId != null) {
            sql += " AND type_id =" + queryTypeId;
            params.add(queryTypeId);
        }
        List<Blog> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Blog.class), params.toArray());
        return list;
    }
}
