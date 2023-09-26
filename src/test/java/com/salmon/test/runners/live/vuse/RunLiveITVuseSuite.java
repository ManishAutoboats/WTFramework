package com.salmon.test.runners.live.vuse;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@VuseITLive"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/liveitvuse",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/liveitvuse/cucumber.json",
        "rerun:target/liveitvuse-rerun.txt"},
        glue = "com.salmon.test")
public class RunLiveITVuseSuite extends AbstractTestNGCucumberTests {
	
}
