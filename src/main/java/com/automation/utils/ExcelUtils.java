package com.automation.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Apache POI-based Excel reader for data-driven testing.
 *
 * @author Banoth Mahesh Kumar
 */
public class ExcelUtils {

    private ExcelUtils() {}

    /**
     * Reads all rows from a sheet and returns them as a 2D Object array
     * suitable for TestNG @DataProvider.
     *
     * @param filePath  path to the .xlsx file
     * @param sheetName name of the sheet to read
     * @return Object[][] where each row is one test data set
     */
    public static Object[][] readSheet(String filePath, String sheetName) {
        List<Object[]> data = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook   = new XSSFWorkbook(fis)) {

            Sheet sheet     = workbook.getSheet(sheetName);
            int   totalRows = sheet.getLastRowNum();          // excludes header (row 0)
            int   totalCols = sheet.getRow(0).getLastCellNum();

            for (int r = 1; r <= totalRows; r++) {
                Row row = sheet.getRow(r);
                if (row == null) continue;

                Object[] rowData = new Object[totalCols];
                for (int c = 0; c < totalCols; c++) {
                    Cell cell = row.getCell(c);
                    rowData[c] = cell == null ? "" : getCellValue(cell);
                }
                data.add(rowData);
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read Excel file: " + filePath, e);
        }

        return data.toArray(new Object[0][]);
    }

    private static String getCellValue(Cell cell) {
        return switch (cell.getCellType()) {
            case STRING  -> cell.getStringCellValue().trim();
            case NUMERIC -> String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default      -> "";
        };
    }
}
