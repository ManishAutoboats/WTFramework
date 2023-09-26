package com.salmon.test.runners.regression.vype;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@GlobalSubsFR"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressionvypefr2",
        "junit:target/cucumber-report/cucumber2.xml",
        "json:target/cucumber-report/regressionvypefr2/cucumber.json",
        "rerun:target/regressionvypefr-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionFRVypeSuiteGlobalSubs extends AbstractTestNGCucumberTests {
	
}
