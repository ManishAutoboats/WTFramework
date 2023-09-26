package com.salmon.test.runners.smoke.vype;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@ITSmoke"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/smokevypeit",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/smokevypeit/cucumber.json",
        "rerun:target/smokevypeit-rerun.txt"},
        glue = "com.salmon.test")
public class RunSmokeITVypeSuite extends AbstractTestNGCucumberTests {
	
}
