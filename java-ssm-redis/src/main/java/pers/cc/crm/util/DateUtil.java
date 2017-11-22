package pers.cc.crm.util;

import java.util.Date;

/**
 * @Title:
 * @Description: 日期工具类
 * @Author: cc
 * @Since:2017年1月9日
 * @Version:1.0.0
 */
public class DateUtil {

    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(getNowDateyyyyMMdd());
    }

    public static Date getNowDateyyyyMMdd() {
        java.sql.Date d = new java.sql.Date(new Date().getTime());
        return d;
    }
}
