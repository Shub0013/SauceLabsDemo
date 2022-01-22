package stepDefinitions;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import ch.qos.logback.classic.pattern.Util;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.AddToCart;
import pageObjects.LoginPage;
import utilities.WaitHelper;
import cucumber.api.Scenario;
import stepDefinitions.BaseClass;

public class Steps extends BaseClass
{
	@Before
	public void setup() throws IOException
	{
		//Logger
		logger=Logger.getLogger("nopComemrce"); //Added logger
		PropertyConfigurator.configure("Log4j.properties");//Added logger
		
		//Reading properties
		configProp=new Properties();
		FileInputStream configPropfile=new FileInputStream("config.properties");
		configProp.load(configPropfile);
		
		String br=configProp.getProperty("browser");
		
		if(br.equals("chrome"))
		{
		System.setProperty("webdriver.chrome.driver",configProp.getProperty("chromepath"));
		driver=new ChromeDriver();
		}
		else if (br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",configProp.getProperty("firefoxpath"));
			driver = new FirefoxDriver();
		}
		else if (br.equals("ie")) {
			System.setProperty("webdriver.ie.driver",configProp.getProperty("iepath"));
			driver = new InternetExplorerDriver();
		}
		
		logger.info("******** Launching browser*********");
	}
	 
	
	@Given("User Launch Chrome browser")
	public void user_Launch_Chrome_browser() {
				
		lp=new LoginPage(driver);
		atc = new AddToCart(driver);
		util= new WaitHelper(driver);
		
		//AddToCart atc = new AddToCart(driver);
	}

	
	@When("User opens URL as Url")
	public void user_opens_URL_as_Url() {
		logger.info("******** Opening URL*********");
		driver.get(configProp.getProperty("Url"));
	  driver.manage().window().maximize();
	 
	}
	

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_Email_as_and_Password_as(String email, String password) {
		logger.info("******** Providing login details*********");
		lp.setUserName(email);
	    lp.setPassword(password);
	}

	@When("Click on Login")
	public void click_on_Login() throws InterruptedException {
		logger.info("******** started login*********");
		lp.clickLogin();
	   Thread.sleep(3000);
	}

	@Then("Page Title should be {string}")
	public void page_Title_should_be(String title) throws InterruptedException {
	   
		if (driver.getPageSource().contains("Login was unsuccessful.")) {
			driver.close();
			logger.info("******** Login Filed*********");
			Assert.assertTrue(false);
		} else {
			logger.info("******** Login Passed*********");
			Assert.assertEquals(title, driver.getTitle());
		}
		Thread.sleep(3000);
		
	}

//	@When("User click on Log out link")
//	public void user_click_on_Log_out_link() throws InterruptedException {
//		logger.info("******** Click on logout link*********");
//		lp.clickLogout();
//		Thread.sleep(3000);
//	}

	@Then("close browser")
	public void close_browser() {
		logger.info("********closing browser********");
		driver.quit();
	}
	
	
	//Add Product
	@Then("Add the product {string} to cart")
	public void add_the_product_to_cart(String product) throws InterruptedException 
	{
		logger.info("********Add product to Cart********");
		atc.clickOnAddToCart(product);
	}	
	
	@Then("Click on Cart icon and Validate the product {string} from Cart")
	public void click_on_Cart_icon_and_Validate_the_product_from_Cart(String product) throws InterruptedException
	{
		logger.info("********Validate Added products to Cart********");
		atc.clickOnAddToCartIcon();
		atc.validateProductFromCartList(product);
	}
	
	@Then("Click on Checkout")
	public void click_on_Checkout() {
		logger.info("********CheckOut********");
		atc.clickOnCheckOut();
	}

	@Then("Enter user First name as {string} Second name as {string} and Postal code as {string}")
	public void enter_user_First_name_as_Second_name_as_and_Postal_code_as(String fName, String sName, String zip) throws InterruptedException {
		logger.info("********Enter User Details********");
		atc.enterUserDetails(fName,sName,zip);
	}

	@Then("Click on Continue")
	public void click_on_Continue() {
		logger.info("********Continue to Checkout********");
		atc.clickOnContinue();
	}
	@Then("Click on Finish")
	public void click_on_finish() throws InterruptedException, AWTException {
		logger.info("********Finish the Checkout********");
		atc.clickOnFinish();
		Thread.sleep(200);
		util.scrollUp();
//		Robot robot = new Robot();
//		robot.keyPress(KeyEvent.VK_PAGE_UP);
//		robot.keyRelease(KeyEvent.VK_PAGE_UP);
		Assert.assertEquals("CHECKOUT: COMPLETE!", driver.findElement(By.xpath("//span[@class='title']")).getText());
	}
	
	@Then("Validate error msg if credentials are Invalid")
	public void validate_error_msg_if_credentials_are_Invalid() {
		logger.info("********Validate Error Msg on Invalid Credential********");
		lp.validateErrorMsg();
		
	}
	
	@Then("verify the product {string} is displayed or not")
	public void verify_the_product_is_displayed_or_not(String product) throws InterruptedException {
	    atc.verifyProductPresent(product);
	}
	@Then("verify the product {string} is listed with the given price")
	public void verify_the_product_is_listed_with_the_given_price(String product) throws InterruptedException {
	    atc.verifyProductPresentWithListedPrice(product);
	}
	
}
