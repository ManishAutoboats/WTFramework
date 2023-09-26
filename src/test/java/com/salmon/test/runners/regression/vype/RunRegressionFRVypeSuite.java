package com.salmon.test.runners.regression.vype;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@fr"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressionvypefr",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/regressionvypefr/cucumber.json",
        "rerun:target/regressionvypefr-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionFRVypeSuite extends AbstractTestNGCucumberTests {
	
}
