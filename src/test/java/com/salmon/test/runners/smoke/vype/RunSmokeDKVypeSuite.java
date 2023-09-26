package com.salmon.test.runners.smoke.vype;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@dksmoke"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/dkvypesmoke",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/dkvypesmoke/cucumber.json",
        "rerun:target/dkvypesmoke-rerun.txt"},
        glue = "com.salmon.test")
public class RunSmokeDKVypeSuite extends AbstractTestNGCucumberTests {
	
}
