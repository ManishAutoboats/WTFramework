package com.salmon.test.runners.mobileRegression;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@RegVuseFR_Mobile"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/mobileregressionvusefr",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/mobileregressionvusefr/cucumber.json",
        "rerun:target/regressionvusefr-rerun.txt"},
        glue = "com.salmon.test")
public class RunMobileRegressionFRVuseSuite extends AbstractTestNGCucumberTests {
	
}
