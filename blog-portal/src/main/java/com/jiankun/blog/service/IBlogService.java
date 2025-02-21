package com.jiankun.blog.service;

import com.jiankun.blog.pojo.Blog;
import com.jiankun.blog.pojo.query.BlogQuery;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/2/21 14:22
 */
public interface IBlogService {
    List<Blog> list(BlogQuery blogQuery);
}
