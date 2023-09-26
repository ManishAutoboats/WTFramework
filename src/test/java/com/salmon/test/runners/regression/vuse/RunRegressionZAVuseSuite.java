package com.salmon.test.runners.regression.vuse;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@VuseZAReg"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressionvuse",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/regressionvuseza/cucumber.json",
        "rerun:target/regressionvuseza-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionZAVuseSuite extends AbstractTestNGCucumberTests {
	
}
