package com.jiankun.blog.service.impl;

import com.jiankun.blog.dao.IBlogDao;
import com.jiankun.blog.dao.impl.BlogDaoImpl;
import com.jiankun.blog.pojo.Blog;
import com.jiankun.blog.pojo.query.BlogQuery;
import com.jiankun.blog.service.IBlogService;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/2/21 14:22
 */
public class BlogServiceImpl implements IBlogService {
    private IBlogDao blogDao = new BlogDaoImpl();

    @Override
    public List<Blog> list(BlogQuery blogQuery) {
        List<Blog> list = blogDao.list(blogQuery);
        return list;
    }
}
