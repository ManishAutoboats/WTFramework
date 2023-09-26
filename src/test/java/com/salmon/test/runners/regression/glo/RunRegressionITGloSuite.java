package com.salmon.test.runners.regression.glo;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@gloItRegression"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressiongloit",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/regressiongloit/cucumber.json",
        "rerun:target/regressiongloit-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionITGloSuite extends AbstractTestNGCucumberTests {
	
}
