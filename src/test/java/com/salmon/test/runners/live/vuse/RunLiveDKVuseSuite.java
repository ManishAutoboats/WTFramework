package com.salmon.test.runners.live.vuse;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@VuseDKLive"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/livedkvuse",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/livedkvuse/cucumber.json",
        "rerun:target/livedkvuse-rerun.txt"},
        glue = "com.salmon.test")
public class RunLiveDKVuseSuite extends AbstractTestNGCucumberTests {
	
}
