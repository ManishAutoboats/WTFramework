package com.salmon.test.runners.GlobalSubs.Smoke;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@VuseDEGlobalSubsSmoke"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/subsvusede",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/subsvusede/cucumber.json",
        "rerun:target/subsvusede-rerun.txt"},
        glue = "com.salmon.test")
public class RunGlobalSubsVuseDESmoke extends AbstractTestNGCucumberTests {
	
}
