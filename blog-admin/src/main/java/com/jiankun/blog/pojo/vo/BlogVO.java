package com.jiankun.blog.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/2/18 17:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogVO {
    private Integer id;
    private String name;
    private String image;
    private Integer clickCount;
    private String content;
    private Integer typeId;
    private Integer status;
    private Boolean isDeleted;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    private String typeName;
}
