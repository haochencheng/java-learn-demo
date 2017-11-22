package pers.cc.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonUtil {

    public JSONArray resultSetToJson(ResultSet rs) throws Exception {
        ResultSetMetaData rsmd = rs.getMetaData();
        int coulmnCount = rsmd.getColumnCount();
        JSONArray jsonArray = new JSONArray();
        while (rs.next()) {
            JSONObject mapOfColValues = new JSONObject();
            for (int i = 1; i <= coulmnCount; i++) {
                Object o = rs.getObject(i);
                String columnName = rsmd.getColumnName(i);
                if (o instanceof Date) {
                    mapOfColValues.put(columnName,
                            DateUtil.formatDate((Date) o, "yyyy-MM-dd"));
                } else {
                    mapOfColValues.put(columnName, o);
                }

            }
            jsonArray.add(mapOfColValues);
        }
        return jsonArray;
    }

}
