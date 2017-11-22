package pers.cc.test;

public class DbUtil {

    public boolean getConn() throws Exception {
        Class.forName("");
        // DriverManager.getConnection(url, user, password);
        return true;
    }

}
