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

public class Topic_03_WebBrowser_WebElement_Commands {
    WebDriver driver;
	String firstName = "Automation";
	String lastName = "Testing";
	private String randomNumber;
	String email = "quynh" + randomNumber + "@gmail.com";
	String password = "123456";
	
	
	By EmailTextbox = By.xpath("//input[@id='mail']");
	By ageUnder18Radio = By.xpath("//input[@id='under_18']");
	By EducationTextArea = By.xpath("//textarea[@id='edu']");
	By jobRoleDrodown = By.xpath("//select[@id='job1']");
	By developmentCheckbox = By.xpath("//input[@id='development']");
	By slider = By.xpath("//input[@id='slider-1']");
	By disabledButton = By.xpath("//button[@id='button-disabled']");
	
	By Password = By.xpath("//input[@id='password']");
	By Radiobutton = By.xpath("//input[@id='radio-disabled']");
	By BioTextArea = By.xpath("//input[@id='bio']");
	By jobRole = By.xpath("//select[@id='job2']");
	By Checkbox = By.xpath("//input[@id='check-disbaled']");
	By slider2 = By.xpath("//input[@id='slider-2']");
	By EnabledButton = By.xpath("//button[@id='button-enabled']");
			
	
 @BeforeTest
 public void beforeTest() {
	 driver = new FirefoxDriver();
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 driver.manage().window().maximize();
	 }
 
  @Test
public void TC01_CheckDisplayed() {
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  
	  if (isElementDisplayed(EmailTextbox));
	  	driver.findElement(EmailTextbox).sendKeys("Automation Testing");
	  	
	  if (isElementDisplayed(EducationTextArea));
	  	driver.findElement(EducationTextArea).sendKeys("Automation Testing");
	  	
	  if (isElementDisplayed(ageUnder18Radio));
	  	driver.findElement(ageUnder18Radio).click();  
	
  }
  
  @Test
  public void TC_02_CheckDisplayedEnable() {
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  
	  Assert.assertTrue(isElementEnabled(EmailTextbox));
	  Assert.assertTrue(isElementEnabled(ageUnder18Radio));
	  Assert.assertTrue(isElementEnabled(EducationTextArea));
	  Assert.assertTrue(isElementEnabled(jobRoleDrodown));
	  Assert.assertFalse(isElementEnabled(disabledButton));
	  Assert.assertTrue(isElementEnabled(slider));
							  
  }
  
  @Test
  public void TC_03_ChecktoElement() {
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  checkToCheckbox(ageUnder18Radio);
	  checkToCheckbox(developmentCheckbox);
	  Assert.assertTrue(isElementSelected(ageUnder18Radio));
	  Assert.assertTrue(isElementSelected(developmentCheckbox));
	  uncheckToCheckbox(developmentCheckbox);
	  Assert.assertFalse(isElementSelected(developmentCheckbox));
  }
	@AfterTest
	  public void afterTest() {
		driver.quit();
	  }
	
  public int randomNumber() {
	  Random random = new Random();
	  int number = random.nextInt(999999);
	  System.out.println("Random number = " + number);
	  return number;
	  
  }
  
  private boolean isElementDisplayed(By byValue) {
	  if (driver.findElement(byValue).isDisplayed()) {
		System.out.println("Element [" + byValue +"] is disabled!");
		return true;
	  } else {
		 System.out.println("Element [" + byValue +"] is enabled!");
		 return false;
	  }
  }
  private boolean isElementEnabled(By byValue) {
	  if (driver.findElement(byValue).isEnabled()) {
		  System.out.println("Element [" + byValue +"] is enabled!");
		  return true;
	  } else {
		  System.out.println("Element [" + byValue +"] is disabled");
		  return false;
	  }
	  
  }
 

  public boolean isElementSelected(By byValue) {
	  if(driver.findElement(byValue).isSelected()) {
		  System.out.println("Element [" + byValue + "] is selected!");
		  return true;
	  } else {
		  System.out.println("Element [" + byValue + "] is de-selected");
		  return false;
	  } 
	  
  }
 public void checkToCheckbox(By byValue) {
	 WebElement element = driver.findElement(byValue);
	 if (!element.isSelected()) {
		 element.click();
		 System.out.println("Element [" + byValue + "]is selected!");
	 }
 }
 
 public void uncheckToCheckbox(By byValue) {
	 WebElement element = driver.findElement(byValue);
	 if (element.isSelected()) {
		 element.click();
		 System.out.println("Element [" + byValue + "]is selected!");
	 }		 
 }
	 
  
}
