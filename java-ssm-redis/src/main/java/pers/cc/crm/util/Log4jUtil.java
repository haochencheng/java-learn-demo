package pers.cc.crm.util;

import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * 输出日志信息工具类
 * 
 * @author cc
 *
 */
public class Log4jUtil {
	public static void main(String[] args) {
		Log4JLogger log = new Log4JLogger("log4j.properties");
		log.debug("cc");
		log.info("aa");
	}

	/**
	 * 在控制台打印日志信息
	 * 
	 * @param str
	 */
	public static void printLogInConsole(String str, Class clazz) {
		PropertyConfigurator.configure("E:/workspace/ccmdm/src/main/resources/log4j.properties");
		Logger logger = Logger.getLogger(Log4jUtil.class);
		logger.info(" debug ");
	}
}
