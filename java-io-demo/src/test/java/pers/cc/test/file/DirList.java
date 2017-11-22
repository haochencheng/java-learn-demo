package pers.cc.test.file;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class DirList {

    public static void main(String[] args) {
        File path = new File(
                "E:/workspace/InputOutDemo/src/main/java/pers/cc/test/file");
        String[] list;
        // list = path.list(new DirFilter("[^/\\\\]+.java$"));
        list = path.list(new DirFilter("[^*]+.java$"));
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String dirItem : list) {
            System.out.println(dirItem);
        }
    }

}

/**
 * 
 * @Title:
 * @Description:
 * @Author: cc
 * @Since:2017年7月18日
 * @Version:1.0.0
 */
class DirFilter implements FilenameFilter {

    private Pattern pattern;

    public DirFilter(String regex) {
        pattern = Pattern.compile(regex);
    }

    @Override
    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }

}