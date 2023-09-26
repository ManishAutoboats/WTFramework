package com.salmon.test.runners.regression.vype;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@MXReg2"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressionvypemx2",
        "junit:target/cucumber-report/cucumber2.xml",
        "json:target/cucumber-report/regressionvypemx2/cucumber.json",
        "rerun:target/regressionvypemx-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionMXVypeSuite2 extends AbstractTestNGCucumberTests {
	
}
