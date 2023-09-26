package com.salmon.test.runners.smoke.vype;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@COSmoke"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/smokevypeco",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/smokevypeco/cucumber.json",
        "rerun:target/smokevypeco-rerun.txt"},
        glue = "com.salmon.test")
public class RunSmokeCOVypeSuite extends AbstractTestNGCucumberTests {
	
}
