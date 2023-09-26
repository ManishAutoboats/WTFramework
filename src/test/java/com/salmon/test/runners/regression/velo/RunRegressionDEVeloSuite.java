package com.salmon.test.runners.regression.velo;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@epokRegression"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressionvelode",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/regressionvelo/cucumber.json",
        "rerun:target/regressionvelode-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionDEVeloSuite extends AbstractTestNGCucumberTests {
	
}
