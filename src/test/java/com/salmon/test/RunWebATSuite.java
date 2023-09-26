package com.salmon.test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

//@CucumberOptions(features = "src/test/resources/features", tags = {"@ramesh,@forceFailureForReRun"}, monochrome = true, plugin = {
@CucumberOptions(features = "src/test/resources/features",
tags = {"@smokeAdminFast"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/runwebat",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/runwebat/cucumber.json",
        "rerun:target/rerun.txt"},
        glue = "com.salmon.test")
public class RunWebATSuite extends AbstractTestNGCucumberTests {
	
}
