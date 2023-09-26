package com.salmon.test.runners.regression.vuse;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@VuseDEReg"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressionvuse",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/regressionvusede/cucumber.json",
        "rerun:target/regressionvusede-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionDEVuseSuite extends AbstractTestNGCucumberTests {
	
}
