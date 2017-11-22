package pers.cc.transfer.util;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import net.iharder.Base64;

/**
 * 将sql查询结果result转为json工具类
 * 
 * @Title:
 * @Description:
 * @Author: cc
 * @Since:2017年7月19日
 * @Version:1.0.0
 */
public class ResultSetToJson {

    public static JSONArray toJson(ResultSet rs) throws Exception {
        ResultSetMetaData rsmd = rs.getMetaData();
        int numberOfColumns = rsmd.getColumnCount();
        JSONArray jsonArray = new JSONArray();
        while (rs.next()) {
            JSONObject mapOfcolValues = new JSONObject();
            for (int i = 1; i <= numberOfColumns; i++) {
                Object o = null;
                if ("BLOB".equals(rs.getMetaData().getColumnTypeName(i))) {
                    Blob blob = rs.getBlob(i);
                    // 转化blob为string并压缩
                    byte[] data = BlobToStringUtil
                            .compress(BlobToStringUtil.loadBlob(blob));
                    String s = new String(Base64.encodeBytes(data));
                    o = s;
                    // System.out.println(s);
                    // File file = new File("/Users/cc/Desktop/a.txt");
                    // PrintStream ps = new PrintStream(new
                    // FileOutputStream(file));
                    // ps.print(s);
                    // ps.flush();
                    // ps.close();
                } else {
                    o = rs.getObject(i);
                }
                String columnName = rsmd.getColumnName(i);
                mapOfcolValues.put(columnName, o);
            }
            jsonArray.add(mapOfcolValues);
        }
        return jsonArray;
    }

}
