package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.page_objects.gui.DeviceRegistrationPage;
import com.salmon.test.page_objects.gui.models.DeviceRegistrationPageModel;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DeviceRegistrationPageSteps {

    private DeviceRegistrationPage deviceRegistrationPage;

    public DeviceRegistrationPageSteps(DeviceRegistrationPage deviceRegistrationPage) {
        this.deviceRegistrationPage = deviceRegistrationPage;
    }

    @Then("^the user should see the (.*) symbol for the mandatory fields in device registration page$")
    public void theUserShouldSeeTheSymbolForTheMandatoryFieldsInDeviceRegistrationPage(String expected, List<String> fields) {
        fields.forEach(s -> assertEquals(deviceRegistrationPage.getCssStyleContent(s), expected,
                s + " field in registration page should be marked as mandatory"));
    }

    @Then("^Device Colour dropdown options should only be provided after selecting the Device Type$")
    public void deviceColourDropdownOptionsShouldOnlyBeProvidedAfterSelectingTheDeviceType() {
        assertEquals(deviceRegistrationPage.getOptionsFromDeviceColourDropDown().size(), 1, "Device Colour Dropdown should not have " +
                "options until Device Type is selected.");
        deviceRegistrationPage.selectDeviceDropDownValue("Hyper");
        assertTrue(deviceRegistrationPage.getOptionsFromDeviceColourDropDown().size() > 1, "Device Colour Dropdown should have " +
                "more options after selecting the Device Type.");
    }

    @When("^complete the form with invalid MyGloNumber$")
    public void completeTheFormWithInvalidMyGloNumber() {
        DeviceRegistrationPageModel deviceRegistrationPageModel = DeviceRegistrationPageModel.builder().build().withDefaultValues();
        deviceRegistrationPageModel.setCouponCode(RandomGenerator.randomInteger(8));
        deviceRegistrationPage.populateFormFields(deviceRegistrationPageModel);
    }

    @And("^assert the content of the page is displayed$")
    public void assertContentIsDisplayed() {
        if(UrlBuilder.getLocale().equalsIgnoreCase("it")){
            assertTrue(deviceRegistrationPage.waitForExpectedElement(deviceRegistrationPage.MY_GLO_NUMBER,10).isDisplayed());
            assertTrue(deviceRegistrationPage.waitForExpectedElement(deviceRegistrationPage.VALIDATE_BUTTON,10).isDisplayed());
        }
        else{
            assertTrue(deviceRegistrationPage.waitForExpectedElement(deviceRegistrationPage.DEVICE_REGISTRATION_HEADER,10).isDisplayed());
            assertTrue(deviceRegistrationPage.waitForExpectedElement(deviceRegistrationPage.VALIDATE_BUTTON,10).isDisplayed());
        }
    }

    @And("^enter invalid device number and assert the error message$")
    public void assertErrorMessageWhenEnterInvalidDeviceNumber() {
        deviceRegistrationPage.enterDeviceNumber();
        deviceRegistrationPage.waitForAjaxElementNotToBePresent(deviceRegistrationPage.getWebDriver(),10);
        assertTrue(deviceRegistrationPage.waitForExpectedElement(deviceRegistrationPage.DEVICE_MESSAGE,5).isDisplayed());
    }

    @Then("^verify the vote button works correctly$")
    public void verifyTheVoteButtonWorksCorrectly() {
        if(deviceRegistrationPage.isPageReadytoVote("voteMessage.key"))
            assertTrue(deviceRegistrationPage.verifyVoteButtonClickable());
    }
}