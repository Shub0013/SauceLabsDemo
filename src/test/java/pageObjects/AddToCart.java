package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;

import cucumber.api.java.en.*;
import utilities.WaitHelper;

public class AddToCart {

public WebDriver driver;
	
	public AddToCart(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public static final By cartIcon = By.xpath("//a[@class='shopping_cart_link']");
	public static final By checkoutIcon = By.xpath("//button[text()='Checkout']");
	public static final By continueBtn = By.xpath("//input[@type='submit']");
	public static final By finishBtn = By.xpath("//button[text()='Finish']");
	public static final By first_Name = By.id("first-name");
	public static final By last_Name = By.id("last-name");
	public static final By zipCode = By.id("postal-code");
	public static final By remove_Btn = By.id("//button[contains(@id,'remove')]");
	
	public void clickOnAddToCart(String product) throws InterruptedException
	{
	    String Product_path="//div[contains(text(),'"+product+"')]/../../..//div//button[contains(text(),'Add to cart')]";
	    System.out.println(Product_path);
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	//	driver.findElement(By.xpath(Product_path)).click();
		WaitHelper.ClickButton(By.xpath(Product_path));
	}	
	
	public void clickOnAddToCartIcon() 
	{
		//WaitHelper.waitUntilElementVisible(remove_Btn,5);
		//driver.findElement(cartIcon).click();
		WaitHelper.ClickButton(cartIcon);
	}
	public void validateProductFromCartList(String product) throws InterruptedException 
	{
		//WaitHelper.waitUntilElementVisible(remove_Btn,5);
		Thread.sleep(2000);
	    String cart_Product_Path="//div[@class='cart_item']//div[contains(text(),'"+product+"')]";
	    String cart_Product_Name=driver.findElement(By.xpath(cart_Product_Path)).getText();
		Assert.assertEquals(cart_Product_Name,product);
	}
	public void clickOnCheckOut() 
	{
	    
		//driver.findElement(checkoutIcon).click();
		WaitHelper.ClickButton(checkoutIcon);
	}
	public void clickOnContinue() 
	{
	    
		//driver.findElement(continueBtn).click();
		WaitHelper.ClickButton(continueBtn);
	}
	public void clickOnFinish() 
	{
	    
	//	driver.findElement(finishBtn).click();
		WaitHelper.ClickButton(finishBtn);
		
	}
	
	public void enterUserDetails(String fName,String sName,String zip) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		/*driver.findElement(first_Name).clear();
		driver.findElement(first_Name).sendKeys(fName);
		driver.findElement(last_Name).clear();
		driver.findElement(last_Name).sendKeys(sName);
		driver.findElement(zipCode).clear();
		driver.findElement(zipCode).sendKeys(zip);*/
		
		WaitHelper.enterText(first_Name,fName);
		WaitHelper.enterText(last_Name,sName);
		WaitHelper.enterText(zipCode,zip);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}
	public void verifyProductPresent(String product) throws InterruptedException 
	{
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		List<WebElement> Products_List = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));
		List<String> actualProduct = new ArrayList<String>();
		for (WebElement e : Products_List) {
			actualProduct.add(e.getText());
		}
		System.out.println("List of Products :-"+actualProduct);
		Boolean ProductPresent = actualProduct.contains(product);
		Assert.assertTrue("Product is present :-" + product, ProductPresent);
	}
	 
	public void verifyProductPresentWithListedPrice(String product) throws InterruptedException 
	{
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		List<WebElement> Products_PriceList = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
		List<String> actualProduct = new ArrayList<String>();
		for (WebElement e : Products_PriceList) {
			actualProduct.add(e.getText().replace("$", ""));
		}
		System.out.println("List of Price without dollar symbol :-"+actualProduct);
		
		driver.findElement(By.xpath("//div[text()='"+product+"']")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		String getPriceFromProductPage=driver.findElement(By.xpath("//div[@class='inventory_details_price']")).getText();
		getPriceFromProductPage=getPriceFromProductPage.replace("$", "");
		System.out.println("getPriceFromProductPage"+getPriceFromProductPage);
		
		Boolean ProductPricePresent = actualProduct.contains(getPriceFromProductPage);
		Assert.assertTrue("Product price "+ getPriceFromProductPage +" is present for Product:- " + product, ProductPricePresent);
	}
	
	
}
