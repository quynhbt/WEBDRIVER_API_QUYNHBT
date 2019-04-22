package selenium;


import java.sql.Timestamp;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.base.Function;

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
    	 waitExplicit = new WebDriverWait(driver, 30);
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
    
    
    public void TC_05_AjaxWithoutExplicitWait() {
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
    	
    	Assert.assertTrue(driver.findElement(By.xpath("//div[@class='contentWrapper']")).isDisplayed());
    	
    	String beforeDateSelected = driver.findElement(By.xpath("//span [@id='ctl00_ContentPlaceholder1_Label1']")).getText();
    	System.out.println("Ngay truoc khi chon =" + beforeDateSelected);
    	
    	driver.findElement(By.xpath("//a[text()='22']")).click();
    	
    	//wait cho 1 element ke tiep hien thi
    	Assert.assertTrue(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1' and contains(text(), \"Monday, April 22, 2019\")]")).isDisplayed());
    	
    	String afterDateSelected = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText().trim();
    	System.out.println("Ngay truoc khi chon =" + afterDateSelected);
    
    }
    
    
    public void TC_06_AjaxWithExplicitWait() {
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
    	
    	waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='contentWrapper']")));
     	
    	String beforeDateSelected = driver.findElement(By.xpath("//span [@id='ctl00_ContentPlaceholder1_Label1']")).getText();
    	System.out.println("Ngay truoc khi chon =" + beforeDateSelected);
    	
    	driver.findElement(By.xpath("//a[text()='22']")).click();
    	
    	waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));    	
    	waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1' and contains(text(), \"Monday, April 22, 2019\")]")));
    
    	String afterDateSelected = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText().trim();
    	System.out.println("Ngay truoc khi chon =" + afterDateSelected);
    }
    
    @Test
    public void TC_07_FluentWait() {
    	driver.get("https://daominhdam.github.io/fluent-wait/");
    	
    	WebElement countdount =  driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
    	waitExplicit.until(ExpectedConditions.visibilityOf(countdount));

    	// Khởi tạo Fluent wait
    	new FluentWait<WebElement>(countdount)
    	           // Tổng time wait là 15s
    	           .withTimeout(15, TimeUnit.SECONDS)
    	            // Tần số mỗi 1s check 1 lần
    	            .pollingEvery(1, TimeUnit.SECONDS)
    	           // Nếu gặp exception là find ko thấy element sẽ bỏ  qua
    	            .ignoring(NoSuchElementException.class)
    	            // Kiểm tra điều kiện
    	            .until(new Function<WebElement, Boolean>() {
    	                public Boolean apply(WebElement element) {
    	                           // Kiểm tra điều kiện countdount = 00
    	                           boolean flag =  element.getText().endsWith("00");
    	                           System.out.println("Time = " +  element.getText());
    	                           // return giá trị cho function apply
    	                           return flag;
    	                }
    	           });
    	
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