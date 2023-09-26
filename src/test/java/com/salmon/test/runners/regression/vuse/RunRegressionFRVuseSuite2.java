package com.salmon.test.runners.regression.vuse;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@VuseFRReg2"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressionvuse2",
        "junit:target/cucumber-report/cucumber2.xml",
        "json:target/cucumber-report/regressionvusefr2/cucumber.json",
        "rerun:target/regressionvusefr-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionFRVuseSuite2 extends AbstractTestNGCucumberTests {
	
}
