package com.salmon.test.runners.universalSmoke;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@UniversalSmokeGloKZ"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/smokeglokz",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/smokeglokz/cucumber.json",
        "rerun:target/smokeglokz-rerun.txt"},
        glue = "com.salmon.test")
public class RunUniversalSmokeGloKZ extends AbstractTestNGCucumberTests {
	
}
