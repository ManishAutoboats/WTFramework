package com.salmon.test.runners.mobileRegression;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@RegLyftSE_Mobile"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/mobileregressionlyftse",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/mobileregressionlyftse/cucumber.json",
        "rerun:target/regressionlyftse-rerun.txt"},
        glue = "com.salmon.test")
public class RunMobileRegressionSELyftSuite extends AbstractTestNGCucumberTests {
	
}
