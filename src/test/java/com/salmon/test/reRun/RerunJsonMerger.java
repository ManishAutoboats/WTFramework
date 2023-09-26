package com.salmon.test.reRun;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * Created by rrbaddipadaga on 13/06/2018.
 */

@CucumberOptions(features = ".", tags = {"@RerunJsonMerger"}, monochrome = true, plugin = {"pretty", "html:target/cucumber-report/RerunJsonMerger",
        "json:target/cucumber-report/RerunJsonMerger/cucumber.json"})
public class RerunJsonMerger extends AbstractTestNGCucumberTests {

}
