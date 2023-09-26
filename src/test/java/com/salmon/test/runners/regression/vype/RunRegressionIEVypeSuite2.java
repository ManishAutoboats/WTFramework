package com.salmon.test.runners.regression.vype;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@IEReg2"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressionvypeie2",
        "junit:target/cucumber-report/cucumber2.xml",
        "json:target/cucumber-report/regressionvypeie2/cucumber.json",
        "rerun:target/regressionvypeie-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionIEVypeSuite2 extends AbstractTestNGCucumberTests {
	
}
