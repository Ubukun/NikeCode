package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.Assert;
import ObjectRepository.HomePage;

public class SearchByWord {
	
	@Test
	public void CheckWord() throws InterruptedException   
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\ds\\Selenium\\TrainningFiles\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		// Navigate to Amazon
		driver.get("https://www.amazon.com");
		driver.manage().window().maximize();
		
		// Select "Books" from the dropdown menu
		Select droplist = new Select(driver.findElement(By.id("searchDropdownBox")));
		droplist.selectByVisibleText("Books");
		
		// Type "Nike" into Search text field
		HomePage hp=new HomePage(driver);
		hp.CheckWord().sendKeys("Nike");
		
		// Search for "Nike"  by clicking on Search button
		HomePage gs=new HomePage(driver);
		gs.GoToSearch().click();
		
		Thread.sleep(2000);
		
		// Validate the author name
		String BookAuthor = driver.findElement(By.linkText("Phil Knight")).getText();
		String BookAuthorExpected = "Phil Knight";
		Assert.assertEquals(BookAuthor, BookAuthorExpected);
		
		// Select 'Hardcover' book version 
		String BookType = driver.findElement(By.linkText("Hardcover")).getText();
		String BookTypeExpected = "Hardcover";
		if (BookType.contentEquals(BookTypeExpected)){
			driver.findElement(By.linkText("Hardcover")).click();
 	 		}else{
 	 		driver.quit();
 	 	}
			
		// Select the book with requested title "Shoe Dog: A Memoir by the Creator of Nike"
		String BookTitle = driver.findElement(By.linkText("Shoe Dog: A Memoir by the Creator of Nike")).getText();
		String BookTitleExpected = "Shoe Dog: A Memoir by the Creator of Nike";
		
		if (BookTitle.contentEquals(BookTitleExpected)){
			driver.findElement(By.linkText("Shoe Dog: A Memoir by the Creator of Nike")).click();
 	 		}else{
 			driver.quit();
 	 	}
					
		// Get book amount from product page (Before it was added to the Shopping Cart)
		String amountBefore = driver.findElement(By.id("buyNewSection")).getText();
		String amountExpected = "$16.59";
			   		
	    // Validation 1: Assert book amount before it was added to the Shopping Cart
		Assert.assertTrue(amountBefore.contains(amountExpected));
		
		// Add 'Hardcover' book version to the Shopping cart by clicking on "Add to Cart" button
		HomePage ad=new HomePage(driver);
		ad.AddToShopCart().click();
					
		// Navigate to Shopping Cart (by clicking on the "Cart" button)
		HomePage vw=new HomePage(driver);
		vw.ViewShopCart().click();
			       
		// Get Subtotal amount from Shopping cart
		String amountShoppCart = driver.findElement(By.id("sc-subtotal-label-activecart")).getText();
		// Validation 2: Assert book amount after book added to the Shopping Cart
		 Assert.assertTrue(amountShoppCart.contains (amountExpected));
		
		// Close the browser
		driver.quit();
	   }
}
