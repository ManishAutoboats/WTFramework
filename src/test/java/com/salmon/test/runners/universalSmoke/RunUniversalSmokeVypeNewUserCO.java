package com.salmon.test.runners.universalSmoke;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@UniversalSmokeVypeNewUserCO"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/smokevypenewuserco",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/smokevypenewuserco/cucumber.json",
        "rerun:target/smokevypenewuserco-rerun.txt"},
        glue = "com.salmon.test")
public class RunUniversalSmokeVypeNewUserCO extends AbstractTestNGCucumberTests {
	
}
