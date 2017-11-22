package pers.cc.crm.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

	public static void main(String[] args) {
		System.out.println(PropertiesUtil.getValue("common.properties", "topNodeParentId"));
	}

	/**
	 * @param properties
	 *            配置文件名字
	 * @param key
	 *            位置key
	 * @return
	 */
	public static String getValue(String properties, String key) {
		Properties prop = new Properties();
		InputStream in = new PropertiesUtil().getClass().getResourceAsStream("/" + properties);
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}

}
