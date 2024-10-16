package BaseClass;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Base {

    WebDriver driver;

    public WebDriver initializeBrowserandOpenURL(String browser) {

        String browserName = "chrome";

        if (browserName.equals("chrome")) {
            driver = new ChromeDriver();

        } else if (browserName.equals("firefox")) {
            driver = new FirefoxDriver();

        } else if (browserName.equals("edge")) {
            driver = new EdgeDriver();

        } else if (browserName.equals("safari")) {
            driver = new SafariDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.get("https://tutorialsninja.com/demo/");

        return driver;
    }
}
