package com.salmon.test.runners.live.vype;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@ITLive"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/livevypeit",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/livevypeit/cucumber.json",
        "rerun:target/livevypeit-rerun.txt"},
        glue = "com.salmon.test")
public class RunLiveITVypeSuite extends AbstractTestNGCucumberTests {
	
}
