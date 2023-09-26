package com.salmon.test.runners.regression.lyft;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@LyftRegression"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressionsvse",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/regressionsvse/cucumber.json",
        "rerun:target/regressionsvse-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionSVSELyftSuite extends AbstractTestNGCucumberTests {
	
}
