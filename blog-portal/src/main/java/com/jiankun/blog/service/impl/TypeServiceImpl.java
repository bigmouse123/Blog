package com.jiankun.blog.service.impl;

import com.jiankun.blog.dao.ITypeDao;
import com.jiankun.blog.dao.impl.TypeDaoImpl;
import com.jiankun.blog.pojo.Type;
import com.jiankun.blog.service.ITypeService;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/2/22 15:11
 */
public class TypeServiceImpl implements ITypeService {
    private ITypeDao typeDao = new TypeDaoImpl();

    @Override
    public List<Type> selectAll() {
        return typeDao.selectAll();
    }
}
