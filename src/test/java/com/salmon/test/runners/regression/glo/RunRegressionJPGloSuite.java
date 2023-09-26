package com.salmon.test.runners.regression.glo;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@gloJPReg"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressionglojp",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/regressionglojp/cucumber.json",
        "rerun:target/regressionglojp-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionJPGloSuite extends AbstractTestNGCucumberTests {
	
}
