package pers.cc.transfer.connpool;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import pers.cc.transfer.connpool.impl.JdbcConnectionPool;
import pers.cc.transfer.util.LoggerUtil;
import pers.cc.transfer.util.PropertiesUtil;

/**
 * 连接池的设计思路 1、 初始化固定数目的连接（空闲连接与活跃连接在同一池中），建立连接的代理类，添加busy与startTime属性以便分发与回收连接
 * 另建立守护线程回收失效连接 2、 维护一空闲连接池，初始为空，需要连接时建立，用完的连接回收进入空闲连接池；
 * 后续所需连接从空闲连接池获取；activeNum记录活跃连接数目；
 * 当空闲连接池为空且活跃连接数达到上限时，请求等待，超时即获取连接失败，超时前有连接被释放方可获得连接 第二个设计巧妙优势明显，采用第二种方式
 * 
 * 数据库连接管理类，单例模式 可以管理加载多个数据库驱动，维护多个数据库连接池
 * 
 * @author cc
 * 
 */
public class DBConnectionManager {

    private static DBConnectionManager dbm = null;

    /**
     * 客户数目
     */
    private static int clients = 0;
    /**
     * 加载的驱动器集合
     */
    private Set<Driver> drivers = new HashSet<Driver>();
    /**
     * 数据库连接池字典
     */
    private Map<String, JdbcConnectionPool> pools = new HashMap<String, JdbcConnectionPool>();

    private final LoggerUtil log = LoggerUtil
            .getInstance(JdbcConnectionPool.class);

    private DBConnectionManager() {
        // 注册驱动
        loadDrivers();
        // 创建连接池
        createPools();
    }

    /**
     * 装载和注册所有的JDBC驱动程序
     */
    private void loadDrivers() {
        String str_drivers = PropertiesUtil.getProperty("jdbcName");
        for (String str_driver : str_drivers.split(" ")) {
            Driver driver = null;
            try {
                driver = (Driver) Class.forName(str_driver).newInstance();
                DriverManager.registerDriver(driver);
                log.info("成功加载JDBC驱动：" + str_driver);
            } catch (InstantiationException e) {
                log.error("加载JDBC驱动" + str_driver + "时初始化异常，请检查配置文件");
            } catch (IllegalAccessException e) {
                log.info("加载JDBC驱动" + str_driver + "时非法访问，请检查配置文件");
            } catch (ClassNotFoundException e) {
                log.info("未找到JDBC驱动" + str_driver + "请引入相关包");
                driver = null;
            } catch (SQLException e) {
                log.info("加载JDBC驱动" + str_driver + "失败，请检查引入包的正确性");
            }
            drivers.add(driver);
        }
    }

    /**
     * 根据配置文件创建数据库连接池
     */
    private void createPools() {
        Enumeration<?> elements = PropertiesUtil.propertiesNames();
        while (elements.hasMoreElements()) {
            String element = (String) elements.nextElement();
            if (element.endsWith(".url")) {
                String poolName = element.substring(0,
                        element.lastIndexOf("."));
                String url = PropertiesUtil.getProperty(poolName + ".url");
                if (url == null) {
                    log.error("无法连接到数据库" + poolName + "请检查配置文件连接字符串");
                    continue;
                }
                String user = PropertiesUtil.getProperty(poolName + ".user");
                String pwd = PropertiesUtil.getProperty(poolName + ".password");
                log.info(pwd);
                String str_max = PropertiesUtil
                        .getProperty(poolName + ".maxconn", "0");
                int maxConn = 0;
                try {
                    maxConn = Integer.parseInt(str_max);
                } catch (NumberFormatException e) {
                    log.error("数据库" + poolName + "最大连接数设置错误，默认设为10");
                    maxConn = 10;
                }
                JdbcConnectionPool pool = new JdbcConnectionPool(url, user,
                        pwd);
                pool.init(maxConn, Integer.parseInt(PropertiesUtil
                        .getProperty(poolName + ".maxWait", "5000")));
                pools.put(poolName, pool);
                log.info("成功创建数据库连接池" + poolName);
            }
        }
    }

    /**
     * 获取链接池中总链接数量
     * 
     * @param poolName
     *            链接池名称
     * @return
     */
    public static int getConnPoolSize(String poolName) {
        return dbm.pools.get(poolName).getTotalConnSize();

    }

    /**
     * 获得单例
     * 
     * @return DBConnectionManager单例
     */
    public synchronized static DBConnectionManager getInstance() {
        if (dbm == null) {
            dbm = new DBConnectionManager();
        }
        clients++;
        return dbm;
    }

    /**
     * 从指定连接池中获取可用连接，无等待
     * 
     * @param poolName
     *            要获取连接的连接池名称
     * @return 连接池中的一个可用连接或null
     */
    public Connection getConnection(String poolName) {
        JdbcConnectionPool pool = pools.get(poolName);
        return pool.getConnection();
    }

    /**
     * 回收指定连接池的连接
     * 
     * @param poolName
     *            连接池名称
     * @param conn
     *            要回收的连接
     */
    public void freeConnection(String poolName, Connection conn) {
        JdbcConnectionPool pool = pools.get(poolName);
        if (pool != null) {
            try {
                pool.release(conn);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            log.error("找不到连接池，无法回收，请检查参数");
        }
    }

    /**
     * 关闭所有连接，撤销驱动器的注册
     */
    public synchronized void release() {
        // 所有客户连接都关闭时才真正关闭连接撤销注册
        if (clients-- != 0) {
            return;
        }
        for (Map.Entry<String, JdbcConnectionPool> poolEntry : pools
                .entrySet()) {
            JdbcConnectionPool pool = poolEntry.getValue();
            pool.close();
        }
        log.info("已经关闭所有连接");
        for (Driver driver : drivers) {
            try {
                DriverManager.deregisterDriver(driver);
                log.info("撤销JDBC驱动器" + driver.getClass().getName() + "的注册");
            } catch (SQLException e) {
                log.error("撤销JDBC驱动器" + driver.getClass().getName() + "的注册异常");
            }
        }
        log.info("驱动器撤销完成");
    }
}
