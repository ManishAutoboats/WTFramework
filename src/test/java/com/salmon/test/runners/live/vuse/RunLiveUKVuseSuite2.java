package com.salmon.test.runners.live.vuse;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@VuseUKLive2"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/livevuse2",
        "junit:target/cucumber-report/cucumber2.xml",
        "json:target/cucumber-report/livevuse2/cucumber.json",
        "rerun:target/livevuse-rerun.txt"},
        glue = "com.salmon.test")
public class RunLiveUKVuseSuite2 extends AbstractTestNGCucumberTests {
	
}
