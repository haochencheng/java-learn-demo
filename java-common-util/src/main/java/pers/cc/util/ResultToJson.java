package pers.cc.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 将sql查询结果result转为json工具类
 * 
 * @Title:
 * @Description:
 * @Author: cc
 * @Since:2017年7月19日
 * @Version:1.0.0
 */
public class ResultToJson {

    public static JSONArray ResultSetToJson(ResultSet rs) throws Exception {
        ResultSetMetaData rsmd = rs.getMetaData();
        int numberOfColumns = rsmd.getColumnCount();
        JSONArray jsonArray = new JSONArray();
        while (rs.next()) {
            JSONObject mapOfcolValues = new JSONObject();
            for (int i = 0; i <= numberOfColumns; i++) {
                Object o = rs.getObject(i);
                String columnName = rsmd.getColumnName(i);
                mapOfcolValues.put(columnName, o);
            }
            jsonArray.add(mapOfcolValues);
        }
        return jsonArray;
    }

}
