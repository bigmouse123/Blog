package com.jiankun.blog.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/2/21 14:35
 */
@Data
//@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Type {
    private Integer id;
    private String name;
    private Boolean deleted;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Data createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
