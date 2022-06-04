package automationFramework.pages;

import org.openqa.selenium.WebDriver;

public abstract class Page_Controller {
    protected WebDriver driver;

    protected Page_Controller(WebDriver driver) {
        this.driver = driver;
    }

    protected abstract void open();
}
