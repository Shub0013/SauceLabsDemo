package stepDefinitions;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import pageObjects.AddToCart;
import pageObjects.LoginPage;
import utilities.WaitHelper;

public class BaseClass {

	public static WebDriver driver;
	public LoginPage lp;
	public AddToCart atc;
	public WaitHelper util;
	public static Logger logger;
	public Properties configProp;
	
	
	
	
	public static byte[] getByteScreenshot() throws IOException
	{
		File src=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		byte[] fileContent= FileUtils.readFileToByteArray(src);
		return fileContent;
	}
}
