package pers.cc.crm.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	
	public static void main(String[] args) {
		System.out.println(PropertiesUtil.getValue("jdbcName"));
	}
	
	public static String getValue(String key) {
		Properties	prop=new Properties();
		InputStream in=new PropertiesUtil().getClass().getResourceAsStream("/.Properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}
	
	
	
}
