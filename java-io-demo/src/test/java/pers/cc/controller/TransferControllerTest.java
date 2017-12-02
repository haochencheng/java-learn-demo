package pers.cc.controller;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * TransferController Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since
 * 
 *        <pre>
 * ���� 28, 2017
 *        </pre>
 */
public class TransferControllerTest {

	@Before
	public void before() throws Exception {
	}

	@After
	public void after() throws Exception {
	}

	/**
	 * Method: main(String[] args)
	 */
	@Test
	public void writeJpgToTxt() throws Exception {
		InputStream in = new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\a.jpg"));
		OutputStream out = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\a.txt"));
		byte[] bytes = new byte[1024];
		int len;
		while ((len = in.read(bytes)) != -1) {
			out.write(bytes, 0, len);
		}
		out.close();
		in.close();
	}

	@Test
	public void transferTxtToJpg() throws Exception {
		BufferedImage image = ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\a.txt"));
		ImageIO.write(image, "png", new File("C:\\Users\\Administrator\\Desktop\\b.jpg"));
	}

	@Test
	public void jpgToString() throws Exception {
		File file = new File("C:\\Users\\Administrator\\Desktop\\a.jpg");
		FileInputStream in = new FileInputStream(file);
		DataInputStream dataIn = new DataInputStream(in);

		in.close();
	}

	@Test
	public void jpgToStringWithBase64() throws Exception {

	}

}
