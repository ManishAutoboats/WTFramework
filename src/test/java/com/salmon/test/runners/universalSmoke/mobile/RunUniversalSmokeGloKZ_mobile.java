package com.salmon.test.runners.universalSmoke.mobile;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@UniversalSmokeGloKZ_mobile"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/smokeglokz_mobile",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/smokeglokz_mobile/cucumber.json",
        "rerun:target/smokeglokz_mobile-rerun.txt"},
        glue = "com.salmon.test")
public class RunUniversalSmokeGloKZ_mobile extends AbstractTestNGCucumberTests {
	
}
