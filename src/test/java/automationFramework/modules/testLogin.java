package automationFramework.modules;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import automationFramework.pages.Login_Page;
import automationFramework.supporter.ExcelUltils;

public class testLogin extends Test_Controller {
        // Verify login success
        @Test(dataProvider = "hybridDATA")
        public void verify_LoginSuccess(String usr, String pwd) {
                Login_Page login_Page = new Login_Page(driver);
                login_Page.open();
                login_Page.login(usr, pwd);
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                Assert.assertEquals(driver.findElement(By.xpath("//*[@id='userName-value']")).getText(), usr);
                Assert.assertTrue(driver.findElement(By.xpath("//*[.='Log out']")).isDisplayed());
                login_Page.logout();
        }

        // Verify login error msg
        @Test(dataProvider = "hybridDATA")
        public void verify_LoginError(String usr, String pwd) {
                Login_Page login_Page = new Login_Page(driver);
                login_Page.open();
                login_Page.login(usr, pwd);
                wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.presenceOfElementLocated(By.id("name")));
                Assert.assertEquals(driver.findElement(By.id("name")).getText(), "Invalid username or password!");
                Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
        }

        @DataProvider(name = "hybridDATA")
        public static Object[][] getDataFromProvider(Method method) throws IOException {
                ExcelUltils file = new ExcelUltils();
                if (method.getName() == "verify_LoginSuccess") {
                        Sheet excelSheet = file.readFile("src/main/resources", "data_LoginTest.xlsx",
                                        "valid_Account");
                        getDataFromFile(excelSheet);
                } else if (method.getName() == "verify_LoginError") {
                        Sheet excelSheet = file.readFile("src/main/resources", "data_LoginTest.xlsx",
                                        "invalid_Account");
                        getDataFromFile(excelSheet);
                }
                return null;
        }

}
