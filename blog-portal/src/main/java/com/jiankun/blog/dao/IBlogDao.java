package com.jiankun.blog.dao;

import com.jiankun.blog.pojo.Blog;
import com.jiankun.blog.pojo.query.BlogQuery;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/2/21 14:23
 */
public interface IBlogDao {
    List<Blog> list(BlogQuery blogQuery);
}
