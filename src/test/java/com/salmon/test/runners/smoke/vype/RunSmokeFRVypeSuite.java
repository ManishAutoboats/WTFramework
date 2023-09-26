package com.salmon.test.runners.smoke.vype;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@frSmoke"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/smokevypefr",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/smokevypefr/cucumber.json",
        "rerun:target/smokevypefr-rerun.txt"},
        glue = "com.salmon.test")
public class RunSmokeFRVypeSuite extends AbstractTestNGCucumberTests {
	
}
