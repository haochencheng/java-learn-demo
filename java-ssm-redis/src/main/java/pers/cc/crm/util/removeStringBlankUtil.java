package pers.cc.crm.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class removeStringBlankUtil {

	private static String[] strs;
	private static Set<String> strList = new HashSet<>();

	public static void main(String[] args) {
		String str = "firstClassify	secondClassify	threeClassify	financialLdentifier	masterCode	codeDescribtion	lifeCycle	codeDate	groupCode	developmentDivision	unit	PLMApplicationNo	type	 category	picture	spliceWidthAndAssembleSize 	sectionSizeAndAssembleSize	lengthAndTotalSize	material	surfaceTreatment	customerPartNo	applicationDrawing	checkDrawing	testReport	 identify	otherAttachment	billNo	PIID	AIID	taskId	workNo	checkStatus			description	remark	creator	createtime	updatetime	version	extendOne	extendTwo	extendThree	extendFour	extendFive	extendFour	extendFive	firstClassify	secondClassify	threeClassify	financialLdentifier	masterCode	codeDescribtion	groupCode	lifeCycle	codeDate	developmentDivision	unit	PLMApplicationNo	type	 category	picture	spliceSize	assembleSize	totalSize	material	spliceMaterial	surfaceTreatment	spliceForce	customerPartNo	applicationDrawing	checkDrawing	testReport	remark	billNo	otherAttachment	PIID	AIID	taskId	workNo	checkStatus	description	remark	creator	createtime	updatetime	version	extendOne	extendTwo	extendThree	extendFour	extendFive	extendFour	extendFive	";

		/*
		 * String str2 = "firstClassify	secondClassify          firstClassify";
		 * String str = "abc dd  eee    ccc";
		 */
		/*
		 * StringBuffer sb = new StringBuffer(str); strs = removeTwoBlank(sb);
		 * removeTwo32(sb, 0); String[] strs = sb.toString().split("  ");
		 * 
		 * System.out.println(sb.toString());
		 * System.out.println(Arrays.toString(strs));
		 */
		System.out.println(subStringBlank(str).replaceAll(",", ""));
		System.out.println(removeBlank(str).replaceAll(",", ""));
	}

	/**
	 * 移除两个相邻的空格中的一个
	 * 
	 * @param sb
	 * @return
	 */
	public static String[] removeTwoBlank(StringBuffer sb) {
		int count = 0;
		int length = sb.length();
		List<Integer> intList = new ArrayList<>();
		for (int i = 0; i < length - 1; i++) {
			if (Character.isWhitespace(sb.charAt(i)) && Character.isWhitespace(sb.charAt(i + 1))) {
				intList.add(i);
			}
		}
		for (int in : intList) {
			sb.deleteCharAt(in - count);
			count++;
		}
		return sb.toString().split(" ");
	}

	public static void removeTwoBlank(StringBuffer sb, int start) {
		int length = sb.length();
		for (int i = start; i < (length - 1); i++) {
			if (Character.isWhitespace(sb.charAt(i)) && Character.isWhitespace(sb.charAt(i + 1))) {
				removeTwoBlank(sb.deleteCharAt(i), i);
				return;
			}
		}
	}

	public static String subStringBlank(String str) {
		int length = str.length();
		String newStr = "";
		String newTrimStr = "";
		for (int i = 0; i < length; i++) {
			if (Character.isWhitespace(str.charAt(i))) {
				newStr = str.substring(0, i);
				newTrimStr = str.substring(i, length - 1).trim();
				strList.add(newStr);
				subStringBlank(newTrimStr);
				return strList.toString();
			}
		}
		return null;
	}

	public static String removeBlank(String str) {
		strs = str.replaceAll("\\s+", " ").split("\\s");
		for (String s : strs) {
			strList.add(s);
		}
		return strList.toString();
	}

}
