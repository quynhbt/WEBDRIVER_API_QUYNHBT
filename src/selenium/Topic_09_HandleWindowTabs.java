package selenium;


import java.util.List;
import java.util.Set;
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

public class Topic_09_HandleWindowTabs {
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
    
    //TC_02
    // Case them: kiem tra xem cua so co bi trung nhau khong
    
    
    public void TC_01_() throws Exception {
    	//parent window
    	driver.get("https://daominhdam.github.io/basic-form/index.html");
    	String parentID = driver.getWindowHandle();
    	System.out.println("parent ID =" + parentID);
       	
    	//click vao here link lan 1
    	driver.findElement(By.xpath("//a[text()='Click Here']")).click();
    	Thread.sleep(2000);
    	//switch qua tab moi
    	switchToChildWindowByID(parentID);
    	
    	//tao ra 1 bien. Kiem tra title window moi
    	String googleTitle = driver.getTitle();
    	System.out.println(googleTitle);
    	
    	Assert.assertEquals(googleTitle, "Google");
    	
    	//quay lai parent window de click vao hera link lan 2
    	switchToChildWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
    	driver.findElement(By.xpath("//a[text()='Click Here']")).click();
    	
    	//close cua so window moi
    	Assert.assertTrue(closeAllWithoutParentWindows(parentID));
    	Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
        Set<String> allWindows = driver.getWindowHandles();
        System.out.println("Tat ca cac cua so dang co = " + allWindows.size());
        
        for(String child: allWindows) {
        	System.out.println("Cua so trung nhau = " + child);
        
        }
    }
    
    
    public void TC_02_() throws Exception {
    	driver.get("http://www.hdfcbank.com/");
    	String parentID = driver.getWindowHandle();
    	
    	List<WebElement> listPopup = driver.findElements(By.xpath("//img[@class='popupCloseButton']"));
		if (listPopup.size() > 0) {
			for (int i = 0; i < listPopup.size(); i++) {
				listPopup.get(i).click();
				System.out.println("POPUP WAS CLOSED!");
			}
		} 
		else {
				System.out.println("CAN NOT FIND OUT THE POPUP!");
			}
		//click vao Agrilink
		driver.findElement(By.xpath("//a[text()='Agri']")).click();
		
		//switch agri linkpage
		switchToChildWindowByTitle("HDFC Bank Kisan Dhan Vikas e-Kendra");
		Thread.sleep(2000);
		
		//click vao account details
		
		driver.findElement(By.xpath("//p[text()='Account Details']")).click();
		
		//switch qua tab moi
		switchToChildWindowByTitle("Welcome to HDFC Bank NetBanking");
		Thread.sleep(2000);
		
		//switch qua frame chua privacy policy link
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='footer']")));
		
		driver.findElement(By.xpath("//a[text()='Privacy Policy']")).click();
		
		switchToChildWindowByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
		Thread.sleep(2000);
		
		//CRS 
		driver.findElement(By.xpath("//a[text()='CSR']")).click();
		
		switchToChildWindowByTitle("HDFC BANK - CSR - Homepage");
		Thread.sleep(2000);
		
		// dong tat ca ngoai tru parent. vi la ham boolean nen se kiem tra luon
		Assert.assertTrue(closeAllWithoutParentWindows(parentID));
		Thread.sleep(2000);
    }
    
    @Test
    public void TC_03() throws Exception {
    	driver.get("http://live.guru99.com/index.php/");
    	String parentID = driver.getWindowHandle();
    	
    	driver.findElement(By.xpath("//a[text()='Mobile']")).click();
    	
    	driver.findElement(By.xpath("//html[@id='top']/body/div[1]/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[1]/div/div[3]/ul/li[2]/a")).click();
    	driver.findElement(By.xpath("//html[@id='top']/body/div[1]/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[3]/div/div[3]/ul/li[2]/a")).click();
    	driver.findElement(By.xpath("//div[@class='actions']//button[@title='Compare']")).click();
    	
    	switchToChildWindowByTitle("Products Comparison List - Magento Commerce");
    	Thread.sleep(2000);
    	
    	Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
    	System.out.println(driver.getTitle());
   	    closeAllWithoutParentWindows(parentID);
    }
    
    //switch if only have 2 window /tab
    public void switchToChildWindowByID(String parentID) {
    	//Get ra ID cua tat ca cac cua so dang co
        Set<String> allWindows = driver.getWindowHandles();
        //Dung for each vong for de duyet qua tung cua so
        for (String runWindow : allWindows) {
        	 System.out.println("window ID=" + runWindow);
        	 //Kiem tra neu ID cua cua so nao ma khac voi ID cua parent thi switch qua
             if (!runWindow.equals(parentID)) {
                 //switch qua cua so do
                 driver.switchTo().window(runWindow);
                 break;
             }
        }
    }
    public void switchToChildWindowByTitle(String expectedTitle) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
             driver.switchTo().window(runWindows);
             String currentWin = driver.getTitle();
             if (currentWin.equals(expectedTitle)) {
                  //Neu no kiem tra thay ID khac voi parent thi break ra khoi vong lap luon
                  break;
             }
        }
    }
    
    //close without parent window/tab
    public boolean closeAllWithoutParentWindows(String parentID) {
    	//lay ra tat ca cac ID cua cua so dang co
        Set<String> allWindows = driver.getWindowHandles();
        //Dung vong lap duyet qua tung cua so
        for (String runWindows : allWindows) {
        	 //Kiem tra cua so nao ma khac voi ID cua parent 
             if (!runWindows.equals(parentID)) {
            	 //switch qua tab truoc vi khong thi se dong cai tab hien tai
                 driver.switchTo().window(runWindows);
                 driver.close();
             }
        }
        //khi con duy nhat 1 cua so chinh la parent thi swich qua luon (step5)
        driver.switchTo().window(parentID);
        if (driver.getWindowHandles().size() == 1)
              return true;
        else
              return false;
    }
    
    @AfterTest
    public void afterTest() {
    	driver.quit();
    	
    }
    
 }