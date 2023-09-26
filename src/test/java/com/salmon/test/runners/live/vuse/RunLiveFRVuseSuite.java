package com.salmon.test.runners.live.vuse;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@VuseFRLive"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/livefrvuse",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/livefrvuse/cucumber.json",
        "rerun:target/livefrvuse-rerun.txt"},
        glue = "com.salmon.test")
public class RunLiveFRVuseSuite extends AbstractTestNGCucumberTests {
	
}
