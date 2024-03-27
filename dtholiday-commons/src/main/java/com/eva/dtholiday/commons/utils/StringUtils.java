package com.eva.dtholiday.commons.utils;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/3/27 23:38
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
public class StringUtils {

    /**
     * 获取UUID
     * @return
     */
    public static String getUUID() {
        return java.util.UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(getUUID());
        }
    }
}
