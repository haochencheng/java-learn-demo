package pers.cc.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.imageio.ImageIO;

import org.springframework.util.Base64Utils;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

/**
 * Created by cc on 2017/7/28.
 */
public class TransferController {

	public static void main(String[] args) throws Exception {
		// 读取文件base64转码压缩为string
		byte[] data = compress(loadFile("C:\\Users\\Administrator\\Desktop\\a.jpg"));
		String json = new String(Base64Utils.encode(data));
		System.out.println("data length:" + json.length());
		System.out.println(json);
		// 将str输出为txt文件
		writeStringToTxt(json, "C:\\Users\\Administrator\\Desktop\\a.txt");
		// 读取txt文件恢复为文件
		txtToJpg("C:\\Users\\Administrator\\Desktop\\a.txt", "C:\\Users\\Administrator\\Desktop\\b.jpg");
	}

	/**
	 * @param strPath
	 *            txt路径
	 * @param jpgPath
	 *            jpg路径
	 */
	public static void txtToJpg(String strPath, String jpgPath) {
		BufferedImage bi = null;
		byte[] bytes = Base64Utils.decode(loadFile(strPath));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ByteArrayInputStream bin = null;
		byte[] newData = null;
		File file = new File(jpgPath);
		try {
			bin = new ByteArrayInputStream(bytes);
			// 解压缩
			GZIPInputStream gunzip = new GZIPInputStream(bin);
			byte[] buffer = new byte[256];
			int n;
			while ((n = gunzip.read(buffer)) >= 0) {
				baos.write(buffer, 0, n);
			}
			gunzip.close();
			newData = baos.toByteArray();
			// 市场图片
			bin = new ByteArrayInputStream(newData);
			bi = ImageIO.read(bin);
			ImageIO.write(bi, "jpg", file);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * @param str
	 *            写入字符串
	 * @param path
	 *            文件路径
	 */
	public static void writeStringToTxt(String str, String path) {
		PrintStream ps = null;
		BufferedOutputStream bufferedOutputStream = null;
		try {
			bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(path)));
			ps = new PrintStream(bufferedOutputStream);
			ps.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (bufferedOutputStream != null)
					bufferedOutputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 加载本地文件,并转换为byte数组
	 *
	 * @return
	 */
	public static byte[] loadFile(String path) {
		File file = new File(path);
		FileInputStream fis = null;
		BufferedInputStream bufferedInputStream;
		ByteArrayOutputStream byteArrayOutputStream = null;
		byte[] data = null;
		try {
			fis = new FileInputStream(file);
			bufferedInputStream = new BufferedInputStream(fis);
			byteArrayOutputStream = new ByteArrayOutputStream((int) file.length());
			byte[] buffer = new byte[1024];
			int len;
			while ((len = bufferedInputStream.read(buffer)) != -1) {
				byteArrayOutputStream.write(buffer, 0, len);
			}
			data = byteArrayOutputStream.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (byteArrayOutputStream != null) {
					byteArrayOutputStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return data;
	}

	/**
	 * 对byte[]进行压缩
	 *
	 * @param data
	 *            要压缩的数据
	 * @return newData 压缩后的数据
	 */
	public static byte[] compress(byte[] data) {
		System.out.println("before:" + data.length);
		GZIPOutputStream gzip = null;
		ByteOutputStream byteOutputStream;
		byte[] newData = null;
		byteOutputStream = new ByteOutputStream();
		try {
			gzip = new GZIPOutputStream(byteOutputStream);
			gzip.write(data);
			gzip.finish();
			newData = byteOutputStream.getBytes();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (gzip != null) {
					gzip.close();
				}
				byteOutputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (newData != null)
			System.out.println("after:" + newData.length);
		return newData;
	}
}