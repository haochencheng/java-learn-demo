package pers.cc.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class MD5Util {

    /**
     * @param str
     * @return md5加密后str
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @Description:
     */
    public static String EncoderPwdByMd5(String str)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        return base64en.encode(md5.digest(str.getBytes("utf-8")));
    }

    public static void main(String[] args)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        System.out.println(EncoderPwdByMd5("123"));

    }

}
