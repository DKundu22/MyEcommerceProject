package com.ecommerce.utility;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

public class ExcelUtil {

    public static List<Map<String, String>> getTestData(String sheetName) {
        List<Map<String, String>> testData = new ArrayList<>();
        try (
            InputStream is = Thread.currentThread().getContextClassLoader()
                                    .getResourceAsStream("TestData/UserRegistrationData.xlsx");
            Workbook workbook = new XSSFWorkbook(is)
        ) {
            Sheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(0);
            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = headerRow.getPhysicalNumberOfCells();

            for (int i = 1; i < rowCount; i++) {
                Map<String, String> dataMap = new HashMap<>();
                Row row = sheet.getRow(i);
                for (int j = 0; j < colCount; j++) {
                    String key = headerRow.getCell(j).getStringCellValue();
                    String value = row.getCell(j) != null ? row.getCell(j).toString() : "";
                    dataMap.put(key, value);
                }
                testData.add(dataMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return testData;
    }
}