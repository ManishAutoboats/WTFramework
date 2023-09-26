package com.salmon.test.runners.regression.vuse;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@VuseDEReg2"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressionvuse2",
        "junit:target/cucumber-report/cucumber2.xml",
        "json:target/cucumber-report/regressionvusede2/cucumber.json",
        "rerun:target/regressionvusede-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionDEVuseSuite2 extends AbstractTestNGCucumberTests {
	
}
