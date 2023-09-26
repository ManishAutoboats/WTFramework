package com.salmon.test.runners.regression.vuse;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@iss"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/iss",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/iss/cucumber.json",
        "rerun:target/iss-rerun.txt"},
        glue = "com.salmon.test")
public class RunUniversalRegressionIssUKVuseSuite extends AbstractTestNGCucumberTests {
	
}
