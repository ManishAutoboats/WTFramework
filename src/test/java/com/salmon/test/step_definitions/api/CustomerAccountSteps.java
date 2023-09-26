package com.salmon.test.step_definitions.api;

import com.salmon.test.enums.DatabaseQueries;
import com.salmon.test.enums.Store;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.ApiHelper;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.WebDriverHelper;
import com.salmon.test.framework.helpers.utils.CodiceFiscaleGenerator;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.framework.helpers.utils.SessionInfo;
import com.salmon.test.models.api.customer.*;
import com.salmon.test.models.database.CustomerDB;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.services.CustomerAccountHttpService;
import com.salmon.test.services.CustomerAccountService;
import com.salmon.test.page_objects.gui.RegistrationPage;
import com.salmon.test.services.YourChoiceService;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.time.LocalDate;

import static com.salmon.test.enums.PermittedCharacters.ALPHABETS;
import static com.salmon.test.enums.PermittedCharacters.NUMERIC;
import static com.salmon.test.framework.helpers.ApiHelper.convertResponseToPojo;
import static com.salmon.test.framework.helpers.ApiHelper.toPrettyFormat;
import static com.salmon.test.framework.helpers.WebDriverHelper.getSiteMode;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.randomDateOfBirth;
import static com.salmon.test.page_objects.gui.constants.Context.CUSTOMER;
import static io.restassured.path.json.JsonPath.from;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertTrue;


public class CustomerAccountSteps {

  private static final Logger LOG = LoggerFactory.getLogger(CustomerAccountSteps.class);
  private CustomerAccountService customerAccountService;
  private CustomerAccountHttpService customerAccountHttpService;
  public static Response response;
  public static Customer customer;
  private String customerId;
  private Customer actualCustomer;
  private String customerEmail;
  private List<Map<String, Object>> userList;
  private ScenarioContext scenarioContext;
  private String site;
  private String code;
  private String PHONE_NUMBER_KZ_MOBILE = "91"+random(9,NUMERIC);
  public CustomerAccountSteps(CustomerAccountService customerAccountService,
                              CustomerAccountHttpService customerAccountHttpService) {
    this.customerAccountService = customerAccountService;
    this.customerAccountHttpService = customerAccountHttpService;
  }

  @When("^I create a customer account$")
  public void i_create_a_customer_account() {
    CustomerBuilder customerBuilder = customerAccountService.createNewCustomer();
    customer = customerBuilder.getCustomer();
    response = customerAccountService.createCustomerAccount(customerBuilder);
    LOG.info(response.getBody().asString());
  }

  @When("^I create a customer account with subscription$")
  public void i_create_a_customer_account_with_subscription() {
    CustomerBuilder customerBuilder = customerAccountService.createNewCustomer();
    customer = customerBuilder.getCustomer();
    customerBuilder.getCustomer().getExtension_attributes().setIs_subscribed("true");
    response = customerAccountService.createCustomerAccount(customerBuilder);
    LOG.info(response.getBody().asString());
  }

  @Then("^Customer account response status code is (.*)$")
  public void customer_account_response_status_code_is(int status) {
    assertThat(response.getStatusCode()).isEqualTo(status);
  }

  @Then("^Customer token response status code is (.*)$")
  public void customer_token_response_status_code_is(int status) {
    assertThat(response.getStatusCode()).isEqualTo(status);
  }

  @And("^Verify customer account is created with the given details$")
  public void verifyCustomerAccountIsCreatedWithTheGivenDetails() {
    Customer expectedCustomer = customer;
    actualCustomer = ApiHelper.convertResponseToPojo(response, Customer.class);
    assertThat(actualCustomer).as("Unable to parse customer account response").isNotNull();
    if(!SessionInfo.scenarioName.contains("Update customer account details by ME")) {
      customerId = String.valueOf(actualCustomer.getId());
    }
    assertThat(actualCustomer.getGroup_id()).isEqualTo(expectedCustomer.getGroup_id());
    //assertThat(actualCustomer.getConfirmation()).isEqualTo(expectedCustomer.getConfirmation());
    assertThat(actualCustomer.getDob()).isEqualTo(expectedCustomer.getDob());
    assertThat(actualCustomer.getFirstname()).isEqualTo(expectedCustomer.getFirstname());
    assertThat(actualCustomer.getLastname()).isEqualTo(expectedCustomer.getLastname());
    assertThat(actualCustomer.getEmail()).isEqualTo(expectedCustomer.getEmail());
    assertThat(actualCustomer.getPrefix()).isEqualTo(expectedCustomer.getPrefix());
    //TODO: Default store and website id's are 2???
    assertThat(actualCustomer.getStore_id()).isEqualTo(expectedCustomer.getStore_id());
    assertThat(actualCustomer.getWebsite_id()).isEqualTo(expectedCustomer.getWebsite_id());
    assertThat(actualCustomer.getDisable_auto_group_change())
            .isEqualTo(expectedCustomer.getDisable_auto_group_change());

    assertThat(Optional.ofNullable(expectedCustomer.getAddresses()).isPresent()).isTrue();
    Optional<Addresses> expectedAddress = Optional.ofNullable(expectedCustomer.getAddresses()).get()
            .stream().findFirst();

    assertThat(Optional.ofNullable(actualCustomer.getAddresses()).isPresent()).isTrue();
    Optional.ofNullable(actualCustomer.getAddresses()).get().stream().findFirst().ifPresent(
            actualAddress -> {
              if (UrlBuilder.storeCode.toLowerCase().contains("de")) {
                assertThat(actualAddress.getRegion().getRegion_id())
                        .isEqualTo(expectedAddress.get().getRegion().getRegion_id());
                assertThat(actualAddress.getRegion().getRegion())
                        .isEqualTo(expectedAddress.get().getRegion().getRegion());
//                        TODO: Region is always coming as null even though we set this as part of customer creation. Bug??
//                    assertThat(actualAddress.getRegion().getRegion_code()).isEqualTo(expectedAddress.get().getRegion().getRegion_code());
              }
              assertThat(actualAddress.getRegion_id()).isEqualTo(expectedAddress.get().getRegion_id());
              assertThat(actualAddress.getCountry_id())
                      .isEqualTo(expectedAddress.get().getCountry_id());
              assertThat(actualAddress.getStreet()).isEqualTo(expectedAddress.get().getStreet());
              assertThat(actualAddress.getCompany()).isEqualTo(expectedAddress.get().getCompany());
              assertThat(actualAddress.getTelephone()).isEqualTo(expectedAddress.get().getTelephone());
              assertThat(actualAddress.getFax()).isEqualTo(expectedAddress.get().getFax());
              assertThat(actualAddress.getPostcode()).isEqualTo(expectedAddress.get().getPostcode());
              assertThat(actualAddress.getCity()).isEqualTo(expectedAddress.get().getCity());
              assertThat(actualAddress.getFirstname()).isEqualTo(expectedAddress.get().getFirstname());
              assertThat(actualAddress.getLastname()).isEqualTo(expectedAddress.get().getLastname());
              assertThat(actualAddress.getPrefix()).isEqualTo(expectedAddress.get().getPrefix());
              assertThat(actualAddress.getDefault_billing())
                      .isEqualTo(expectedAddress.get().getDefault_billing());
              assertThat(actualAddress.getDefault_shipping())
                      .isEqualTo(expectedAddress.get().getDefault_shipping());
            }
    );

    // Verifying Extension attributes
    assertThat(actualCustomer.getExtension_attributes().getData_handling_consent())
            .isEqualTo(expectedCustomer.getExtension_attributes().getData_handling_consent());
    assertThat(actualCustomer.getExtension_attributes().getMarket_research_consent())
            .isEqualTo(expectedCustomer.getExtension_attributes().getMarket_research_consent());
    assertThat(actualCustomer.getExtension_attributes().getEmail_consent())
            .isEqualTo(expectedCustomer.getExtension_attributes().getEmail_consent());
    assertThat(actualCustomer.getExtension_attributes().getTelephone_consent())
            .isEqualTo(expectedCustomer.getExtension_attributes().getTelephone_consent());
    assertThat(actualCustomer.getExtension_attributes().getEmail_optout())
            .isEqualTo(expectedCustomer.getExtension_attributes().getEmail_optout());
    assertThat(actualCustomer.getExtension_attributes().getTelephone_optout())
            .isEqualTo(expectedCustomer.getExtension_attributes().getTelephone_optout());
    assertThat(actualCustomer.getExtension_attributes().getInitial_load())
            .isEqualTo(expectedCustomer.getExtension_attributes().getInitial_load());
    assertThat(actualCustomer.getExtension_attributes().getCompany())
            .isEqualTo(expectedCustomer.getExtension_attributes().getCompany());
    assertThat(Boolean.valueOf(actualCustomer.getExtension_attributes().getIs_subscribed()))
            .isEqualTo(Boolean.valueOf(expectedCustomer.getExtension_attributes().getIs_subscribed()));
    assertThat(actualCustomer.getExtension_attributes().getAmazon_id())
            .isEqualTo(expectedCustomer.getExtension_attributes().getAmazon_id());
    assertThat(actualCustomer.getExtension_attributes().getVertex_customer_code())
            .isEqualTo(expectedCustomer.getExtension_attributes().getVertex_customer_code());

    // Verifying Customer attributes
//        assertThat(Optional.ofNullable(expectedCustomer.getCustom_attributes()).get().size()).isEqualTo(Optional.ofNullable(actualCustomer.getCustom_attributes()).get().size());

       /* Map<String, String> actualCustomAttrMap = Optional.ofNullable(actualCustomer.getCustom_attributes()).get().stream().collect(Collectors.toMap(CustomAttributes::getAttribute_code, CustomAttributes::getValue));
        //TODO: filtered attribute "reward_warning_notification" exists in db and not returned by API. Need to understand why? market specific attribute?
        //TODO: filtered attribute "reward_update_notification" & "reward_warning_notification" exists in db and not on Magento admin console. Need to understand why?
        Optional.ofNullable(expectedCustomer.getCustom_attributes()).get().stream().filter(ca -> !ca.getAttribute_code().equals("reward_warning_notification")).forEach(expectedCustomAttr -> {
                    assertThat(actualCustomAttrMap.get(expectedCustomAttr.getAttribute_code())).as("Customer attribute "+expectedCustomAttr.getAttribute_code()+" value not matched").isEqualTo(expectedCustomAttr.getValue());
                }
        );*/
  }

  @When("^I create a customer account with invalid (.*)$")
  public void iCreateACustomerAccountWithInvalid(String invalidField) {
    CustomerBuilder customerBuilder = customerAccountService.createNewCustomer();
    switch (invalidField) {
      case "email":
        customerBuilder.getCustomer().setEmail("");
        break;
      case "password":
        customerBuilder.setPassword("");
        break;
      case "created_at":
        customerBuilder.getCustomer().setCreated_at("");
        break;
    }
    response = customerAccountService.createCustomerAccount(customerBuilder);
  }

  @And("^Customer account error message is (.*)$")
  public void customerAccountErrorMessageIs(String errorMessage) {
    errorMessage = Props.getLocalMessage(errorMessage);
    assertThat(from(response.asString()).get("message").toString()).isEqualTo(errorMessage);
  }

  @When("^I create a customer account with existing user email$")
  public void iCreateACustomerAccountWithExistingUserEmail() {
    CustomerBuilder customerBuilder = customerAccountService.createNewCustomer();
    customerBuilder.setCustomer(customer);
    response = customerAccountService.createCustomerAccount(customerBuilder);
  }

  @When("^I get the customer details for the given customer id$")
  public void iGetTheCustomerDetailsForTheGivenCustomerId() {
    response = customerAccountService.getCustomerDetailsByID(customerId);
    LOG.info(response.prettyPrint());
  }

  @When("^I get the customer details for the given customer details$")
  public void iGetTheCustomerDetailsForTheGivenCustomerDetails() {
    response = customerAccountService.getCustomerDetailsByME();
    LOG.info(response.prettyPrint());
  }

  @When("^I get the customer details without token$")
  public void iGetTheCustomerDetailsWithoutToken() {
    response = customerAccountService.getCustomerDetailsByMEWithoutToken();
    LOG.info(response.prettyPrint());
  }

  @Then("^Verify appropriate customer details are returned$")
  public void verifyAppropriateCustomerDetailsAreReturned() {
    actualCustomer = ApiHelper.convertResponseToPojo(response, Customer.class);
    customerId = String.valueOf(actualCustomer.getId());
    CustomerDB expectedCustomer = customerAccountService.getCustomerDetailsFromDB(customerId);
    Customer actualCustomer = ApiHelper.convertResponseToPojo(response, Customer.class);
    Optional<Customer> optionalActualCustomer = Optional.ofNullable(actualCustomer);

    Optional.ofNullable(expectedCustomer).ifPresent($ -> {
      // Verify Customer data
      assertThat(optionalActualCustomer.get().getId()).isEqualTo($.getEntity_id());
      assertThat(optionalActualCustomer.get().getGroup_id()).isEqualTo($.getGroup_id());
      assertThat(optionalActualCustomer.get().getDefault_billing())
              .isEqualTo($.getDefault_billing());
      assertThat(optionalActualCustomer.get().getDefault_shipping())
              .isEqualTo($.getDefault_shipping());
      assertThat(optionalActualCustomer.get().getCreated_at())
              .isEqualTo($.getCreated_at().substring(0, $.getCreated_at().length() - 2));
      assertThat(optionalActualCustomer.get().getUpdated_at())
              .isEqualTo($.getUpdated_at().substring(0, $.getUpdated_at().length() - 2));
      assertThat(optionalActualCustomer.get().getCreated_in()).isEqualTo($.getCreated_in());
      assertThat(optionalActualCustomer.get().getDob()).isEqualTo($.getDob());
      assertThat(optionalActualCustomer.get().getEmail()).isEqualTo($.getEmail());
      assertThat(optionalActualCustomer.get().getFirstname()).isEqualTo($.getFirstname());
      assertThat(optionalActualCustomer.get().getLastname()).isEqualTo($.getLastname());
      assertThat(optionalActualCustomer.get().getGender()).isEqualTo($.getGender());
      assertThat(optionalActualCustomer.get().getStore_id()).isEqualTo($.getStore_id());
      assertThat(optionalActualCustomer.get().getWebsite_id()).isEqualTo($.getWebsite_id());

      // Verify Customer address data
      assertThat(Optional.ofNullable(optionalActualCustomer.get().getAddresses()).get().size())
              .isEqualTo(Optional.ofNullable($.getAddresses()).get().size());
      Map<Integer, Addresses> actualAddressMap = Optional
              .ofNullable(optionalActualCustomer.get().getAddresses()).get().stream()
              .collect(Collectors.toMap(Addresses::getId, Function.identity()));
      Optional.ofNullable($.getAddresses()).get().forEach(
              expectedAddress -> {
                assertThat((actualAddressMap.get(expectedAddress.getEntity_id()).getId()))
                        .isEqualTo(expectedAddress.getEntity_id());
                assertThat((actualAddressMap.get(expectedAddress.getEntity_id()).getCustomer_id()))
                        .isEqualTo($.getEntity_id());
                if (UrlBuilder.storeCode.toLowerCase().contains("de")) {
                  Region region = Optional.ofNullable(expectedAddress.getRegionInfo())
                          .orElseGet(Region::new);
                  assertThat(
                          (actualAddressMap.get(expectedAddress.getEntity_id()).getRegion().getRegion_id()))
                          .isEqualTo(region.getRegion_id());
//                  assertThat(
//                          (actualAddressMap.get(expectedAddress.getEntity_id()).getRegion().getRegion()))
//                          .isEqualTo(region.getRegion());
//                        TODO: Region is always coming as null even though we set this as part of customer creation. Bug??
//                        assertThat((actualAddressMap.get(expectedAddress.getEntity_id()).getRegion().getRegion_code())).isEqualTo(region.getRegion_code());
                }
                assertThat((actualAddressMap.get(expectedAddress.getEntity_id()).getRegion_id()))
                        .isEqualTo(expectedAddress.getRegion_id());
                assertThat((actualAddressMap.get(expectedAddress.getEntity_id()).getCountry_id()))
                        .isEqualTo(expectedAddress.getCountry_id());
                assertThat((actualAddressMap.get(expectedAddress.getEntity_id()).getStreet()))
                        .isEqualTo(Arrays.asList(expectedAddress.getStreet().split("\n")));
                assertThat((actualAddressMap.get(expectedAddress.getEntity_id()).getCompany()))
                        .isEqualTo(expectedAddress.getCompany());
                assertThat((actualAddressMap.get(expectedAddress.getEntity_id()).getTelephone()))
                        .isEqualTo(expectedAddress.getTelephone());
                assertThat((actualAddressMap.get(expectedAddress.getEntity_id()).getPostcode()))
                        .isEqualTo(expectedAddress.getPostcode());
                assertThat((actualAddressMap.get(expectedAddress.getEntity_id()).getCity()))
                        .isEqualTo(expectedAddress.getCity());
                assertThat((actualAddressMap.get(expectedAddress.getEntity_id()).getFirstname()))
                        .isEqualTo(expectedAddress.getFirstname());
                assertThat((actualAddressMap.get(expectedAddress.getEntity_id()).getLastname()))
                        .isEqualTo(expectedAddress.getLastname());
              }
      );

      assertThat(optionalActualCustomer.get().getDisable_auto_group_change())
              .isEqualTo($.getDisable_auto_group_change());

      // Verifying Extension attributes
      assertThat(optionalActualCustomer.get().getExtension_attributes().getData_handling_consent())
              .isEqualTo($.getExtension_attributes().getData_handling_consent());
      assertThat(
              optionalActualCustomer.get().getExtension_attributes().getMarket_research_consent())
              .isEqualTo($.getExtension_attributes().getMarket_research_consent());
      assertThat(optionalActualCustomer.get().getExtension_attributes().getEmail_consent())
              .isEqualTo($.getExtension_attributes().getEmail_consent());
      assertThat(optionalActualCustomer.get().getExtension_attributes().getTelephone_consent())
              .isEqualTo($.getExtension_attributes().getTelephone_consent());
      assertThat(optionalActualCustomer.get().getExtension_attributes().getEmail_optout())
              .isEqualTo($.getExtension_attributes().getEmail_optout());
      assertThat(optionalActualCustomer.get().getExtension_attributes().getTelephone_optout())
              .isEqualTo($.getExtension_attributes().getTelephone_optout());
      assertThat(optionalActualCustomer.get().getExtension_attributes().getInitial_load())
              .isEqualTo($.getExtension_attributes().getInitial_load());
      assertThat(optionalActualCustomer.get().getExtension_attributes().getCompany())
              .isEqualTo($.getExtension_attributes().getCompany());

      boolean isSubscribed = Optional.ofNullable($.getExtension_attributes())
              .filter(ea -> "1".equals(ea.getSubscriber_status())).isPresent();
      //Todo: attributes don't return from DB.
//      assertThat(Boolean
//              .valueOf(optionalActualCustomer.get().getExtension_attributes().getIs_subscribed()))
//              .isEqualTo(isSubscribed);
      assertThat(optionalActualCustomer.get().getExtension_attributes().getAmazon_id())
              .isEqualTo($.getExtension_attributes().getAmazon_id());
      assertThat(optionalActualCustomer.get().getExtension_attributes().getVertex_customer_code())
              .isEqualTo($.getExtension_attributes().getVertex_customer_code());

      // Verifying Customer attributes
//            assertThat(Optional.ofNullable($.getCustom_attributes()).get().size()).isEqualTo(Optional.ofNullable(optionalActualCustomer.get().getCustom_attributes()).get().size());

      Map<String, String> actualCustomAttrMap = Optional
              .ofNullable(optionalActualCustomer.get().getCustom_attributes()).get().stream().collect(
                      Collectors.toMap(CustomAttributes::getAttribute_code, CustomAttributes::getValue));
      //TODO: filtered attribute "reward_warning_notification" exists in db and not returned by API. Need to understand why? market specific attribute?
      //TODO: filtered attribute "reward_update_notification" & "reward_warning_notification" exists in db and not on Magento admin console. Need to understand why?
      Optional.ofNullable($.getCustom_attributes()).get().stream()
              .filter(ca -> !ca.getAttribute_code().equals("reward_warning_notification"))
              .forEach(expectedCustomAttr -> {
                        assertThat(actualCustomAttrMap.get(expectedCustomAttr.getAttribute_code()))
                                .as("Customer attribute value not matched")
                                .isEqualTo(expectedCustomAttr.getValue());
                      }
              );
    });

  }

  @When("^I get the customer details for the invalid (.*)$")
  public void iGetTheCustomerDetailsForTheInvalid(String customerId) {
    response = customerAccountService.getCustomerDetailsByID(customerId);
  }

  @When("^I update customer details for the given customer id$")
  public void iUpdateCustomerDetailsForTheGivenCustomerId() {
    UpdateCustomer updateCustomer = new UpdateCustomer();
    actualCustomer.setFirstname(actualCustomer.getFirstname() + "_Updated");
    actualCustomer.setLastname("Sekhar");
    actualCustomer.setDob("1985-10-23");
    customer = actualCustomer;
    updateCustomer.setCustomer(actualCustomer);
    response = customerAccountService.updateCustomerAccount(customerId, updateCustomer);
    response.prettyPrint();
  }

  @When("^I update a customer account with invalid (.*)$")
  public void iUpdateACustomerAccountWithInvalid(String invalidField) {
    UpdateCustomer updateCustomer = new UpdateCustomer();
    switch (invalidField) {
      case "invalidCustomerId":
        actualCustomer.setId(0);
        break;
      case "invalidBodyCustomerId":
        actualCustomer.setId(1298753470);
        break;
      case "emptyEmail":
        actualCustomer.setEmail("");
        break;
      case "existingEmail":
        actualCustomer.setEmail(UrlBuilder.getMessage("loginValidEmail.key"));
        break;
      case "emptyLastname":
        actualCustomer.setLastname("");
        break;
      case "emptyCreatedAt":
        actualCustomer.setCreated_at("");
        break;
    }
    updateCustomer.setCustomer(actualCustomer);
    response = customerAccountService.updateCustomerAccount(customerId, updateCustomer);
  }

  @When("^I delete a customer account with invalid (.*)$")
  public void iDeleteACustomerAccountWithInvalid(String customerId) {
    response = customerAccountService.deleteCustomerAccount(customerId);
  }

  @When("^I delete customer details for the given customer id$")
  public void iDeleteCustomerDetailsForTheGivenCustomerId() {
    response = customerAccountService.deleteCustomerAccount(customerId);
    //response = customerAccountService.deleteCustomerAccount("858164");
  }

  @And("^Verify the given customer account is deleted$")
  public void verifyTheGivenCustomerAccountIsDeleted() {
    assertThat(customerAccountService.isCustomerExistInDB(customerId))
            .as("Customer account is not deleted").isFalse();

  }

  @When("^I update customer details for the customer id (.*)$")
  public void iUpdateCustomerDetailsForTheCustomerId(String customerId) {
    UpdateCustomer updateCustomer = new UpdateCustomer();
    actualCustomer.setId(0);
    actualCustomer.setEmail("auto_" + RandomGenerator.randomEmailAddress(7));
    updateCustomer.setCustomer(actualCustomer);
    response = customerAccountService.updateCustomerAccount(customerId, updateCustomer);
    LOG.info(toPrettyFormat(response.getBody().asString()));
  }

  @When("^I create a test account for different site$")
  public void i_create_a_test_accounnt_account(DataTable userTable) {
    List<Map<String, String>> users = userTable.asMaps(String.class, String.class);
    for (Map<String, String> user : users) {
      CustomerBuilderMultipleLocale customerBuilder = customerAccountService
              .createNewCustomerMultipleLocale(user);
      customer = customerBuilder.getCustomer();
      response = customerAccountService.createCustomerAccount(customerBuilder);
      LOG.info(response.getBody().asString());
    }
  }

  @When("^I create test account for different site$")
  public void i_create_test_accounnt_account(DataTable userTable) {
    List<Map<String, String>> users = userTable.asMaps(String.class, String.class);
    Map<String, String> userEditable = new HashMap<String, String>();
    for (Map<String, String> user : users) {
      RestAssured.baseURI = user.get("baseURL");
      response = customerAccountHttpService.getCookies();

      String formKeyValue = response.getBody().asString().split("form_key")[1].substring(23, 39);
      userEditable.clear();
      userEditable.putAll(user);
      userEditable.put("form_key", formKeyValue);
      userEditable.put("success_url", "");
      userEditable.put("error_url", "");
      userEditable.remove("baseURL");

      response = customerAccountHttpService
              .createCustomerAccount(userEditable, "form_key", formKeyValue);
      LOG.info(response.prettyPrint());
      LOG.info(response.getHeaders().toString());

//        customer_account_response_status_code_is(302);
//        verifyRediretLocationIsCorrect();
    }
  }

  @When("^I create the test account (.*) for (.*)$")
  public void i_create_test_accounnt_account(String account, String site) {
    customerEmail = account;
    this.site=site;
    this.code=code;
    RestAssured.baseURI = UrlBuilder.getHttpRequestPathURI(site);
    response = customerAccountHttpService.getCookies();
    String formKeyValue = response.getBody().asString().split("form_key")[1].substring(23, 39);

    Map<String, String> usertable = new HashMap<String, String>();
    usertable.put("form_key", formKeyValue);
    usertable.put("success_url", "");
    usertable.put("error_url", "");
    usertable.put("email", account);
    usertable.put("firstname", "Auto");
    usertable.put("lastname", "Test");
    usertable.put("dob", "08/05/1977");
    usertable.put("gender", "1");
    usertable.put("create_address", "1");
    usertable.put("telephone", "0987654321234");
    usertable.put("company", "");
    usertable.put("country_id", "GB");
    usertable.put("region", "");
    usertable.put("city", "BdPtY");
    usertable.put("postcode", "UB10 9DW");
    usertable.put("street[]", "10 Downing Street");
    usertable.put("default_billing", "1");
    usertable.put("default_shipping", "1");
    usertable.put("password", "Pa55word");
    usertable.put("password_confirmation", "Pa55word");
    usertable.put("persistent_remember_me", "on");
    usertable.put("custom-terms", "on");
    switch (site.toLowerCase()) {
      case "eu-vuse-gb/en":
        usertable.put("street[]","14 Langley Lane");
        usertable.replace("city", "Hertfordshire");
        usertable.replace("postcode", "WD50LR");
        break;
      case "eu-glo-it/it":
        usertable.replace("telephone", "+391234567890");
        usertable.replace("country_id", "IT");
        LocalDate dob = randomDateOfBirth();
        CodiceFiscaleGenerator fcg = new CodiceFiscaleGenerator(dob, true);
        usertable.put("codice_fiscale", fcg.getFiscalCode());
        break;
      case "eu-glo-kz/ru":
        usertable.replace("telephone", PHONE_NUMBER_KZ_MOBILE);
        usertable.replace("country_id", "KZ");
        break;
      case "eu-vype-nl/nl":
        usertable.replace("telephone", "013561346113");
        usertable.replace("country_id", "NL");
        break;
      case "eu-vype-fr/fr":
        usertable.replace("telephone", "+33626887242");
        usertable.replace("country_id", "FR");
        break;
      case "eu-vype-dk/da":
        usertable.replace("telephone", "019458770659");
        usertable.replace("country_id", "DK");
        break;
      case "am-vuse-mx/es":
        usertable.replace("telephone", "+528065012269");
        usertable.replace("country_id", "MX");
        usertable.put("region_id", "592");
        usertable.put("neo_ext", "434567");
        usertable.put("referal_code", "");
        usertable.put("referee_name", "");
        break;
      case "am-vype-co/es":
        usertable.replace("telephone", "+573447596217");
        usertable.replace("country_id", "CO");
        usertable.put("region_id", "706");
        usertable.put("citizen_card_number", "12345");
        break;
      case "eu-vype-ie/en":
        usertable.replace("telephone", "019458770659");
        usertable.replace("country_id", "IE");
        break;
      case "eu-lyft-se/sv":
        usertable.replace("dob", "1977-05-08");
        usertable.replace("telephone", "016156721262");
        usertable.replace("country_id", "SE");
        usertable.put("bank_id", "197506032916");
        break;
      case "eu-lyft-dk/da":
        usertable.replace("telephone", "019458770659");
        usertable.replace("country_id", "DK");
        break;
      case "eu-vype-it/it":
      case "eu-vuse-it/it":
        usertable.replace("telephone", "+391234567890");
        usertable.replace("country_id", "IT");
        usertable.put("mgm_promo_code", "MGM-53553");
        LocalDate dob2 = randomDateOfBirth();
        CodiceFiscaleGenerator fcg2 = new CodiceFiscaleGenerator(dob2, true);
        usertable.put("codice_fiscale", fcg2.getFiscalCode());
        usertable.put("birth_city", "Test City");
        usertable.put("consent_store_my_data", "1");
        break;
      case "eu-vype-de/de":
        usertable.replace("telephone", "+497743380569");
        usertable.replace("country_id", "DE");
        usertable.replace("dob", "02.01.1980");
        usertable.replace("lastname", random(6, ALPHABETS));
        break;
      case "eu-glo-de/de":
        usertable.replace("telephone", "+497743380569");
        usertable.replace("dob", "02.01.1980");
        usertable.replace("country_id", "DE");
        usertable.replace("lastname", random(6, ALPHABETS));
        break;
      case "eu-glo-pl/pl":
        usertable.replace("telephone", "+48223456889");
        usertable.replace("country_id", "PL");
        usertable.put("consent_bat_agreement", "1");
        usertable.put("consent_bat_agreement_fake", "1");
        usertable.put("consent_store_my_data", "1");
        break;
    }
    response = customerAccountHttpService
            .createCustomerAccount(usertable, "form_key", formKeyValue);
    LOG.info(response.prettyPrint());
    LOG.info(response.getHeaders().toString());

  }

  @And("^Redirect location is correct$")
  public void verifyRediretLocationIsCorrect() {
    LOG.info(response.getHeader("Location"));
    switch (code) {
      case "vuse_de_de_de":
        assertThat(response.getHeader("Location")).endsWith("register/account/success/");
        break;
      case "glo_de_de_de":
      case "glo_pl_pl_pl":
        assertThat(response.getHeader("Location")).endsWith("customer/account/index/");
        break;
      default:
        assertThat(response.getHeader("Location")).endsWith("customer/account/");
    }
  }

  @And("^Confirm the created user with (.*) if needed$")
  public void confirmCreatedUser(String code) {
//      switch (WebDriverHelper.BROWSER.toLowerCase()) {
//        case ("browserstackdesktop"):
    if (WebDriverHelper.BROWSER.toLowerCase().equals("browserstackdesktop") || !UrlBuilder.ENVIRONMENT.contains("uat1")) {
      switch (code) {
        case "vuse_uk_en_gb":
        case "vype_uk_en_gb":
        case "vuse_de_de_de":
        case "glo_de_de_de":
        case "glo_pl_pl_pl":
        case "epok_de_de_de":
        case "velo_eu_de_de":
          LOG.info("email substring " + customerEmail.substring(0, customerEmail.indexOf("@")));

          //Redirect to Mailinator
          WebDriverHelper.startDriver(getSiteMode());
          WebDriverHelper.getWebDriver().get("https://www.mailinator.com/v4/public/inboxes.jsp?to=" + customerEmail
                  .substring(0, customerEmail.indexOf("@")) + "#/#inboxpane");
          RegistrationPage registrationPage = new RegistrationPage();
          registrationPage.verifyEmailAndReturn(customerEmail, "confirm");
          WebDriverHelper.getWebDriver().close();
          break;
      }
    }
    else{
          RestAssured.baseURI = UrlBuilder.getHttpRequestPathURI(site);
          switch (code) {
            case "vuse_uk_en_gb":
            case "vype_uk_en_gb":
            case "vuse_de_de_de":
            case "glo_de_de_de":
            case "glo_pl_pl_pl":
            case "epok_de_de_de":
            case "velo_eu_de_de":
              response = customerAccountHttpService.getUserConfirm(userList);
              assertThat(response.getStatusCode()).isEqualTo(200);
              break;
          }
      }
  }

  @And("^Confirm the user is created with (.*) in DB$")
  public void confirmUserIsCreatedInDB(String code) {
    if (WebDriverHelper.BROWSER.toLowerCase().equals("browserstackdesktop") || !UrlBuilder.ENVIRONMENT.contains("uat1"))
    {return;}
      //    switch (WebDriverHelper.BROWSER.toLowerCase()){
//      case ("browserstackdesktop"):
//        break;
//      default:
    else{
        userList=customerAccountHttpService.getConfirmUserInfo(customerEmail, code);
        assertTrue(userList.size()!=0);
    }
  }

  @When("^create the test account (.*) for (.*) and (.*)$")
  public void createTheAccountWithViaApi(String account, String site,String code) throws Throwable {
    customerEmail=account;
    this.site=site;
    this.code=code;
    UrlBuilder.setStoreCode(code);
    UrlBuilder.setRegionCode("region_"+site.substring(0,2));
    UrlBuilder.setEnvironment(site.substring(0,2)+UrlBuilder.ENVIRONMENT.substring(2,UrlBuilder.ENVIRONMENT.length()));
    RestAssured.baseURI = UrlBuilder.getBasePathURI().toString();
    CustomerBuilder customerBuilder=new CustomerBuilder();
    customerBuilder.setEmail(account);
    customerBuilder.setWebsiteId(code);
    customerBuilder.setStoreId(code);
    customerBuilder=customerBuilder.newCustomer();
    response = customerAccountService.createCustomerAccount(customerBuilder);
    response.prettyPrint();
    //assertThat(response.getStatusCode()).isEqualTo(200);
  }

  @When("^create token for the test account (.*) for (.*) and (.*)$")
  public void createTheAccountTokenViaApi(String account, String site,String code) throws Throwable {
    customerEmail=account;
    this.site=site;
    this.code=code;
    UrlBuilder.setStoreCode(code);
    UrlBuilder.setRegionCode("region_"+site.substring(0,2));
    UrlBuilder.setEnvironment(site.substring(0,2)+UrlBuilder.ENVIRONMENT.substring(2,UrlBuilder.ENVIRONMENT.length()));
    RestAssured.baseURI = UrlBuilder.getBasePathURI().toString();
    response = customerAccountService.getCustomerDetailsByME(account, "Pa55word");
    LOG.info(response.prettyPrint());
    actualCustomer = ApiHelper.convertResponseToPojo(response, Customer.class);
    customerId = String.valueOf(actualCustomer.getId());
  }

  @When("^update customer details by ME$")
  public void updateCustomerDetailsByME() {
    UpdateCustomer updateCustomer = new UpdateCustomer();
    customer.setFirstname(customer.getFirstname() + "_Updated");
    customer.setLastname("Sekhar");
    customer.setDob("1985-10-23");
    updateCustomer.setCustomer(customer);
    CustomerAccountSteps.response = YourChoiceService.updateCustomerAccount(updateCustomer,customer.getEmail(),UrlBuilder.getMessage("loginValidPassword.key"));
  }
}
