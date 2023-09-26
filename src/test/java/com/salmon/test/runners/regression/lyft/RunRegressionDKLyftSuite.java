package com.salmon.test.runners.regression.lyft;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@LyftDKReg"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/regressionlyftdk",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/regressionlyftdk/cucumber.json",
        "rerun:target/regressionlyftdk-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionDKLyftSuite extends AbstractTestNGCucumberTests {
	
}
