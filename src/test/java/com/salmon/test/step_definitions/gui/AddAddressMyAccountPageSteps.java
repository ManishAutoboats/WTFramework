package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.models.cucumber.glo.myaccount.AddressModel;
import com.salmon.test.page_objects.gui.AccountDashboardPage;
import com.salmon.test.page_objects.gui.AddNewAddressPage;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class AddAddressMyAccountPageSteps {

  private AddNewAddressPage addNewAddressPage;
  private AccountDashboardPage accountDashboardPage;
  private SoftAssertions softAssertions;

  public AddAddressMyAccountPageSteps(AddNewAddressPage addNewAddressPage, AccountDashboardPage accountDashboardPage) {
    this.addNewAddressPage = addNewAddressPage;
    softAssertions = new SoftAssertions();
    this.accountDashboardPage = accountDashboardPage;
  }

  @And("^ensure Add Address page is loaded$")
  public void paymentPageDetailsConfirmed() {
    assertTrue(addNewAddressPage.isAddAddressPopupPresent());
  }

  @And("^address finder field should be present in the page$")
  public void addressFinderIsVisible() {
    assertTrue(addNewAddressPage.isAddressFinderVisible());
  }

  @And("^confirm Add Address expected input and action elements are present$")
  public void confirmAddAddressExpectedInputAndActionElementsArePresent() {
    addNewAddressPage.inputElementsDisplayed();
  }

  @And("^user clicks on Save address without populating any default fields$")
  public void userClicksOnSaveAddressWithoutPopulatingAnyDefaultFields() {
    addNewAddressPage.pressSaveAddressButton();
  }

  @Then("^page displays expected errors$")
  public void pageDisplaysExpectedErrors() {
    addNewAddressPage.waitForPage();
    addNewAddressPage.assertStreetAddressCityAndPostCodeErrorsDisplayed();
  }

  @And("^click the go back button$")
  public void clickTheGoBackButton() {
    addNewAddressPage.pressGoBackButton();
  }
  
  @And("^click on back button$")
  public void clickOnBackButton() {
    addNewAddressPage.clickOnBackButton();
  }

  @And("^populate all address fields including new first and last name$")
  public void populateAllAddressFieldsIncludingNewFirstAndLastName() {
    if("glode".equals(UrlBuilder.getLocale()))
      addNewAddressPage.populateAllAddressFieldsExceptForFirstAndLastName();
    else
      addNewAddressPage.populateAllAddressInputFieldsIncludingFirstAndLastName();
  }

  @And("^populate all address fields except for first and last name$")
  public void populateAllAddressFieldsExceptForFirstAndLastName() {
    addNewAddressPage.populateAllAddressFieldsExceptForFirstAndLastName();
  }

  @Then("^click the save address button$")
  public void clickTheSaveAddressButton() {
    addNewAddressPage.pressSaveAddressButton();
  }

  @And("^click save button after changing address under Address page$")
  public void clickSaveButtonAfterChangingAddressUnderAddressPage() {
    addNewAddressPage.clickFirstElementByQueryJSExecutor(addNewAddressPage.SAVE_ADDRESS_BUTTON_VELO);
  }

  @Then("^click the save address Pop up screen button$")
  public void clickTheSaveAddressPopUpButton() {
    addNewAddressPage.pressPopUpFormSaveAddressButton();
  }

  @And("^tick default billing address")
  public void ticketDefaultBilling() {
    addNewAddressPage.tickDefaultBillingAddress();
  }

  @And("^tick default shipping address")
  public void ticketDefaultShipping() {
    addNewAddressPage.tickDefaultShippingAddress();
  }

  @And("^confirm Add address form is presented to user$")
  public void confirmAddAddressFormIsPresentedToUser() {

    switch (UrlBuilder.getLocale()) {
      case "vusede":
      case "fr":
      case "vusefr":
      case "mx":
      case "vusemx":
      case "vypeit":
      case "vuseit":
      case "vuseco":
      case "vuseza":
        addNewAddressPage.waitForExpectedElement(addNewAddressPage.eleAddNewAddressFormDe).isDisplayed();
        break;
      case "uk":
        break;
      case "pl":
        addNewAddressPage.waitForExpectedElement(addNewAddressPage.eleAddNewAddressFormPl).isDisplayed();
        break;
      case "vuseuk":
      case "ie":
        addNewAddressPage.waitForExpectedElement(addNewAddressPage.eleAddNewAddressForm).isDisplayed();
        break;
      default:
        addNewAddressPage.waitForExpectedElement(addNewAddressPage.eleAddnewAddressSection).isDisplayed();
    }
  }

  @And("^clear all address fields$")
  public void clearDataFromAllEditAddressInputFields() {
    addNewAddressPage.clearDataFromAllEditAddressInputFields();
  }
  
  @And("^assert error validation message '(.*)' is displayed$")
  public void assertErrorValidationMessagesForMandatoryFields(String strErrorMessage) {
    addNewAddressPage.assertErrorValidationMessageForEditAddressInputFields(strErrorMessage);
  }
  
  @And("^user clicks on Cancel button from Address pop-up screen$")
  public void clickOnCancelButtonFromEditAddressPopUp() {
    addNewAddressPage.clickOnCancelButtonFromEditAddressPopUp();
  }
  
  @And("^assert Edit and Remove buttons presence under Default Billing Address$")
  public void assertEditAndRemoveButtonsUnderDefaultBillingAddress() {
    assertTrue(addNewAddressPage.waitForExpectedElement(By.cssSelector("#edit_billing"),10).isDisplayed());
    assertFalse(addNewAddressPage.getWebDriver().findElements(By.cssSelector("#delete_billing")).size()>0);
  }
  
  @And("^assert Edit and Remove buttons presence under Default Shipping Address$")
  public void assertEditAndRemoveButtonsUnderDefaultShippingAddress() {
    assertTrue(addNewAddressPage.waitForExpectedElement(By.cssSelector("#edit_shipping"),10).isDisplayed());
    assertFalse(addNewAddressPage.getWebDriver().findElements(By.cssSelector("#delete_shipping")).size()>0);
  }
  
  @And("^assert Edit and Remove buttons presence under Additional Addresses$")
  public void assertEditAndRemoveButtonsUnderAdditonalAddresses() {
    assertTrue(addNewAddressPage.waitForExpectedElement(By.cssSelector("#edit_address"),10).isDisplayed());
    assertTrue(addNewAddressPage.getWebDriver().findElements(By.cssSelector("a.action.delete:nth-child(2)")).size()>0);
  }

  @And("^users clicks on Edit Address link$")
  public void userClicksOnEditAddressLink() {
    addNewAddressPage.userClicksOnEditAddressLink();
  }

  @And("^assert pre-populated first name with the existing account$")
  public void assertPrePopulatedFirstNameWithExistingAccount() {
    addNewAddressPage.assertPrePopulatedFirstNameWithExistingAccount();
  }

  @And("^assert pre-populated last name with the existing account$")
  public void assertPrePopulatedLastNameWithExistingAccount() {
    addNewAddressPage.assertPrePopulatedLastNameWithExistingAccount();
  }

  @And("^users clicks on Edit Shipping Address link$")
  public void userClicksOnEditShippingAddressLink() {
    addNewAddressPage.userClicksOnEditShippingAddressLink();
  }

  @Then ("^users clicks on the The Address Book link$")
  public void userClicksOnTheAddressBookLink() {
    addNewAddressPage.userClicksOnTheAddressBookLink();
  }

  @Then ("^user clicks on Edit Billing Address Link$")
  public void userClicksOnEditBillingAddressLink() {
    addNewAddressPage.userClicksOnEditBillingAddressLink();
  }

  @And("^populate all address fields for an existing User$")
  public void populateAllAddressFieldsForAnExistingUser() {
    addNewAddressPage.populateAllAddressFieldsForAnExistingUser();
  }

  @When("^user attempts to add the new address with (.*)$")
  public void userAttemptsToAddTheNewAddressWithNIP(String nip) {
    addNewAddressPage.populateAllAddressFieldsExceptForFirstAndLastName();
    addNewAddressPage.populateTaxIDFieldWith(nip);
    addNewAddressPage.pressSaveAddressButton();
  }

  @When("^user attempts to add the new address as with following NIP Then assert error message$")
  public void userAttemptsToAddNewAddressWithNIPThenAssertErrorMsg(List<Map<String, String>> nipMap) {
    addNewAddressPage.populateAllAddressFieldsExceptForFirstAndLastName();
    nipMap.forEach(map -> {
      addNewAddressPage.populateTaxIDFieldWith(map.get("pattern"));
      addNewAddressPage.pressSaveAddressButton();
      String expectedMessage = UrlBuilder.getMessage(map.get("expected"));
      softAssertions.assertThat(addNewAddressPage.getWebDriver().getPageSource().contains(expectedMessage))
              .as("Expected Message: "+ expectedMessage + " :is not displayed in the page")
              .isTrue();
    });
    softAssertions.assertAll();
  }

  @And("^user add '(.*)' '(.*)' '(.*)' '(.*)' address manually$")
  public void addAddressManually(String streetAndHouseNumber, String city, String postal, String telephone){
    addNewAddressPage.addAddressManuallyOnDeliveryAddressPage(UrlBuilder.getMessage(streetAndHouseNumber), UrlBuilder.getMessage(city), UrlBuilder.getMessage(postal), UrlBuilder.getMessage(telephone));
  }

  @And("^add new address with more than 30 characters in address field$")
  public void addNewAddressFromMyAccountWithMoreThan30ChractersInAddressField() {
    accountDashboardPage.addNewAddressFromMyAccountWithMoreThan30CharactersInAddressField();
  }

  @When("^users clicks on address link (.*)$")
  public void userClicksOnChangeDeliveryBillingAddressLink(String addressType) {
    if ("change delivery address".equalsIgnoreCase(addressType)) {
      addNewAddressPage.userClicksChangeDeliveryAddressLink();
    } else {
      addNewAddressPage.userClicksChangeBillingAddressLink();
    }
  }

  @When("^edit address basic information$")
  public void editAddressBasicInformation(List<AddressModel> addressInfo) {
     for(AddressModel adModel:addressInfo){
       addNewAddressPage.setAddressInformation(adModel);
     }
    addNewAddressPage.clickOnSaveAddressButtonToAddAndEditAddress();
  }

  @Then("^assert edit address success$")
  public void assertEditAddressSuccess() {
      assertTrue(addNewAddressPage.isEditSavedElementPresent());
  }

  @And("^users clicks on edit (billing|shipping) address$")
  public void userClicksOnEditAddress(String addressType) {
      if ("billing".equalsIgnoreCase(addressType)) {
        addNewAddressPage.userClickEditBillingAddress();
      } else if ("shipping".equalsIgnoreCase(addressType)) {
        addNewAddressPage.userClicksOnEditShippingAddressLink();
      }
  }

  @And("^populate address fields without phone number$")
  public void populateAddressFieldsWithoutPhoneNumber() {
    addNewAddressPage.populateAddressFieldsWithoutPhoneNumber();
  }

  @Then("^assert address '(.*)' displayed correctly$")
  public void assertAddressYourAddressContentKeyDisplayedCorrectly(String addressContent) {
    assertTrue(!addNewAddressPage.isAddressDuplicateOrDisappear(UrlBuilder.getMessage(addressContent)));
  }

  @When("^user closes New Address pop-up$")
  public void userClosesNewAddressPopUp() {
    addNewAddressPage.waitAndClickByElementByJSExecutor(addNewAddressPage.CLOSE_NEW_ADDRESS_POP_UP,5);
  }

  @And("user populates address details using loqate including phone number and save$")
  public void userPopulatesAddressDetailsUsingLoqateIncludingPhoneNumberAndSave() {
    addNewAddressPage.populateAddressDetailsUsingLoqateIncludingPhoneNumberAndSave();
  }

  @And("clear pre-filled data from Phone Number field$")
  public void clearPreFilledDataFromPhoneNumberField() {
    addNewAddressPage.clearFieldUsingControlKeys(addNewAddressPage.TELEPHONE_INPUT);
  }

  @And("^assert province field displayed as drop down$")
  public void assertProvinceFieldDisplayedAsDropDown() {
    addNewAddressPage.assertProvinceFieldDisplayedAsDropDown();
  }

  @And("^user enters '(.*)' in phone number field on Address pop-up$")
  public void userEntersPhoneNumberFieldOnAddressPopUp(String strValue) {
    addNewAddressPage.userEntersPhoneNumberFieldOnAddressPopUp(strValue);
  }

  @And("^user selects Default Billing Address radiobutton and click Save Address - Glo$")
  public void userSelectsDefaultBillingAddressRadiobutton() {
    addNewAddressPage.selectDefaultBillingAddressRadiobutton();
  }
}