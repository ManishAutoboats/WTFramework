package com.salmon.test.runners.live.vuse;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@isslive"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/iss",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/iss/cucumber.json",
        "rerun:target/iss-rerun.txt"},
        glue = "com.salmon.test")
public class RunLiveUKVuseIssSuite extends AbstractTestNGCucumberTests {
	
}
