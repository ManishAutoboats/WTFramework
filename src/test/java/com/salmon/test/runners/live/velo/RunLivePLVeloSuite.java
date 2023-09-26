package com.salmon.test.runners.live.velo;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@veloPLLive"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/livevelopl",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/livevelopl/cucumber.json",
        "rerun:target/livevelopl-rerun.txt"},
        glue = "com.salmon.test")
public class RunLivePLVeloSuite extends AbstractTestNGCucumberTests {
	
}
