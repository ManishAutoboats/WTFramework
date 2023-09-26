package com.salmon.test.runners.api;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(features = "src/test/resources/features/api", tags = {"@MiddlewareToMagento,@yourChoice"}, monochrome = true, plugin = {
        "pretty", "html:target/cucumber-report/runapiat",
        "junit:target/cucumber-report/runapiat/cucumber.xml",
        "json:target/cucumber-report/runapiat/cucumber.json",
        "rerun:target/cucumber-report/runapiat/rerun.txt"},
        glue = "com.salmon.test")
public class RunApiRegressionPLGloSuite extends AbstractTestNGCucumberTests {
}
