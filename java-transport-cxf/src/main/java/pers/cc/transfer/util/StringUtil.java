package pers.cc.transfer.util;

public class StringUtil {

    /**
     * 是否是空
     * 
     * @param str
     * @return 空返回true否则返回false
     * @Description:
     */
    public static boolean isEmpty(String str) {
        if ("".equals(str) || str == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 是否不是空
     * 
     * @param str
     * @return 不是空返回true,否则返回false
     * @Description:
     */
    public static boolean isNotEmpty(String str) {
        if (!"".equals(str) && str != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * str中是否存在strArr中的数据
     * 
     * @param str
     * @param strArr
     * @return 存在返回true,否则返回false
     * @Description:
     */
    public static boolean existstrArr(String str, String[] strArr) {
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].equals(str)) {
                return true;
            }
        }
        return false;
    }

}
