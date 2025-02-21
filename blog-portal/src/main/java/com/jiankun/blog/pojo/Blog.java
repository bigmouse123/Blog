package com.jiankun.blog.pojo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
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
public class Blog {
    @ExcelProperty(value = "ID")
    private Integer id;
    @ExcelProperty(value = "姓名")
    private String name;
    @ExcelProperty(value = "头像")
    private String image;
    @ExcelProperty(value = "点击数量")
    private Integer clickCount;
    @ExcelIgnore
    private String content;
    @ExcelProperty(value = "分类")
    private Integer typeId;
    @ExcelProperty(value = "状态")
    private Integer status;
    @ExcelProperty(value = "逻辑删除")
    private Integer deleted;//is_deleted
    @ExcelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @ExcelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}

