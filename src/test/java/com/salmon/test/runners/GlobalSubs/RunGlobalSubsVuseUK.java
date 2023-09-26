package com.salmon.test.runners.GlobalSubs;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@VuseUKGlobalSubs"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/subsvuseuk",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/subsvuseuk/cucumber.json",
        "rerun:target/subsvuseuk-rerun.txt"},
        glue = "com.salmon.test")
public class RunGlobalSubsVuseUK extends AbstractTestNGCucumberTests {
	
}
