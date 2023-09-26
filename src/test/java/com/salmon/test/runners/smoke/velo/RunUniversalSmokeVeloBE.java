package com.salmon.test.runners.smoke.velo;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@veloBESmoke"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/smokevelobe",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/smokevelobe/cucumber.json",
        "rerun:target/smokevelobe-rerun.txt"},
        glue = "com.salmon.test")
public class RunUniversalSmokeVeloBE extends AbstractTestNGCucumberTests {
	
}
