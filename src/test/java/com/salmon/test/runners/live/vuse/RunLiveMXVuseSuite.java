package com.salmon.test.runners.live.vuse;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
        tags = {"@VuseMXLive"}, monochrome = true, plugin = {
        "pretty",
        "html:target/cucumber-report/livevusemx",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/livevusemx/cucumber.json",
        "rerun:target/livevusemx-rerun.txt"},
        glue = "com.salmon.test")
public class RunLiveMXVuseSuite extends AbstractTestNGCucumberTests {

}
