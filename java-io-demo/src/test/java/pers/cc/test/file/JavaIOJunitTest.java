package pers.cc.test.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.junit.Test;

public class JavaIOJunitTest {

    @Test
    public void getPathByThread() {
        System.out.println(
                Thread.currentThread().getContextClassLoader().getResource("")
                        + this.getClass().getName());
    }

    @Test
    public void getPathByClass() {
        Class<?> cls = this.getClass();
        // 检查用户传入的参数是否为空
        if (cls == null)
            throw new java.lang.IllegalArgumentException("参数不能为空！");
        ClassLoader loader = cls.getClassLoader();
        // 获得类的全名，包括包名
        String clsName = cls.getName() + ".class";
        // 获得传入参数所在的包
        Package pack = cls.getPackage();
        String path = "";
        // 如果不是匿名包，将包名转化为路径
        if (pack != null) {
            String packName = pack.getName();
            // 此处简单判定是否是Java基础类库，防止用户传入JDK内置的类库
            if (packName.startsWith("java.") || packName.startsWith("javax."))
                throw new java.lang.IllegalArgumentException("不要传送系统类！");
            // 在类的名称中，去掉包名的部分，获得类的文件名
            clsName = clsName.substring(packName.length() + 1);
            // 判定包名是否是简单包名，如果是，则直接将包名转换为路径，
            if (packName.indexOf(".") < 0)
                path = packName + "/";
            else {// 否则按照包名的组成部分，将包名转换为路径
                int start = 0, end = 0;
                end = packName.indexOf(".");
                while (end != -1) {
                    path = path + packName.substring(start, end) + "/";
                    start = end + 1;
                    end = packName.indexOf(".", start);
                }
                path = path + packName.substring(start) + "/";
            }
        }
        // 调用ClassLoader的getResource方法，传入包含路径信息的类文件名
        java.net.URL url = loader.getResource(path + clsName);
        // 从URL对象中获取路径信息
        String realPath = url.getPath();
        // 去掉路径信息中的协议名"file:"
        int pos = realPath.indexOf("file:");
        if (pos > -1)
            realPath = realPath.substring(pos + 5);
        // 去掉路径信息最后包含类文件信息的部分，得到类所在的路径
        pos = realPath.indexOf(path + clsName);
        realPath = realPath.substring(0, pos - 1);
        // 如果类文件被打包到JAR等文件中时，去掉对应的JAR等打包文件名
        if (realPath.endsWith("!"))
            realPath = realPath.substring(0, realPath.lastIndexOf("/"));
        /*------------------------------------------------------------ 
        ClassLoader的getResource方法使用了utf-8对路径信息进行了编码，当路径 
         中存在中文和空格时，他会对这些字符进行转换，这样，得到的往往不是我们想要 
         的真实路径，在此，调用了URLDecoder的decode方法进行解码，以便得到原始的 
         中文及空格路径 
         -------------------------------------------------------------*/
        try {
            realPath = java.net.URLDecoder.decode(realPath, "utf-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // return realPath;
        System.out.println(realPath);
    }

    /**
     * 输出文件到控制台
     * 
     * @Description:
     */
    @Test
    public void bufferReadInputFile() throws Exception {
        String fileName = "src/main/java/pers/cc/test/JavaIOJunitTest.java";
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = in.readLine()) != null) {
            sb.append(s + "/n");
        }
        in.close();
        System.out.println(sb.toString());
    }

    /**
     * 读取文件输出到制定目录
     * 
     * @throws Exception
     * @Description:
     */
    @Test
    public void basicFileOutPut() throws Exception {
        String fileName = "C:/Users/Administrator/Desktop/JavaIOJunitTest.java";
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile(); // 不存在穿件文件
        } else {
            file.delete(); // 存在删除文件
        }
        BufferedReader in = new BufferedReader(new FileReader(
                "src/main/java/pers/cc/test/JavaIOJunitTest.java"));
        String s;
        int lineCount = -1;
        StringBuilder sb = new StringBuilder();
        PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter(fileName)));
        while ((s = in.readLine()) != null) {
            out.print(lineCount++ + ":" + s + "\n");
        }
        in.close();
        out.close();
        System.out.println(sb.toString());
    }

}
