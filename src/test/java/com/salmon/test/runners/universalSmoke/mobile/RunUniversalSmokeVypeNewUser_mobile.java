package com.salmon.test.runners.universalSmoke.mobile;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@UniversalSmokeVypeNewUser_mobile"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/smokevypenewuser_mobile",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/smokevypenewuser_mobile/cucumber.json",
        "rerun:target/smokevypenewuser_mobile-rerun.txt"},
        glue = "com.salmon.test")
public class RunUniversalSmokeVypeNewUser_mobile extends AbstractTestNGCucumberTests {
	
}
