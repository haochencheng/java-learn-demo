package pers.cc.test.file;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class DirListInnerClassDemo {

    public static void main(String[] args) {
        File path = new File(
                "E:/workspace/InputOutDemo/src/main/java/pers/cc/test/file");
        String[] list = path.list(new FilenameFilter() {
            private Pattern pattern = Pattern.compile("[^*]+.java$");

            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        });
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String dirItem : list) {
            System.out.println(dirItem);
        }
    }

}
