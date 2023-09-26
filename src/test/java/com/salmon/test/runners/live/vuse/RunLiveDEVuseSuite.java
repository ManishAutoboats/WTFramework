package com.salmon.test.runners.live.vuse;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@VuseDELive"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/livedevuse",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/livedevuse/cucumber.json",
        "rerun:target/livedevuse-rerun.txt"},
        glue = "com.salmon.test")
public class RunLiveDEVuseSuite extends AbstractTestNGCucumberTests {
	
}
