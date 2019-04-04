package selenium;

import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class Exercise_06_UserInteractions {
    WebDriver driver;
	//khai báo thư viện
	Actions action;
	
  
	@BeforeTest
  public void beforeTest() {
//    cài chromedriver tương ứng với chrome mình dùng
	  System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
	  
	  driver = new ChromeDriver();
	  driver = new FirefoxDriver();
	  //khoi tao
	  action = new Actions(driver);
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }
  @Test
  public void TC_01_HoverMouser() {
	  driver.get("http://www.myntra.com/");
	  
	  WebElement profileIcon = driver.findElement(By.xpath("//span[contains(@class,'desktop-iconUser')]"));
	  //hover chuot
	  action.moveToElement(profileIcon).perform();
	  
	  WebElement loginButton = driver.findElement(By.xpath("//a[@class='desktop-linkButton' and text()='log in']"));
	  loginButton.click();
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='login-box']")).isDisplayed());
	  
  }
  @Test
  public void TC_02_ClickandHold() throws Exception {
	  driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
	  
	  List <WebElement> numberItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
	  System.out.println("Element truoc khi chon = " + numberItems.size());

	  //click and hold tu 1 - 4 giong nhu mang se tinh tu 0 tuc la 0-3
	  
	  action.clickAndHold(numberItems.get(0)).moveToElement(numberItems.get(3)).release().perform();
	  
	  Thread.sleep(3000);
	  
	  //kiem tra 4 phan tu duoc chon thanh cong
	  List <WebElement> numberItemsSelected = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
	  System.out.println("Element sau khi chon = " + numberItemsSelected.size());
	  
	  Assert.assertEquals(numberItemsSelected.size(), 4);
	  
  }
  
  @Test
  public void TC_03_ClickAndHoldRandom() {
	  driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
	  
	  List <WebElement> numberItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
	  
	  action.keyDown(Keys.CONTROL).build().perform();
	  action.click(numberItems.get(0));
	  action.click(numberItems.get(2));
	  action.click(numberItems.get(4));
	  action.click(numberItems.get(6));
	  action.keyUp(Keys.CONTROL).perform();
	  
	  List <WebElement> numberItemsSelected = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
	  System.out.println("Element sau khi chon = " + numberItemsSelected.size());
	  
	  Assert.assertEquals(numberItemsSelected.size(), 4);  
	  
  }
  
  @Test
  public void TC_04_DoubleClick() throws Exception {
	  driver.get("http://www.seleniumlearn.com/double-click");
	  
	  WebElement doubleClickbutton = driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));
	  
	  action.doubleClick(doubleClickbutton).perform();
	  
	  Thread.sleep(3000);
	  
	  Alert alert = driver.switchTo().alert();
	  Assert.assertEquals(alert.getText(), "The Button was double-clicked.");
	  alert.accept();
	  
  }
 
  @Test
  public void TC_05_RightClick() throws Exception {
	  driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
	  

	  WebElement rightClickBtn = driver.findElement(By.xpath("//span[text()='right click me']"));
	  action.contextClick(rightClickBtn).perform();

	  //hover
	  WebElement quitButton = driver.findElement(By.xpath("//li[contains(@class, 'context-menu-icon-quit')]"));
	  action.moveToElement(quitButton).perform();

	  //check có visible and hover
	  //thứ tự của class xpath trên FF quit-visible-hover, Chrome: quit-hover-visible
	  Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class, 'context-menu-icon-quit') and contains(@class,'context-menu-hover') and contains(@class,'context-menu-visible')]")).isDisplayed());

	  quitButton.click();
	  //alert này chỉ bật cho chrome, firefox k support
	  Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: quit");
	  driver.switchTo().alert().accept();

	  Thread.sleep(2000);
	  //check xong thao tác, element k còn hover và visible nữa
	  Assert.assertFalse(quitButton.isDisplayed());

  }
  
  @Test
  public void TC_06_Draganddrop() {
	  driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
	  
	  WebElement sourceButton = driver.findElement(By.xpath("//*[@id='draggable']"));
	  WebElement targetButton = driver.findElement(By.xpath("//*[@id='droptarget']"));
	  action.dragAndDrop(sourceButton, targetButton).build().perform();
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@id='droptarget' and text()='You did great!']")).isDisplayed());
  }
  @AfterTest
	public void afterTest() {
		driver.quit();
	}
}
