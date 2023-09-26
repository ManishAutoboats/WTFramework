package com.salmon.test.runners.regression.vuse;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@FDT_FR"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressionvuseFDT",
        "junit:target/cucumber-report/cucumberFDT.xml",
        "json:target/cucumber-report/regressionvusefrFDT/cucumber.json",
        "rerun:target/regressionvusefr-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionFRVuseSuiteFDT extends AbstractTestNGCucumberTests {
	
}
