package com.jiankun.blog.service.impl;

import com.jiankun.blog.dao.ITypeDao;
import com.jiankun.blog.dao.impl.TypeDaoImpl;
import com.jiankun.blog.pojo.Type;
import com.jiankun.blog.query.TypeQuery;
import com.jiankun.blog.service.ITypeService;
import com.jiankun.blog.utils.PageResult;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/2/17 21:58
 */
public class TypeServiceImpl implements ITypeService {
    private ITypeDao typeDao = new TypeDaoImpl();

    @Override
    public PageResult<Type> selectByPage(TypeQuery typeQuery) {
        List<Type> list = typeDao.selectByPage(typeQuery);
        int totalCount = typeDao.selectTotalCount(typeQuery);
        PageResult<Type> pageResult = new PageResult<>(0, "", totalCount, list);
        return pageResult;
    }

    @Override
    public void add(Type type) {
        typeDao.add(type);
    }

    @Override
    public List<Type> selectAll() {
        return typeDao.selectAll();
    }
}
