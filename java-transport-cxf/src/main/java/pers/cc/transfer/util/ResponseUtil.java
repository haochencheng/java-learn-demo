package pers.cc.transfer.util;

import com.alibaba.fastjson.JSONObject;

public class ResponseUtil {

    private static JSONObject result = new JSONObject();

    public static String error(String message) {
        result.put("state", "error");
        result.put("message", message);
        return result.toString();
    }

    public static String success(String message) {
        result.put("state", "success");
        result.put("message", message);
        return result.toString();
    }
}
