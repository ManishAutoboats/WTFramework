package com.salmon.test.runners.live.glo;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@gloDeLive"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/liveglode",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/liveglode/cucumber.json",
        "rerun:target/liveglode-rerun.txt"},
        glue = "com.salmon.test")
public class RunLiveDEGloSuite extends AbstractTestNGCucumberTests {
	
}
