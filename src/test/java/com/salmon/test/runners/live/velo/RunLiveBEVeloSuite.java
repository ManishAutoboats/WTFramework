package com.salmon.test.runners.live.velo;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@veloBELive"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/livevelobe",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/livevelobe/cucumber.json",
        "rerun:target/livevelobe-rerun.txt"},
        glue = "com.salmon.test")
public class RunLiveBEVeloSuite extends AbstractTestNGCucumberTests {
	
}
