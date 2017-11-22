package pers.cc.transfer.connpool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;

import javax.sql.rowset.serial.SerialBlob;

import org.junit.Test;

import pers.cc.transfer.controller.impl.DoSqlControllerImpl;
import pers.cc.transfer.util.BlobUtil;
import pers.cc.transfer.util.InputStreamUtil;
import pers.cc.transfer.util.LoggerUtil;

public class ConnPoolTest {

    DBConnectionManager connPool;

    /**
     * 创建连接池
     *
     * @throws Exception
     */
    @Test
    public void createConnPool() throws Exception {
        connPool = DBConnectionManager.getInstance();
        connPool.getConnection("oracle");
        System.out.println(
                "链接池数量：" + DBConnectionManager.getConnPoolSize("oracle"));
    }

    /**
     * 插入blob文件到数据库
     *
     * @throws Exception
     */
    @Test
    public void insertBlob() throws Exception {
        if (connPool == null) {
            connPool = DBConnectionManager.getInstance();
        }
        Connection conn = connPool.getConnection("oracle");
        BlobUtil.writeBlob(conn);
        connPool.release();
    }

    /**
     * 关闭连接池
     *
     * @throws Exception
     */
    @Test
    public void closeConnPool() throws Exception {
        if (connPool != null) {
            connPool.release();
        }
    }

    /**
     * 测试日志文件
     *
     * @throws Exception
     */
    @Test
    public void loggerTest() throws Exception {
        LoggerUtil log = LoggerUtil.getInstance(ConnPoolTest.class);
        log.debug("cc");
    }

    /**
     * 测试dosql
     *
     * @throws Exception
     */
    @Test
    public void doSqlTest() throws Exception {
        DoSqlControllerImpl doSqlController = new DoSqlControllerImpl();
        if (connPool == null) {
            connPool = DBConnectionManager.getInstance();
        }
        // String result = doSqlController.doSql("SELECT * FROM TEST_BLOB");
        // System.out.println(result);
        // System.out.println(doSqlController
        // .getSqlResult("SELECT USERNAME,USERCODE FROM PLATFORM_USER where
        // rownum <=3
        // order by rownum asc"));
        System.out.println(doSqlController.getSqlResult(
                "SELECT USERNAME,USERCODE FROM PLATFORM_USER where USERCODE ='1'"));
        connPool.release();
    }

    /**
     * 读取测试文本a.txt
     *
     * @throws Exception
     */
    @Test
    public void readTxtTest() throws Exception {
        File file = new File("/Users/cc/Desktop/a.txt");
        InputStream in = new FileInputStream(file);
        String str = InputStreamUtil.InputStreamTOString(in);
        Blob image = new SerialBlob(str.getBytes());
        InputStream is = image.getBinaryStream(); // 获得该blob的输出流
        FileOutputStream fos = new FileOutputStream("/Users/cc/Desktop/b.jpg");
        int i = 0;
        while ((i = is.read()) != -1) {
            fos.write(i);
        }
        fos.flush();
        fos.close();
        is.close();
        System.out.println("成功输出图片!!!");
    }

}
