package com.salmon.test.runners.regression.velo;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@veloZAReg"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressionveloza",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/regressionvelo/cucumber.json",
        "rerun:target/regressionveloza-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionZAVeloSuite extends AbstractTestNGCucumberTests {
	
}
