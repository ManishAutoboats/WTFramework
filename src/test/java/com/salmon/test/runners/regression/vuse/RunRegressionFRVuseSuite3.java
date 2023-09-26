package com.salmon.test.runners.regression.vuse;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@VuseFRReg3"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressionvuse3",
        "junit:target/cucumber-report/cucumber3.xml",
        "json:target/cucumber-report/regressionvusefr3/cucumber.json",
        "rerun:target/regressionvusefr-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionFRVuseSuite3 extends AbstractTestNGCucumberTests {
	
}
