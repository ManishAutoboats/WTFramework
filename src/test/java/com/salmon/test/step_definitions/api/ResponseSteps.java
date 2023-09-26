package com.salmon.test.step_definitions.api;

import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import cucumber.api.java.en.Then;
import org.assertj.core.api.Assertions;

public class ResponseSteps {

    private ScenarioContext scenarioContext;

    public ResponseSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @Then("^assert response status code is (\\d+)$")
    public void assertResponseStatusCodeIs(int statusCode) {
        int actualStatusCode = (int) scenarioContext.getContext(Context.RESPONSE_STATUS_CODE);
        Assertions.assertThat(actualStatusCode).isEqualTo(statusCode);
    }
}
