package selenium;


import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Exercise_10_WebDriverWait {
    WebDriver driver;
    WebDriverWait waitExplicit;
    By startButton = By.xpath("//div[@id='start']/button");
    By loadingIcon = By.xpath("//div[@id='loading']/img");
    By helloText = By.xpath("//div[@id='finish']/h4[text()='Hello World!']");
    
    @BeforeTest
    public void beforeTest() {
//        driver = new FirefoxDriver();    	
    	 System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
    	 driver = new ChromeDriver();
    } 
    
    public void TC_01_Implicit() throws Exception {
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
    	driver.findElement(startButton).click();
    	//sau click se mat 5s de render ra text
    	Assert.assertTrue(driver.findElement(helloText).isDisplayed());
    	
    }
    
    public void TC02_LoadingIconInvisible() {
    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    	
    	waitExplicit = new WebDriverWait(driver, 7);
    	driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

    	
    	driver.findElement(startButton).click();
    	waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
    	Assert.assertTrue(driver.findElement(helloText).isDisplayed());
    	
    }
    
    public void TC_03_HellotextVisible() {
    	driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    	
    	waitExplicit = new WebDriverWait(driver, 2);
    	driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

    	
    	driver.findElement(startButton).click();
    	
    	waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(helloText));
    	Assert.assertTrue(driver.findElement(helloText).isDisplayed());
    	
    }
    
    @Test
    public void TC_04_Hellotext_LoadingIcon_NoLongerInDom() {
    	driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
    	
    	waitExplicit = new WebDriverWait(driver, 5);
    	driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
    	
    	System.out.println("Start time =" + getDateTimeSecond());
    	//khong visible + khong co trong Dom
    	waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(helloText));
    	System.out.println("End time =" + getDateTimeSecond());
    	
    	////khong visible + khong co trong Dom
    	System.out.println("Start time =" + getDateTimeSecond());
    	waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
    	System.out.println("End time =" + getDateTimeSecond());

    	System.out.println("Start time =" + getDateTimeSecond());
    	driver.findElement(startButton).click();
    	System.out.println("End time =" + getDateTimeSecond());
    	
    	//ko visible + co trong Dom 
    	System.out.println("Start time =" + getDateTimeSecond());
    	waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
    	System.out.println("End time =" + getDateTimeSecond());
    	
    	System.out.println("Start time =" + getDateTimeSecond());
    	waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(startButton));
    	System.out.println("End time =" + getDateTimeSecond());
    	
    	
    }
    
    @AfterTest
    public void afterTest() {
    	driver.quit();
    	
    }
    
    public Date getDateTimeSecond() {
        Date date = new Date();
        date = new Timestamp(date.getTime());
        return date;
}

    
 }