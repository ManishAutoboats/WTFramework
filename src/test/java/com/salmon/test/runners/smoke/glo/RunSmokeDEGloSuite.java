package com.salmon.test.runners.smoke.glo;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@gloDeSmoke"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/smokeglode",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/smokeglode/cucumber.json",
        "rerun:target/smokeglode-rerun.txt"},
        glue = "com.salmon.test")
public class RunSmokeDEGloSuite extends AbstractTestNGCucumberTests {
	
}
