package com.salmon.test.runners.live.glo;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@gloPlLive"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/liveglopl",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/liveglopl/cucumber.json",
        "rerun:target/liveglopl-rerun.txt"},
        glue = "com.salmon.test")
public class RunLivePLGloSuite extends AbstractTestNGCucumberTests {
	
}
