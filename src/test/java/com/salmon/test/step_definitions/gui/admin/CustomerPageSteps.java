package com.salmon.test.step_definitions.gui.admin;


import static com.salmon.test.enums.PermittedCharacters.NUMERIC;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.RegistrationPage;
import com.salmon.test.page_objects.gui.admin.CustomerPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.testng.Assert;

public class CustomerPageSteps {

  private CustomerPage customerPage;
  private RegistrationPage registrationPage;


  public CustomerPageSteps(CustomerPage customerPage, RegistrationPage registrationPage) {
    this.customerPage = customerPage;
    this.registrationPage=registrationPage;

  }

  @Given("^user navigates to all customers page$")
  public void userNavigatesToAllCustomersPage() {
    customerPage.clickCustomersLink();
    customerPage.clickAllCustomersLink();
  }

  @And("^customer list is displayed correctly$")
  public void customerListIsDisplayedCorrectly(){
    Assert.assertTrue(customerPage.isAddNewCustomerBtnPresent());
    Assert.assertEquals(customerPage.getPageTitle(),"Customers");
    Assert.assertTrue(customerPage.isCustomerRecordTextPresent());
    Assert.assertNotEquals(customerPage.getCustomerRecordNumbers(),0);
  }

  @And("^customer table count and columns are correct$")
  public void customerTableCountAndColumnsAreCorrect()throws Exception  {
    Assert.assertNotEquals(customerPage.getCustomerTableCol(),0);
    Assert.assertNotEquals(customerPage.getCustomerTableCount(),0);
  }

  @And("^next page arrow button is enabled$")
  public void nextPageArrowButtonIsEnabled() {
   //Assert.assertTrue(customerPage.isNextPageBtnEnable());
  }

  @And("^customer table info is correct$")
  public void customerTableInfoIsCorrect(){
    customerPage.verifyAllCustomerIds();
  }

  @And("^user selects the first entry in customer table$")
  public void userSelectsTheFirstEntryInCustomerTable() throws Throwable {
    customerPage.selectItemInCustomerTable(0);
  }

  @And("^user selects edit option from action listbox$")
  public void userSelectsEditOptionFromActionListbox() throws Throwable {
    customerPage.clickActionBtn();
    customerPage.selectEditOption();
    customerPage.clickRecordNumbers();

  }

  @And("^user updates telephone and clicks save$")
  public void userUpdatesTelephoneAndClicksSave() {
    customerPage.setCityzenCardNumber("01"+random(10,NUMERIC));
    customerPage.clickSaveBtn();
  }


  @Then("^save is successful$")
  public void saveIsSuccessful(){
   Assert.assertTrue(customerPage.isSuccesMsgPresent());
  }


  @And("^user clicks edit link$")
  public void userClicksEditLink() throws Throwable {
    customerPage.clickEditLink();
  }

  @And("^user switches to account info page$")
  public void userSwitchesToAccountInfoPage(){
    customerPage.clickAccountInfoLink();
  }

  @And("^user searches customer by search keyword$")
  public void userSearchesCustomerBySearchKeyword() throws Throwable {
    customerPage.enterSearchTerm(UrlBuilder.getMessage("loginValidEmail.key"));
    customerPage.clickSearchBtn();
  }
  @Then("^search result is displayed correctly$")
  public void searchResultIsDisplayedCorrectly() {
    Assert.assertNotEquals(customerPage.getCustomerRecordNumbers(),0);
  }
  @And("^user enable IDScan for new customer$")
  public void userEnableIDScan(){
    customerPage.clickOnAccountDashboardLink();
  }

  @And("^user toggles IDScan on$")
  public void IDScanIsON() throws Throwable {
    customerPage.userTurnOnIDScan();
  }

  @And("^user approves newly created user$")
  public void userApprovesNewlyCreatedUser() throws Throwable {
    customerPage.enterSearchTerm(registrationPage.emailAddressData);
    customerPage.userApprovesNewlyCreatedUser(registrationPage.emailAddressData);
  }

  @And("^user logins Magento Admin and assert MGM Verification Status of the customer$")
  public void userLoginsMagentoAdminAndAssertCustomerMGMVerificationStatus() throws Throwable {
    customerPage.loginMagentoAdminAndAssertCustomerMGMVerificationStatus();
  }
}
