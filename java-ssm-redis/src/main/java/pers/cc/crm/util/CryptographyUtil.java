package pers.cc.crm.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 加密工具 Created by cc on 2016/11/20.
 */
public class CryptographyUtil {

    public static String md5(String str, String salt) {
        return new Md5Hash(str, salt).toString();
    }

    public static void main(String[] args) {
        System.out.println(CryptographyUtil.md5("chencheng", "cc"));
    }
}
