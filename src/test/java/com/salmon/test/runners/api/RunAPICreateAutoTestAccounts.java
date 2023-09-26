package com.salmon.test.runners.api;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@createAutoTestAccount"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/createAutoTestAccounts",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/createautotestaccount/cucumber.json",
        "rerun:target/createautotestaccount-rerun.txt"},
        glue = "com.salmon.test")
public class RunAPICreateAutoTestAccounts extends AbstractTestNGCucumberTests {
	
}
