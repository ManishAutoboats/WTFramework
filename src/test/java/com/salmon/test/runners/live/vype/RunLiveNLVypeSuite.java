package com.salmon.test.runners.live.vype;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@NLlive"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/livevypenl",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/livevypenl/cucumber.json",
        "rerun:target/livevypenl-rerun.txt"},
        glue = "com.salmon.test")
public class RunLiveNLVypeSuite extends AbstractTestNGCucumberTests {
	
}
