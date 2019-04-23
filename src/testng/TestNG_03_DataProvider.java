package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNG_03_DataProvider {
	WebDriver driver;
	
	@BeforeTest
	public void preCondition() {
//		driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://live.guru99.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test(dataProvider = "userPassInfo")
	public void Login_01_LoginWithValidInformation(String username, String password) {
		 driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();
		 driver.findElement(By.xpath("//input[@id='email']")).sendKeys(username);
		 driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(password);
		 driver.findElement(By.xpath("//button[@id='send2']")).click();
		 
		 Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
		 
		 driver.findElement(By.xpath("//a[@class='skip-link skip-account']//span[text()='Account']")).click();
		 driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		 
		 Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(), 'This is demo site for')]")).isDisplayed());

	}
	
	@DataProvider
	public Object[][] userPassInfo() {
		return new Object[][] {
			{"auto_test_05@gmail.com", "123123"},
			{"auto_test_06@gmail.com", "123123"},
			{"auto_test_07@gmail.com", "123123"} };
		
	}
	
	@AfterTest
	public void postCondition() {
		driver.quit();
	}
	 
	
}
