package com.salmon.test.runners.universalSmoke;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@UniversalSmokeVypeNewUser"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/smokevypenewuser",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/smokevypenewuser/cucumber.json",
        "rerun:target/smokevypenewuser-rerun.txt"},
        glue = "com.salmon.test")
public class RunUniversalSmokeVypeNewUser extends AbstractTestNGCucumberTests {
	
}
