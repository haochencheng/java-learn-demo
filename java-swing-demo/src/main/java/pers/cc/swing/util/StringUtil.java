/**  
 * MDM主数据管理系统  
 * com.soa.mdm.util  
 * StringUtil.java  
 * 2017年1月16日-下午4:36:10  
 * 重庆斯欧信息技术股份有限公司-版权所有    
 */
package pers.cc.swing.util;

/**
 * 简单描述
 * 
 * @author cc
 * @createTime 2017年1月16日-下午4:36:10
 * @version 1.0.0
 */
public class StringUtil {

	public static void main(String[] args) {
		String str = "";
		System.out.println(StringUtil.isEmpty(str));
		;
	}

	/**
	 * 判断字符串是否为空，为空返回true，不为空返回false
	 * 
	 * @param str
	 * @return
	 *
	 * @author 郝晨成
	 * @Time 2017年2月21日 上午10:57:03
	 */
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str);
	}

	/**
	 * 判断字符串是否不为空，不为空返回true，为空返回false
	 * 
	 * @param str
	 * @return
	 *
	 * @author 郝晨成
	 * @Time 2017年2月21日 上午10:57:19
	 */
	public static boolean isNotEmpty(String str) {
		return str != null && !"".equals(str);
	}

}
