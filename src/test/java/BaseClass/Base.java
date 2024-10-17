package BaseClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import Utils.Utilities;

public class Base {

    WebDriver driver;
    public Properties properties;

    // Constructor to load properties file
    public Base() {
        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/java/config/config.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WebDriver initializeBrowserAndOpenURL() {
        // Get the browser and URL from the properties file
        String browserName = properties.getProperty("browser");
        String url = properties.getProperty("url");

        // Browser selection logic
        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browserName.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        } else {
            throw new RuntimeException("Browser not supported: " + browserName);
        }

        // Common setup steps
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));

        // Open the URL dynamically from properties
        driver.get(url);

        return driver;
    }
}
