package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.FeedBackPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.testng.AssertJUnit.assertTrue;

public class FeedBackFormSteps {

    private FeedBackPage feedBackPage;

    public FeedBackFormSteps(FeedBackPage feedBackPage) {
        this.feedBackPage = feedBackPage;
    }

    @When("^user completes the feed back form and submits$")
    public void userCompletesTheFeedBackFormAndSubmits() {
        feedBackPage.completeForm();
        feedBackPage.agreeToConsent();
        feedBackPage.clickSubmitButton();
    }

    @When("^user completes the feed back form and submits without selecting the product$")
    public void userCompletesTheFeedBackFormAndSubmitsWithoutSelectingProduct() {
        feedBackPage.completeFormWithoutSelectProduct();
        feedBackPage.agreeToConsent();
        feedBackPage.clickSubmitButton();
    }

    @When("^user completes the feed back form and submits without selecting the Privacy Policy checkbox$")
    public void userCompletesTheFeedBackFormAndSubmitsWithoutSelectingPrivacyPolicyCheckbox() {
        feedBackPage.completeForm();
        feedBackPage.agreeToConsent();
        feedBackPage.clickSubmitButton();
    }

    @When("^user completes the contact us form and submits$")
    public void userCompletesContactUSFormAndSubmits() {
        feedBackPage.completeForm();
        feedBackPage.clickSubmitButton();
    }

    @Then("^user should see an email of '(.*)'$")
    public void userShouldSeeAnEmail(String expectedText) {
        assertTrue(feedBackPage.getWebDriver().getPageSource().contains(UrlBuilder.getMessage(expectedText)));
    }

    @And("^submit the form$")
    public void submitContactUSForm() {
        feedBackPage.clickSubmitButton();
    }

    @And("^enter invalid combination for email and telephone number$")
    public void enterInvalidEmailAndTelephoneFormat() {
        feedBackPage.enterInvalidEmailAndTelephoneFormat();
    }

    @Then("^assert error message for invalid format email and telephone number$")
    public void assertErrorMessageForInvalidEmailTelephoneFormat() {
        feedBackPage.assertErrorMessageForInvalidEmailTelephoneFormat();
    }

    /*@And("^assert error messages displayed of all mandatory fields of Contact Us$")
    public void assertErrorMessageAllMandatoryFieldContactUs() {
        feedBackPage.assertErrorMessageAllMandatoryFieldContactUs();
    }*/
}