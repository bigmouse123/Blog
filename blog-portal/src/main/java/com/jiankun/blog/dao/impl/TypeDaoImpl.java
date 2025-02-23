package com.jiankun.blog.dao.impl;

import com.jiankun.blog.dao.ITypeDao;
import com.jiankun.blog.pojo.Type;
import com.jiankun.blog.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/2/22 15:12
 */
public class TypeDaoImpl implements ITypeDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Type> selectAll() {
        System.out.println("Dao");
        String sql = "select * from type";
        List<Type> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Type.class));
        for (Type type : list) {
            System.out.println(type);
        }
        return list;
    }
}
