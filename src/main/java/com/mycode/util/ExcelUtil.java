package com.mycode.util;

import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther kexiangwei
 * @date 2019/8/3
 */
@Component
public class ExcelUtil {

    public static List<Map<String, Object>> getData(boolean isExcel2007, InputStream inputStream, String[] titleArr) throws IOException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Workbook workbook = null;
        if (isExcel2007) {
            workbook = new XSSFWorkbook(inputStream);
        } else {
            workbook = new HSSFWorkbook(inputStream);
        }
        for(int sheetNum = 0;sheetNum<workbook.getNumberOfSheets();sheetNum++){
            Sheet sheet = workbook.getSheetAt(sheetNum);
            if(sheet.getLastRowNum() == 0 && sheet.getPhysicalNumberOfRows() == 0){
                continue; //sheet中没有数据,直接跳过
            }
            //获取行数据
            for (int i =1; i<=sheet.getLastRowNum(); i++) { // 行，起始坐标为0（表头占了）,数据从下标从1开始
                Row row = sheet.getRow(i);
                //获取列的值
                Map<String, Object> map = new HashMap<>();
                for (int j = 0; j < row.getLastCellNum(); j++) { // 列，起始坐标为0
                    Cell cell = row.getCell(j);
                    if(!StringUtils.isEmpty(cell)){
                        map.put(titleArr[j],getCellValue(cell));
                    }else{
                        map.put(titleArr[j],null);
                    }
                }//cell end.
                mapList.add(map);
            }//row end .
        }
        return mapList;
    }

    //获取列的值
    public static String getCellValue(Cell cell) {
        String value;
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC: // 数字
                // 如果为时间格式的内容
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // 注：format格式 yyyy-MM-dd hh:mm:ss 中小时为12小时制，若要24小时制，则把小h变为H即可，yyyy-MM-dd HH:mm:ss
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
                    break;
                }
                //设置数字格式,poi默认为double类型的数据
                HSSFDataFormatter dataFormatter = new HSSFDataFormatter();
                value = dataFormatter.formatCellValue(cell);
                break;
            case Cell.CELL_TYPE_STRING: // 字符串
                value = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BOOLEAN: // Boolean
                value = cell.getBooleanCellValue() + "";
                break;
            case Cell.CELL_TYPE_FORMULA: // 公式
                value = cell.getCellFormula() + "";
                break;
            case Cell.CELL_TYPE_BLANK: // 空值
                value = "";
                break;
            case Cell.CELL_TYPE_ERROR:
                value = "非法字符";
                break;
            default:
                value = "未知类型";
                break;
        }
        return value;
    }
}
