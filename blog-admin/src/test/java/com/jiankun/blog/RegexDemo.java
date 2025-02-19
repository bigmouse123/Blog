package com.jiankun.blog;

import org.junit.Test;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/2/19 14:58
 */
public class RegexDemo {
    public static void main(String[] args) {
        String[] array = {"2025011030", "2023-1108-02", "2324 9875 52", "20289875*25"};
        String regex = "(\\d{4})[\\-\\s]?(\\d{4})[\\-\\s]?(\\d{2})";
        Pattern pattern = Pattern.compile(regex);
        for (String string : array) {
            Matcher matcher = pattern.matcher(string);
            if (matcher.find()) {
                System.out.println(matcher.group(0));
                System.out.println(matcher.group(1) + "-" + matcher.group(2) + "-" + matcher.group(3));
            }
        }
    }

    @Test
    public void main1() {
        System.out.println("请输入字符串:");
        Scanner scanner = new Scanner(System.in);
        String regex = "(\\d{4})[\\-\\s]?(\\d{4})[\\-\\s]?(\\d{2})";
        Pattern pattern = Pattern.compile(regex);
        String string = scanner.nextLine();
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            System.out.println(matcher.group(1) + "-" + matcher.group(2) + "-" + matcher.group(3));
        }
    }
}
