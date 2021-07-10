package test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import pom.SearchResult;
import utils.Common;

public class Base {

	static WebDriver driver;
	Common common;

	@Parameters({"browser","url"})
	@BeforeTest(alwaysRun = true)
	public void beforeTest(String browser,String url) {

		common=new Common();
		common.setUp(false, browser, url);
		driver=common.getDriver();

	}
	
	
	  @BeforeTest(alwaysRun = true)
	  public void closeModal()
	  { 
		  SearchResult search= new SearchResult(driver); 
		  search.closeModal();
	  
	  }
	 

	@AfterTest(alwaysRun =true,
			description="Close the browser")
	public void afterTest() {
		System.out.println("closing browser");
		common.quitBrowser();
	}

}
