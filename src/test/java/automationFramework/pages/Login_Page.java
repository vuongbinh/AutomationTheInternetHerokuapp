package automationFramework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login_Page extends Page_Controller {

    By loginButton = By.id("login");
    By username = By.id("userName");
    By password = By.id("password");
    By logoutBTN = By.xpath("//*[.='Log out']");

    public Login_Page(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get("https://demoqa.com/login");
    }

    public void login(String usr, String pwd) {
        driver.findElement(username).sendKeys(usr);
        driver.findElement(password).sendKeys(pwd);
        driver.findElement(loginButton).click();
    }

    public void logout() {
        driver.findElement(logoutBTN).click();
    }

}
