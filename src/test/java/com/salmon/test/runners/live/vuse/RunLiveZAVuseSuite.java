package com.salmon.test.runners.live.vuse;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@VuseZALive"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/livezavuse",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/livezavuse/cucumber.json",
        "rerun:target/livezavuse-rerun.txt"},
        glue = "com.salmon.test")
public class RunLiveZAVuseSuite extends AbstractTestNGCucumberTests {
	
}
