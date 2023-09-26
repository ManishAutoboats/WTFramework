package com.salmon.test.runners.smoke.vype;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@MXSmoke"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/smokevypemx",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/smokevypemx/cucumber.json",
        "rerun:target/smokevypemx-rerun.txt"},
        glue = "com.salmon.test")
public class RunSmokeMXVypeSuite extends AbstractTestNGCucumberTests {
	
}
