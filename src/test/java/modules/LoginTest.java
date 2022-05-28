package modules;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DataController;
import pages.LoginPage;

public class LoginTest extends BaseTest {
    @DataProvider
    public Object[][] Authentication()throws Exception{
        Object[][] testObjArray = DataController.getTableArray("D:\\Automation\\Practices\\AK36\\src\\test\\java\\data\\authentication_TESTDATA.xlsx","Sheet1");
        return testObjArray;
    }
    @Test(dataProvider = "Authentication")
    void validateLogin(String sUserName, String sPassword) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(sUserName,sPassword);
    }
}
