package pers.spring.boot.demo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

/**
 * 读取配置文件工具类 Created by cc on 2017/11/20
 */
public class PropertiesUtil {

	private static Properties props = null;
	private static File configFile = null;
	private static long fileLastModified = 0L;

	private static String configFileName = "system.properties";

	private static void init() {
		URL url = PropertiesUtil.class.getClassLoader().getResource(configFileName);
		configFile = new File(url.getFile());
		fileLastModified = configFile.lastModified();
		props = new Properties();
		load();
	}

	private static void load() {
		try {
			props.load(new InputStreamReader(new FileInputStream(configFile), "UTF-8"));
			fileLastModified = configFile.lastModified();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String getConfig(String key) {
		if ((configFile == null) || (props == null))
			init();
		if (configFile.lastModified() > fileLastModified)
			load(); // 当检测到文件被修改时重新加载配置文件
		return props.getProperty(key);
	}

	public static void main(String[] args) {
		System.out.println(getConfig("fuqing-bu"));
	}

}
