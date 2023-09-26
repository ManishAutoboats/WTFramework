package com.salmon.test.runners.live.glo;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@gloKzLive"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/liveglokz",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/liveglokz/cucumber.json",
        "rerun:target/liveglokz-rerun.txt"},
        glue = "com.salmon.test")
public class RunLiveKZGloSuite extends AbstractTestNGCucumberTests {
	
}
