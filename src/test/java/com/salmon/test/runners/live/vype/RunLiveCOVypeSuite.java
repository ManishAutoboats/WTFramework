package com.salmon.test.runners.live.vype;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@CoLive"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/livevypeco",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/livevypeco/cucumber.json",
        "rerun:target/livevypeco-rerun.txt"},
        glue = "com.salmon.test")
public class RunLiveCOVypeSuite extends AbstractTestNGCucumberTests {
	
}
