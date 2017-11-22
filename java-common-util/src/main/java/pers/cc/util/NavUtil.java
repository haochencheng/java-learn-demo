package pers.cc.util;

public class NavUtil {

    public static String genNewManageNavigation(String modName,
            String actionName) throws Exception {
        StringBuffer navcode = new StringBuffer();
        navcode.append("当前位置：&nbsp;&nbsp;");
        navcode.append("主页&nbsp;>&nbsp;>&nbsp;&nbsp;");
        navcode.append(modName + "&nbsp;>&nbsp;>&nbsp;&nbsp;");
        navcode.append(actionName + "&nbsp;>&nbsp;");
        return navcode.toString();
    }

    public static String genNewsListnavigation(String typeName, String typeId)
            throws Exception {
        StringBuffer navcode = new StringBuffer();
        navcode.append("当前位置：&nbsp;&nbsp;");
        navcode.append("<a href='goIndex'>主页</a>&nbsp;>&nbsp;");
        navcode.append("<a href='news?action=list&typeId=" + typeId + "'>"
                + typeName + "</a>");
        return navcode.toString();
    }

    public static String genNewsNavigation(String typeName, int typeId,
            String newsName) throws Exception {
        StringBuffer navcode = new StringBuffer();
        navcode.append("当前位置：&nbsp;&nbsp;");
        navcode.append("<a href='goIndex'>主页</a>&nbsp;>&nbsp;");
        navcode.append("<a href='news?action=list&typeId=" + typeId + "'>"
                + typeName + "</a>&nbsp;>&nbsp;" + newsName);
        return navcode.toString();
    }

}
