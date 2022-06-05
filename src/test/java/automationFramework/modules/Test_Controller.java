package automationFramework.modules;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Test_Controller {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static WebElement element;

    @BeforeTest
    void setup() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    @AfterClass
    void tearDown() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.close();
    }

    protected static Object[][] getDataFromFile(Sheet excelSheet) {
        int rowCOUNT = excelSheet.getLastRowNum() - excelSheet.getFirstRowNum();
        Object[][] object = new Object[rowCOUNT][2];
        for (int i = 0; i < rowCOUNT; i++) {
            Row row = excelSheet.getRow(i + 1);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                object[i][j] = row.getCell(j).toString();
            }
        }
        return object;
    }
}
