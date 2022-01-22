package stepDefinitions;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
//import io.cucumber.java.Scenario;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;

public class Hooks extends  BaseClass
{

	/*@After
	public void as(Scenario scenario) throws IOException
	{
		scenario.embed(BaseClass.getByteScreenshot(), "image/png");
	}*/
//	
	@After
	public void takeScreenshotOnFailure(Scenario scenario)
	{
		if(scenario.isFailed())
		{
			TakesScreenshot ts=(TakesScreenshot) driver;
			byte[] src=ts.getScreenshotAs(OutputType.BYTES);	
			scenario.embed(src,"image/png");
		
			
		}
	}
	
}
