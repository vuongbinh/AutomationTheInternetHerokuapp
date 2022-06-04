package automationFramework.modules;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;
import automationFramework.pages.Login_Page;
import automationFramework.supporter.ExcelUltils;

public class testLogin extends Test_Controller {
        @DataProvider(name = "hybridDATA")
        public static Object[][] getDataFromProvider() throws IOException {
                Object[][] object;
                ExcelUltils file = new ExcelUltils();

                Sheet excelSheet = file.readFile("src/main/resources", "data_LoginTest.xlsx", "Sheet1");
                int rowCOUNT = excelSheet.getLastRowNum() - excelSheet.getFirstRowNum();
                object = new Object[rowCOUNT][2];
                for (int i = 0; i < rowCOUNT; i++) {
                        Row row = excelSheet.getRow(i + 1);
                        for (int j = 0; j < row.getLastCellNum(); j++) {
                                object[i][j] = row.getCell(j).toString();
                        }
                }
                System.out.println();
                return object;
        }

        // Here we are calling the Data Provider object with its Name
        @Test(dataProvider = "hybridDATA")
        public void test(String usr, String pwd) {
                Login_Page login_Page = new Login_Page(driver);
                login_Page.open();
                login_Page.login(usr, pwd);
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                Assert.assertEquals(driver.findElement(By.xpath("//*[@id='userName-value']")).getText(),usr);
                Assert.assertTrue(driver.findElement(By.xpath("//*[.='Log out']")).isDisplayed());
                login_Page.logout();
        }
}
