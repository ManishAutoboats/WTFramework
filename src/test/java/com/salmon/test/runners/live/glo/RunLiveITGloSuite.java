package com.salmon.test.runners.live.glo;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@gloItLive"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/livegloit",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/livegloit/cucumber.json",
        "rerun:target/livegloit-rerun.txt"},
        glue = "com.salmon.test")
public class RunLiveITGloSuite extends AbstractTestNGCucumberTests {
	
}
