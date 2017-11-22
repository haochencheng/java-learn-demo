package pers.cc.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * 前台页面响应输出数据
 * 
 * @Title:
 * @Description:
 * @Author: cc
 * @Since:2017年7月19日
 * @Version:1.0.0
 */
public class ResponseUtil {

    public static void write(HttpServletResponse response, Object o)
            throws Exception {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(o.toString());
        out.flush();
        out.close();
    }

}
