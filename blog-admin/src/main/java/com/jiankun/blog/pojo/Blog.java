package com.jiankun.blog.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/2/17 16:39
 */
@Data
public class Blog {
    private Integer id;
    private String name;
    private String image;
    private Integer clickCount;
    private String content;
    private Integer typeId;
    private Integer status;
    private Boolean deleted;
    private Date createTime;
    private Date updateTime;
}
