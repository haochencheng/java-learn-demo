package pers.spring.boot.demo.util;

import java.io.IOException;
import java.io.InputStream;

import net.iharder.Base64;

/**
 * Created by cc on 2017/11/20
 */
public class Base64Util {

	/**
	 * 图片转为base64.string
	 * 
	 * @parm param return
	 */
	public static String encode(InputStream in) throws IOException {
		byte[] data = new byte[in.available()];
		in.read(data);
		in.close();
		// 对字节数组Base64编码
		return Base64.encodeBytes(data);
	}

}
