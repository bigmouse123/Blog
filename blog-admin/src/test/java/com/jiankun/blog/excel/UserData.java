package com.jiankun.blog.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/2/20 10:23
 */
@Data
public class UserData {
    //设置Excel表头名称
    @ExcelProperty(value = "学生id")
    private Integer id;
    @ExcelProperty(value = "学生姓名")
    private String name;
}
