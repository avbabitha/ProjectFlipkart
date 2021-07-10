package test;



import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;


import pom.SearchResult;


@Test
public class SearchResultTest extends Base{

	/*
	 * @Test(description = "Test to close the login modal box", groups
	 * ="regression") public void closeModal() { SearchResult search= new
	 * SearchResult(driver); search.closeModal();
	 * 
	 * }
	 */

	@Test(description ="Test to verify the label",
			groups ="regression")
	public void verifyLabel() {
		SearchResult search= new SearchResult(driver);
		String expectedLabel = "Latest from MI : ";
		String actualLabel= search.verifyLabel();
		System.out.println(actualLabel);
		assertTrue(actualLabel.contains(expectedLabel), "Title validation failed");

	}

	
}
