package com.salmon.test.runners.regression.vype;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@ITReg"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressionvypeit",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/regressionvypeit/cucumber.json",
        "rerun:target/regressionvypeit-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionITVypeSuite extends AbstractTestNGCucumberTests {
	
}
