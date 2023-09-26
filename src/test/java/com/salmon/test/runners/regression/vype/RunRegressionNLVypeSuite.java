package com.salmon.test.runners.regression.vype;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@nl"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressionvypenl",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/regressionvypenl/cucumber.json",
        "rerun:target/regressionvypenl-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionNLVypeSuite extends AbstractTestNGCucumberTests {
	
}
