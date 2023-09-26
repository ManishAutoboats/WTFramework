package com.salmon.test.runners.smoke.velo;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@veloPLSmoke"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/smokevelopl",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/smokevelode/cucumber.json",
        "rerun:target/smokevelopl-rerun.txt"},
        glue = "com.salmon.test")
public class RunSmokePLVeloSuite extends AbstractTestNGCucumberTests {
	
}
