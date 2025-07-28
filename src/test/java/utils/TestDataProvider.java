package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDataProvider {
    
    @DataProvider(name = "excelData")
    public static Object[][] getExcelData(String filePath, String sheetName) {
        List<Map<String, String>> data = new ArrayList<>();
        
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(0);
            
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                Map<String, String> rowData = new HashMap<>();
                
                for (int j = 0; j < headerRow.getLastCellNum(); j++) {
                    Cell headerCell = headerRow.getCell(j);
                    Cell dataCell = row.getCell(j);
                    
                    String headerValue = headerCell.getStringCellValue();
                    String dataValue = getCellValue(dataCell);
                    
                    rowData.put(headerValue, dataValue);
                }
                data.add(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Object[][] result = new Object[data.size()][];
        for (int i = 0; i < data.size(); i++) {
            result[i] = new Object[]{data.get(i)};
        }
        
        return result;
    }
    
    private static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }
}
