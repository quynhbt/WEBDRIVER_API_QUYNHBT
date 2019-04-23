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

public class TestNG_01_Annotations {
	  @Test
	  public void TC_01() {
		  System.out.println("Run Testcase 01");
	  }
	  
	  @Test
	  public void TC_02() {
		  System.out.println("Run Testcase 01");
	  }
	  
	  @BeforeMethod
	  public void beforeMethod() {
		  System.out.println("Run Testcase 01");
	  }
	
	  @AfterMethod
	  public void afterMethod() {
		  System.out.println("Run afterMethod");
	  }

	  @BeforeClass
	  public void beforeClass() {
		  System.out.println("Run beforeClass");
	  }
	
	  @AfterClass
	  public void afterClass() {
		  System.out.println("Run afterClass");
	  }
	
	  @BeforeTest
	  public void beforeTest() {
		  System.out.println("Run beforeTest");
	  }
	
	  @AfterTest
	  public void afterTest() {
		  System.out.println("Run afterTest");
	  }
	
	  @BeforeSuite
	  public void beforeSuite() {
		  System.out.println("Run beforeSuite");
		  
	  }
	
	  @AfterSuite
	  public void afterSuite() {
		  System.out.println("Run afterSuite");
	  }
	
	}
