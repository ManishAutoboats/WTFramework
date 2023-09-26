package com.salmon.test.runners.regression.vuse;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@VuseITAnonReg2"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressionvuse2",
        "junit:target/cucumber-report/cucumber2.xml",
        "json:target/cucumber-report/regressionvuseit2/cucumber.json",
        "rerun:target/regressionvuseit-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionITVuseSuite2 extends AbstractTestNGCucumberTests {
	
}
