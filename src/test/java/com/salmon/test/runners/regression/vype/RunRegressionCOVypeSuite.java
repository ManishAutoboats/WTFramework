package com.salmon.test.runners.regression.vype;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@COReg"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressionvypeco",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/regressionvypeco/cucumber.json",
        "rerun:target/regressionvypeco-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionCOVypeSuite extends AbstractTestNGCucumberTests {
	
}
