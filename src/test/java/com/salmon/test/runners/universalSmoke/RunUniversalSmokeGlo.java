package com.salmon.test.runners.universalSmoke;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@UniversalSmokeGlo"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/smokeglo",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/smokeglo/cucumber.json",
        "rerun:target/smokeglo-rerun.txt"},
        glue = "com.salmon.test")
public class RunUniversalSmokeGlo extends AbstractTestNGCucumberTests {
	
}
