package com.salmon.test.runners.universalSmoke;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@UniversalSmokeVeloZA"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/smokevelo",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/smokevelo/cucumber.json",
        "rerun:target/smokevelo-rerun.txt"},
        glue = "com.salmon.test")
public class RunUniversalSmokeVeloZA extends AbstractTestNGCucumberTests {
	
}
