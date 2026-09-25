package com.automation.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for reading Excel test data.
 *
 * Uses Apache POI.
 *
 * Java 11 compatible.
 *
 * @author Banoth Mahesh Kumar
 */
public final class ExcelUtils {

    private ExcelUtils() {
        // Prevent object creation
    }

    public static Object[][] readSheet(
            String filePath,
            String sheetName) {

        File file = new File(filePath);

        if (!file.exists()) {
            throw new RuntimeException(
                    "Excel file not found: "
                            + file.getAbsolutePath()
            );
        }

        if (!file.isFile()) {
            throw new RuntimeException(
                    "Excel path is not a file: "
                            + file.getAbsolutePath()
            );
        }

        List<Object[]> data = new ArrayList<>();

        DataFormatter formatter =
                new DataFormatter();

        try (FileInputStream inputStream =
                     new FileInputStream(file);
             Workbook workbook =
                     new XSSFWorkbook(inputStream)) {

            Sheet sheet =
                    workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new RuntimeException(
                        "Excel sheet not found: "
                                + sheetName
                );
            }

            int firstRow =
                    sheet.getFirstRowNum();

            int lastRow =
                    sheet.getLastRowNum();

            for (int rowIndex = firstRow + 1;
                 rowIndex <= lastRow;
                 rowIndex++) {

                Row row =
                        sheet.getRow(rowIndex);

                if (row == null) {
                    continue;
                }

                int lastCell =
                        row.getLastCellNum();

                if (lastCell <= 0) {
                    continue;
                }

                Object[] rowData =
                        new Object[lastCell];

                boolean hasData = false;

                for (int cellIndex = 0;
                     cellIndex < lastCell;
                     cellIndex++) {

                    Cell cell =
                            row.getCell(cellIndex);

                    String value = "";

                    if (cell != null) {
                        value =
                                formatter.formatCellValue(
                                        cell
                                );
                    }

                    if (!value.trim().isEmpty()) {
                        hasData = true;
                    }

                    rowData[cellIndex] = value;
                }

                if (hasData) {
                    data.add(rowData);
                }
            }

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to read Excel file: "
                            + file.getAbsolutePath(),
                    e
            );
        }

        if (data.isEmpty()) {
            throw new RuntimeException(
                    "Excel sheet contains no test data: "
                            + sheetName
            );
        }

        return data.toArray(
                new Object[0][]
        );
    }
}
