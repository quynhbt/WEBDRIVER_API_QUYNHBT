package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNG_02_TestngGroup {
	  @Test(groups = "payment" , description = "Description de mieu ta ten testcase", priority = 3)
	  public void TC_01() {
		  System.out.println("Run Testcase 01");
	  }
	  
	  @Test(groups = "payment", priority = 6, enabled = false)
	  public void TC_02() {
		  System.out.println("Run Testcase 02");
	  }
	  
	  @Test(groups = "customer", priority = 1)
	  public void TC_03() {
		  System.out.println("Run Testcase 03");
	  }
	  
	  @Test(groups = "customer", priority = 2)
	  public void TC_04() {
		  System.out.println("Run Testcase 04");
	  }
	  
	  @Test(groups = "order", priority = 4)
	  public void TC_05() {
		  System.out.println("Run Testcase 05");
	  }
	  
	  @Test(groups = "order")
	  public void TC_06() {
		  System.out.println("Run Testcase 06");
	  }
}