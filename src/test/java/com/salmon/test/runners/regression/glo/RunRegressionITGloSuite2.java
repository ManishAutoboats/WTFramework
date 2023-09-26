package com.salmon.test.runners.regression.glo;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@gloItRegression2"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressiongloit2",
        "junit:target/cucumber-report/cucumber2.xml",
        "json:target/cucumber-report/regressiongloit2/cucumber.json",
        "rerun:target/regressiongloit-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionITGloSuite2 extends AbstractTestNGCucumberTests {
	
}
