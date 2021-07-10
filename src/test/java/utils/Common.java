package utils;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Common {
	
	WebDriver driver;
	String gridUrl="http://10.10.10.190:4444/wd/hub";
	

	public void setUp(boolean grid, String browser, String url) {


		if(grid) {
			setupGrid(browser, url);
		} else {
			setupBrowser(browser, url);
		}
		driver.manage().window().maximize();

		if(url!="") {
			driver.get(url);
		}
		else {
			driver.get("about:blank");
		}
	}

	public void setupGrid(String browser, String url) {
		
		System.out.println("****Executing  using grid***");
		
		DesiredCapabilities cap=null;
		if(browser.equalsIgnoreCase("chrome")){
			cap = DesiredCapabilities.chrome();

		} 
		else if (browser.equalsIgnoreCase("firefox")){
			cap = DesiredCapabilities.firefox();
		}
		else {
			System.out.println("No valid browser, hence quitiing the automation run");
			System.exit(0);
		}

		try {
			driver = new RemoteWebDriver(new URL(gridUrl), cap);

		} catch (MalformedURLException e) {
			System.out.println("Something went wrong in invoking the grid");
			e.printStackTrace();
		}

	}

	public void setupBrowser(String browser, String url)
	{
		String currDir = System.getProperty("user.dir");

		if (browser.equalsIgnoreCase("chrome"))

		{
			System.setProperty("webdriver.chrome.driver", currDir + "\\drivers\\chromedriver.exe" );
			driver = new ChromeDriver();
		} 
		else if(browser.equalsIgnoreCase("firefox"))

		{
			System.setProperty("webdriver.gecko.driver", currDir + "\\drivers\\geckodriver.exe" );
			driver = new FirefoxDriver();

		}
		else {
			System.out.println("No valid browser, hence quitiing the automation run");
			System.exit(0);
		}
	}

	public WebDriver getDriver() {

		return this.driver;
	}

	public void closeBrowser() {
		driver.close();
	}

	public void quitBrowser() {
		driver.quit();
	}
}

