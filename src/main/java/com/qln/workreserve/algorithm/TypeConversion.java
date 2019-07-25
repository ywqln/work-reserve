package com.qln.workreserve.algorithm;

import java.util.Scanner;

/**
 * @Title: $
 * @Project: 类型转换
 * @Description: TODO
 * @Author 秦莉娜
 * @Date 2019/7/18$ 15:00$
 * @Version V2.0
 **/
//二进制转成十进制
//十进制转成二进制
public class TypeConversion {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("请输入一个二进制数字");
        String binaryNum = s.nextLine();
        int a = 1;
        int b = 0;
        int length = binaryNum.length();
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                a = 1;
            } else {
                a = a * 2;
            }
            b = a;
            b = b + a;
            System.out.println(b);
        }
        int c = b-1;
        System.out.println("b---" + c);
    }
}
