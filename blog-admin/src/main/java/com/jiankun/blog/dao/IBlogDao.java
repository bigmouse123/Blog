package com.jiankun.blog.dao;

import com.jiankun.blog.pojo.Blog;
import com.jiankun.blog.pojo.vo.BlogVO;
import com.jiankun.blog.query.BlogQuery;
import com.jiankun.blog.utils.PageResult;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/2/17 22:07
 */
public interface IBlogDao {
    List<BlogVO> selectByPage(BlogQuery blogQuery);
    int selectTotalCount(BlogQuery blogQuery);

    void add(Blog blog);

    Blog selectById(String id);

    void update(Blog blog);
}
