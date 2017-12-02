package pers.spring.boot.demo.test.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

/**
 * Created by cc on 2017/11/2
 */
public class IOcharsetTest {

	public static final String FILE = "C:\\Users\\Administrator\\Desktop\\work\\write.txt";

	public static void main(String[] args) throws IOException {
		// write();
		read();

	}

	private static void write() throws IOException {
		String charset = "UTF-8";
		// 写字符转换成字节流
		FileOutputStream fileOutputStream = new FileOutputStream(FILE);
		OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream);
		try {
			writer.write("这是要保存的字符");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}

	private static void read() throws IOException {
		FileInputStream inputStream = new FileInputStream(FILE);
		InputStreamReader reader = new InputStreamReader(inputStream);
		StringBuilder buffer = new StringBuilder();
		char[] buf = new char[64];
		int count = 0;
		try {
			while ((count = reader.read()) != -1) {
				buffer.append(count);
			}
			Charset charset = Charset.forName("UTF-8");
			System.out.println(buffer.toString());
		} finally {
			reader.close();
		}
	}

}
