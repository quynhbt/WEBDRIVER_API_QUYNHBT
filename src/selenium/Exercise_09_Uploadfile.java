package selenium;


import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Exercise_09_Uploadfile {
    WebDriver driver;
    //get duong dan. get relative path
    String rootFolder = System.getProperty("user.dir");
    String fileName01 = "image 01.png";
    String fileName02 = "image 02.png";
    String fileName03 = "image 03.png";
    
    //lay duog dan cua anh ra
    String fileNamePath01 = rootFolder + "\\Upload file\\" + fileName01;
    String fileNamePath02 = rootFolder + "\\Upload file\\" + fileName02;
    String fileNamePath03 = rootFolder + "\\Upload file\\" + fileName03;
    
    String[] files = {fileNamePath01, fileNamePath02, fileNamePath03};


    @BeforeTest
    public void beforeTest() {
//        driver = new FirefoxDriver();
    	System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
    	driver = new ChromeDriver();
    	driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    	driver.manage().window().maximize();
    	
    	System.out.println("File path 01 = " + fileNamePath01);
    	System.out.println("File path 02 = " + fileNamePath02);
    	System.out.println("File path 03 = " + fileNamePath03);

    }
    
    
    public void TC_01_Upload_Queue()  {
    	driver.get("http://blueimp.github.io/jQuery-File-Upload/");
    	
//    	//su dung vong for
//    	for(int i = 0; i<files.length; i++) {
//    		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
//        	uploadFile.sendKeys(files[i]);
//        	try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    	}
    	
    	//su dung for-each
    	//file se duyet qua files
    	for(String file : files) {
    		//moi lan senkey se find element
        	WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
        	uploadFile.sendKeys(file);
        	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	driver.findElement(By.xpath("//span[text()='Start upload']")).click();
    	
    	Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileName01 + "']")).isDisplayed());
    	Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileName02 + "']")).isDisplayed());
    	Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileName03 + "']")).isDisplayed());
    	
    }
    
    @Test
    public void TC_02_SendKeys_Multiple_A_Time() throws Exception  {
    	driver.get("http://blueimp.github.com/jQuery-File-Upload/");

    	WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
    	uploadFile.sendKeys(fileNamePath01 +  "\n" + fileNamePath02 + "\n" + fileNamePath03);
    	Thread.sleep(1000);
    	
    	List <WebElement> startButtons = driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));
    	for (WebElement startButton : startButtons) {
    		startButton.click();
    		Thread.sleep(1000);
    	}
    	
    	Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileName01 + "']")).isDisplayed());
    	Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileName02 + "']")).isDisplayed());
    	Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileName03 + "']")).isDisplayed());
    	
    	
    }

    @AfterTest
    public void afterTest() {
    	driver.quit();
    	
    }
    
 }