package automationFramework.supporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUltils {
    public Sheet readFile(String filePATH, String fileNAME, String sheetNAME) throws IOException {
        File file = new File(filePATH + "\\" + fileNAME);
        FileInputStream inputStream = new FileInputStream(file);
        Workbook excelWBook = null;
        String fileExtensionName = fileNAME.substring(fileNAME.indexOf("."));
        if (fileExtensionName.equals(".xlsx")) {
            excelWBook = new XSSFWorkbook(inputStream);
        } else if (fileExtensionName.equals("xls")) {
            excelWBook = new HSSFWorkbook(inputStream);
        }
        if (excelWBook != null) {
            return excelWBook.getSheet(sheetNAME);
        }
        return null;
    }
}
