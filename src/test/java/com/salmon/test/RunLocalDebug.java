package com.salmon.test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
    tags = {"@localdebug"}, monochrome = true, plugin = {
    "pretty",
    "html:target/cucumber-report/runwebat",
    "junit:target/cucumber-report/cucumber.xml",
    "json:target/cucumber-report/cucumber.json",
    "rerun:target/rerun.txt"},
    glue = "com.salmon.test")
public class RunLocalDebug extends AbstractTestNGCucumberTests {

}
