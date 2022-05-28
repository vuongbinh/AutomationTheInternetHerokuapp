package pages;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    protected String base_url = "https://the-internet.herokuapp.com/";
    protected WebDriver driver;
    protected BasePage (WebDriver driver){
        this.driver = driver;
    }

    protected abstract void open();


}
