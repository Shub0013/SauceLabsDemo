package testRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions
		(
		features= {"src/test/resources/Features"},
		glue="stepDefinitions",
		dryRun=false,
		monochrome=true,
		plugin= {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		tags= {"@All_Scenario"}
		)

public class TestRun { 

 
}
