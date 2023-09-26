package com.salmon.test.runners.universalSmoke;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@UniversalSmokeLyft"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/smokelyft",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/smokelyft/cucumber.json",
        "rerun:target/smokelyft-rerun.txt"},
        glue = "com.salmon.test")
public class RunUniversalSmokeLyft extends AbstractTestNGCucumberTests {
	
}
