package com.salmon.test.runners.smoke.vype;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@smokeFast"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/smokevypefast",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/smokevypefast/cucumber.json",
        "rerun:target/smokevypefast-rerun.txt"},
        glue = "com.salmon.test")
public class RunSmokeFastVypeSuite extends AbstractTestNGCucumberTests {
	
}
