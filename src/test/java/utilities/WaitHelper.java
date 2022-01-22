package utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {

	//public static WebDriver driver;
	private static WebDriver driver;
	
	public WaitHelper(WebDriver driver){
		this.driver = driver;
	}
	
	//For wait
	public static void waitUntilElementVisible(By by, long timeOutInSeconds) {
		(new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions
				.visibilityOfElementLocated(by));
	}
	//for entering Text
	public static void enterText(By by,String text)
	{
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(text);
	}
	
	//for clicking button
	public static void  ClickButton(By by) 
	{ 
		driver.findElement(by).click();
	}
	//for scroll up
	public static void scrollUp() throws AWTException{
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_PAGE_UP);
		robot.keyRelease(KeyEvent.VK_PAGE_UP);
		}
	
}
