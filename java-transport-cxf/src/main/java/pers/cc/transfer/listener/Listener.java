package pers.cc.transfer.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import pers.cc.transfer.connpool.DBConnectionManager;
import pers.cc.transfer.util.LoggerUtil;

public class Listener implements ServletContextListener {

    private LoggerUtil log = LoggerUtil.getInstance(Listener.class);
    private DBConnectionManager connpool;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 加载数据库连接池
        connpool = DBConnectionManager.getInstance();
        // 发布webService
        log.info("发布webservice成功！");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // 关闭数据库连接池
        connpool.release();
    }

}
