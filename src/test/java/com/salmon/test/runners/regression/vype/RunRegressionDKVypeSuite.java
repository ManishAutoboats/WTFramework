package com.salmon.test.runners.regression.vype;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@dk"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressiondkvype",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/regressiondkvype/cucumber.json",
        "rerun:target/regressiondkvype-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionDKVypeSuite extends AbstractTestNGCucumberTests {
	
}
