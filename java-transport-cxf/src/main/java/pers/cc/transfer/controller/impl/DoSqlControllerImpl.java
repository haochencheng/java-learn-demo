package pers.cc.transfer.controller.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;

import pers.cc.transfer.connpool.DBConnectionManager;
import pers.cc.transfer.controller.DoSqlController;
import pers.cc.transfer.service.CommandService;
import pers.cc.transfer.util.LoggerUtil;
import pers.cc.transfer.util.ResponseUtil;
import pers.cc.transfer.util.ResultSetToJson;

@Component("doSqlController")
@WebService(endpointInterface = "com.thsoft.transfer.controller.DoSqlController")
public class DoSqlControllerImpl implements DoSqlController {

    /**
     * 程序开始时间
     */
    private long startTime;

    /**
     * 程序结束时间
     */
    private long endTime;

    @Resource
    private CommandService commandService;

    private final LoggerUtil log = LoggerUtil
            .getInstance(DoSqlControllerImpl.class);

    @Override
    public String excuteSql(String sql) {
        log.debug("开始时间:" + System.currentTimeMillis());
        log.debug("excuteSql:" + sql);
        if (sql == null || sql.equals("")) {
            ResponseUtil.error("执行的sql不可以为空！");
        }
        boolean excuteSqlFlag;
        try {
            excuteSqlFlag = commandService.excuteSql(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseUtil.error("执行sql失败！" + e.getMessage());
        }
        if (excuteSqlFlag) {

            log.debug("结束时间:" + System.currentTimeMillis());
            log.debug("总共耗时:" + String.valueOf(endTime - startTime));
            return ResponseUtil.success("执行成功！");
        }
        return null;
    }

    @Override
    public String getSqlResult(String sql) {
        log.debug("getSqlResult:" + sql);
        if (sql == null || sql.equals("")) {
            ResponseUtil.error("执行的sql不可以为空！");
        }
        ResultSet resultSet;
        try {
            resultSet = commandService.getResultSet(sql);
            if (resultSet == null || !resultSet.next()) {
                return ResponseUtil.success("");
            }
        } catch (SQLException e) {
            log.error("执行sql出错！" + e.getMessage());
            return ResponseUtil.error(e.getMessage());
        }
        JSONArray jsonArray;
        try {
            jsonArray = ResultSetToJson.toJson(resultSet);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtil.error("将查询结果转为为jsonArray出错，" + e.getMessage());
        }
        return ResponseUtil.success(jsonArray.toString());
    }

    public static void main(String[] args) throws Exception {
        DoSqlControllerImpl doSqlController = new DoSqlControllerImpl();
        DBConnectionManager connpool = DBConnectionManager.getInstance();
        System.out.println(
                doSqlController.getSqlResult("select * from act_ge_property"));
        // System.out.println(doSqlController.doSql("SELECT USERNAME,USERCODE
        // FROM PLATFORM_USER"));
        connpool.release();
        // Endpoint.publish("http://localhost:8080/Transmission/dosql", new
        // DoSqlController());
    }

}
