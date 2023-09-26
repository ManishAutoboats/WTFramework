package com.salmon.test.runners.smoke.vype;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@smokeVypeAdminFast"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/smokevypeadminfast",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/smokevypeadminfast/cucumber.json",
        "rerun:target/smokevypeadminfast-rerun.txt"},
        glue = "com.salmon.test")
public class RunSmokeVypeAdminSuite extends AbstractTestNGCucumberTests {
	
}
