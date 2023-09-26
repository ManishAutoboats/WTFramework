package com.salmon.test.runners.universalSmoke.mobile;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@UniversalSmokeVypeSmokeUser_mobile"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/smokevypesmokeuser_mobile",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/smokevypesmokeuser_mobile/cucumber.json",
        "rerun:target/smokevypesmokeuser-rerun.txt"},
        glue = "com.salmon.test")
public class RunUniversalSmokeVypeSmokeUser_mobile extends AbstractTestNGCucumberTests {
	
}
