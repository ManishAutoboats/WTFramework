package com.salmon.test.runners.smoke.lyft;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@LyftDKSmoke"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/smokedkda",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/smokedkda/cucumber.json",
        "rerun:target/smokedkda-rerun.txt"},
        glue = "com.salmon.test")
public class RunSmokeDKDALyftSuite extends AbstractTestNGCucumberTests {
	
}
