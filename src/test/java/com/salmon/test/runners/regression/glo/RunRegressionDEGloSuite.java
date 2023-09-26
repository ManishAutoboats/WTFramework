package com.salmon.test.runners.regression.glo;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@gloDeRegression"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressionglode",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/regressionglode/cucumber.json",
        "rerun:target/regressionglode-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionDEGloSuite extends AbstractTestNGCucumberTests {
	
}
