package automationFramework.modules;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.Parameters;

public class Test_Controller {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static WebElement element;

    @Parameters({"browserName"})
    @BeforeClass
    void setup(String browserName) {
        driver = openBrowser(browserName);
    }

    public static WebDriver openBrowser(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("window-size=1920,1080");
            options.addArguments("--Headless");
            return new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxBinary firefoxBinary = new FirefoxBinary();
            FirefoxOptions options = new FirefoxOptions();
            options.setBinary(firefoxBinary);
            options.setHeadless(true);
            return new FirefoxDriver(options);
        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            options.addArguments("window-size=1920,1080");
            options.addArguments("--Headless");
            return new EdgeDriver(options);
        } else throw new IllegalArgumentException("The Browser " + browserName + " does not support");
    }

    @AfterClass(alwaysRun = true)
    void tearDown() {
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
