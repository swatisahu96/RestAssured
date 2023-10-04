package org.example.utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;

public class UtilExcel {

    static String sheetPath = System.getProperty("user.dir") + "\\src\\main\\resources\\RestAssured_External.xlsx";

    @DataProvider
    public Object[][] getTestData(String SheetName) throws Exception {

        FileInputStream file = new FileInputStream(sheetPath);

        Workbook myWorkBook = new XSSFWorkbook(file);
        int indexOfSheet = myWorkBook.getSheetIndex(SheetName);
        //WorkbookFactory.create does not create a workbook (an Excel file).
        // It instantiates a class that represents a workbook(located in memory)
        // And only after having access to this Workbook object we can use its methods.

        Sheet sheet = myWorkBook.getSheetAt(indexOfSheet);
        int row = sheet.getLastRowNum();
        int col = sheet.getRow(0).getLastCellNum();
        Object[][] data = new Object[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                data[i][j] = sheet.getRow(i + 1).getCell(j).toString();

            }
        }
        return data;
    }

    @DataProvider
    public Object[][] getData() throws Exception {
        return getTestData("Sheet1");
    }

}
