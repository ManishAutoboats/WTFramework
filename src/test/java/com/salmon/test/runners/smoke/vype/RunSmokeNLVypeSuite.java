package com.salmon.test.runners.smoke.vype;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@nlSmoke"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/smokevypenl",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/smokevypenl/cucumber.json",
        "rerun:target/smokevypenl-rerun.txt"},
        glue = "com.salmon.test")
public class RunSmokeNLVypeSuite extends AbstractTestNGCucumberTests {
	
}
