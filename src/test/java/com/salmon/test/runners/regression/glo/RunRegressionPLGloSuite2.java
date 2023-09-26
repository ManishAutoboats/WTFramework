package com.salmon.test.runners.regression.glo;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
        tags = {"@gloPlRegression2"}, monochrome = true, plugin = {
        "pretty",
        "html:target/cucumber-report/regressionglopl2",
        "junit:target/cucumber-report/cucumber2.xml",
        "json:target/cucumber-report/regressionglopl2/cucumber.json",
        "rerun:target/regressionglopl-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionPLGloSuite2 extends AbstractTestNGCucumberTests {

}