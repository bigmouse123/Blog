package com.jiankun.blog.dao.impl;

import com.jiankun.blog.dao.IBlogDao;
import com.jiankun.blog.pojo.Blog;
import com.jiankun.blog.pojo.vo.BlogVO;
import com.jiankun.blog.query.BlogQuery;
import com.jiankun.blog.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/2/17 22:08
 */
public class BlogDaoImpl implements IBlogDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<BlogVO> selectByPage(BlogQuery blogQuery) {
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
        sql += " LIMIT ?,?";
        int offset = (blogQuery.getPage() - 1) * blogQuery.getLimit();
        params.add(offset);
        params.add(blogQuery.getLimit());
        List<BlogVO> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BlogVO.class), params.toArray());
        return list;
    }

    @Override
    public int selectTotalCount(BlogQuery blogQuery) {
        String sql = "SELECT COUNT(*) FROM blog WHERE 1=1";
        List<Object> params = new ArrayList<>();
        String queryName = blogQuery.getName();
        if (queryName != null && !"".equals(queryName)) {
            sql += " AND name LIKE ?";
            params.add("%" + queryName + "%");
        }
        Integer totalCount = jdbcTemplate.queryForObject(sql, Integer.class, params.toArray());
        return totalCount;
    }

    @Override
    public void add(Blog blog) {
        String sql = "insert into blog(name,image,content,type_id) values(?,?,?,?)";
        jdbcTemplate.update(sql, blog.getName(), blog.getImage(), blog.getContent(), blog.getTypeId());
    }

    @Override
    public Blog selectById(String id) {
        String sql = "SELECT * FROM blog WHERE id = ?";
        Blog blog = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Blog.class), id);
        return blog;
    }

    @Override
    public void update(Blog blog) {
        String sql = "update blog set name=?,image=?,content=?,type_id=? where id=?";
        jdbcTemplate.update(sql, blog.getName(), blog.getImage(), blog.getContent(), blog.getTypeId(), blog.getId());
    }

    @Override
    public List<Blog> selectAll() {
        String sql = "select * from blog";
        List<Blog> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Blog.class));
        return list;
    }

    @Override
    public void addExcel(Blog blog) {
        String sql = "insert into blog(name,image,content,type_id,status,is_deleted,click_count,create_time,update_time) values(?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, blog.getName(), blog.getImage(), blog.getContent(), blog.getTypeId(), blog.getStatus(), blog.getIsDeleted(), blog.getClickCount(), blog.getCreateTime(), blog.getUpdateTime());
    }
}
