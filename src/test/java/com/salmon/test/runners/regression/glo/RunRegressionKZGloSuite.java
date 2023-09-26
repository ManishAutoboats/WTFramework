package com.salmon.test.runners.regression.glo;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@gloKzRegression"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressionglokz",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/regressionglokz/cucumber.json",
        "rerun:target/regressionglokz-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionKZGloSuite extends AbstractTestNGCucumberTests {
	
}
