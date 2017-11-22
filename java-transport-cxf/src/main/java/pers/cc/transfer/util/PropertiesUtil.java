package pers.cc.transfer.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 * 读取配置文件
 * 
 * @Title:
 * @Description:
 * @Author: cc
 * @Since:2017年7月19日
 * @Version:1.0.0
 */
public class PropertiesUtil {

    public static void main(String[] args) {
        PropertiesUtil.getProperty("dbUserName");
    }

    private static Properties pro = new Properties();

    private PropertiesUtil() {
    }

    private static InputStream in;
    static {
        try {
            pro.load(in = PropertiesUtil.class
                    .getResourceAsStream("/properties"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static String getProperty(String key) {
        return pro.getProperty(key).trim();
    }

    public static String getProperty(String key, String defaultValue) {
        // 找不到key用defaultValue，而不是说后面为空字符串采用defaultValue
        return pro.getProperty(key, defaultValue).trim();
    }

    public static Enumeration<?> propertiesNames() {
        return pro.propertyNames();
    }

    /**
     * @param key
     *            配置文件key
     * @return
     * @Description:
     */
    public static String getValue(String key) {
        return pro.getProperty(key).trim();
    }

}
