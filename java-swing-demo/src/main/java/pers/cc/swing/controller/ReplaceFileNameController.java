/**  
 * MDM主数据管理系统  
 * com.soa.mdm.controller  
 * ReplaceFileName.java  
 * 2017年3月29日-下午7:16:05  
 * 重庆斯欧信息技术股份有限公司-版权所有    
 */
package pers.cc.swing.controller;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 批量替换文件名
 * 
 * @author 郝晨成
 * @createTime 2017年3月29日-下午7:16:05
 * @version 1.0.0
 */
public class ReplaceFileNameController {

	public boolean replaceFileName(String filePath, String param) {
		boolean flag = false;
		flag = traverseFolder(filePath, param);
		return flag;

	}

	/**
	 * 递归遍历文件夹,如果是js文件，根据excel配置文件替换其中的内容
	 * 
	 * @param path
	 *
	 * @author 郝晨成
	 * @Time 2017年3月29日 下午2:54:37
	 */
	private boolean traverseFolder(String filePath, String param) {
		boolean flag = false;
		File file = new File(filePath);
		if (file.exists()) {
			File[] files = file.listFiles();
			if (files.length == 0) {
				System.out.println("文件夹是空的!");
				return flag;
			} else {
				for (File file2 : files) {
					if (file2.isDirectory()) {
						System.out.println("文件夹:" + file2.getAbsolutePath());
						flag = traverseFolder(file2.getAbsolutePath(), param);
						if (!flag) {
							// System.out.println("递归遍历文件夹出错" + flag);
							return flag;
						}
					} else {
						System.out.println("文件:" + file2.getAbsolutePath());
						// 正则表达式匹配js文件
						Pattern p = Pattern.compile(".*\\.js");
						Matcher m = p.matcher(file2.getName());
						if (m.matches()) {
							// 读取excel替换其中的内容
							flag = replace(file2.getAbsolutePath(), param);
							if (!flag) {
								System.out.println("更加excel替换JS出错！" + flag);
								return flag;
							}
						}
					}
				}
			}
		} else {
			System.out.println("文件不存在!");
			return flag;
		}
		return flag;
	}

	/**
	 * 重命名
	 * 
	 * @param path
	 *            源文件路径
	 * @param param
	 *            改名追缴参数
	 * @return 改名后
	 *
	 * @author 郝晨成
	 * @Time 2017年3月29日 下午7:49:52
	 */
	private boolean replace(String path, String param) {
		File file = new File(path);
		String fileName = path.replaceAll(".js", "");
		if (file.renameTo(new File(fileName + param + ".js"))) {
			return true;
		} else {
			return false;
		}
	}

}
