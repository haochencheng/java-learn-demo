/**  
 * MDM主数据管理系统  
 * com.soa.mdm  
 * ExcelFileFilter.java  
 * 2017年3月29日-下午1:25:56  
 * 重庆斯欧信息技术股份有限公司-版权所有    
 */
package pers.cc.swing;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * 选择excel过滤器
 * 
 * @author 郝晨成
 * @createTime 2017年3月29日-下午1:25:56
 * @version 1.0.0
 */
public class ExcelFileFilter extends FileFilter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
	 */
	@Override
	public boolean accept(File f) {
		String name = f.getName();
		return f.isDirectory() || name.toLowerCase().endsWith(".xls") || name.toLowerCase().endsWith(".xlsx");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.filechooser.FileFilter#getDescription()
	 */
	@Override
	public String getDescription() {
		return "*.xls;*.xlsx";
	}

}
