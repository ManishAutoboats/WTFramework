package com.salmon.test.runners.universalSmoke.mobile;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@UniversalSmokeGlo_mobile"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/smokeglo_mobile",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/smokeglo_mobile/cucumber.json",
        "rerun:target/smokeglo_mobile-rerun.txt"},
        glue = "com.salmon.test")
public class RunUniversalSmokeGlo_mobile extends AbstractTestNGCucumberTests {
	
}
