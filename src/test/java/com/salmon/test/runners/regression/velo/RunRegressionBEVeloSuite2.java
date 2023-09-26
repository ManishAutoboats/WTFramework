package com.salmon.test.runners.regression.velo;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@veloBeReg2"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressionvelobe2",
        "junit:target/cucumber-report/cucumber2.xml",
        "json:target/cucumber-report/regressionvelo2/cucumber.json",
        "rerun:target/regressionvelobe-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionBEVeloSuite2 extends AbstractTestNGCucumberTests {
	
}
