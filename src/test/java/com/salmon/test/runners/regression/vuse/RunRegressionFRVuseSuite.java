package com.salmon.test.runners.regression.vuse;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@VuseFRReg"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressionvuse",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/regressionvusefr/cucumber.json",
        "rerun:target/regressionvusefr-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionFRVuseSuite extends AbstractTestNGCucumberTests {
	
}
