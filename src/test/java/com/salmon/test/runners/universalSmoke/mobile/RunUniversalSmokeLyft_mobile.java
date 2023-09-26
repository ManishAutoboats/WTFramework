package com.salmon.test.runners.universalSmoke.mobile;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@UniversalSmokeLyft_mobile"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/smokelyft_mobile",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/smokelyft_mobile/cucumber.json",
        "rerun:target/smokelyft_mobile-rerun.txt"},
        glue = "com.salmon.test")
public class RunUniversalSmokeLyft_mobile extends AbstractTestNGCucumberTests {
	
}
