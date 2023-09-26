package com.salmon.test.runners.live.vuse;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@VuseUKLive"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/livevuse",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/livevuse/cucumber.json",
        "rerun:target/livevuse-rerun.txt"},
        glue = "com.salmon.test")
public class RunLiveUKVuseSuite extends AbstractTestNGCucumberTests {
	
}
