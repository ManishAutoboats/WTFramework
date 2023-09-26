package com.salmon.test.runners.regression.vuse;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@VuseUKReg2"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressionvuse2",
        "junit:target/cucumber-report/cucumber2.xml",
        "json:target/cucumber-report/regressionvuseuk2/cucumber.json",
        "rerun:target/regressionvuseuk-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionUKVuseSuite2 extends AbstractTestNGCucumberTests {
	
}
