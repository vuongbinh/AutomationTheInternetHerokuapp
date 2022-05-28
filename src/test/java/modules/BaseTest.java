package modules;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    static WebDriver driver;


    @BeforeClass
    void setup() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions opt = new EdgeOptions();
        //opt.addArguments("--Headless");
        driver = new EdgeDriver(opt);
    }

    @AfterClass
    void tearDown() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.close();
    }
}
