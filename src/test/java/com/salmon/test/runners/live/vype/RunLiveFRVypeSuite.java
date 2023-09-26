package com.salmon.test.runners.live.vype;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@frlive"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/livevypefr",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/livevypefr/cucumber.json",
        "rerun:target/livevypefr-rerun.txt"},
        glue = "com.salmon.test")
public class RunLiveFRVypeSuite extends AbstractTestNGCucumberTests {
	
}
