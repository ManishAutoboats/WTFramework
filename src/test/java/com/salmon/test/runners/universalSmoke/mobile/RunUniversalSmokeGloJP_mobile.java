package com.salmon.test.runners.universalSmoke.mobile;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@UniversalSmokeGloJP_mobile"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/smokeglopl",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/smokeglopl/cucumber.json",
        "rerun:target/smokeglopl-rerun.txt"},
        glue = "com.salmon.test")
public class RunUniversalSmokeGloJP_mobile extends AbstractTestNGCucumberTests {
	
}
