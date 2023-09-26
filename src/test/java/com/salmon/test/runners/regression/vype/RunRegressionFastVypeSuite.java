package com.salmon.test.runners.regression.vype;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@regression"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressionvype",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/regressionvype/cucumber.json",
        "rerun:target/regressionvype-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionFastVypeSuite extends AbstractTestNGCucumberTests {
	
}
