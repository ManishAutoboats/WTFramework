package com.salmon.test.runners.smoke.glo;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@gloItAdminSmoke"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/smokegloit",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/smokegloit/cucumber.json",
        "rerun:target/smokegloit-rerun.txt"},
        glue = "com.salmon.test")
public class RunSmokeAdminITGloSuite extends AbstractTestNGCucumberTests {
	
}
