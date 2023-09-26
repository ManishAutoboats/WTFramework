package com.salmon.test.runners.GlobalSubs;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@VuseFRGlobalSubs"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/subsvusefr",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/subsvusefr/cucumber.json",
        "rerun:target/subsvusefr-rerun.txt"},
        glue = "com.salmon.test")
public class RunGlobalSubsVuseFR extends AbstractTestNGCucumberTests {
	
}
