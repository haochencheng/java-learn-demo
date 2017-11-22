package pers.cc.util;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * Created by cc on 2016/10/22.
 */
public class ExcelUtil {

    /**
     * 
     * 遍历xls表数据
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        InputStream inp = new FileInputStream("c:\\cc.xls");
        POIFSFileSystem fs = new POIFSFileSystem(inp);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        for (Sheet sheet : wb) {
            for (Row row : sheet) {
                for (Cell cell : row) {
                    System.out.println(cell);
                }
            }
        }
        fs.close();
    }
}
