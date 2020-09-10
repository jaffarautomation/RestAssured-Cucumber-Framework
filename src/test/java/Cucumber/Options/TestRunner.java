package Cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.*;

@RunWith(Cucumber.class) 
@CucumberOptions(features="src/test/java/featuresfile"
,glue= {"stepdefination"} ,tags= "@DeletePlace",plugin ="json:target/cucumber.json")

public class TestRunner {

	

}
