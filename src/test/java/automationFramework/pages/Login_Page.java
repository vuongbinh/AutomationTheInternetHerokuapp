package automationFramework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Login_Page extends Page_Controller {

    By loginButton = By.id("login");
    By username = By.id("userName");
    By password = By.id("password");
    By logoutBTN = By.xpath("//*[.='Log out']");
    WebDriverWait wait;

    public Login_Page(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get("https://demoqa.com/login");
    }

    public void login(String usr, String pwd) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable((loginButton)));
        driver.findElement(username).sendKeys(usr);
        driver.findElement(password).sendKeys(pwd);
        driver.findElement(loginButton).click();
    }

    public void logout() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable((logoutBTN)));
        driver.findElement(logoutBTN).click();
    }

}
