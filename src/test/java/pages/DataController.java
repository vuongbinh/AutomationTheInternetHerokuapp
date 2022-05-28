package pages;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataController {
    private static XSSFSheet excelWSheet;
    private static XSSFWorkbook excelWBook;
    private static XSSFCell Cell;
    private static XSSFRow Row;

    public static Object[][] getTableArray(String filePath, String sheetName) throws Exception {
        String[][] tabArray = null;
        try {
            FileInputStream excelFile = new FileInputStream(filePath);
            excelWBook = new XSSFWorkbook(excelFile);
            excelWSheet = excelWBook.getSheet(sheetName);

            int startRow = 1;
            int startCol = 1;
            int ci, cj;
            int totalRows = excelWSheet.getLastRowNum();

            int totalCols = 2;
            tabArray = new String[totalRows][totalCols];
            ci = 0;
            for (int i = startRow; i <= totalRows; i++, ci++) {
                cj = 0;
                for (int j = startCol; j <= totalCols; j++, cj++) {
                    tabArray[ci][cj] = getCellData(i, j);
                    System.out.println(tabArray[ci][cj]);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Could not read the excel sheet");
            e.printStackTrace();
        }
        return (tabArray);
    }

    private static String getCellData(int rowNum, int colNum) throws Exception {
        try {
            Cell = excelWSheet.getRow(rowNum).getCell(colNum);
            CellType dataType = Cell.getCellType();
            if (dataType == CellType.ERROR) {
                return "";
            } else {
                String cellData = Cell.getStringCellValue();
                return cellData;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw (e);
        }
    }
}
