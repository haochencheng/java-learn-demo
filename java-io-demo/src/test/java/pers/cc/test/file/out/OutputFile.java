package pers.cc.test.file.out;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * 根据路径输出文件
 * 
 * @Title:
 * @Description:
 * @Author: cc
 * @Since:2017年7月19日
 * @Version:1.0.0
 */
public class OutputFile {

    static class Test {
        public static void main(String[] args) throws Exception {
            StoringAndRecoveringData();
        }
    }

    public static void main(String[] args) throws Exception {
        outPutFileByFilePath(
                "src/main/java/pers/cc/test/file/out/OutputFile.java",
                "C:/Users/Administrator/Desktop/OutputFile.java");
    }

    /**
     * @param filePath
     *            文件路径(包含文件名)
     * @param newFilePath
     *            输出文件路径
     * @Description:
     */
    public static void outPutFileByFilePath(String filePath, String newFilePath)
            throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(filePath));
        PrintWriter out = new PrintWriter(newFilePath);
        int lineCount = -1;
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = in.readLine()) != null) {
            out.print(lineCount + ":" + s + "\n");
            sb.append(s + "\n");
        }
        System.out.println(sb.toString());
        out.close();
        in.close();
    }

    /**
     * 
     * @Description:
     */
    public static void StoringAndRecoveringData() throws Exception {
        DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(
                        "C:/Users/Administrator/Desktop/Data.txt")));
        out.writeDouble(3.145);
        out.writeUTF("That was pi");
        out.close();
        DataInputStream in = new DataInputStream(
                new BufferedInputStream(new FileInputStream(
                        "C:/Users/Administrator/Desktop/Data.txt")));
        System.out.println(in.readDouble());
        System.out.println(in.readUTF());
        in.close();
    }

}
