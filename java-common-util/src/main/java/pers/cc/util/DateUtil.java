package pers.cc.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Title:
 * @Description:
 * @Author: cc
 * @Since:2017年7月19日
 * @Version:1.0.0
 */
public class DateUtil {

    /**
     * 日期对象转字符串
     * 
     * @param date
     *            日期对象
     * @param format
     *            格式:例如yyyy-MM-dd hh:mm:ss
     * @return str 格式化后字符串
     */
    public static String formatDate(Date date, String format) {
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (date != null) {
            result = sdf.format(date);
        }
        return result;
    }

    /**
     * 字符串转日期对象
     * 
     * @param str
     *            字符串
     * @param format
     *            格式:例如yyyy-MM-dd hh:mm:ss
     * @return date 日期对象
     * @throws Exception
     */
    public static Date formatString(String str, String format)
            throws Exception {
        if (StringUtil.isEmpty(str)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(str);
    }

    /**
     * 获取当前日期yyyyMMddhhmmss
     * 
     * @return str 当前日期字符串
     * @throws Exception
     * @Description:
     */
    public static String getCurrentDateStr() throws Exception {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        return sdf.format(date);
    }

}
