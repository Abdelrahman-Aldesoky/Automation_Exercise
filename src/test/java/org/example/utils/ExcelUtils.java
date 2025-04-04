package org.example.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {
    private static final String TEST_DATA_PATH = "src/test/resources/testdata/";

    public static Object[][] getTestData(String fileName, String sheetName) {
        Object[][] data = null;
        try (FileInputStream fis = new FileInputStream(TEST_DATA_PATH + fileName);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet '" + sheetName + "' not found in file '" + fileName + "'");
            }

            Row headerRow = sheet.getRow(0);
            int colCount = headerRow.getPhysicalNumberOfCells();

            // Count non-empty rows first
            List<Row> dataRows = new ArrayList<>();
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);
                if (row != null && !isEmptyRow(row, colCount)) {
                    dataRows.add(row);
                }
            }

            data = new Object[dataRows.size()][1];

            // Extract column names from header row
            List<String> headers = new ArrayList<>();
            for (int i = 0; i < colCount; i++) {
                Cell cell = headerRow.getCell(i);
                headers.add(cell.getStringCellValue());
            }

            // Process only non-empty rows
            for (int i = 0; i < dataRows.size(); i++) {
                Row row = dataRows.get(i);
                Map<String, String> dataMap = new HashMap<>();

                for (int j = 0; j < colCount; j++) {
                    Cell cell = row.getCell(j);
                    String value = getCellValueAsString(cell);
                    dataMap.put(headers.get(j), value);
                }

                data[i][0] = dataMap;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private static boolean isEmptyRow(Row row, int expectedCells) {
        if (row == null) return true;

        boolean isEmpty = true;
        for (int i = 0; i < expectedCells; i++) {
            Cell cell = row.getCell(i);
            if (cell != null && !getCellValueAsString(cell).trim().isEmpty()) {
                isEmpty = false;
                break;
            }
        }
        return isEmpty;
    }

    public static Map<String, String> getTestDataByRowId(String fileName, String sheetName, String idColumn, String idValue) {
        try (FileInputStream fis = new FileInputStream(TEST_DATA_PATH + fileName);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(0);

            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = headerRow.getPhysicalNumberOfCells();

            // Extract column names from header row
            List<String> headers = new ArrayList<>();
            int idColumnIndex = -1;
            for (int i = 0; i < colCount; i++) {
                Cell cell = headerRow.getCell(i);
                String header = cell.getStringCellValue();
                headers.add(header);
                if (header.equals(idColumn)) {
                    idColumnIndex = i;
                }
            }

            // Find the row with matching ID
            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                Cell idCell = row.getCell(idColumnIndex);

                if (idCell != null && getCellValueAsString(idCell).equals(idValue)) {
                    Map<String, String> dataMap = new HashMap<>();
                    for (int j = 0; j < colCount; j++) {
                        Cell cell = row.getCell(j);
                        String value = getCellValueAsString(cell);
                        dataMap.put(headers.get(j), value);
                    }
                    return dataMap;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case NUMERIC:
                double value = cell.getNumericCellValue();
                // Check if it's an integer value
                if (value == Math.floor(value)) {
                    return String.format("%.0f", value);
                }
                return String.valueOf(value);
            case STRING:
                return cell.getStringCellValue();
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}