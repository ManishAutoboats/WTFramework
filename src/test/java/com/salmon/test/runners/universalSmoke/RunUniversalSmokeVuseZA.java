package com.salmon.test.runners.universalSmoke;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@UniversalSmokeVuseZA"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/smokevusedk",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/smokevusedk/cucumber.json",
        "rerun:target/smokevusedk-rerun.txt"},
        glue = "com.salmon.test")
public class RunUniversalSmokeVuseZA extends AbstractTestNGCucumberTests {
	
}
