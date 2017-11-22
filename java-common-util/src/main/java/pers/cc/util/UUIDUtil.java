package pers.cc.util;

import java.util.UUID;

/**
 * 获取UUID
 * 
 * @Title:
 * @Description:
 * @Author: cc
 * @Since:2017年7月19日
 * @Version:1.0.0
 */
public class UUIDUtil {

    /**
     * 获取UUID
     * 
     * @return uuid.toString
     * @Description:
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static void main(String[] args) {

        System.out.println(UUIDUtil.getUUID());
    }

}
