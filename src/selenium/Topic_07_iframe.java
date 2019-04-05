package selenium;


import java.util.List;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_07_iframe {
    WebDriver driver;
    JavascriptExecutor javascript;

    @BeforeTest
    public void beforeTest() {
        driver = new FirefoxDriver();
    	driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    	driver.manage().window().maximize();
    	//gan gia tri va ep kieu
    	
    	javascript = (JavascriptExecutor)driver;
    }
    
    @Test
    public void TC_01_Closebutton() throws Exception {
    		driver.get("https://www.hdfcbank.com/");
    	
    		List<WebElement> listPopup = driver.findElements(By.xpath("//img[@class='popupCloseButton']"));
    		if (listPopup.size() > 0) {
    				for (int i = 0; i < listPopup.size(); i++) {
    					listPopup.get(i).click();
    					System.out.println("POPUP WAS CLOSED!");
    				}
    		} else {
    					System.out.println("CAN NOT FIND OUT THE POPUP!");
    				}
    
    		//switch qua iframe chua text
    		WebElement lookingForIframe = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
    		driver.switchTo().frame(lookingForIframe);
		
    		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='messageText' and text()='What are you looking for?']")).isDisplayed());
    	
    		//switch ve topwindow. giua 2 (>2) frame thi phai switch ve topwin roi moi chuyen sang frame khac
    		driver.switchTo().defaultContent();
//    	
//
//    		WebElement slidingBannersIframe = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
//    		driver.switchTo().frame(slidingBannersIframe);

//    		List<WebElement> bannerImages = driver.findElements(By.xpath("//img[@class='bannerimage']"));
//    		System.out.println("banner Image are " + bannerImages.size());
//    		Assert.assertEquals(bannerImages.size(), 6);
//
//    		for (WebElement image : bannerImages) {
//    			Assert.assertTrue(isImageLoadedSuccess(image));
//    		}

//    		driver.switchTo().defaultContent();

    		List<WebElement> flipBannerImages = driver.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
    		System.out.println("flip Image are " + flipBannerImages.size());
    		Assert.assertEquals(flipBannerImages.size(), 8);

    		for (WebElement image : flipBannerImages) {
    			Assert.assertTrue(isImageLoadedSuccess(image));
    			Assert.assertTrue(image.isDisplayed());
    		}
    }
 
    public boolean isImageLoadedSuccess (WebElement imageElement) {
    	 return (boolean) javascript.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", imageElement);
    }

    
    @AfterTest
    public void afterTest() {
    	driver.quit();
    	
    }
    
 }