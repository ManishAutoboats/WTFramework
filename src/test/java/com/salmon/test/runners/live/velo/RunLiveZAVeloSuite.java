package com.salmon.test.runners.live.velo;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@veloZALive"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/liveveloza",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/liveveloza/cucumber.json",
        "rerun:target/liveveloza-rerun.txt"},
        glue = "com.salmon.test")
public class RunLiveZAVeloSuite extends AbstractTestNGCucumberTests {
	
}
