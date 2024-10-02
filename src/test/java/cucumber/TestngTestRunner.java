package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features="src/test/java/cucumber",glue="stepDefination",monochrome=true,tags="@ErrorValidation",
plugin= {"html:target/cucumber.html"})

//first url is feature file contains 
//glue is which contains step defination files
//monochrome is to read the outputs in readable format
//plugin is for html report its in key value pair format:storage

public class TestngTestRunner extends AbstractTestNGCucumberTests{

}

