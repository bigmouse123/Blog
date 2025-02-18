package com.jiankun.blog.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/2/17 21:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeQuery {
    private Integer page;
    private Integer limit;
    private String name;
}
