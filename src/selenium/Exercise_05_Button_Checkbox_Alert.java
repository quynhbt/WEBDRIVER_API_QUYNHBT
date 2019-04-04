package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;


		public class Exercise_05_Button_Checkbox_Alert {
			WebDriver driver;
			JavascriptExecutor jsExecutor;

	
			@BeforeTest
			public void beforeTest() {
				driver = new FirefoxDriver();
	 
				//khoi tao va truyen driver vao
				jsExecutor = (JavascriptExecutor) driver;
	 
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.manage().window().maximize();
			}
	
			public void TC_01_Button_Click() {
				driver.get("http://live.guru99.com/");
				WebElement myAccountLink  = driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']"));
				//Click thong thuong cua selenium
				myAccountLink.click();
		
				Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");
		
				WebElement createAccountLink = driver.findElement(By.xpath("//a[@title='Create an Account']"));
				createAccountLink.click();
		
				Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");
			}
	
				public void TC_02_Button_JSClick() {
					driver.get("http://live.guru99.com/");
					WebElement myAccountLink  = driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']"));
					//click vao Elenment bang javascript
					//arguments[0]: la click vao element cua myAccountLink
					jsExecutor.executeScript("arguments[0].click();", myAccountLink);
		
					Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");
		
					WebElement createAccountLink = driver.findElement(By.xpath("//a[@title='Create an Account']"));
					jsExecutor.executeScript("arguments[0].click();", createAccountLink);
		
					Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");
		
				}
				
			public void TC_03_Default_Checkbox() throws Exception {
				driver.get("https://daominhdam.github.io/basic-form/index.html");
				WebElement developmentCheckbox = driver.findElement(By.xpath("//label[text()='Development']/preceding-sibling::input"));
				
				developmentCheckbox.click();
				Assert.assertTrue(developmentCheckbox.isSelected());
				developmentCheckbox.click();
				Thread.sleep(1000);
				Assert.assertFalse(developmentCheckbox.isSelected());
				
			}
		
			public void TC_04_Custom_Checkbox() throws Exception {
				//neu default(khong co hieu ung gi) thi dung click selenium, verify dung ham isSelected
				//custom(hieu ung) thi dung click cua javascript
				//trong bai nay checkbox dang bi che nen k dung click thong thuong duoc, con k bi an thi dung click thong thuong
				//lable click dk k verify dk, input thi nguoc lai nen dung ket hop ca 2
				
				driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
				
				//click
				WebElement dualZoneCheckbox = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
				jsExecutor.executeScript("arguments[0].click();", dualZoneCheckbox);
				
				//kiem tra no duoc chon thanh cong
				
				Assert.assertTrue(dualZoneCheckbox.isSelected());
				Thread.sleep(2000);
			
				//uncheck

				jsExecutor.executeScript("arguments[0].click();", dualZoneCheckbox);
			
				//kiem tra
				Assert.assertFalse(dualZoneCheckbox.isSelected());
			
			}
			
		
			public void TC_05_Custom_Radiobutton() {
				driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
				
				WebElement carengienrbutton = driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
				jsExecutor.executeScript("arguments[0].click();", carengienrbutton);
						
			}
			
			public void TC_06_Accept_Confirm_Prompt_Alert() {
				driver.get("https://daominhdam.github.io/basic-form/index.html");
				driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
				
				//khai bao alert
				Alert aceptAlert = driver.switchTo().alert();
			
				Assert.assertEquals(aceptAlert.getText(), "I am a JS Alert");
				
				
				//Để thực hiện click OK
				aceptAlert.accept();
				
				Assert.assertTrue(driver.findElement(By.xpath("//p[@id='result' and text()='You clicked an alert successfully ']")).isDisplayed());
				
				driver.navigate().refresh();
				//2
				driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
			
				Alert JsConfirmAlert = driver.switchTo().alert();
				
				Assert.assertEquals(JsConfirmAlert.getText(), "I am a JS Confirm");
				JsConfirmAlert.dismiss();
				
				Assert.assertTrue(driver.findElement(By.xpath("//p[@id='result' and text()='You clicked: Cancel']")).isDisplayed());
				
				driver.navigate().refresh();
				//3
				String textimput = "Bui Thi Quynh";
				
				driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
				
				Alert JsPrompt = driver.switchTo().alert();
				
				Assert.assertEquals(JsPrompt.getText(), "I am a JS prompt");
				JsPrompt.sendKeys(textimput);
				
				aceptAlert.accept();
				Assert.assertTrue(driver.findElement(By.xpath("//p[@id='result' and text()='You entered: " + textimput +  "']")).isDisplayed());
				
			}
			
			@Test
			public void TC_07_AuthenticationAlert() {
				driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
				Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
				
			}
			public void checkToCheckbox(WebElement element) {
				if (!element.isSelected()) {
					 jsExecutor.executeScript("arguments[0].click();", element);
				 }
			 }
			 
			 public void uncheckToCheckbox(WebElement element) {
				 if (element.isSelected()) {
					 jsExecutor.executeScript("arguments[0].click();", element);

				 }		 
			 }
			 
					
			@AfterTest
			public void afterTest() {
				driver.quit();
			}
		}
