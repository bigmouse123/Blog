package com.jiankun.blog.dao;

import com.jiankun.blog.pojo.Type;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/2/22 15:12
 */
public interface ITypeDao {
    List<Type> selectAll();
}
