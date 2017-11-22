package com.cc.algorithm;

/**
 * Created by cc on 2016/11/13.
 * 打印字符的ACSII码
 */
public class PrintAscii {

    public static void main(String[] args) {
        PrintAscii obj=new PrintAscii();
        obj.dispAscii('0');
        obj.dispAscii('9');
        obj.dispAscii('A');
        obj.dispAscii('Z');
        obj.dispAscii('a');
        obj.dispAscii('z');
    }

    public void dispAscii(char ch){
        int iTmp=(char)ch;
        System.out.println(ch+"的Ascii码是："+iTmp);
    }

}
