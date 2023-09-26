package com.salmon.test.runners.live.vype;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@NonCaptchaLive"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/livevypemxnoncaptcha",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/livevypemxnoncaptcha/cucumber.json",
        "rerun:target/livevypemxnoncaptcha-rerun.txt"},
        glue = "com.salmon.test")
public class RunLiveMXNonCaptchaVypeSuite extends AbstractTestNGCucumberTests {
	
}
