package com.salmon.test.runners.smoke.vype;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@desmoke"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/smokevypede",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/smokevypedev/cucumber.json",
        "rerun:target/smokevypede-rerun.txt"},
        glue = "com.salmon.test")
public class RunSmokeDEVypeSuite extends AbstractTestNGCucumberTests {
	
}
