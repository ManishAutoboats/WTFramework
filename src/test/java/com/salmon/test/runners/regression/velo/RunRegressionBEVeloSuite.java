package com.salmon.test.runners.regression.velo;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@veloBeReg"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressionvelobe",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/regressionvelo/cucumber.json",
        "rerun:target/regressionvelobe-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionBEVeloSuite extends AbstractTestNGCucumberTests {
	
}
