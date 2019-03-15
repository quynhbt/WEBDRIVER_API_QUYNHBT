package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

@Test
public class Topic_04_TextboxTextarea {
    WebDriver driver;
    String customerName, Gender, dateOfBirth, Address, City, State, Pin, mobileNumber, email, password, customerID;
    String editcustomerName, editGender, editdateOfBirth, editAddress, editCity, editState, editPin, editmobileNumber, editemail, editpassword;
	
	By customerNameTextbox = By.xpath("//input[@name='name']");
	By GenderRadiobutton = By.xpath("//input[@value='m']");
	By dateofBirth = By.xpath("//input[@name='dob']");
	By AddressTextbox = By.xpath("//textarea[@name='addr']");
	By CityTextbox = By.xpath("//input[@name='city']");
	By StateTextbox = By.xpath("//input[@name='state']");
	By PinTextbox = By.xpath("//input[@name='pinno']");
	By MobilenumberTextbox = By.xpath("//input[@name='telephoneno']");
	By emailTextbox = By.xpath("//input[@name='emailid']");		
	By passwordTextbox = By.xpath("//input[@name='password']");
	By submitButton = By.xpath("//input[@name='sub']");
	
	
 @BeforeTest
 public void BeforeTest() {
	 driver = new FirefoxDriver();
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 driver.manage().window().maximize();
	 driver.get("http://demo.guru99.com/v4");
	 driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr181358");
	 driver.findElement(By.xpath("//input[@name='password']")).sendKeys("berydUp");
	 driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	 Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
 
 	customerName   = "Automation Testing";
 	Gender = "male";
 	dateOfBirth = "1995-07-24";
 	Address = "123 Kien Quoc";
 	City = "Hai Duong";
 	State = "Ninh Giang";
 	Pin = "123456";
 	mobileNumber = "0966469326";
 	email = "buihanh" +randomNumber() + "@gmail.com";
 	password = "123123";
 	
 	editAddress = "345 Kien Quoc";
 	editCity = "Hai Hung";
 	editState = "12 Ninh Giang";
 	editPin = "654321";
 	editmobileNumber = "0966469111";
 	editemail = "quynh" + randomNumber() + "@gmail.com";
 	editpassword = "111111";
 }
 
 @Test
 public void TC_01_CreatCustomer() throws Exception {
	 driver.findElement(By.xpath("//a[text()='New Customer']")).click();
	 Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Add New Customer']")).isDisplayed());
	 
	 driver.findElement(customerNameTextbox).sendKeys(customerName);
	 driver.findElement(GenderRadiobutton).sendKeys(Gender);
	 driver.findElement(dateofBirth).sendKeys(dateOfBirth);
	 driver.findElement(AddressTextbox).sendKeys(Address);
	 driver.findElement(CityTextbox).sendKeys(City);
	 driver.findElement(StateTextbox).sendKeys(State);
	 driver.findElement(PinTextbox).sendKeys(Pin);
	 driver.findElement(MobilenumberTextbox).sendKeys(mobileNumber);
	 driver.findElement(emailTextbox).sendKeys(email);
	 driver.findElement(passwordTextbox).sendKeys(password);
	 driver.findElement(submitButton).click();
	 Thread.sleep(20000);
	 
	 Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']")).isDisplayed());
	 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
	 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), Gender);
	 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dateOfBirth);
	 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), Address);
	 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), City);
	 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), State);
	 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), Pin);
	 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), mobileNumber);
	 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);
	 
	 customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
	 System.out.println("Customer at TC_01 = " + customerID);
	 
 }
 
 public void TC_02_EditCustomer() {
	 driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
	 driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
	 System.out.println("Customer at TC_02" + customerID);
	 driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
	 Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Edit Customer']")).isDisplayed());
	 
	 driver.findElement(AddressTextbox).clear();
	 driver.findElement(AddressTextbox).sendKeys(editAddress);
	 driver.findElement(CityTextbox).clear();
	 driver.findElement(CityTextbox).sendKeys(editCity);
	 driver.findElement(StateTextbox).clear();
	 driver.findElement(StateTextbox).sendKeys(editState);
	 driver.findElement(PinTextbox).clear();
	 driver.findElement(PinTextbox).sendKeys(editPin);
	 driver.findElement(MobilenumberTextbox).clear();
	 driver.findElement(MobilenumberTextbox).sendKeys(editmobileNumber);
	 driver.findElement(emailTextbox).clear();
	 driver.findElement(emailTextbox).sendKeys(editemail);
	 driver.findElement(submitButton).click();
	 
	 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), editAddress);
	 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCity);
	 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), editState);
	 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPin);
	 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), editmobileNumber);
	 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), editemail);
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
	  }