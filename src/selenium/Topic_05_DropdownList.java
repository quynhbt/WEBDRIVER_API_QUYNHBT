package selenium;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Topic_05_DropdownList {
	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor javascripExecutor;

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 30);
		javascripExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();	
	}

	 @Test
	  public void TC_01_DefaulDropDown() throws Exception{
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		WebElement jobRole1 = driver.findElement(By.xpath("//select[@id='job1']"));
		 Select jobRoleSelecte = new Select(jobRole1);
		 //2 - Kiểm tra dropdown Job Role 01 không hỗ trợ thuộc tính multi-select
		 Assert.assertFalse(jobRoleSelecte.isMultiple());
		 //3 - Chọn giá trị Automation Tester trong dropdown bằng phương thức selectVisible
		 jobRoleSelecte.selectByVisibleText("Automation Tester");
		 //4 - Kiểm tra giá trị đã được chọn thành công
		 Thread.sleep(3000);
		 Assert.assertEquals(jobRoleSelecte.getFirstSelectedOption().getText(),"Automation Tester");
		 //5 - Chọn giá trị Manual Tester trong dropdown bằng phương thức selectValue
		 jobRoleSelecte.selectByValue("manual");
		 Thread.sleep(3000);
		 //6 - Kiểm tra giá trị đã được chọn thành công
		 Assert.assertEquals(jobRoleSelecte.getFirstSelectedOption().getText(),"Manual Tester");
         //	7 - Chọn giá trị Mobile Tester trong dropdown bằng phương thức selectIndex
		 jobRoleSelecte.selectByIndex(3);
		 Thread.sleep(3000);
		 //8 - Kiểm tra giá trị đã được chọn thành công
		 Assert.assertEquals(jobRoleSelecte.getFirstSelectedOption().getText(),"Mobile Tester");
		 //9 - Kiểm tra dropdown có đủ 5 giá trị
		 Thread.sleep(3000);
		 Assert.assertEquals(jobRoleSelecte.getOptions().size(),5);

		 //Get ra giá trị đã chọn
		 jobRoleSelecte.getFirstSelectedOption().getText();

		 //Get ra số lượng item trong dropdown
		 jobRoleSelecte.getOptions().size();
		 //Kiểm tra dropdown co/ko hỗ trọn multiple-select
		 Assert.assertFalse(jobRoleSelecte.isMultiple());

		 //Select giá trị ctrong drop dơn theo index(chỉ số)
		 jobRoleSelecte.selectByIndex(3);

		//Select giá trị ctrong drop dơn theo atribute value
		jobRoleSelecte.selectByValue("website");

		//Select giá trị ctrong drop dơn theo atribute value
				jobRoleSelecte.selectByVisibleText("Mobile Tester");

}	
	 //1-click vào dropdownlist để xổ ra tất cả các giá trị
	 //2- chờ tất cả các giá trị trong dropdown list load ra thành công
	 //3- Scroll đến item cần chọn(nếu như item cần chọn nhìn thấy thì không cần scroll)
	 //4- Click vào item cần chọn
	 //5- Kiểm tra giá trị đã chọn thành công
	 @Test
	  public void TC_02_CustomDropdownList() throws Exception{
		 driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		 selectItemInCustomerDropDown("//span[@id='number-button']","//ul[@id='number-menu']//li[@class='ui-menu-item']/div", "12");
		 Assert.assertTrue(isElementDisplayed("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='12']"));

		 selectItemInCustomerDropDown("//span[@id='number-button']","//ul[@id='number-menu']//li[@class='ui-menu-item']/div", "2");
		 Assert.assertTrue(isElementDisplayed("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='2']"));

	 }


	 @Test
	 public void TC_03_CustomAngularDropdown() throws Exception {
		driver.get("https://material.angular.io/components/select/examples");

		selectItemInCustomerDropDown("//div[@class='mat-form-field-infix']//mat-label[text()='State']", "//mat-option/span", "Wyoming");
		Assert.assertTrue(isElementDisplayed("//div[@class='mat-select-value']//span[text()='Wyoming']"));
		Thread.sleep(3000);

		selectItemInCustomerDropDown("//div[@class='mat-form-field-infix']//mat-label[text()='State']", "//mat-option/span", "Kansas");
		Assert.assertTrue(isElementDisplayed("//div[@class='mat-select-value']//span[text()='Kansas']"));
		Thread.sleep(3000);

		selectItemInCustomerDropDown("//div[@class='mat-form-field-infix']//mat-label[text()='State']", "//mat-option/span", "California");
		Assert.assertTrue(isElementDisplayed("//div[@class='mat-select-value']//span[text()='California']"));
		Thread.sleep(3000);
	}

	 @Test
	 public void TC_04_Telerik() throws Exception{
		 driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");
		selectItemInCustomerDropDown("//span[@class='k-input' and text()='Black']", "//ul[@id='color_listbox']/li", "Orange");
	    Assert.assertTrue(isElementDisplayed("//span[@class='k-input' and text()='Orange']"));
	    Thread.sleep(1000);
	 }

	 @Test
	 public void TC_05_MikerodhamVueJS() throws Exception{
		 driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemInCustomerDropDown("//li[@class='dropdown-toggle']", "//div[@class='btn-group']//ul[@class='dropdown-menu']/li" , "Second Option");
		 Assert.assertTrue(isElementDisplayed("//div[@class='btn-group']/li[@class='dropdown-toggle'and contains(text(),'Second Option')]"));
		 Thread.sleep(1000);

		 selectItemInCustomerDropDown("//li[@class='dropdown-toggle']", "//div[@class='btn-group']//ul[@class='dropdown-menu']/li" , "First Option");
		 Assert.assertTrue(isElementDisplayed("//div[@class='btn-group']/li[@class='dropdown-toggle'and contains(text(),'First Option')]"));
		 Thread.sleep(1000);

		 selectItemInCustomerDropDown("//li[@class='dropdown-toggle']", "//div[@class='btn-group']//ul[@class='dropdown-menu']/li" , "Third Option");
		 Assert.assertTrue(isElementDisplayed("//div[@class='btn-group']/li[@class='dropdown-toggle'and contains(text(),'Third Option')]"));
	     Thread.sleep(1000);
	 }

//	 @Test
//	 public void TC_06_CustomMutilElement() throws Exception{
//		 driver.get("http://multiple-select.wenzhixin.net.cn/examples/#basic.html");
//		 By contentIframeXpath=By.xpath("//div[@class='content']//iframe");
//		 String[] items= {"July","May","June"};
//		 String[] newItems= {"October","July","May","June","Augest"};
//		 
//		 WebElement contantIframe = driver.findElement(contentIframeXpath);
//		 driver.switchTo().frame(contantIframe);
//		 selectMutilElement("//button[@class='ms-choice']", "//div[@class='ms-drop bottom']//ul//li", items);
//			 
//	 }

//	 public void selectMutilElement(String parentXpath, String allItemXpath, String[] listexpectedValueItem) throws Exception{
		//1-click vào dropdownlist để xổ ra tất cả các giá trị
		 //2- chờ tất cả các giá trị trong dropdown list load ra thành công
		 //3- Scroll đến item cần chọn(nếu như item cần chọn nhìn thấy thì không cần scroll)
		 //1
//		 WebElement parentDropDown = driver.findElement(By.xpath(parentXpath)); 
//		 parentDropDown.click();
//		 //2
//		 waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
//		 //3
//		 List<WebElement> allVItems= driver.findElements(By.xpath(allItemXpath));
//		 for(WebElement children: allVItems) {
//			 for(String valueChildren: listexpectedValueItem) {
//				 if(children.getText().equals(valueChildren)) {
//					 //3
//					 javascripExecutor.executeScript("arguments[0].scrollIntoView(true);",children);
//					 //4
//					 javascripExecutor.executeScript("arguments[0].click();", children);
//					
//					 List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
//				 System.out.println("Item selected ="+itemSelected.size());
//				 if(listexpectedValueItem.length==itemSelected.size())
//					 break;
//				 }
//				 
//			 }
//			 
//		 }
//		 
//		 
//	 }

	public void selectItemInCustomerDropDown(String perantXpath, String allItemXpath, String exceptValueItem) throws Exception{
		WebElement parentDropdown = driver.findElement(By.xpath(perantXpath));
		 //1-click vào dropdownlist để xổ ra tất cả các giá trị
		javascripExecutor.executeScript("arguments[0].click();", parentDropdown);
		//2- chờ tất cả các giá trị trong dropdown list load ra thành công
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));

	List <WebElement> allItems = driver.findElements(By.xpath(allItemXpath));
	for(WebElement childen : allItems) {
		if(childen.getText().contains(exceptValueItem)) {
			//3- Scroll đến item cần chọn(nếu như item cần chọn nhìn thấy thì không cần scroll)
			javascripExecutor.executeScript("arguments[0].scrollIntoView(true);", childen);
			//4- Click vào item cần chọn
			childen.click();
			Thread.sleep(1000);
			break;
		}
	  }

	 }
	 private boolean isElementDisplayed(String xpathValue) {
			WebElement element = driver.findElement(By.xpath(xpathValue));
			if(element.isDisplayed()) {
				return true;
			}else {
				return false;
			}
	}

  @AfterTest
  public void afterTest() {
//driver.quit();
  }

}