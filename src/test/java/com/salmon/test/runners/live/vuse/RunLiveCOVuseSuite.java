package com.salmon.test.runners.live.vuse;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@CoLive"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/livevuseco",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/livevuseco/cucumber.json",
        "rerun:target/livevuseco-rerun.txt"},
        glue = "com.salmon.test")
public class RunLiveCOVuseSuite extends AbstractTestNGCucumberTests {
	
}
