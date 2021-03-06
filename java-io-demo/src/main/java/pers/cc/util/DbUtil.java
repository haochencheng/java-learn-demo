package pers.cc.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by cc on 2017/7/28.
 */
public class DbUtil {

    /**
     * 连接数据库,需在配置文件中定义连接数据库信息
     *
     * @return 数据库连接
     * @throws Exception
     * @Description:
     */
    public static Connection getCon() throws Exception {
        Class.forName(PropertiesUtil.getValue("jdbcName"));
        Connection con = DriverManager.getConnection(
                PropertiesUtil.getValue("dbUrl"),
                PropertiesUtil.getValue("dbUserName"),
                PropertiesUtil.getValue("dbPassword"));
        return con;
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
        try {
            DbUtil.getCon();
            System.out.println("数据库连接成功!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据库连接失败!");
        }
    }

}
