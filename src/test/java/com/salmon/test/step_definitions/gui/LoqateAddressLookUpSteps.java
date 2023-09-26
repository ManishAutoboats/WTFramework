package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.LoqateAddressLookUpPage;
import com.salmon.test.page_objects.gui.models.RegistrationPageModel.Address;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Optional;

import static com.salmon.test.framework.helpers.UrlBuilder.getLocale;
import static com.salmon.test.framework.helpers.UrlBuilder.getMessage;
import static com.salmon.test.page_objects.gui.constants.Locale.PL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertTrue;

public class LoqateAddressLookUpSteps {

    private LoqateAddressLookUpPage loqateAddressLookUpPage;
    private String strEnterAddress;
    private String strEnterStreet;
    private String houseNumber;
    private String city;
    private String postcode;
    private String country;
    private List<WebElement> autoCompleteAddressList;
    private String page;

    public LoqateAddressLookUpSteps(LoqateAddressLookUpPage loqateAddressLookUpPage) {
        this.loqateAddressLookUpPage = loqateAddressLookUpPage;
        strEnterAddress = getMessage("addressKeyword.key");
        strEnterStreet = getMessage("streetKeyword.key");
        houseNumber = getMessage("housenumberkeyword.key");
        city = getMessage("citykeyword.key");
        postcode = getMessage("postcode.key");
        country = getMessage("countrykeyword.key");
    }

    @When("^(?:Glo|user) start entering the address in (.*) with (.*)$")
    public void gloStartEnteringTheAddressIn(String page, String addressKey) {
        this.page = page;
        strEnterAddress = getMessage(addressKey);
        loqateAddressLookUpPage.startEnteringAddressWith(strEnterAddress, this.page);
    }

    @Then("^(?:Glo|user) should be presented with Loqate address look up auto-completion$")
    public void gloShouldBePresentedWithLoqateAddressLookUpAutoCompletionIn() {
        assertTrue(loqateAddressLookUpPage.isLoqateAutoCompleteDisplayed(page),
                "Issue with Country Specific Address look up - Loqate:");
    }

    @When("^(?:Glo|user) has selected an address from the auto-completion$")
    public void gloHasSelectedAnAddressFromTheAutoCompletionIn() throws InterruptedException {
        if (getLocale().equals(PL.getName()) || getLocale().equals("velobe")) {
            loqateAddressLookUpPage.clickFirstOptionIfPresent(strEnterAddress);
        }
        else {
            autoCompleteAddressList = loqateAddressLookUpPage
                .getLoqateAutoCompleteAddressesList(page);
            Thread.sleep(1000);
            autoCompleteAddressList.stream()
                .filter(webElement -> StringUtils.
                    containsIgnoreCase(webElement.getAttribute("title"), strEnterAddress))
                .findFirst()
                .ifPresent(WebElement::click);
        }
    }

    @Then("^(?:Glo|user) address fields should be populated with the selected address$")
    public void GloAddressFieldsArePopulatedWithTheSelectedAddressIn() {
        Address selectedAddress = loqateAddressLookUpPage.getSelectedAddress(page);

        if(getLocale().equals(PL.getName())) {
            assertThat(selectedAddress.getStreetAndHouseNumber(),
                containsStringIgnoringCase(strEnterStreet));
        }
        else {
            assertThat(selectedAddress.getStreetAndHouseNumber(),
                containsStringIgnoringCase(strEnterAddress));
        }
        assertHouseNumber(selectedAddress);
        assertThat(selectedAddress.getCity(), equalToIgnoringCase(city));
        assertThat(selectedAddress.getPostcode(), equalToIgnoringCase(postcode));
        assertCountry(selectedAddress);
    }

    @And("^(?:Glo|user) can amend the fields manually if desired$")
    public void gloCanAmendTheFieldsManuallyIfDesiredIn() {
        String amendStreet = "amendStreet";
        loqateAddressLookUpPage.startEnteringAddressWith(amendStreet, page);

        Address selectedAddress = loqateAddressLookUpPage.getSelectedAddress(page);

        assertThat(selectedAddress.getStreetAndHouseNumber(), containsString(amendStreet));
        assertHouseNumber(selectedAddress);
        assertThat(selectedAddress.getCity(), equalToIgnoringCase(city));
        assertThat(selectedAddress.getPostcode(), equalToIgnoringCase(postcode));
        assertCountry(selectedAddress);
    }

    private void assertHouseNumber(Address selectedAddress) {
        //assert only if the House Number field exists
        Optional.ofNullable(selectedAddress.getHouseNumber())
                .ifPresent(s -> assertThat(selectedAddress.getHouseNumber(), equalToIgnoringCase(houseNumber)));
    }

    private void assertCountry(Address selectedAddress) {
        //assert only if the country field exists
        Optional.ofNullable(selectedAddress.getCountry())
                .ifPresent(s -> assertThat(selectedAddress.getCountry(), equalToIgnoringCase(country)));
    }

    @When("^start entering the address in (.*) with (.*)$")
    public void startEnteringTheAddressIn(String page, String addressKey) {
        this.page = page;
        strEnterAddress = getMessage(addressKey);
        switch (UrlBuilder.getLocale()) {
            case "vusefr":
            case "vuseuk":
                loqateAddressLookUpPage.startEnteringAddressFR(strEnterAddress, this.page);
                break;
            default:
                loqateAddressLookUpPage.startEnteringAddressWithZA(strEnterAddress, this.page);
        }
    }
}
