package util;

import java.io.IOException;
import java.io.InputStream;
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
        System.out.println(PropertiesUtil.getValue("jdbcName"));
    }

    /**
     * @param key 配置文件key
     * @return
     * @Description:
     */
    public static String getValue(String key) {
        Properties prop = new Properties();
        InputStream in;
        try {
            in = PropertiesUtil.class
                    .getResourceAsStream("/jdbc.properties");
            try {
                prop.load(in);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("打开配置文件失败!");
        }
        return prop.getProperty(key);
    }

}