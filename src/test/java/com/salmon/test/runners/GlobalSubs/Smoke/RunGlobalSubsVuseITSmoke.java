package com.salmon.test.runners.GlobalSubs.Smoke;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@VuseITGlobalSubsSmoke"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/subsvuseit",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/subsvuseit/cucumber.json",
        "rerun:target/subsvuseit-rerun.txt"},
        glue = "com.salmon.test")
public class RunGlobalSubsVuseITSmoke extends AbstractTestNGCucumberTests {
	
}
