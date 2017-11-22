package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 连接数据库工具类
 *
 * @Title:
 * @Description:
 * @Author: cc
 * @Since:2017年7月19日
 * @Version:1.0.0
 */
public class DbUtil {

    /**
     * 连接数据库,需在配置文件中定义连接数据库信息
     *
     * @return 数据库连接
     * @throws Exception
     * @Description:
     */
    public Connection getCon() throws Exception {
        Class.forName(PropertiesUtil.getValue("jdbcName"));
        return DriverManager.getConnection(
                PropertiesUtil.getValue("dbUrl"),
                PropertiesUtil.getValue("dbUserName"),
                PropertiesUtil.getValue("dbPassword"));
    }

    /**
     * 关闭数据库连接
     *
     * @param con
     * @throws Exception
     * @Description:
     */
    public final void closeCon(final Connection con) throws Exception {
        if (con != null) {
            con.close();
        }
    }

    public static void main(String[] args) {
        DbUtil dbUtil = new DbUtil();
        try {
            dbUtil.getCon();
            System.out.println("数据库连接成功!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据库连接失败!");
        }
    }

}