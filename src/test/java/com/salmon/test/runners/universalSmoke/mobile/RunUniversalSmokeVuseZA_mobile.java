package com.salmon.test.runners.universalSmoke.mobile;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@UniversalSmokeVuseZA_mobile"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/smokevuseza",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/smokevuseza/cucumber.json",
        "rerun:target/smokevuseza-rerun.txt"},
        glue = "com.salmon.test")
public class RunUniversalSmokeVuseZA_mobile extends AbstractTestNGCucumberTests {
	
}
