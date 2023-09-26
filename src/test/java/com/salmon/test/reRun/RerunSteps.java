package com.salmon.test.reRun;

import com.salmon.test.framework.helpers.Props;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.Map;

import static org.testng.AssertJUnit.assertTrue;


/**
 * Created by rrbaddipadaga on 07/06/2018.
 */
public class RerunSteps {

    private static final String RUN_CONFIG_PROPERTIES = "/environment.properties";
    public static String OverwriteResultIfRerunPassed;
    private static final Logger LOG = LoggerFactory
        .getLogger(RerunSteps.class);

    @Given("^Merge rerun json report to original report$")
    public void merge_rerun_json_report_to_original_report(Map<String, String> arg1) {
        Props.loadRunConfigProps(RUN_CONFIG_PROPERTIES);
        if (Props.getEnvironmentProps().getProperty("profile.path").equals("false")) {
            LOG.info("OverwriteResultIfRerunPassed mode switched off");
            return;
        }
        LOG.info("***************************************************************");
        LOG.info("Merging rerun json file:" + arg1.get("src"));
        JsonReportHandler.refineAndRewriteJSONFile(new File(arg1.get("src")), new File(arg1.get("target")));
        LOG.info("***************************************************************");

    }

    @Then("^Clear rerun json report$")
    public void clear_rerun_json_report(List<String> arg1) {
        Props.loadRunConfigProps(RUN_CONFIG_PROPERTIES);
        if (Props.getEnvironmentProps().getProperty("profile.path").equals("false")) {
            LOG.info("OverwriteResultIfRerunPassed mode switched off");
            return;
        }
        for (String fileName : arg1) {
            LOG.info("Json report removed:" + fileName);
            JsonReportHandler.deleteFile(fileName);
        }
    }

    @Then("^Clear merge json report$")
    public void clear_merge_json_report(List<String> arg1) {
        for (String fileName : arg1) {
            LOG.info("Merge json report removed:" + fileName);
            JsonReportHandler.deleteFile(fileName);
        }
    }

    @Given("^step to create a failure without any condition$")
public void stepToCreateAFailureWithoutAnyCondition() {
        assertTrue(false);
    }
}
