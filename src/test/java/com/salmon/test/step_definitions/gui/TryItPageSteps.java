package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.RegistrationPage;
import com.salmon.test.page_objects.gui.TryItPage;
import com.salmon.test.page_objects.gui.models.RegistrationPageModel;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;

import java.util.List;
import java.util.Map;

public class TryItPageSteps {

    private TryItPage tryItPage;
    private SoftAssertions softAssertions;

    public TryItPageSteps(TryItPage tryItPage, SoftAssertions softAssertions) {
        this.tryItPage = tryItPage;
        this.softAssertions = softAssertions;
    }

    @And("^user click on Try link$")
    public void userClickOnTryLink() {
        tryItPage.clickTryItLink();
    }

    @When("^user enters Try form details and submits the form with age as in table then assert expected is displayed$")
    public void userEntersTryFormDetailsAndSubmitsTheFormWithAgeAsInTableThenAssertExpectedIsDisplayed(List<Map<String, String>> mapList) {
        RegistrationPageModel pageModel = RegistrationPageModel.builder().build().withDefaultValues();
        mapList.forEach(map -> {
            pageModel.getPersonalInfo().setDob(new RegistrationPage().getDateOfBirthFromAge(map.get("age")));
            tryItPage.completeFormAndSubmit(pageModel);
            tryItPage.waitForAjaxElementNotToBePresent(tryItPage.getWebDriver(), 2);
            String expectedMessage = UrlBuilder.getMessage(map.get("expected"));
            softAssertions.assertThat(tryItPage.getWebDriver().getPageSource().contains(expectedMessage))
                    .as("Expected Message: "+ map.get("expected") + "-" +  expectedMessage + " :is not displayed in the page")
                    .isTrue();
        });
        softAssertions.assertAll();
    }
}
