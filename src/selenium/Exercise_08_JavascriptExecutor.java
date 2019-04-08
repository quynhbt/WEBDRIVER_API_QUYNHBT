package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;

public class Exercise_08_JavascriptExecutor {
  WebDriver driver;
  

  String customerName, Gender, dateOfBirth, Address, City, State, Pin, mobileNumber, email, password, customerID;
  
  By customerNameTextboxBy = By.xpath("//input[@name='name']");
  By GenderRadiobutton = By.xpath("//input[@value='m']");
  By dateofBirthBy = By.xpath("//input[@name='dob']");
  By AddressTextbox = By.xpath("//textarea[@name='addr']");
  By CityTextbox = By.xpath("//input[@name='city']");
  By StateTextbox = By.xpath("//input[@name='state']");
  By PinTextbox = By.xpath("//input[@name='pinno']");
  By MobilenumberTextbox = By.xpath("//input[@name='telephoneno']");
  By emailTextbox = By.xpath("//input[@name='emailid']");		
  By passwordTextbox = By.xpath("//input[@name='password']");
  By submitButton = By.xpath("//input[@name='sub']");
  
  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
//	  System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
//	  driver = new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  
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
	  
  }

  public void TC_01() {
	  //01 - Truy cập vào trang: http://live.guru99.com/
	  navigateToUrlByJS("http://live.guru99.com/");

	  //02 - Sử dụng JE để get domain của page
	  String domainName = executeForBrowser("return document.domain");
	  //Verify domain =  live.guru99.com
	  Assert.assertEquals(domainName, "live.guru99.com");

	  //03 - Sử dụng JE để get URL của page
	  String url = executeForBrowser("return document.URL");
	  Assert.assertEquals(url, "http://live.guru99.com/");

	  //04 - Open MOBILE page (Sử dụng JE)
	  WebElement mobileLink = driver.findElement(By.xpath("//a[text()='Mobile']"));
	  highlightElement(mobileLink);
	  clickToElementByJS(mobileLink);


	  //05 - Add sản phẩm SAMSUNG GALAXY vào Cart (click vào ADD TO CART button bằng JE)
	  WebElement samsungCartButton = driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//button"));
	  highlightElement(samsungCartButton);
	  clickToElementByJS(samsungCartButton);

	  //06 - Verify message được hiển thị:  Samsung Galaxy was added to your shopping cart
	  //(Sử dụng JE - Get innertext of the entire webpage )
	  String innerText = executeForBrowser("return document.documentElement.innerText;");
	  Assert.assertTrue(innerText.contains("Samsung Galaxy was added to your shopping cart."));

	  //07 - Open PRIVACY POLICY page (Sử dụng JE)
	  //Verify title của page = Privacy Policy (Sử dụng JE)
	  WebElement privacyPolicyLink = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
	  highlightElement(privacyPolicyLink);
	  clickToElementByJS(privacyPolicyLink);
	  
	  String titlePage = executeForBrowser("return document.title");
	  Assert.assertEquals(titlePage, "Privacy Policy");

	  //08 - Srcoll xuống cuối page
	  scrollToBottomPage();

	  //09 - Verify dữ liệu có hiển thị với chỉ 1 xpath: 
	  WebElement verifyText = driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']/following-sibling::td[text()='The number of items in your Wishlist.']"));
	  highlightElement(verifyText);
	  Assert.assertTrue(verifyText.isDisplayed());

	  //10 - Navigate tới domain: http://demo.guru99.com/v4/  (Sử dụng JE)
	  //Verify domain sau khi navigate = demo.guru99.com
	  navigateToUrlByJS("http://demo.guru99.com/v4/");
	  String domainNavigate = executeForBrowser("return document.domain");
	  Assert.assertEquals(domainNavigate, "demo.guru99.com");

  }
  

  public void TC_02() throws Exception {
	  driver.get("http://demo.guru99.com/v4");
	  driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr187285");
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("vyqegYb");
	  driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	  Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
		
	  driver.findElement(By.xpath("//a[text()='New Customer']")).click();
	  Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Add New Customer']")).isDisplayed());
		 
	  driver.findElement(customerNameTextboxBy).sendKeys(customerName);
	  driver.findElement(GenderRadiobutton).click();
	  
	  driver.findElement(GenderRadiobutton).sendKeys(Gender);
	  
	  WebElement dateOfBirthTextbox = driver.findElement(dateofBirthBy);
	  removeAttributeInDOM(dateOfBirthTextbox, "type");
	  dateOfBirthTextbox.sendKeys(dateOfBirth);
	  
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
	  
  }
  
  @Test
  public void TC_03() {
	  driver.get("http://live.guru99.com/");
	  
	  WebElement myaccount = driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']"));
	  highlightElement(myaccount);
	  clickToElementByJS(myaccount);
	  
	  WebElement createanacc = driver.findElement(By.xpath("//span[text()='Create an Account']"));
	  highlightElement(createanacc);
	  clickToElementByJS(createanacc);
	  
	  sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='firstname']")), "Bui");
	  sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='middlename']")), "Thi");
	  sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='lastname']")), "Quynh");
	  String email = "quynh"+randomNumber()+"@gmail.com";
	  sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='email_address']")), email);
	  sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='password']")), "123123");
	  sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='confirmation']")), "123123");
	  
	  WebElement register = driver.findElement(By.xpath("//button[@title='Register']"));
	  highlightElement(register);
	  clickToElementByJS(register);
	  
	  String msgRegisterSuccess = executeForBrowser("return document.documentElement.innerText;");
	  Assert.assertTrue(msgRegisterSuccess.contains("Thank you for registering with Main Website Store."));
	  
	  WebElement accountbutton = driver.findElement(By.xpath("//div[@class='skip-links']//span[text()='Account']"));
	  clickToElementByJS(accountbutton);
	  
	  WebElement logoutbutton = driver.findElement(By.xpath("//a[text()='Log Out']"));
	  clickToElementByJS(logoutbutton);
	  
	  String titleHomePage = executeForBrowser("return document.title");
	  Assert.assertEquals(driver.getTitle(), titleHomePage);
	  
  }
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

  public void highlightElement(WebElement element) {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("arguments[0].style.color='red'", element);
      try {
		Thread.sleep(1000);
      } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
      }
  }

  public String executeForBrowser(String javaSript) {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      return (String) js.executeScript(javaSript);
  }

  public Object clickToElementByJS(WebElement element) {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      return js.executeScript("arguments[0].click();", element);
  }

  public Object sendkeyToElementByJS(WebElement element, String value) {
     JavascriptExecutor js = (JavascriptExecutor) driver;
     return js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
  }

  public void removeAttributeInDOM(WebElement element, String attribute) {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
      try {
		Thread.sleep(3000);
	  } 
      catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   }
  }

  public Object scrollToBottomPage() {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
  }

  public Object navigateToUrlByJS(String url) {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      return js.executeScript("window.location = '" + url + "'");
  }

	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(10000);
	}

}