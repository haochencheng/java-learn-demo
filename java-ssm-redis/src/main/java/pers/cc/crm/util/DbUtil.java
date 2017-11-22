package pers.cc.crm.util;

import java.sql.DriverManager;

/**
 * Created by cc on 2016/11/22.
 */
public class DbUtil {

	public static void getCon() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/db_crm?&useUnicode=true&characterEncoding=utf8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false";
		String username = "root";
		String password = "niubidecc.";
		DriverManager.getConnection(url, username, password);
	}

	public static void main(String[] args) {
		try {
			DbUtil.getCon();
			System.out.println("success!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error!");
		}
	}
}
