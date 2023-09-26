package com.salmon.test.runners.regression.vype;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@de"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressionvypede",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/regressionvypedev/cucumber.json",
        "rerun:target/regressionvypede-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionDEVypeSuite extends AbstractTestNGCucumberTests {
	
}
