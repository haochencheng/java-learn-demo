/**  
 * MDM主数据管理系统  
 * com.soa.mdm.test  
 * UnitTest.java  
 * 2017年3月29日-下午3:06:15  
 * 重庆斯欧信息技术股份有限公司-版权所有    
 */
package pers.cc.swing.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import pers.cc.swing.controller.ReplaceTextController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 简单描述
 * 
 * @author 郝晨成
 * @createTime 2017年3月29日-下午3:06:15
 * @version 1.0.0
 */
public class UnitTest {

	/**
	 * @throws java.lang.Exception
	 *
	 * @author 郝晨成
	 * @Time 2017年3月29日 下午3:06:15
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 *
	 * @author 郝晨成
	 * @Time 2017年3月29日 下午3:06:15
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * 正则表达式测试
	 *
	 * @author 郝晨成
	 * @Time 2017年3月29日 下午3:13:10
	 */
	@Test
	public void regTest() {
		Pattern p = Pattern.compile(".*\\.js");
		Matcher m = p.matcher("aa.jsa");
		System.out.println(m.matches());
	}

	/**
	 * 测试遍历excel
	 *
	 * @author 郝晨成
	 * @Time 2017年3月29日 下午3:13:10
	 */
	@Test
	public void excelTest() {
		ReplaceTextController replace = new ReplaceTextController();
	}

}
