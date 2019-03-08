package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

@Test
public class Topic_02_Xpath_Css_Excersice {
	WebDriver driver;
	
 @BeforeTest
 public void beforeTest() {
	 driver = new FirefoxDriver();
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 driver.manage().window().maximize();
	 driver.get("http://live.guru99.com/");
	 }
 
  public void TC01_LoginWithEmailAndPasswordEmpty() throws Exception {
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  Thread.sleep(2000);
	  
	  driver.findElement(By.id("email")).sendKeys("");
	  driver.findElement(By.name("login[password]")).sendKeys();
	  driver.findElement(By.xpath("//button[@title='Login']")).click();
	  
	  String emailRequired = driver.findElement(By.id("advice-required-entry-email")).getText();
	  Assert.assertEquals(emailRequired, "This is a required field.");
	  
	  String passRequired = driver.findElement(By.id("advice-required-entry-pass")).getText();
	  Assert.assertEquals(passRequired, "This is a required field.");
	  
	  
	  	   }
  
  public void TC_02_LoginWithEmailInvalid() throws Exception {
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  Thread.sleep(2000);
	  
	  driver.findElement(By.id("email")).sendKeys("123434234@12312.123123");
	  driver.findElement(By.name("login[password]")).sendKeys("123123");
	  driver.findElement(By.xpath("//button[@title='Login']")).click();
	  
	  String emailInvalid = driver.findElement(By.id("advice-validate-email-email")).getText();
	  Assert.assertEquals(emailInvalid, "Please enter a valid email address. For example johndoe@domain.com.");
  }
  
  public void TC_03_LoginWithPasswordLessThanSixChar() throws Exception {
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  Thread.sleep(2000);
	  
	  driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
	  driver.findElement(By.name("login[password]")).sendKeys("123");
	  driver.findElement(By.xpath("//button[@title='Login']")).click();
	  
	  String passError = driver.findElement(By.id("advice-validate-password-pass")).getText();
	  Assert.assertEquals(passError, "Please enter 6 or more characters without leading or trailing spaces.");
  }
  
  public void TC_04_LoginWithPasswordIncorrect() throws Exception {
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  Thread.sleep(2000);
	  
	  driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
	  driver.findElement(By.name("login[password]")).sendKeys("123123123");
	  driver.findElement(By.xpath("//button[@title='Login']")).click();
	  
	  String loginError = driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[2]/div/div/div/ul/li/ul/li/span")).getText();
	  Assert.assertEquals(loginError, "Invalid login or password.");
  }
  
  public void TC_05_CreateanAccount() throws Exception {
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  Thread.sleep(2000);
	  
	  driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
	  driver.findElement(By.id("firstname")).sendKeys("Linh");
	  driver.findElement(By.id("middlename")).sendKeys("Hanh");
	  driver.findElement(By.id("lastname")).sendKeys("Mai");
	  
	  Random rd = new Random();
	  int number= rd.nextInt();
	  String email= "tung1" + number + "@gmail.com";
	  driver.findElement(By.id("email_address")).sendKeys(email);
	  
	  driver.findElement(By.id("password")).sendKeys("123123");
	  driver.findElement(By.id("confirmation")).sendKeys("123123");
	  driver.findElement(By.xpath("//button[@title='Register']")).click();
	  Thread.sleep(2000);

	  String successfullMsg = driver.findElement(By.xpath("//div[@class='my-account']//ul[@class='messages']//span")).getText();
	  Assert.assertEquals(successfullMsg, "Thank you for registering with Main Website Store.");
	  
	  driver.findElement(By.xpath("//a[contains(@class,'skip-account')]")).click();
	  driver.findElement(By.xpath("//a[@title='Log Out']")).click();
			  
	  Thread.sleep(5000);
	  Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/");
  }


@AfterTest
  public void afterTest() {
	driver.quit();
  }
}










