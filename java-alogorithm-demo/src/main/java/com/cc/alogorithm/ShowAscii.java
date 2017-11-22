package com.cc.alogorithm;

/**
 * Created by cc on 2016/11/14. 显示ASCII码位于32~126之间的95个字符显示在屏幕上。
 * 小于100的码值前填充一个0，每打印8个换行
 */
public class ShowAscii {

    public static void main(String[] args) {
        String temp;
        for (int i = 0; i < (126 - 32) / 8 + 1; i++) {
            int j = 32 + 8 * i;
            int end = 40 + 8 * i;
            for (; j < end; j++) {
                temp = j < 100 ? ("0" + j) : ("" + j);
                if (j < 127)
                    System.out.print(temp + "=" + (char) j + " ");
            }
            System.out.println();
        }
    }

}
