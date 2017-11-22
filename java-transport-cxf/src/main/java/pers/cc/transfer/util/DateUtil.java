package pers.cc.transfer.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static void main(String[] args) throws Exception {
        System.out.println(getCurrentDateStr());
    }

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
     * 获取当前日期yyyy-MM-dd hh:mm:ss
     * 
     * @return str 当前日期字符串
     * @throws Exception
     * @Description:
     */
    public static String getCurrentDateStr() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(date);
    }

}
