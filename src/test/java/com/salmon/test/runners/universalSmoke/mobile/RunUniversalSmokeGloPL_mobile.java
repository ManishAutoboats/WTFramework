package com.salmon.test.runners.universalSmoke.mobile;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@UniversalSmokeGloPL_mobile"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/smokeglopl_mobile",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/smokeglopl_mobile/cucumber.json",
        "rerun:target/smokeglopl_mobile-rerun.txt"},
        glue = "com.salmon.test")
public class RunUniversalSmokeGloPL_mobile extends AbstractTestNGCucumberTests {
	
}
