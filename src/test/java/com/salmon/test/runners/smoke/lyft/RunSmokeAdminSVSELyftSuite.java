package com.salmon.test.runners.smoke.lyft;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@LyftAdminSmoke"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/smokesvse",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/smokesvse/cucumber.json",
        "rerun:target/smokesvse-rerun.txt"},
        glue = "com.salmon.test")
public class RunSmokeAdminSVSELyftSuite extends AbstractTestNGCucumberTests {
	
}
