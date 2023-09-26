package com.salmon.test.runners.regression.vuse;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
        tags = {"@VuseITAnonReg3"}, monochrome = true, plugin = {
        "pretty",
        "html:target/cucumber-report/regressionvuse3",
        "junit:target/cucumber-report/cucumber3.xml",
        "json:target/cucumber-report/regressionvuseit3/cucumber.json",
        "rerun:target/regressionvuseit-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionITVuseSuite3 extends AbstractTestNGCucumberTests {

}
