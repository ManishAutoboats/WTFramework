package com.salmon.test.runners.regression.vype;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features/web/Vype/Vype-UAT",
        tags = {"@Cxregression"}, monochrome = true, plugin = {
        "pretty",
        "html:target/cucumber-report/regressionvypeukcx",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/regressionvypeukcx/cucumber.json",
        "rerun:target/regressionvypeukcx-rerun.txt"},
        glue = "com.salmon.test")
public class RunRegressionUKVypeCXSuite extends AbstractTestNGCucumberTests {

}
