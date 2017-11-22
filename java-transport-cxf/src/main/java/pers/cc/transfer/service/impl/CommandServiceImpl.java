package pers.cc.transfer.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import pers.cc.transfer.connpool.DBConnectionManager;
import pers.cc.transfer.service.CommandService;

@Service("commandService")
public class CommandServiceImpl implements CommandService {

    private DBConnectionManager connPool;

    /*
     * (non-Javadoc)
     * 
     * @see com.thsoft.transfer.service.CommandService#excute(java.lang.String)
     */
    @Override
    public boolean excuteSql(String sql) throws SQLException {
        ResultSet resultSet = getResultSet(sql);
        if (resultSet != null) {
            return true;
        }
        return false;
    }

    @Override
    public ResultSet getResultSet(String sql) throws SQLException {
        if (connPool == null) {
            connPool = DBConnectionManager.getInstance();
        }
        Connection conn = connPool.getConnection("mysql");
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet resultSet = pstmt.executeQuery();
        connPool.freeConnection("mysql", conn);
        return resultSet;
    }

}
