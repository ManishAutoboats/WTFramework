package com.salmon.test.runners.regression.lyft;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@LyftRegression2"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressionsvse2",
        "junit:target/cucumber-report/cucumber2.xml",
        "json:target/cucumber-report/regressionsvse2/cucumber.json",
        "rerun:target/regressionsvse-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionSVSELyftSuite2 extends AbstractTestNGCucumberTests {
	
}
