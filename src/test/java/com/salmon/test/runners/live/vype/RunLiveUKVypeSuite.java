package com.salmon.test.runners.live.vype;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@live"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/livevype",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/livevype/cucumber.json",
        "rerun:target/livevype-rerun.txt"},
        glue = "com.salmon.test")
public class RunLiveUKVypeSuite extends AbstractTestNGCucumberTests {
	
}
