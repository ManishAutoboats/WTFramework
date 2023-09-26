package com.salmon.test.runners.live.velo;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@veloDELive"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/livevelode",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/livevelode/cucumber.json",
        "rerun:target/livevelode-rerun.txt"},
        glue = "com.salmon.test")
public class RunLiveDEVeloSuite extends AbstractTestNGCucumberTests {
	
}
