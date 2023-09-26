package com.salmon.test.runners.universalSmoke.mobile;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@UniversalSmokeVuse_mobile"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/smokevuse",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/smokevuse/cucumber.json",
        "rerun:target/smokevuse-rerun.txt"},
        glue = "com.salmon.test")
public class RunUniversalSmokeVuse_mobile extends AbstractTestNGCucumberTests {
	
}
