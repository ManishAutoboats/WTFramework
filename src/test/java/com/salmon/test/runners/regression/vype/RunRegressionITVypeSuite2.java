package com.salmon.test.runners.regression.vype;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@ITReg2"}, monochrome = true, plugin = {
        "pretty",
        "html:target/cucumber-report/smokevypeit2",
        "junit:target/cucumber-report/cucumber2.xml",
        "json:target/cucumber-report/smokevypeit2/cucumber.json",
        "rerun:target/smokevypeit-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionITVypeSuite2 extends AbstractTestNGCucumberTests {
	
}
