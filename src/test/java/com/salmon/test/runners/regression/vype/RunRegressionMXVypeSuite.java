package com.salmon.test.runners.regression.vype;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@MXReg"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressionvypemx",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/regressionvypemx/cucumber.json",
        "rerun:target/regressionvypemx-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionMXVypeSuite extends AbstractTestNGCucumberTests {
	
}
