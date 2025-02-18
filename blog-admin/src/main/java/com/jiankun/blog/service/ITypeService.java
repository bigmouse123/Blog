package com.jiankun.blog.service;

import com.jiankun.blog.pojo.Type;
import com.jiankun.blog.query.TypeQuery;
import com.jiankun.blog.utils.PageResult;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/2/17 21:58
 */
public interface ITypeService {
    PageResult<Type> selectByPage(TypeQuery typeQuery);

    void add(Type type);

    List<Type> selectAll();
}
