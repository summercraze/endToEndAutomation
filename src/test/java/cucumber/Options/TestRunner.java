/*
 * This is the test runner, it has the feature and 
 * glue that is need to run the test
 * */
 
package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions
(
		features = "src/test/java/features", 
		glue = {"stepDefinition"},
		plugin = "json:target/cucumber-report.json",
		tags= {"@DeletePlace"}
) 
public class TestRunner 
{

}
