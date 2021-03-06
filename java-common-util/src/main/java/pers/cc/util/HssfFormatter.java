package pers.cc.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

/**
 * 格式化poi单元格输出内容,例如 1.0 变成1 Created by cc on 2016/10/22.
 */
public class HssfFormatter {
    @SuppressWarnings("deprecation")
    public static String getValue(HSSFCell cell) {
        switch (cell.getCellTypeEnum()) {
        case STRING:
            return cell.getRichStringCellValue().getString();
        case NUMERIC:
            if (DateUtil.isCellDateFormatted(cell)) {
                return String.valueOf(cell.getDateCellValue());
            } else {
                return String.valueOf(cell.getNumericCellValue());
            }
        case BOOLEAN:
            return String.valueOf(cell.getBooleanCellValue());
        case FORMULA:
            return String.valueOf(cell.getCellFormula());
        case BLANK:
            return "";
        default:
            return "";
        }
    }

    public static String foramtc(Cell c) {
        String formatc = String.valueOf(c);
        if ((formatc).contains(".")) {
            return formatc.substring(0, formatc.indexOf("."));
        }
        return formatc;
    }
}
