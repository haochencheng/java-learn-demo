package pers.cc.transfer.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.zip.GZIPOutputStream;

/**
 * java操作blob
 * 
 * @author cc
 *
 */
public class BlobUtil {

    private static PreparedStatement pres;
    private static final LoggerUtil log = LoggerUtil
            .getInstance(BlobUtil.class);

    // 写入blob数据
    public static void writeBlob(Connection conn) {
        try {
            conn.setAutoCommit(false); // 关闭自动提交
            String sql = "insert into test_blob values(?,?)"; // 插入语句

            pres = conn.prepareStatement(sql);
            pres.setInt(1, 1);
            pres.setBlob(2, oracle.sql.BLOB.getEmptyBLOB()); // 先插入空的BLOB，获取游标

            pres.executeUpdate();

            sql = "select bloba from test_blob where id=?";
            pres = conn.prepareStatement(sql);
            pres.setInt(1, 1); // 找出ID为1的，也就是刚刚插入的

            ResultSet res = pres.executeQuery();
            res.next();
            Blob imageBlob = res.getBlob(1); // 得到该空的blob

            OutputStream os = imageBlob.setBinaryStream(0);
            // 读取想要存储的图片文件
            InputStream is = new FileInputStream("/Users/cc/Desktop/a.jpg");
            // 依次读取流字节,并输出到已定义好的数据库字段中.
            int i = 0;
            while ((i = is.read()) != -1) {
                os.write(i); // Blob的输入流，相当于输入到数据库中
            }
            os.flush();
            os.close();
            conn.commit();
            conn.setAutoCommit(true);// 恢复现场
            is.close();
            res.close();
            if (pres != null)
                pres.close();
            log.info("插入成功!!!");
        } catch (Exception e) {
            log.info("插入失败!!!");
            e.printStackTrace();
        }

    }

    public void getImage(Connection conn) {
        String sql = "select photo from blobtest where id=?";

        try {
            pres = conn.prepareStatement(sql);
            pres.setInt(1, 1);
            ResultSet res = pres.executeQuery();
            while (res.next()) {
                Blob image = res.getBlob(1); // 得到该blob

                InputStream is = image.getBinaryStream(); // 获得该blob的输出流
                FileOutputStream fos = new FileOutputStream(
                        "E:\\outputImage.jpg");
                int i = 0;
                while ((i = is.read()) != -1) {
                    fos.write(i);
                }
                fos.flush();
                fos.close();
                is.close();
            }
            log.info("成功输出图片!!!");
        } catch (SQLException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // public static String convertBlobToString(Blob blob) {
    // try {
    //// return compress(byteToString(loadBlob(blob.getBinaryStream())));
    // return byteToString(loadBlob(blob.getBinaryStream()));
    // } catch (SQLException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // return null;
    // }

    public static String convertBlobToString(Blob blob) {
        String result = "";
        try {
            result = inputStreamTOString(blob.getBinaryStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    private final static int BUFFER_SIZE = 4096;

    /**
     * 将InputStream转换成String
     * 
     * @param in
     *            InputStream
     * @return String
     * @throws Exception
     * 
     */
    public static String inputStreamTOString(InputStream in) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;
        while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
            outStream.write(data, 0, count);

        data = null;
        return new String(outStream.toByteArray(), "ISO-8859-1");
    }

    public static byte[] blobToBytes(Blob blob) {
        BufferedInputStream is = null;
        try {
            is = new BufferedInputStream(blob.getBinaryStream());
            byte[] bytes = new byte[(int) blob.length()];
            int len = bytes.length;
            int offset = 0;
            int read = 0;
            while (offset < len
                    && (read = is.read(bytes, offset, len - offset)) >= 0) {
                offset += read;
            }
            return bytes;
        } catch (Exception e) {
            return null;
        } finally {
            try {
                is.close();
                is = null;
            } catch (IOException e) {
                return null;
            }
        }
    }

    // ========jon传图片的方法===============
    //// 我的方法分三个步骤：
    //
    // 1.将图片转为字节数组
    //
    // 2.将字节数组转为字符串
    //
    // 3.压缩字符串，放入json传输

    /**
     * 将图片转换为字节数组
     * 
     * @return
     */
    public static byte[] loadBlob(InputStream fin) {
        // 用于返回的字节数组
        byte[] data = null;
        // 打开字节输出流
        ByteArrayOutputStream bout = null;
        try {
            // 输出流定义缓冲区大小
            bout = new ByteArrayOutputStream(fin.available());
            // 定义字节数组，用于读取文件流
            byte[] buffer = new byte[1024];
            // 用于表示读取的位置
            int len = -1;
            // 开始读取文件
            while ((len = fin.read(buffer)) != -1) {
                // 从buffer的第0位置开始，读取至第len位置，结果写入bout
                bout.write(buffer, 0, len);
            }
            // 将输出流转为字节数组
            data = bout.toByteArray();
            // 关闭输入输出流
            fin.close();
            bout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 把字节数组转化为字符串----"ISO-8859-1"
     * 
     * @param date
     * @return
     */
    public static String byteToString(byte[] data) {
        String dataString = null;
        try {
            // 将字节数组转为字符串，编码格式为ISO-8859-1
            dataString = new String(data, "ISO-8859-1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataString;
    }

    /**
     * 压缩字符串----"ISO-8859-1"
     * 
     * @param data
     * @return
     */
    public static String compress(String data) {
        String finalData = null;
        try {
            // 打开字节输出流
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            // 打开压缩用的输出流,压缩后的结果放在bout中
            GZIPOutputStream gout = new GZIPOutputStream(bout);
            // 写入待压缩的字节数组
            gout.write(data.getBytes("ISO-8859-1"));
            // 完成压缩写入
            gout.finish();
            // 关闭输出流
            gout.close();
            finalData = bout.toString("ISO-8859-1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return finalData;
    }

}
