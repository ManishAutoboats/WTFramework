package com.salmon.test.runners.regression.velo;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@veloPLReg"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressionvelopl",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/regressionvelo/cucumber.json",
        "rerun:target/regressionvelopl-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionPLVeloSuite extends AbstractTestNGCucumberTests {
	
}
