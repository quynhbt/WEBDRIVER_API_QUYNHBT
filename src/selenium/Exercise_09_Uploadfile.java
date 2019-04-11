package selenium;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
    
    String chromePath = rootFolder + "\\Upload file\\chrome.exe";
    String firefoxPath = rootFolder + "\\Upload file\\firefox.exe";
    String iePath = rootFolder + "\\Upload file\\ie.exe";
    
    		
    String[] files = {fileNamePath01, fileNamePath02, fileNamePath03};


    @BeforeTest
	public void beforeTest() {

		// driver = new FirefoxDriver();

		// Run on fifox lastest
		 System.setProperty("webdriver.gecko.driver", ".\\lib\\geckodriver.exe");
		 driver = new FirefoxDriver();

		// Run with chorme
//		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
//		driver = new ChromeDriver();

		// Run on iE
//		 System.setProperty("webdriver.ie.driver", ".\\lib\\IEDriverServer.exe");
//		 driver = new InternetExplorerDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

    	
//    	System.out.println("File path 01 = " + fileNamePath01);
//    	System.out.println("File path 02 = " + fileNamePath02);
//    	System.out.println("File path 03 = " + fileNamePath03);

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
    
    
    public void TC_02_SendKeys_Multiple_A_Time() throws Exception  {
    	driver.get("http://blueimp.github.com/jQuery-File-Upload/");
    	Thread.sleep(5000);

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

    
    public void TC_03_UploadFilebyAutoIT() throws Exception {
    	driver.get("http://blueimp.github.com/jQuery-File-Upload/");
    	
    	if(driver.toString().contains("chrome")) {
        	WebElement uploadFile = driver.findElement(By.cssSelector(".fileinput-button"));
        	uploadFile.click();
        	Thread.sleep(1000);
        	//execute file exe
        	Runtime.getRuntime().exec(new String[] { chromePath, fileNamePath01 });
    	} else if(driver.toString().contains("firefox")) {
    		WebElement uploadFile = driver.findElement(By.cssSelector(".fileinput-button"));
        	uploadFile.click();
        	Thread.sleep(1000);
        	//execute file exe
        	Runtime.getRuntime().exec(new String[] { chromePath, fileNamePath01 });
    	}
    	Thread.sleep(4000);
    }
    
    @Test
    public void TC_04_Robot() throws Exception {
    	driver.get("http://blueimp.github.com/jQuery-File-Upload/");
    	
    	// Specify the file location with extension
        StringSelection select = new  StringSelection(fileNamePath01);

        // Copy to clipboard
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
        
        if (driver.toString().contains("chrome")  || driver.toString().contains("firefox")) {
            WebElement uploadFile =  driver.findElement(By.cssSelector(".fileinput-button"));
            uploadFile.click();
            Thread.sleep(1000);
        } else {
            System.out.println("Go to IE");
            WebElement uploadFile =  driver.findElement(By.xpath("//input[@type='file']"));
            clickToElementByJS(uploadFile);
            Thread.sleep(1000);
        }
    	
        Robot robot = new Robot();
        Thread.sleep(1000);

        // Nhan phim Enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        // Nhan xuong Ctrl - V
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        // Nha Ctrl - V
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        Thread.sleep(1000);

        // Nhan Enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
    
    @AfterTest
    public void afterTest() {
    	driver.quit();
    	
    }
    
    public Object clickToElementByJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript("arguments[0].click();", element);
    }
    
 }