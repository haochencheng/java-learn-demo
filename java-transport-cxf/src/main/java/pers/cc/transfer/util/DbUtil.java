package pers.cc.transfer.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;

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

    public static final String POOL_NAME = "mysql";

    /**
     * 连接数据库,需在配置文件中定义连接数据库信息
     * 
     * @return 数据库连接
     * @throws Exception
     * @Description:
     */
    public static Connection getCon() throws Exception {

        Connection con = DriverManager.getConnection(
                PropertiesUtil.getValue(
                        PropertiesUtil.getProperty(POOL_NAME + ".url")),
                PropertiesUtil.getValue(
                        PropertiesUtil.getProperty(POOL_NAME + ".user")),
                PropertiesUtil.getValue(
                        PropertiesUtil.getProperty(POOL_NAME + ".password")));
        return con;
    }

    /**
     * 关闭数据库连接
     * 
     * @param con
     * @throws Exception
     * @Description:
     */
    public static final void closeCon(final Connection con) throws Exception {
        if (con != null) {
            con.close();
        }
    }

    public static void main(String[] args) throws Exception {
        DriverManager.registerDriver(
                (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/db_activiti?characterEncoding=UTF-8&useSSL=false",
                "root", "niubidecc.");
        System.out.println("数据库连接成功!");
        java.sql.Statement statement = conn.createStatement();
        ResultSet resultSet = statement
                .executeQuery("select * from act_ge_property");
        System.out.println(resultSet.getString(1));
        conn.close();
    }

}
