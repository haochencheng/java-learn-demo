/**  
 * MDM主数据管理系统  
 * com.soa.mdm.controller  
 * ReplaceTextController.java  
 * 2017年3月29日-下午2:40:35  
 * 重庆斯欧信息技术股份有限公司-版权所有    
 */
package pers.cc.swing.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import pers.cc.swing.util.StringUtil;

/**
 * 替换文本内容控制层
 * 
 * @author 郝晨成
 * @createTime 2017年3月29日-下午2:40:35
 * @version 1.0.0
 */
public class ReplaceTextController {

	/**
	 * encoding:设置文件编码UTF-8
	 */
	private static final String encoding = "UTF-8";

	/**
	 * 更加excel替换文件夹中的内容控制层
	 * 
	 * @param replacePath
	 * @param excelPath
	 * @return
	 *
	 * @author 郝晨成
	 * @Time 2017年3月29日 下午2:56:48
	 */
	public boolean replace(String replacePath, String excelPath) throws Exception {
		boolean flag = false;
		flag = isSameFileName(replacePath, excelPath);
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
	private List<String> traverseFolder(String path, String fileName) {
		List<String> resultList = new ArrayList<String>();
		File file = new File(path);
		if (file.exists()) {
			File[] files = file.listFiles();
			if (files.length == 0) {
				// System.out.println("文件夹是空的!");
				return resultList;
			} else {
				for (File file2 : files) {
					if (file2.isDirectory()) {
						// System.out.println("文件夹:" + file2.getAbsolutePath());
						resultList = traverseFolder(file2.getAbsolutePath(), fileName);
						return resultList;
					} else {
						// System.out.println("文件:" + file2.getAbsolutePath());
						// 正则表达式匹配js文件
						Pattern p = Pattern.compile(".*\\.js");
						Matcher m = p.matcher(file2.getName());
						if (m.matches()) {
							if (fileName.equals(file2.getName().replaceAll(".js", ""))) {
								resultList.add(file2.getAbsolutePath());
							}
						}
					}
				}
			}
		} else {
			// System.out.println("文件不存在!");
			return resultList;
		}
		return resultList;
	}

	private boolean isSameFileName(String filePath, String excelPath) throws Exception {
		boolean flag = false;
		Workbook wb = null;
		wb = WorkbookFactory.create(new File(excelPath));
		Sheet sheet = wb.getSheet("目录");
		for (Row row : sheet) {
			String jsName = getCellContent(row.getCell(0));
			if (!"文件名".equals(jsName)) {
				List<String> replaceList = traverseFolder(filePath, jsName);
				if (!replaceList.isEmpty()) {
					for (String pathStr : replaceList) {
						// 读取excel替换其中的内容
						flag = replaceJSContentWithExcel(pathStr, jsName, wb);
					}
				}
			}
		}
		wb.close();
		return flag;
	}

	/**
	 * 替换js内容
	 * 
	 * @param file
	 * @param excelPath
	 * @return
	 *
	 * @author 郝晨成
	 * @Time 2017年3月29日 下午4:14:11
	 */
	public boolean replaceJSContentWithExcel(String filePath, String sheetName, Workbook wb) throws Exception {
		boolean flag = false;
		String strTemp = "";
		Sheet sheet = wb.getSheet(sheetName);
		// // 创建文件输入流
		// File file = new File(filePath);
		// FileReader fis = new FileReader(file);
		// // 替换js文本
		// char[] data = new char[10240];// 创建缓冲字符数组
		// int rn = 0;
		// StringBuilder sb = new StringBuilder();// 创建字符串构建器
		// while ((rn = fis.read(data)) > 0) {// 读取文件内容到字符串构建器
		// String str = String.valueOf(data, 0, rn);
		// sb.append(str);
		// }
		// // System.out.println("替换前JS文件中的" + sb.toString());
		// strTemp = sb.toString();
		// fis.close();
		strTemp = read(filePath, encoding);
		for (Row row : sheet) {
			if (row.getCell(0) == null || row.getCell(1) == null) {
				break;
			} else if (StringUtil.isEmpty(row.getCell(0).getStringCellValue().toString())
					|| StringUtil.isEmpty(row.getCell(1).getStringCellValue().toString())) {
				break;
			}
			// 关闭输入流
			String oldText = getCellContent(row.getCell(0));
			String newText = getCellContent(row.getCell(1));
			System.out.println(oldText + "-----------" + newText);
			// 如果存在就替换

			if (strTemp.contains(oldText) && !"替换文本".equals(oldText)) {
				// 从构建器中生成字符串，并替换搜索文本
				strTemp = strTemp.replace(oldText, newText);
			}
		}
		write(filePath, strTemp, encoding);
		// FileOutputStream fos = new FileOutputStream(file);
		// OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
		// osw.write(strTemp);
		// osw.flush();
		// osw.close();
		//
		// // 创建文件输出流
		// FileWriter fout = new FileWriter(file);
		// // 把替换完成的字符串写入文件内
		// fout.write(strTemp);
		// fout.close();// 关闭输出流
		wb.close();
		flag = true;
		return flag;
	}

	private String getCellContent(Cell cell) {
		String str = "";
		switch (cell.getCellTypeEnum()) {
		case STRING:
			str = cell.getRichStringCellValue().getString();
			break;
		case NUMERIC:
			str = String.valueOf((int) cell.getNumericCellValue());
			break;
		case BOOLEAN:
			if (cell.getBooleanCellValue()) {
				str = "true";
			} else {
				str = "false";
			}
			break;
		case FORMULA:
			str = cell.getCellFormula();
			break;
		case BLANK:
			break;
		default:
			str = "";
		}
		return str;
	}

	public static void write(String path, String content, String encoding) throws IOException {
		File file = new File(path);
		file.delete();
		file.createNewFile();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encoding));
		writer.write(content);
		writer.close();
	}

	public static String read(String path, String encoding) throws IOException {
		StringBuilder content = new StringBuilder();
		File file = new File(path);
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
		String line = null;
		while ((line = reader.readLine()) != null) {
			content.append(line + "\n");
		}
		reader.close();
		return content.toString();
	}

}
