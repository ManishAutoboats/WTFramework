package com.salmon.test.runners.mobileRegression;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@RegGloIT_Mobile"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/mobileregressiongloit",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/mobileregressiongloit/cucumber.json",
        "rerun:target/regressiongloit-rerun.txt"},
        glue = "com.salmon.test")
public class RunMobileRegressionITGloSuite extends AbstractTestNGCucumberTests {
	
}
