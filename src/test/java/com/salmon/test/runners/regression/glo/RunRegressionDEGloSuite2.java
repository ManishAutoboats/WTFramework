package com.salmon.test.runners.regression.glo;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@gloDeRegression2"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressionglode2",
        "junit:target/cucumber-report/cucumber2.xml",
        "json:target/cucumber-report/regressionglode2/cucumber.json",
        "rerun:target/regressionglode-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionDEGloSuite2 extends AbstractTestNGCucumberTests {
	
}
