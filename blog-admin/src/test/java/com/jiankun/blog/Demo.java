package com.jiankun.blog;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/2/17 16:27
 */
public class Demo {
    @Test
    public void Test() {
//        String content = "\\abc$(a.bc(123( )";
        String content = "a211111a1aaaaahello";
        //演示?的使用, 遵守贪婪匹配
        String regStr = "a1?"; //匹配 a 或者 a1
//        String regStr = "a{3}";// 表示匹配 aaa
        //匹配( => \\(
        //匹配. => \\.
//        String regStr = "\\\\";
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println("找到 " + matcher.group(0));
        }
    }
}
