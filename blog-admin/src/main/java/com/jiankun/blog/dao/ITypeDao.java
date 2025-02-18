package com.jiankun.blog.dao;

import com.jiankun.blog.pojo.Type;
import com.jiankun.blog.query.TypeQuery;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/2/17 22:07
 */
public interface ITypeDao {
    List<Type> selectByPage(TypeQuery typeQuery);
    int selectTotalCount(TypeQuery typeQuery);

    void add(Type type);

    List<Type> selectAll();
}
