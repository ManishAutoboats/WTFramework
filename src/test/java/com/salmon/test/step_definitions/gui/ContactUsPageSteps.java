package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.ContactUsPage;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

public class ContactUsPageSteps {

    private ContactUsPage contactUsPage;
    private SoftAssertions softAssertions;

    public ContactUsPageSteps(ContactUsPage contactUsPage, SoftAssertions softAssertions) {
        this.contactUsPage = contactUsPage;
        this.softAssertions = softAssertions;
    }

    @And("^completes the contact us form and submits$")
    public void completesTheContactUsFormAndSubmits() {
        contactUsPage.completeForm();
        if(!"glode".equals(UrlBuilder.getLocale()))
            contactUsPage.agreeToConsent();
        contactUsPage.clickSubmitButton();
    }

    @And("^completes the contact us form and submits without agree to consent$")
    public void completesTheContactUsFormAndSubmitsWithoutAgreeToConsent() {
        contactUsPage.completeForm();
        contactUsPage.clickSubmitButton();
    }

    @When("^submit the form without mandatory fields then assert error message$")
    public void submitTheFormWithoutMandatoryFieldsThenAssertErrorMessage(List<Map<String, String>> mapList) {
        mapList.forEach(map -> {
            contactUsPage.completeFormWithout(map.get("field"));
            if(!"glode".equals(UrlBuilder.getLocale()))
                contactUsPage.agreeToConsent();
            contactUsPage.clickSubmitButton();
            String expectedMessage = UrlBuilder.getMessage("contactUsFormRequiredErrorMsg.key");
            softAssertions.assertThat(contactUsPage.getWebDriver().getPageSource().contains(expectedMessage))
                    .as("Expected Message: "+ expectedMessage + " :is not displayed in the page")
                    .isTrue();
        });
        softAssertions.assertAll();
    }

    @When("^submit form and assert error message$")
    public void submitFormAndAssertErrorMsg(){
        contactUsPage.clickSubmitButton();
        String expectedMessage = UrlBuilder.getMessage("contactUsFormRequiredErrorMsg.key");
        Assert.assertTrue(contactUsPage.getWebDriver().getPageSource().contains(expectedMessage));
    }

    @Then("^mandatory fields are displayed with asterisks on contact us page$")
    public void mandatoryFieldsAreDisplayedWithAsterisksOnContactUsPage() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(contactUsPage.getContactNameLabel().contains("*"));
        softAssert.assertTrue(contactUsPage.getContactEmailLabel().contains("*"));
        softAssert.assertTrue(contactUsPage.getContactCommentLabel().contains("*"));
        softAssert.assertAll();
    }
    @And("^Click all required fields and validate error messages$")
    public void clickAllRequiredFieldsAndValidateErrorMessages(DataTable fieldnames) throws Throwable {
        contactUsPage.completeFormWithouts(fieldnames);
    }

}