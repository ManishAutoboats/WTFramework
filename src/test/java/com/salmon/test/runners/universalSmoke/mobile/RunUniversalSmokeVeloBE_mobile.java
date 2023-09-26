package com.salmon.test.runners.universalSmoke.mobile;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@UniversalSmokeVeloBE_mobile"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/smokevelo_mobile",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/smokevelo_mobile/cucumber.json",
        "rerun:target/smokevelo_mobile-rerun.txt"},
        glue = "com.salmon.test")
public class RunUniversalSmokeVeloBE_mobile extends AbstractTestNGCucumberTests {
	
}
