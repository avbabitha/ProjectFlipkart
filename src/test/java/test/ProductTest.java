package test;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import pom.SearchResult;

public class ProductTest extends Base {
	
	@Test(description ="Test to check the price slider functionality", 
	        groups= "sanity",
	        dependsOnMethods = {"searchTest"},       
	        priority =2)
	public void priceSliderTest() {

		SearchResult search= new SearchResult(driver);
		search.priceSlider();

	}

	@Test(description ="Test to check the price dropdown functionality",
		    groups= "sanity",
		    dependsOnMethods = {"searchTest"},  
		    priority = 2)
	public void priceDropdownTest()
	{

		SearchResult search= new SearchResult(driver);
		search.priceDropdown();

	}

	@Test(description ="Test to check search functionality",
			groups = "sanity",
			priority=1 )
	public void searchTest() {

		SearchResult search= new SearchResult(driver);
		search.searchPhone();

	}
	
	@Test(description ="Test to verify the price amount",
			groups = "sanity",
			dependsOnMethods = {"searchTest"})
	public void verifyPriceTest() {
		SearchResult search= new SearchResult(driver);
		 String priceLabel = search.verifyPriceAmount();
		System.out.println(priceLabel);
		assertTrue(priceLabel.contains(","));		
		
	}
	
	@Test(description ="Test to check iframe",
			groups = "sanity",
			dependsOnMethods = {"verifyPriceTest"})
	public void iframeTest() {
		
		SearchResult search= new SearchResult(driver);
		search.switchToIframe();
		
	}
	
	@Test(description ="Test to enter pincode",
			groups = "sanity",
			dependsOnMethods = {"verifyPriceTest"})
	public void pincodeTest() {
		SearchResult search= new SearchResult(driver);
		search.enterPincode();
	}
	
	
	  @Test(description ="Test to view address details", 
			  groups = "sanity",
			  dependsOnMethods = {"pincodeTest"}) 
	  public void viewDetailsTest() { 
	  SearchResult search= new SearchResult(driver);
	  search.viewDetails(); 
	  }
	 
	

	@Test(description ="Test to view address details",
			groups = "sanity",
			dependsOnMethods = {"verifyPriceTest"})
	public void addToCartTest() {
		SearchResult search= new SearchResult(driver);
		search.addToCart();
		
	}
	
	


}
