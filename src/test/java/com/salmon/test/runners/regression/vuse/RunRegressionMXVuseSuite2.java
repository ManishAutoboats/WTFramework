package com.salmon.test.runners.regression.vuse;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@VuseMXReg2"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressionvuse",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/regressionvusemx/cucumber.json",
        "rerun:target/regressionvusemx-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionMXVuseSuite2 extends AbstractTestNGCucumberTests {
	
}
