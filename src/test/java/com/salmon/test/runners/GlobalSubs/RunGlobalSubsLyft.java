package com.salmon.test.runners.GlobalSubs;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@LyftGlobalSubs"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/subslyft",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/subslyft/cucumber.json",
        "rerun:target/subslyft-rerun.txt"},
        glue = "com.salmon.test")
public class RunGlobalSubsLyft extends AbstractTestNGCucumberTests {
	
}
