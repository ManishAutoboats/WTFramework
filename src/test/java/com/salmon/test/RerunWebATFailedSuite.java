package com.salmon.test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * Created by rrbaddipadaga on 11/06/2018.
 */
@CucumberOptions(features = "@target/rerun.txt", monochrome = true, plugin = {
        "pretty", "html:target/cucumber-report/reRunFailed",
        "json:target/cucumber-report/reRunFailed/cucumber.json"}
)
public class RerunWebATFailedSuite extends AbstractTestNGCucumberTests {


}
