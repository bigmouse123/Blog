package com.jiankun.blog.service.impl;

import com.jiankun.blog.dao.IBlogDao;
import com.jiankun.blog.dao.impl.BlogDaoImpl;
import com.jiankun.blog.pojo.Blog;
import com.jiankun.blog.pojo.vo.BlogVO;
import com.jiankun.blog.query.BlogQuery;
import com.jiankun.blog.service.IBlogService;
import com.jiankun.blog.utils.PageResult;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/2/17 21:58
 */
public class BlogServiceImpl implements IBlogService {
    private IBlogDao blogDao = new BlogDaoImpl();

    @Override
    public PageResult<BlogVO> selectByPage(BlogQuery blogQuery) {
        List<BlogVO> list = blogDao.selectByPage(blogQuery);
        int totalCount = blogDao.selectTotalCount(blogQuery);
        PageResult<BlogVO> pageResult = new PageResult<>(0, "", totalCount, list);
        return pageResult;
    }

    @Override
    public void add(Blog blog) {
        blogDao.add(blog);
    }

    @Override
    public Blog selectById(String id) {
        return blogDao.selectById(id);
    }

    @Override
    public void update(Blog blog) {
        blogDao.update(blog);
    }
}
