package AllTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import BaseClass.Base;

public class Search extends Base{
	
	WebDriver driver;
	Base base;
	@AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @BeforeMethod
    public void setup() {
    	base = new Base();
    	driver = base.initializeBrowserAndOpenURL(); 
        // text() is used to target the text content inside an element (e.g., text()='My Account')
        
    }
    
    

	@Test(priority = 1)
    public void verifySearchFieldValidInputs() {
        driver.findElement(By.name("search")).sendKeys("HP");
        driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();        
        

        String actualSuccessHeading = driver.findElement(By.linkText("HP LP3065")).getText();
        Assert.assertEquals(actualSuccessHeading, "HP LP3065");
    }
    
    @Test(priority = 2)
    public void verifySearchFieldInvalidInputs() {
        driver.findElement(By.name("search")).sendKeys("Honda");
        driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();        
        

        String actualSuccessHeading = driver.findElement(By.xpath("//p[contains(text(),'There is no product that matches the search criter')]")).getText();
        Assert.assertEquals(actualSuccessHeading, "There is no product that matches the search criteria.");
    }
    
    @Test(priority = 3)
    public void verifySearchFieldNullInputs() {
        driver.findElement(By.name("search")).sendKeys("");
        driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();        
        

        String actualSuccessHeading = driver.findElement(By.xpath("//p[contains(text(),'There is no product that matches the search criter')]")).getText();
        Assert.assertEquals(actualSuccessHeading, "There is no product that matches the search criteria.");
    }


}
