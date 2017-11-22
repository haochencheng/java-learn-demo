package pers.cc.transfer.service;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * WebService接口
 */
public interface CommandService {

    /**
     * @param sql
     *            执行sql
     */
    public ResultSet getResultSet(String sql) throws SQLException;

    public boolean excuteSql(String sql) throws SQLException;

}
