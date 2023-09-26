package com.salmon.test.runners.live.vype;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@MXLive"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/livevypemx",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/livevypemx/cucumber.json",
        "rerun:target/livevypemx-rerun.txt"},
        glue = "com.salmon.test")
public class RunLiveMXVypeSuite extends AbstractTestNGCucumberTests {
	
}
