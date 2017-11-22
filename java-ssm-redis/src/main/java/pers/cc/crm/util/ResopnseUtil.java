package pers.cc.crm.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by cc on 2016/12/8.
 */
public class ResopnseUtil {

	/**
	 * 输出响应工具类
	 * 
	 * @param response
	 * @param o
	 */
	public static void write(HttpServletResponse response, Object o) {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.println(o.toString());
			out.flush();
			out.close();
		}

	}

}
