package com.salmon.test.runners.live.vype;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@delive"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/livevypede",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/livevypedev/cucumber.json",
        "rerun:target/livevypede-rerun.txt"},
        glue = "com.salmon.test")
public class RunLiveDEVypeSuite extends AbstractTestNGCucumberTests {
	
}
