package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class LoginPage {
	public WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		
	}

	public static final By userName = By.id("user-name");
	public static final By password = By.id("password");
	public static final By login = By.xpath("//input[@value='Login']");
	

	

	

	public void setUserName(String uname)
	{
		WaitHelper.enterText(userName,uname);
	}

	public void setPassword(String pwd) 
	{
		WaitHelper.enterText(password,pwd);
	}

	public void clickLogin() 
	{
		WaitHelper.ClickButton(login);
	}
	
	public void validateErrorMsg() 
	{
		String err_Msg="Epic sadface: Username and password do not match any user in this service";
		Assert.assertEquals(err_Msg, driver.findElement(By.xpath("//h3[@data-test='error']")).getText());
	}
	
//	public void clickLogout() {
//		lnkLogout.click();
//	}
}
