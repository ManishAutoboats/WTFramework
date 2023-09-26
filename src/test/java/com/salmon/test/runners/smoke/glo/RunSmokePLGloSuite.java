package com.salmon.test.runners.smoke.glo;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@gloPlSmoke"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/smokeglopl",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/smokeglopl/cucumber.json",
        "rerun:target/smokeglopl-rerun.txt"},
        glue = "com.salmon.test")
public class RunSmokePLGloSuite extends AbstractTestNGCucumberTests {
	
}
