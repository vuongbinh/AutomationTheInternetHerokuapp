package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    By loginButton = By.xpath("//*[contains(text(),' Login')]");
    By username = By.id("username");
    By password = By.id("password");
    String extend_URL = "/login";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(base_url + extend_URL);
    }

    public void login(String usr, String pwd) {
        driver.findElement(username).sendKeys(usr);
        driver.findElement(password).sendKeys(pwd);
        driver.findElement(loginButton).click();
    }
}
