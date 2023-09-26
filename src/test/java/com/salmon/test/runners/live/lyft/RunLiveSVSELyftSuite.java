package com.salmon.test.runners.live.lyft;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@LyftLive"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/livesvse",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/livesvse/cucumber.json",
        "rerun:target/livesvse-rerun.txt"},
        glue = "com.salmon.test")
public class RunLiveSVSELyftSuite extends AbstractTestNGCucumberTests {
	
}
