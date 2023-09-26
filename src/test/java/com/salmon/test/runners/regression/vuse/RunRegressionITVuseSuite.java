package com.salmon.test.runners.regression.vuse;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@VuseITReg"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressionvuse",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/regressionvuseit/cucumber.json",
        "rerun:target/regressionvuseit-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionITVuseSuite extends AbstractTestNGCucumberTests {
	
}
