package com.salmon.test.runners.mobileRegression;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@RegVeloZA_Mobile"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/mobileregressionveloza",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/mobileregressionveloza/cucumber.json",
        "rerun:target/regressionveloza-rerun.txt"},
        glue = "com.salmon.test")
public class RunMobileRegressionZAVeloSuite extends AbstractTestNGCucumberTests {
}
