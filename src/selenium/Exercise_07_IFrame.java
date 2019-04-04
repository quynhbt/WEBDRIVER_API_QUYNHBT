package selenium;


import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Exercise_07_IFrame {
    WebDriver driver;
    JavascriptExecutor javascript;

  
    @BeforeTest
    public void beforeTest() {
//    	driver = new FirefoxDriver();
//    	driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
//    	driver.manage().window().maximize();
    }
    
    @Test
    public void TC_01_Closebutton() throws Exception {
    	driver.get("https://www.hdfcbank.com/");
    	
    	checkAndClosePopup();
 
    	//switch qua iframe chua text
    	WebElement lookingForIframe = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
    	driver.switchTo().frame(lookingForIframe);
    	
    	Assert.assertTrue(driver.findElement(By.xpath("//span[@id='messageText' and text()='What are you looking for?']")).isDisplayed());
		
    }

    @AfterTest
    public void afterTest() {
    	
    }
    
    public void checkAndClosePopup () {
    	List<WebElement> listPopup = driver.findElements(By.xpath("//img[@class='popupCloseButton']"));
		if (listPopup.size() > 0) {
			for (int i = 0; i < listPopup.size(); i++) {
				listPopup.get(i).click();
				System.out.println("POPUP WAS CLOSED!");
			}
		} else {
			System.out.println("CAN NOT FIND OUT THE POPUP!");
		}
    }



} 