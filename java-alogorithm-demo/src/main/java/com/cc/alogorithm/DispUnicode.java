package com.cc.alogorithm;

/**
 * Created by cc on 2016/11/14.
 */
public class DispUnicode {

    public static void main(String[] args) {
        DispUnicode d = new DispUnicode();
        d.queryCoding("少年强 则中国强！");
    }

    private void queryCoding(String asArg) {
        System.out.println("用户传入的字符串是：" + asArg);
        System.out.println("计算得到汉字Unicode编码是：");
        // 依次去除每一个字符进行操作
        for (int i = 0; i < asArg.length(); i++) {
            // 判断是不是汉字，不是跳过
            char ch = asArg.charAt(i);
            if (ch < 19968 || ch > 40869) {
                continue;
            } else {
                System.out.print((int) ch + " ");
            }
        }
    }

}
