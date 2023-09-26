package com.salmon.test.runners.GlobalSubs.Smoke;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@VuseCOGlobalSubsSmoke"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/subsvuseco",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/subsvuseco/cucumber.json",
        "rerun:target/subsvuseco-rerun.txt"},
        glue = "com.salmon.test")
public class RunGlobalSubsVuseCOSmoke extends AbstractTestNGCucumberTests {
	
}
