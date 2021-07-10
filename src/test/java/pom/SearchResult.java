package pom;


import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SearchResult {

	WebDriver driver;
	WebDriverWait wait;


	@CacheLookup
	@FindBy(xpath = "//button[@class='_2KpZ6l _2doB4z']")
	WebElement close;

	@CacheLookup
	@FindBy(partialLinkText = "Explore")
	WebElement explore;

	@CacheLookup
	@FindBy(xpath = "//span[contains(text(),'Electronics')]")
	WebElement electronics;

	@CacheLookup
	@FindBy(partialLinkText = "Mi")
	WebElement mi;

	@CacheLookup
	@FindBy(xpath = "//div[@class='_1D76KH']")
	WebElement label;

	@CacheLookup
	@FindBy(xpath = "//div[@class='HQL4QS _28DFQy']")
	WebElement slider;

	@CacheLookup
	@FindBy(xpath = "//div[@class='_3uDYxP']/select")
	WebElement dropdown;

	@CacheLookup
	@FindBy(xpath = "//div[@class='_4rR01T']")
	WebElement iphone;

	@CacheLookup
	@FindBy(xpath = "//div[@class='_30jeq3 _16Jk6d']")
	WebElement pricelabel;


	/*
	 * @CacheLookup
	 * 
	 * @FindBy(xpath ="//span[@class='_2P_LDn']") WebElement check;
	 * 
	 * @CacheLookup
	 * 
	 * @FindBy(xpath ="//span[@class='YxlyDn']") WebElement details;
	 */

	public SearchResult(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}

	public void closeModal() {
		//2. Close the Login Modal Screen (Handle Modal )
		close.click();

	}

	public String verifyLabel() {

		///**NOTE: The home page doesn't have Menu Electronics >> MI link. Hence opening "ExplorePlus" page
		explore.click(); 

		//3. Hover the Menu Electronics >> MI link (Use Mouse Hover Event)		

		Actions action = new Actions(driver);
		action.moveToElement(wait.until(ExpectedConditions.elementToBeClickable(electronics))).build().perform();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Mi"))).click();

		//4. Verify ‘Latest from MI” label on the search result page
		//		String actualLabel= wait.until(ExpectedConditions.visibilityOf(label)).getText();
		String actualLabel= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='_1D76KH']"))).getText();

		return actualLabel;

	}

	public void priceSlider() {

		//5. Change the PRICE Slider (Use Actions class)
		Actions action1 = new Actions(driver);
		action1.dragAndDropBy(slider, 200, 0).perform();

	}

	public void priceDropdown() {

		//6. Choose the third option from the Max Dropdown under PRICE Slider (Handle Dropdowns)
		Select priceRange= new Select(dropdown);
		priceRange.selectByIndex(2);

	}

	public void searchPhone() {

		//7. Search for “redmi go (black, 8 gb)” in the search bar.
		//NOTE:The product redmi is temporarily not available . Hence searching for iPhone 
		driver.findElement(By.name("q")).sendKeys("iphone");
		driver.findElement(By.tagName("button")).click();
	}

	public String verifyPriceAmount() {
		//8. Click on the first product displayed on the screen

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='_4rR01T']"))).click();
		//iphone.click();

		//9. Verify that the product amount should be greater than or equal to 0 (Use switch to new window)
		ArrayList<String> tabs =  new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));

		String priceLabel = pricelabel.getText();
		return priceLabel;
	}

	public void switchToIframe() {

		//10. Click on the image thumbnail which displays play video icon (use IFrame)
		driver.findElement(By.xpath("//div[@class='_3g-Cpg']")).click();

		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//div[@class='_3g-Cpg']")));
		//driver.switchTo().frame(0);

	}

	//11. Enter the Delivery pincode by keyboard event (Use Keyboard event)
	public void enterPincode() {

		wait = new WebDriverWait(driver, 20); 
		WebElement pincode=wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("pincodeInputId"))));
		Actions action = new Actions(driver);
		action.moveToElement(pincode).click().sendKeys("671300").build().perform();
	}

	//12. Click on the “View Details” link under Delivery pincode 
	public void viewDetails() {

		wait = new WebDriverWait(driver, 20);
		WebElement check = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[@class='_2P_LDn']"))));
		check.click();
		WebElement details = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[@class='YxlyDn']"))));
		details.click();

	}


	//13. Click on “Add to Cart”
	public void addToCart() {	

		wait = new WebDriverWait(driver, 20); 
		WebElement butttonCart= wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@class='_2KpZ6l _2U9uOA _3v1-ww']"))));
		butttonCart.click();
	}



}
