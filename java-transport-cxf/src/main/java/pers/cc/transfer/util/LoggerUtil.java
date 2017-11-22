package pers.cc.transfer.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * 日志文件创建维护类，单例模式
 * 
 * @author cc
 * 
 */
public class LoggerUtil {

    private static LoggerUtil logger = null;
    private PrintWriter log = null;
    private static int level = 0;
    private Class<?> c = null;
    private static final int DEBUGLEVEL = 1;
    private static final int INFOLEVEL = 2;
    private static final int ERRORLEVEL = 3;
    private static String isopen;

    static {
        isopen = PropertiesUtil.getProperty("openlog", "0");
    }

    private LoggerUtil(Class<?> c) {
        String logFileName = PropertiesUtil.getProperty("logfile", "sql.log");
        String str_level = PropertiesUtil.getProperty("loglevel", "3");
        this.c = c;
        level = Integer.parseInt(str_level);
        try {
            log = new PrintWriter(new FileWriter(logFileName), true);
        } catch (IOException e) {
            System.err.println("无法打开日志文件" + logFileName);
            log = new PrintWriter(System.err);
        }
    }

    public synchronized static LoggerUtil getInstance(Class<?> c) {
        if (logger == null) {
            logger = new LoggerUtil(c);
        }
        return logger;
    }

    public void debug(String msg) {
        if ("0".equals(isopen)) {
            return;
        }
        if (level > DEBUGLEVEL) {
            msg = "DEBUG:" + new Date() + "-" + msg;
            System.out.println(msg);
            log.println(msg);
        }

    }

    public void info(String msg) {
        if ("0".equals(isopen)) {
            return;
        }
        if (level > INFOLEVEL) {
            msg = "INFO:" + new Date() + "-" + msg;
            System.out.println(msg);
            log.println(msg);
        }

    }

    public void error(String msg) {
        if ("0".equals(isopen)) {
            return;
        }
        if (level > ERRORLEVEL) {
            msg = "ERROR:" + new Date() + "-" + c + "-" + msg;
            System.out.println(msg);
            log.println(msg);
        }
    }
}
