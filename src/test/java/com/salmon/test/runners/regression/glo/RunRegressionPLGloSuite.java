package com.salmon.test.runners.regression.glo;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@gloPlRegression"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressionglopl",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/regressionglopl/cucumber.json",
        "rerun:target/regressionglopl-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionPLGloSuite extends AbstractTestNGCucumberTests {
	
}
