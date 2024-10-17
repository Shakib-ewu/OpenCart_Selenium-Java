package AllTests;

import java.util.UUID;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import BaseClass.Base;

public class RegisterAuth extends Base {
    WebDriver driver;
    Base base;

    // For email generator
    public String generateRandomEmail() {
        String randomEmail = "user" + UUID.randomUUID().toString().substring(0, 5) + "@sparkroi.com";
        return randomEmail;
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @BeforeMethod
    public void setup() {
    	base = new Base();
    	driver = base.initializeBrowserAndOpenURL(); 
        // text() is used to target the text content inside an element (e.g., text()='My Account')
        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();
    }



	@Test(priority = 1)
    public void verifyRegisterWithMandatoryFields() {
        driver.findElement(By.id("input-firstname")).sendKeys("Sakib");
        driver.findElement(By.id("input-lastname")).sendKeys("Sarkar");
        String randomEmail = generateRandomEmail();
        System.out.println("Generated Random Email: " + randomEmail);
        driver.findElement(By.id("input-email")).sendKeys(randomEmail);
        driver.findElement(By.id("input-telephone")).sendKeys("1234566767");
        driver.findElement(By.id("input-password")).sendKeys("123456");
        driver.findElement(By.id("input-confirm")).sendKeys("123456");
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        String actualSuccessHeading = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
        Assert.assertEquals(actualSuccessHeading, "Your Account Has Been Created!");
    }

    @Test(priority = 2)
    public void verifyLoginWithAllFields() {
        driver.findElement(By.id("input-firstname")).sendKeys("Sakib");
        driver.findElement(By.id("input-lastname")).sendKeys("Sarkar");
        String randomEmail = generateRandomEmail();
        System.out.println("Generated Random Email: " + randomEmail);
        driver.findElement(By.id("input-email")).sendKeys(randomEmail);
        driver.findElement(By.id("input-telephone")).sendKeys("1234566767");
        driver.findElement(By.id("input-password")).sendKeys("123456");
        driver.findElement(By.id("input-confirm")).sendKeys("123456");
        driver.findElement(By.name("newsletter")).click();
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        String actualSuccessHeading = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
        Assert.assertEquals(actualSuccessHeading, "Your Account Has Been Created!");
    }

    @Test(priority = 3)
    public void verifyRegisterLoginWithExistingEmail() {
        driver.findElement(By.id("input-firstname")).sendKeys("Sakib");
        driver.findElement(By.id("input-lastname")).sendKeys("Sarkar");
        driver.findElement(By.id("input-email")).sendKeys("vesot82226@sparkroi.com");
        driver.findElement(By.id("input-telephone")).sendKeys("1234566767");
        driver.findElement(By.id("input-password")).sendKeys("123456");
        driver.findElement(By.id("input-confirm")).sendKeys("123456");
        driver.findElement(By.name("newsletter")).click();
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        String actualWarningMsg = driver.findElement(By.className("alert-dismissible")).getText();
        Assert.assertEquals(actualWarningMsg, "Warning: E-Mail Address is already registered!");
    }

    @Test(priority = 4)
    public void verifyRegisterAccWithoutAnyDetails() {
        driver.findElement(By.name("newsletter")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        String actualPrivacyPolicyWarningMsg = driver.findElement(By.className("alert-dismissible")).getText();
        Assert.assertEquals(actualPrivacyPolicyWarningMsg, "Warning: You must agree to the Privacy Policy!");

        String firstNameWarning = driver.findElement(By.xpath("//div[contains(text(),'First Name must be between 1 and 32 characters!')]")).getText();
        Assert.assertEquals(firstNameWarning, "First Name must be between 1 and 32 characters!");

        String lastNameWarning = driver.findElement(By.xpath("//div[contains(text(),'Last Name must be between 1 and 32 characters!')]")).getText();
        Assert.assertEquals(lastNameWarning, "Last Name must be between 1 and 32 characters!");

        String emailWarning = driver.findElement(By.xpath("//div[contains(text(),'E-Mail Address does not appear to be valid!')]")).getText();
        System.out.println("Email Warning Message: " + emailWarning);
        Assert.assertEquals(emailWarning, "E-Mail Address does not appear to be valid!");

        String telephoneWarning = driver.findElement(By.xpath("//div[contains(text(),'Telephone must be between 3 and 32 characters!')]")).getText();
        Assert.assertEquals(telephoneWarning, "Telephone must be between 3 and 32 characters!");

        String passwordWarning = driver.findElement(By.xpath("//div[contains(text(),'Password must be between 4 and 20 characters!')]")).getText();
        Assert.assertEquals(passwordWarning, "Password must be between 4 and 20 characters!");
    }
}
