/**
 * 
 */
package pers.cc.crm.util;

/**
 * @author cc
 * @todo  
 * @version 1.0.0
 * @date 2016��11��8��
 * @company china-soa
 */
public class StringUtil {
	
	public static void main(String[] args) {
		String str="   ";
		System.out.println(StringUtil.isNotEmpty(str));
	}
	
	/**
	 * �ж��ַ����Ƿ�Ϊ��
	 * @param str
	 * @return ��Ϊ�շ���true��Ϊ�շ���false
	 */
	public static boolean isEmpty(String str) {
		return "".equals(str) || str==null;
	}
	
	/**
	 * �ж��ַ����Ƿ�Ϊ��
	 * @param str
	 * @return ��Ϊ�� ����true��Ϊ�շ���false
	 */
	public static boolean isNotEmpty(String str) {
		return !"".equals(str) && str!=null;
	}
	
	/**
	 * @param str
	 * @param strArr
	 * @return ��
	 */
	public static boolean existstrArr(String str,String []strArr){
	    for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].equals(str)) {
                return true;
            }
        }
	    return false;
	}
	
}
