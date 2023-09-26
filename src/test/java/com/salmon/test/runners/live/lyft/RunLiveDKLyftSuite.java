package com.salmon.test.runners.live.lyft;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@LyftDKLive"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/livedk",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/livedk/cucumber.json",
        "rerun:target/livedk-rerun.txt"},
        glue = "com.salmon.test")
public class RunLiveDKLyftSuite extends AbstractTestNGCucumberTests {
	
}
