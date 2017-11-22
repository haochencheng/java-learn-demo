package pers.cc.transfer.controller;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface DoSqlController {

    String excuteSql(@WebParam(name = "sql") String sql);

    String getSqlResult(@WebParam(name = "sql") String sql);

}
