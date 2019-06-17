package com.qln.workreserve.utils;

import java.util.UUID;

/**
 * @Title: UUIDGenerator
 * @Project: csai-api
 * @Description: UUID生成工具
 * @Author 秦莉娜
 * @Date 2019/6/5 11:08
 * @Version V2.0
 **/
public class UUIDGenerator {
    /**
     * 获得指定数目的UUID
     *
     * @param number int 需要获得的UUID数量
     * @return String[] UUID数组
     */
    public static String[] getUUID(int number) {
        if (number < 1) {
            return null;
        }
        String[] retArray = new String[number];
        for (int i = 0; i < number; i++) {
            retArray[i] = getUUID();
        }
        return retArray;
    }

    /**
     * 获得一个UUID
     *
     * @return String UUID
     */
    private static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", "");
    }
}

