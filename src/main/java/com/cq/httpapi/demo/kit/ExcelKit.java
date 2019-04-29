package com.cq.httpapi.demo.kit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;

public class ExcelKit {

    public static Row getRow(Workbook workbook, int sheetNum, int rowNum) {
        Sheet sheet = workbook.getSheetAt(sheetNum);
        return sheet.getRow(rowNum);
    }

    public static Row getRow(Workbook workbook, String sheetName, int rowNum) {
        Sheet sheet = workbook.getSheet(sheetName);
        return sheet.getRow(rowNum);
    }

    /**
     * 按列读取效率比较低
     *
     * @param workbook
     * @param sheetNum
     * @param colNum
     * @return
     */
    public static ArrayList<Cell> getColumn(Workbook workbook, int sheetNum, int colNum) {
        ArrayList<Cell> res = new ArrayList<>();
        Sheet sheet = workbook.getSheetAt(sheetNum);
        int maxRow = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < maxRow; i++) {
            Row row = sheet.getRow(i);
            Cell cell = row.getCell(colNum);
            res.add(cell);
        }
        return res;
    }

    public static ArrayList<Cell> getColumn(Workbook workbook, String sheetNum, int colNum) {
        ArrayList<Cell> res = new ArrayList<>();
        Sheet sheet = workbook.getSheet(sheetNum);
        int maxRow = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < maxRow; i++) {
            Row row = sheet.getRow(i);
            Cell cell = row.getCell(colNum);
            res.add(cell);
        }
        return res;
    }

    public static Cell getCell(Workbook workbook, int sheetNum, int rowNum, int colNum) {
        Row row = getRow(workbook, sheetNum, rowNum);
        Cell cell = row.getCell(colNum);
        return cell;
    }

    public static Cell getCell(Workbook workbook, String sheetNum, int rowNum, int colNum) {
        Row row = getRow(workbook, sheetNum, rowNum);
        Cell cell = row.getCell(colNum);
        return cell;
    }
}
