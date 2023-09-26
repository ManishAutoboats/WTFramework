package com.salmon.test.runners.live.vype;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@dklive"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/livedkvype",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/livedkvype/cucumber.json",
        "rerun:target/livedkvype-rerun.txt"},
        glue = "com.salmon.test")
public class RunLiveDKVypeSuite extends AbstractTestNGCucumberTests {
	
}
