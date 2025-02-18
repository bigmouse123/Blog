package com.jiankun.blog.query;

import lombok.Data;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/2/17 21:47
 */
@Data
public class BlogQuery {
    private Integer page;
    private Integer limit;
    private String name;
}
