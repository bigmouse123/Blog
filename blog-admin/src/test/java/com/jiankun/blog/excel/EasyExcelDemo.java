package com.jiankun.blog.excel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/2/20 10:24
 */
public class EasyExcelDemo {
    @Test
    public void testWrite() {
        //构建数据的集合
        List<UserData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserData data = new UserData();
            data.setId(i);
            data.setName("excel" + i);
            list.add(data);
        }

        //设置excel文件的路径和文件的名称
        String fileName = "F:\\01.xlsx";
        //调用方法实现写的操作
        EasyExcel.write(fileName, UserData.class).sheet("用户的信息").doWrite(list);
    }

    @Test
    public void testRead() {
        String fileName = "F:\\01.xlsx";
        EasyExcel.read(fileName, UserData.class, new ExcelListener()).sheet().doRead();
    }
}
