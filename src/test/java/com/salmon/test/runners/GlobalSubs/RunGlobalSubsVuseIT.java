package com.salmon.test.runners.GlobalSubs;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@VuseITGlobalSubs"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/subsvuseit",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/subsvuseit/cucumber.json",
        "rerun:target/subsvuseit-rerun.txt"},
        glue = "com.salmon.test")
public class RunGlobalSubsVuseIT extends AbstractTestNGCucumberTests {
	
}
