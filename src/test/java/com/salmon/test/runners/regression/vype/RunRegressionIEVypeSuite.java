package com.salmon.test.runners.regression.vype;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@IEReg"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressionvypeie",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/regressionvypeie/cucumber.json",
        "rerun:target/regressionvypeie-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionIEVypeSuite extends AbstractTestNGCucumberTests {
	
}
