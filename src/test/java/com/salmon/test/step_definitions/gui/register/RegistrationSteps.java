package com.salmon.test.step_definitions.gui.register;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.CodiceFiscaleGenerator;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.models.api.customer.Customer;
import com.salmon.test.models.api.customer.CustomerBuilder;
import com.salmon.test.page_objects.gui.AccountDashboardPage;
import com.salmon.test.page_objects.gui.BATHelper;
import com.salmon.test.page_objects.gui.PaymentPage;
import com.salmon.test.page_objects.gui.RegistrationPage;
import com.salmon.test.page_objects.gui.*;
import com.salmon.test.page_objects.gui.admin.CustomerPage;
import com.salmon.test.page_objects.gui.admin.StorePage;
import com.salmon.test.page_objects.gui.*;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.constants.Locale;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.gui.cucumberContext.TestContext;
import com.salmon.test.page_objects.gui.models.RegistrationPageModel;
import com.salmon.test.services.CustomerAccountService;
import com.salmon.test.step_definitions.gui.ForgotPasswordPageSteps;
import com.salmon.test.step_definitions.gui.LoginPageSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import lombok.Getter;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.salmon.test.enums.PermittedCharacters.*;
import static com.salmon.test.framework.helpers.ApiHelper.convertResponseToPojo;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.randomDateOfBirth;
import static com.salmon.test.page_objects.gui.constants.Context.*;
import static com.salmon.test.page_objects.gui.constants.Locale.LYFTSE;
import static java.util.stream.Collectors.toCollection;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Getter
public class RegistrationSteps {

    private static final Logger LOG = LoggerFactory.getLogger(RegistrationSteps.class);
    private static final String MISMATCH_PASSWORD = "mismatchPassword";

    private ScenarioContext scenarioContext;
    private RegistrationPage registrationPage;
    private AccountDashboardPage accountDashboardPage;
    private BATHelper batHelper;
    private SoftAssertions softAssertions;
    private CustomerAccountService customerAccountService;
    private LoginPageSteps loginPageSteps;
    private PaymentPage paymentPage;
    private LogininPage logininPage;
    private HomePage homePage;
    private CustomerPage customerPage;
    private PLP plp;
    private StorePage storePage;
    private PDP pdp;
    private ForgotPasswordPage forgotPasswordPage;
    private ForgotPasswordPageSteps forgotPasswordPageSteps;

    private String firstNameData = random(6, ALPHABETS);
    private String lastNameData = random(6, ALPHABETS);
    private String DOBData = "08/05/2019";

    private String companyName = random(20, ALPHABETS);
    private String phoneNumber = "01" + random(10, NUMERIC);
    private String PHONENUMBER_CO = "6" + random(9, NUMERIC);
    private String streetAddressData = "12 The Cloisters";
    private String IntMGMPromoCode = "MGM-" + random(5, NUMERIC);
    private String phoneNumberCO = random(11, NUMERIC);
    private String phoneNumberIT = "1" + random(9, NUMERIC);
    private String phoneNumberMX = "1" + random(9, NUMERIC);

    private String loginIdData = random(6, ALPHANUMERIC);
    private String passwordData = random(6, ALPHANUMERIC);
    private String titleData = "Dr.";

    private String postCodeData = "UB10 9DW";
    private String address1Data = random(6, ALPHABETS);
    private String townOrCityData = random(6, ALPHABETS);
    private String emailAddressData = RandomGenerator.randomEmailAddress(6);
    private String password = "Pa55word";
    private String RandomPostCodeData = random(4, NUMERIC) + " " + random(3, NUMERIC);

    private String user_email_address = System.getProperty("UserEmailAddress.key");
    private String user_first_name = System.getProperty("UserFirstName.key");
    private String user_last_name = System.getProperty("UserLastName.key");
    private String user_dob = System.getProperty("UserDOB.key");
    private String user_address_post_code = System.getProperty("UserPostCode.key");
    private List<String> actualDataProtectionUrlLink;

    public RegistrationSteps(TestContext testContext, RegistrationPage registrationPage, AccountDashboardPage accountDashboardPage, BATHelper batHelper, SoftAssertions softAssertions, CustomerAccountService customerAccountService, LoginPageSteps loginPageSteps, ForgotPasswordPage forgotPasswordPage, HomePage homePage, LogininPage logininPage, PaymentPage paymentPage, PLP plp, PDP pdp, ForgotPasswordPageSteps forgotPasswordPageSteps) {
        scenarioContext = testContext.getScenarioContext();
        this.registrationPage = registrationPage;
        this.accountDashboardPage = accountDashboardPage;
        this.batHelper = batHelper;
        this.softAssertions = softAssertions;
        this.customerAccountService = customerAccountService;
        this.loginPageSteps = loginPageSteps;
        this.logininPage = logininPage;
        this.homePage = homePage;
        this.customerPage = customerPage;
        this.plp = plp;
        this.storePage = storePage;
        this.pdp = pdp;
        this.paymentPage = paymentPage;
        this.forgotPasswordPage = forgotPasswordPage;
        this.forgotPasswordPageSteps = forgotPasswordPageSteps;
    }

    @And("^assert page title is '(.*)'$")
    public void assertPgeTaitleIsCreateNewCustomerAccount(String actualPageTitle) throws Throwable {
        if (UrlBuilder.getLocale().equals("vuseuk") || UrlBuilder.getLocale().equals("ie") || UrlBuilder.getLocale().equals("vuseza")) {
            registrationPage.waitForPage();
            if (registrationPage.getWebDriver().getTitle().equals("Create An Account")) {
                Assert.assertTrue(UrlBuilder.getMessage("newCustomerText.key").equalsIgnoreCase(registrationPage.getWebDriver().getTitle()));
            } else if (registrationPage.getWebDriver().getTitle().equals("Welcome to our secure checkout")) {
                Assert.assertTrue(UrlBuilder.getMessage("registrationPageTitle.key").equalsIgnoreCase(registrationPage.getWebDriver().getTitle()));
            }
        } else if (!UrlBuilder.getLocale().equals("mx") && !UrlBuilder.getLocale().equals("vuseco")) {
            registrationPage.waitForPage();
            String expectedPageHeading = UrlBuilder.getMessage(actualPageTitle);//"Create New Customer Account";
            registrationPage.assertTrueWithCustomError(expectedPageHeading, registrationPage.getCurrentPageTitle());
        }
    }

    @And("^assert text of '(.*)' is displayed on my registration page$")
    public void assertTextOfCustomerLoginIsDisplayedOnMyRegistrationPage(String expectedText) {
        Assert.assertTrue(registrationPage.getWebDriver().getPageSource().contains(expectedText));
    }

    @And("^populate Personal Information - first and last name with randomly generated data$")
    public void populatePersonalInformationFirstAndLastNameWithRandomlyGeneratedData() {
        registrationPage.waitForExpectedElement(registrationPage.firstNameInput, 10);
        registrationPage.enterDataAndWait(registrationPage.firstNameInput, firstNameData);
        registrationPage.enterDataAndWait(registrationPage.surNameInput, lastNameData);
    }

    @And("^populate personal informations section fields$")
    public void populatePersonalInformationsFields() {
        LocalDate dob = randomDateOfBirth();
        Boolean isMale = new Random().nextBoolean();
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String sex = (isMale) ? "1" : "2";
        String locale = UrlBuilder.getLocale();

        if (locale.equals("de") || locale.equals("vusede") || locale.equals("velode")) {
            formatters = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        } else if (locale.equals("nl")) {
            formatters = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        }

        String formattedDate = dob.format(formatters);

        registrationPage.waitForExpectedElement(registrationPage.firstNameInput, 10);

        if (UrlBuilder.getLocale().equals("mx") || UrlBuilder.getLocale().equals("vuseco") || UrlBuilder.getLocale().equals("vusemx")) {
            registrationPage.enterText(registrationPage.firstNameInput_mx, firstNameData);
            registrationPage.enterText(registrationPage.surNameInput_mx, lastNameData);
        } else {
            registrationPage.enterText(registrationPage.firstNameInput, firstNameData);
            registrationPage.enterText(registrationPage.surNameInput, lastNameData);
        }

        registrationPage.enterText(registrationPage.DOBInput, formattedDate);
        registrationPage.selectOptionFromDropDownByValue(sex, registrationPage.gender);

        if (registrationPage.isElementPresentByby(registrationPage.txtFiscalCode)) {
            CodiceFiscaleGenerator fcg = new CodiceFiscaleGenerator(dob, isMale);
            registrationPage.enterText(registrationPage.txtFiscalCode, fcg.getFiscalCode());
        }

        if (registrationPage.isElementPresentByby(registrationPage.MGM_PROMO_CODE))
            registrationPage.enterText(registrationPage.MGM_PROMO_CODE, IntMGMPromoCode);

        if (registrationPage.isElementPresentByby(registrationPage.txtBirthCity))
            registrationPage.enterText(
                    registrationPage.txtBirthCity,
                    UrlBuilder.getMessage("birthCity.key")
            );

        if (registrationPage.isElementPresentByby(registrationPage.citizenCardNumber))
            registrationPage.enterText(registrationPage.citizenCardNumber, "12345");
    }

    @And("^create a new account with manual address input$")
    public void createNewAccountWithManualAddress() {
        populatePersonalInformationsFields();
        populateIdentificationFields();
        populateSigninFieldsWithoutCheckingTsandCs();
        registrationPage.checkTsCs();
        try{
        registrationPage.clickByElementByQueryJSExecutor(registrationPage.CREATEACCOUNT_BUTTON);}
        catch (Exception e)
        {
            registrationPage.waitForExpectedElement(registrationPage.M_CREATEACCOUNT_BUTTON_FR);
            registrationPage.clickByElementByQueryJSExecutor(registrationPage.M_CREATEACCOUNT_BUTTON_FR);}

        if (UrlBuilder.getLocale().contains("vuseuk")) {
            registrationPage.verifyEmailAndReturn(System.getProperty("UserEmailID.key"), "confirm");
        }
        registrationPage.waitForAjaxElementNotToBePresent(registrationPage.getWebDriver(), 10);
    }

    @And("^populate address informations section fields$")
    public void populateIdentificationFields() {
        String phone = phoneNumber;
        String streetAddress = streetAddressData;
        String city = townOrCityData;
        String postCode = postCodeData;
        switch (UrlBuilder.getLocale()) {
            case "vypeit":
            case "vuseit":
                phone = phoneNumberIT;
                city = UrlBuilder.getMessage("AddressCity.key");
                postCode = "35040";
                break;
            case "uk":
            case "vuseuk":
                registrationPage.waitForExpectedElement(registrationPage.EXPAND_MANUAL_ADDRESS_UK, 5).click();
                phone = phoneNumber;
                break;
            case "mx":
            case "vusemx":
                phone = phoneNumberMX;
                break;
            case "vuseco":
                phone = PHONENUMBER_CO;
                break;
            case "vuseza":
            case "vusefr":
                registrationPage.clickManualAddressEntryButton();
                break;
        }
        if (UrlBuilder.getLocale().equalsIgnoreCase("vusefr") || UrlBuilder.getLocale().equalsIgnoreCase("vuseuk"))
            registrationPage.enterText(registrationPage.phoneNumber, UrlBuilder.getMessage("validPhoneNumber.key"));
        else if (registrationPage.isElementPresentByby(RegistrationPage.phoneNumber) && !UrlBuilder.getLocale().equalsIgnoreCase("vusefr"))
            registrationPage.enterDataAndWait(RegistrationPage.phoneNumber, phone);
        if (registrationPage.isElementPresent(registrationPage.MANUAL_DELIVERY_ADDRESS_ENTRY_LINK)) {
            registrationPage.clickManualAddressEntryButton();
        }
        if (!UrlBuilder.getLocale().equalsIgnoreCase("vuseit")) {
            registrationPage.enterText(registrationPage.streetAddressLine1, streetAddress);
        }
        if (registrationPage.isElementPresentByby(registrationPage.txtOutDoorNumber))
            registrationPage.enterText(
                    registrationPage.txtOutDoorNumber,
                    UrlBuilder.getMessage("outdoorNumber.key")
            );

        if (registrationPage.isElementPresentByby(registrationPage.province)) {
            List<WebElement> states = registrationPage.getWebDriver().findElements(registrationPage.provinceOptions);
            if (states.size() > 0)
                registrationPage.selectOptionFromDropDownByValue(
                        states.get(ThreadLocalRandom.current().nextInt(states.size()) % states.size())
                                .getAttribute("value"),
                        registrationPage.province

                );
        }

        if (registrationPage.isElementPresentByby(registrationPage.citySelector)) {
            switch (UrlBuilder.getLocale()) {
                case "vuseco":
                    registrationPage.selectValueFromDropDownByIndex(1, registrationPage.citySelector);
                    break;
                default:
                    registrationPage.selectValueFromDropDownByIndex(3, registrationPage.citySelector);
            }
        } else {
            registrationPage.enterText(registrationPage.city, city);
        }
        if (!UrlBuilder.getLocale().contains("co")) {
            registrationPage.enterText(registrationPage.postCode, postCode);
        }
    }

    @And("^populate DOB field with '(.*)'$")
    public void populateDOBFieldWith(String DOBToEnter) {
        try {
            registrationPage.waitClearAndEnterText(registrationPage.DOBInput, UrlBuilder.getMessage(DOBToEnter));
        } catch (Exception e) {
            LOG.info("Failed to enter data in 'DOB' field due to exception: " + e.getMessage());
        }
    }

    @And("^populate gender field with '(.*)'$")
    public void populateGenderFieldWithMale(String genderToSelect) {
        try {
            registrationPage.selectValueFromDropDownByby(UrlBuilder.getMessage(genderToSelect), registrationPage.gender);
        } catch (Exception e) {
            LOG.info("Failed to select 'Gender' from drop-down due to exception: " + e.getMessage());
        }
    }

    @And("^populate fiscal code with '(.*)'$")
    public void populateFiscalCode(String fiscalCode) {
        registrationPage.waitClearAndEnterText(registrationPage.txtFiscalCode, UrlBuilder.getMessage(fiscalCode));
    }

    @And("^populate address fields$")
    public void populateAddressFields() {
        registrationPage.populateAddressFields();
    }

    @And("^populate signin fields$")
    public void populateSigninFields() {
        String emailAddress = emailAddressData;
        scenarioContext.setContext(EMAIL_ID, emailAddress);
        registrationPage.enterDataAndWait(registrationPage.email, emailAddress);
        registrationPage.enterDataAndWait(registrationPage.password, password);
        registrationPage.enterDataAndWait(registrationPage.passwordConfirmation, password);
        registrationPage.checkTsCs();
        if (Locale.valueOf(UrlBuilder.getLocale().toUpperCase()).equals(LYFTSE)) {
            registrationPage.checkPrivacyPolicy();
        }
    }

    @And("^populate signin fields with username of '(.*)'$")
    public void populateSigninFieldsWithUserName(String username) {
        String emailAddress = UrlBuilder.getMessage(username);
        LOG.info("Email  : " + emailAddress);
        LOG.info("Password : " + password);
        registrationPage.enterText(registrationPage.email, emailAddress);
        registrationPage.enterText(registrationPage.password, password);
        registrationPage.enterText(registrationPage.passwordConfirmation, password);
        registrationPage.checkTsCs();
    }

    @And("^populate signin fields without selecting Ts and Cs$")
    public void populateSigninFieldsWithoutCheckingTsandCs() {
        String emailAddress = emailAddressData;
        LOG.info("Email  : " + emailAddress);
        LOG.info("Password : " + password);
        System.setProperty("UserEmailID.key", emailAddress);

        registrationPage.enterText(registrationPage.email, emailAddress);
        registrationPage.enterText(registrationPage.password, password);
        registrationPage.enterText(registrationPage.passwordConfirmation, password);
    }

    @Then("^user select Ts and C's$")
    public void selectTsAndCs() {
        registrationPage.clickByElementByQueryJSExecutor(By.cssSelector("#register-input-custom"));
    }

    @Then("^(?:I|user) selects the create an account button$")
    public void userSelectsTheCreateAnAccountButton() {
        registrationPage.clickByElementByQueryJSExecutor(registrationPage.CREATEACCOUNT_BUTTON);
        registrationPage.waitForExpectedElement(registrationPage.messageContainer, 10);
        if (UrlBuilder.getLocale().contains("de") || UrlBuilder.getLocale().contains("pl") || UrlBuilder.getLocale().contains("uk")) {
            registrationPage.verifyEmailAndReturnToMyAccountPage();
        }
    }

    @Then("^user selects the create an account button without Email Verification script$")
    public void userSelectsTheCreateAnAccountButtonWithOutEmailVerificationScript() {
        registrationPage.clickByElementByQueryJSExecutor(registrationPage.CREATEACCOUNT_BUTTON);
        registrationPage.waitForExpectedElement(registrationPage.messageContainer, 10);

    }

    @And("^tick signup for newsLetter tick box$")
    public void ticketSignupForNewsLetterTickBox() {
        registrationPage.signUpForNewsLetterTickBoxSelection();
    }

    @And("^add BankID for user-Lyft$")
    public void addBankIdForUser() {
        registrationPage.addBankId();
    }

    @And("^populate phone number field with '(.*)'$")
    public void populatePhoneNumberFieldWith(String textToEnter) {
        registrationPage.enterText(RegistrationPage.phoneNumber, UrlBuilder.getMessage(textToEnter));
        registrationPage.waitForExpectedElement(RegistrationPage.phoneNumber).submit();
    }

    @And("^and assert error validation message of '(.*)'$")
    public void andAssertErrorValidationMessageOfTelephoneError(String expectedMobileValidationMessage) {
        String telephoneErrorValidationMessage = registrationPage.waitForExpectedElement(By.cssSelector("#telephone-error")).getText();
        LOG.info("Error message provided : " + telephoneErrorValidationMessage);
        registrationPage.assertTrueWithCustomError(expectedMobileValidationMessage, telephoneErrorValidationMessage);
    }

    @And("^enter postcode and assert postcode lookup working$")
    public void enterPostcodeAndAssertPostcodeLookupWorking() throws Throwable {
        Thread.sleep(6000);
        registrationPage.enterText(registrationPage.postCode, postCodeData);
        registrationPage.waitForExpectedElement(registrationPage.postCode).sendKeys(Keys.DOWN);
        registrationPage.waitForExpectedElement(registrationPage.postCode).sendKeys(Keys.RETURN);
        registrationPage.waitForExpectedElement(registrationPage.postCode).sendKeys(Keys.RETURN);
        Thread.sleep(3000);
        String valueInCityField = registrationPage.getWebDriver().findElement(By.cssSelector("#city")).getAttribute("value");
        registrationPage.assertTrueWithCustomError("Uxbridge", valueInCityField);
        LOG.info("Assert the text displayed as expected - results should be available to choose");
    }

    @Then("^users clicks on the Continue Shopping button$")
    public void userClicksOnContinueShoppingButton() {
        registrationPage.clickByElementByQueryJSExecutor(registrationPage.btnContinueShopping);
    }

    @Then("^assert home page is displayed$")
    public void assertHomePageIsDisplayed() {
        registrationPage.assertHomePageIsDisplayed();
    }

    @And("^navigate back to Registration Confirmation page$")
    public void navigateBackToRegistrationConfirmationPage() {
        registrationPage.getWebDriver().navigate().back();
        assertTrue(registrationPage.getWebDriver().getCurrentUrl().contains("uk/registration-confirmation"));
    }

    @Then("^users clicks on the Your Account button$")
    public void userClicksOnYourAccountButton() {
        registrationPage.clickByElementByQueryJSExecutor(registrationPage.btnYourAccount);
    }

    @Then("^assert My Account page is displayed$")
    public void assertMyAccountPageIsDisplayed() {
        Assert.assertTrue(registrationPage.waitForExpectedElement(registrationPage.btnEditAccountDetails, 10).isDisplayed());
    }

    @And("^populate signin fields with specific email$")
    public void populateSigninFieldsWithSpecificEmail() {
        registrationPage.enterDataAndWait(registrationPage.email, UrlBuilder.getMessage("loginValidEmail.key"));
        registrationPage.enterDataAndWait(registrationPage.password, UrlBuilder.getMessage("loginValidPassword.key"));
        registrationPage.enterDataAndWait(registrationPage.passwordConfirmation, UrlBuilder.getMessage("loginValidPassword.key"));
        registrationPage.checkTsCs();
    }

    @And("^populate address fields with valid details$")
    public void populateAddressFieldsWithValidDetails() {
        switch (UrlBuilder.getLocale()) {
            case "vypeit":
            case "vuseit":
                registrationPage.enterDataAndWait(RegistrationPage.phoneNumber, phoneNumberIT);
                registrationPage.enterDataAndWait(registrationPage.streetAddressLine1, UrlBuilder.getMessage("streetAddress.key"));
                registrationPage.enterDataAndWait(registrationPage.city, UrlBuilder.getMessage("AddressCity.key"));
                registrationPage.selectValueFromDropDownByby(UrlBuilder.getMessage("stateRegion.key"), registrationPage.drpITState);
                registrationPage.enterDataAndWait(registrationPage.postCode, "35040");
                registrationPage.enterDataAndWait(registrationPage.txtFiscalCode, UrlBuilder.getMessage("FiscalCode.key"));
                registrationPage.enterDataAndWait(registrationPage.txtBirthCity, UrlBuilder.getMessage("birthCity.key"));
                break;
            case "nl":
                registrationPage.enterText(registrationPage.streetAddressLine1, UrlBuilder.getMessage("streetAddress.key"));
                registrationPage.enterText(registrationPage.city, UrlBuilder.getMessage("city.key"));
                registrationPage.enterText(registrationPage.postCode, UrlBuilder.getMessage("postalCode.key"));
                break;
            case "fr":
            case "vusefr":
                registrationPage.enterText(RegistrationPage.phoneNumber, phoneNumber);
                registrationPage.enterText(registrationPage.streetAddressLine1, UrlBuilder.getMessage("streetAddress.key"));
                registrationPage.enterText(registrationPage.city, UrlBuilder.getMessage("city.key"));
                registrationPage.enterText(registrationPage.postCode, UrlBuilder.getMessage("postalCode.key"));
                break;
            case "glode":
                registrationPage.enterText(RegistrationPage.phoneNumber, phoneNumber);
                registrationPage.enterText(registrationPage.streetAddressLine1, UrlBuilder.getMessage("streetAddress.key"));
                registrationPage.enterText(registrationPage.city, UrlBuilder.getMessage("city.key"));
                registrationPage.enterText(registrationPage.postCode, UrlBuilder.getMessage("postalCode.key"));
                break;
            case "vuseco":
                registrationPage.enterText(registrationPage.company, companyName);
                registrationPage.enterText(RegistrationPage.phoneNumber, PHONENUMBER_CO);
                registrationPage.enterText(registrationPage.streetAddressLine1, UrlBuilder.getMessage("streetAddress.key"));
                registrationPage.enterText(registrationPage.city, UrlBuilder.getMessage("city.key"));
                registrationPage.enterText(registrationPage.postCode, UrlBuilder.getMessage("postalCode.key"));
                break;
            default:
                registrationPage.enterText(registrationPage.company, companyName);
                registrationPage.enterText(RegistrationPage.phoneNumber, phoneNumber);
                registrationPage.enterText(registrationPage.streetAddressLine1, UrlBuilder.getMessage("streetAddress.key"));
                registrationPage.enterText(registrationPage.city, UrlBuilder.getMessage("city.key"));
                registrationPage.enterText(registrationPage.postCode, UrlBuilder.getMessage("postalCode.key"));
        }
    }

    @And("^populate address and Sign in fields and click on Create Account button$")
    public void populateAddressSignInFieldsAndClickCreateAccount() throws Throwable {
        try {
            switch (UrlBuilder.getLocale()) {
                case "lyftse":
                    populateAddressFieldsForLyftUser();
                    registrationPage.addBankId();
                    populateSigninFields();
                    userSelectsTheCreateAnAccountButton();
                default:
                    populateAddressFields();
                    populateSigninFields();
                    userSelectsTheCreateAnAccountButton();
            }
        } catch (Exception e) {
            LOG.info("Failed to click on Create An Account button due to exception: " + e.getMessage());
        }
    }

    @And("^populate address fields for user-Lyft$")
    public void populateAddressFieldsForLyftUser() {
        registrationPage.enterText(RegistrationPage.phoneNumber, phoneNumber);
        registrationPage.enterText(registrationPage.streetAddressLine1, UrlBuilder.getMessage("streetAddress.key"));
        registrationPage.enterText(registrationPage.city, UrlBuilder.getMessage("cityName.key"));
        registrationPage.enterText(registrationPage.postCode, UrlBuilder.getMessage("postalCode.key"));
    }

    @And("^create a new account from PLP$")
    public void createNewAccount() throws Throwable {
        registrationPage.createNewAccount();
    }

    @Then("^assert user is still on the create account page$")
    public void assertUserIsStillOnTheCreateAccountPage() {
        Assert.assertTrue(registrationPage.getCurrentUrl().contains("customer/account/create/"));
    }

    @Then("^assert error message displayed$")
    public void assertErrorMessageDisplayed() {
        Assert.assertTrue(registrationPage.isElementPresentByby(registrationPage.messageContainer));
    }

    @When("^user attempts to create a new account without (.*)$")
    public void userAttemptsToCreateANewAccountWithOut(String fieldUnderTest) {
        registrationPage.enterRegistrationDetailsAndCreateAccountWithout(fieldUnderTest);
    }

    @When("^user create a new account without filling any details$")
    public void createANewAccountWithOutFillAnyDetails() {
        registrationPage.userSelectsTheCreateAnAccountButton();
    }

    @When("^user enters phone number in the phone number field as (.*)$")
    public void userEntersPhoneNumberInThePhoneNumberFieldAs(String phoneNumber) {
        registrationPage.enterText(RegistrationPage.phoneNumber, phoneNumber);
    }

    @Then("^assert phone number is pre-filled with country code (.*)$")
    public void assertPhoneNumberIsPreFilledWith(String countryCode) {
        WebElement phoneNumber = registrationPage.waitForExpectedElement(RegistrationPage.phoneNumber,10);

        if (!UrlBuilder.getLocale().contains("mx")) {
            assertEquals(phoneNumber.getAttribute("placeholder"), countryCode);
        }
        if (UrlBuilder.getLocale().contains("mx") || UrlBuilder.getLocale().contains("glode")) {
            assertEquals(phoneNumber.getAttribute("placeholder"), countryCode);
        } else if(!UrlBuilder.getLocale().contains("vuseza")){
            String phoneNumberWithCountryCode = phoneNumber.getAttribute("value");
            assertEquals(phoneNumberWithCountryCode.substring(0, 3), countryCode);
        }
    }

    @Then("^the user should see the (.*) symbol for the mandatory fields$")
    public void theUserShouldSeeTheSymbolForTheMandatoryFields(String expected, List<String> fields) {
        registrationPage.waitForPage();
        fields.forEach(s -> assertEquals(registrationPage.getCssStyleContent(s), expected,
                s + " field in registration page should be marked as mandatory"));
    }

    @When("^the user attempts to create an account with mismatch password and confirm password$")
    public void theUserAttemptsToCreateAnAccountWithMismatchPasswordAndConfirmPassword() {
        RegistrationPageModel registrationPageModel = RegistrationPageModel.builder().build().withDefaultValues();
        registrationPageModel.getSignInInfo().setConfirmPassword(MISMATCH_PASSWORD);
        registrationPage.createUserWithOutMailinatorCheck(registrationPageModel);
    }

    @When("^(?:Glo|Velo|Lyft|Vype) attempts to create an account (?:with|without) (.*)$")
    public void gloAttemptsToCreateAndAccountWith(String fieldUnderTest) {
        if (fieldUnderTest.contains("CodiceFiscal")) {
            String input = fieldUnderTest.substring(fieldUnderTest.indexOf("-") + 1);
            registrationPage.enterRegistrationDetailsWithCodiceFiscal(input);
        }
    }

    @And("^create a new account with random email$")
    public void createANewAccountWithRandomEmail() {
        String emailAddress = RandomGenerator.randomEmailAddress(6);
        scenarioContext.setContext(EMAIL_ID, emailAddress);
        batHelper.createNewAccount(emailAddress);
    }

    @And("^user attempts to create an account with age as in table then assert expected is displayed$")
    public void userAttemptsToCreateAnAccountWithAgeAsInTableThenAssertExpectedIsDisplayed(List<Map<String, String>> mapList) {
        mapList.forEach(map -> {
            registrationPage.enterRegistrationDetailsWithAge(map.get("age"));
            String expectedMessage = UrlBuilder.getMessage(map.get("expected"));
            softAssertions.assertThat(registrationPage.getWebDriver().getPageSource().contains(expectedMessage))
                    .as("Expected Messageis not displayed in the page: " + expectedMessage)
                    .isTrue();
        });
        softAssertions.assertAll();
    }

    @And("^Admin approves the newly created account$")
    public void adminApprovesTheNewlyCreatedAccount() throws Throwable {
        batHelper.approveNewUserInAdminAndReturn(registrationPage.emailAddressData);
    }

    @And("^create a new account via api$")
    public void createANewAccountWithViaApi() throws Throwable {
        CustomerBuilder customerBuilder = customerAccountService.createNewCustomer();
        Response createAccountResponse = customerAccountService.createCustomerAccount(customerBuilder);
        LOG.info(createAccountResponse.prettyPrint());
        assertThat(createAccountResponse.getStatusCode()).isEqualTo(200);
        Customer customer = convertResponseToPojo(createAccountResponse, Customer.class);
        scenarioContext.setContext(CUSTOMER, customer);
        scenarioContext.setContext(EMAIL_ID, customer.getEmail());
        scenarioContext.setContext(EMAIL_ID_CREATED_VIA_API_CREATE_ACCOUNT, customer.getEmail());
        scenarioContext.setContext(PASSWORD_CREATED_VIA_API_CREATE_ACCOUNT, customerBuilder.getPassword());
        scenarioContext.setContext(PASSWORD, customerBuilder.getPassword());
        scenarioContext.setContext(CURRENT_ADDRESS, "");
        batHelper.approveUser(customer.getEmail(), customerBuilder.getPassword());
        if (UrlBuilder.getLocale().contains("de") || UrlBuilder.getLocale().contains("pl") || UrlBuilder.getLocale().contains("vuseit") || UrlBuilder.getLocale().contains("uk") || UrlBuilder.getLocale().contains("bugveloza")) {
            if (UrlBuilder.getLocale().contains("vuseit") || UrlBuilder.getLocale().contains("vusefr")) {
                registrationPage.verifyEmailAndReturn(customer.getEmail(), "createPassword");
            } else {
                registrationPage.verifyEmailAndReturn(customer.getEmail(), "confirm");
            }
        }
        if (UrlBuilder.getLocale().equals("velobe")) {
            customerPage.loginMagentoAdminAndSetAgeVerified(true, customer.getEmail());
        }
    }

    @And("^create a new account via api and log in with the account$")
    public void createANewAccountWithViaApiAndLogin() throws Throwable {
        createANewAccountWithViaApi();
        Customer customer = (Customer) scenarioContext.getContext(CUSTOMER);
        if(!"vuseuk".equalsIgnoreCase(UrlBuilder.getLocale()))
            loginPageSteps.userSignsIn(customer.getEmail(), UrlBuilder.getMessage("loginValidPassword.key"));
    }

    @And("^delete the account via api$")
    public void deleteTheAccountViaApi() {
        Customer context = (Customer) scenarioContext.getContext(CUSTOMER);
        Response response = customerAccountService.deleteCustomerAccount(String.valueOf(context.getId()));
        assertThat(response.getStatusCode()).isEqualTo(200);
    }

    @And("^tick signup for consentMobile tick box$")
    public void tickSignupForConsentMobileTickBox() {
        registrationPage.signUpForConsentMobileTickBoxSelection();
    }

    @Then("^assert phone number is field is not displayed$")
    public void assertPhoneNumberIsFieldIsNotDisplayed() {
        assertThat(registrationPage.isElementDisplayedBySeconds(RegistrationPage.phoneNumber, 2)).isFalse();
    }

    @And("^create a new account with existing user details$")
    public void createANewAccountWithExistingUserDetails() throws Throwable {
        registrationPage.createANewAccountWithSpecifiedUserDetails(UrlBuilder.getMessage("loginValidEmail.key"), user_first_name, user_last_name, user_dob, user_address_post_code);
    }

    @And("^create a new account with deleted user details$")
    public void createANewAccountWithDeletedUserDetails() throws Throwable {
        user_email_address = System.getProperty("UserEmailAddress.key");
        user_first_name = System.getProperty("UserFirstName.key");
        user_last_name = System.getProperty("UserLastName.key");
        user_dob = System.getProperty("UserDOB.key");
        user_address_post_code = System.getProperty("UserPostCode.key");
        registrationPage.createANewAccountWithSpecifiedUserDetails(user_email_address, user_first_name, user_last_name, user_dob, user_address_post_code);
    }

    @And("^create a new loyality account with deleted user details$")
    public void createANewLoyalityAccountWithDeletedUserDetails() throws Throwable {
        user_email_address = System.getProperty("UserEmailAddress.key");
        user_first_name = System.getProperty("UserFirstName.key");
        user_last_name = System.getProperty("UserLastName.key");
        user_dob = System.getProperty("UserDOB.key");
        user_address_post_code = System.getProperty("UserPostCode.key");
        registrationPage.createANewLoyalityAccountWithSpecifiedUserDetails(user_email_address, user_first_name, user_last_name, user_dob, user_address_post_code);
    }

    @And("^create a new account with Non-Duplicate DOB and email address$")
    public void createANewAccountWithNonDuplicateDOBAndEmailAddress() throws Throwable {
        String DOB = RandomGenerator.fetchRandomDOBinSpecifiedFormat("dd.MM.yyyy");
        registrationPage.createANewAccountWithSpecifiedUserDetails(emailAddressData, System.getProperty("UserFirstName.key"), user_last_name, DOB, user_address_post_code);
    }

    @And("^create a new account with Non-Duplicate Postal Code and Email Address$")
    public void createANewAccountWithNonDuplicatePostCodeAndEmailAddress() throws Throwable {
        registrationPage.createANewAccountWithSpecifiedUserDetails(emailAddressData, System.getProperty("UserFirstName.key"), user_last_name, user_dob, RandomPostCodeData);
    }

    @And("^create a new account with Non-Duplicate First Name and Email Address$")
    public void createANewAccountWithNonDuplicateFirstNameAndEmailAddress() throws Throwable {
        switch (UrlBuilder.getLocale()) {
            case "glode":
                registrationPage.createANewAccountWithSpecifiedUserDetails(emailAddressData, firstNameData, lastNameData, user_dob, user_address_post_code);
                break;
            default:
                registrationPage.createANewAccountWithSpecifiedUserDetails(emailAddressData, firstNameData, user_last_name, user_dob, user_address_post_code);
        }
    }

    @And("^create a new account with Non-Duplicate Last Name and Email Address$")
    public void createANewAccountWithNonDuplicateLastNameAndEmailAddress() throws Throwable {
        registrationPage.createANewAccountWithNonDuplicateLastNameAndEmailAddress();
    }

    @And("^create a new account with existing Address details$")
    public void createANewAccountWithExistingAddressDetails() throws Throwable {
        registrationPage.createANewAccountWithSpecifiedUserDetails(emailAddressData, System.getProperty("UserFirstName.key"), user_last_name, user_dob, user_address_post_code);
    }

    @Then("^assert registration error message displayed$")
    public void assertRegistrationErrorMessageDisplayed() throws Throwable {
        assertTrue(registrationPage.waitForExpectedElement(registrationPage.REGISTRAION_ERROR_MESSAGE, 10).isDisplayed());
    }

    @And("^assert DOB error is presented$")
    public void assertDOBErrorIsPresented() {
        registrationPage.waitForExpectedElement(registrationPage.REGISTRATION_DOB_ERROR_MESSAGE).isDisplayed();
    }

    @When("^input invalid values for fields on registration page$")
    public void inputInvalidValuesForFieldsOnRegistrationPage() {
        switch (UrlBuilder.getLocale()) {
            case "vusedk":
            case "vusede":
                RegistrationPageModel registrationPageModel = RegistrationPageModel.builder().build()
                        .withInvalidValues();
                registrationPage.createUserPopulateInvalidInput(registrationPageModel);
                break;
            default:
                registrationPage.waitClearAndEnterText(registrationPage.email,
                        UrlBuilder.getMessage("loginInvalidEmail.key"));
                registrationPage.waitClearAndEnterText(registrationPage.DOBInput,
                        UrlBuilder.getMessage("UnderAgeDob.key"));
                registrationPage.waitForExpectedElement(registrationPage.DOBInput).sendKeys(Keys.TAB);
                registrationPage.waitClearAndEnterText(RegistrationPage.phoneNumber,
                        UrlBuilder.getMessage("InvalidPhoneNumber.key"));
                registrationPage.waitClearAndEnterText(registrationPage.password,
                        UrlBuilder.getMessage("loginInvalidPassword.key"));
                registrationPage.waitClearAndEnterText(registrationPage.passwordConfirmation,
                        UrlBuilder.getMessage("loginInvalidPassword.key") + "0");
        }
    }

    @And("^expand address fields$")
    public void expandAddressFields() {
        switch (UrlBuilder.getLocale()) {
            case "vusede":
                if (registrationPage.doesURLContain("customer/address/"))
                    registrationPage.clickByElementByQueryJSExecutor(registrationPage.MANUAL_DELIVERY_ADDRESS_ENTRY_LINK);
                else{
                if(registrationPage.getWebDriver().findElements(registrationPage.ADD_ADDRESS_ACCORDIAN).size()>0){
                    registrationPage.enterDataAndWait(registrationPage.firstNameInput,firstNameData);
                    registrationPage.enterDataAndWait(registrationPage.surNameInput,lastNameData);
                    registrationPage.enterDataAndWait(registrationPage.DOBInput, UrlBuilder.getMessage("ValidDOB.key"));
                    registrationPage.waitForExpectedElement(registrationPage.DOBInput, 10).sendKeys(Keys.TAB);
                    registrationPage.clickFirstElementByQueryJSExecutor(registrationPage.ACCORDIAN_CONTINUE_BUTTON);}
                }
                if(registrationPage.isElementPresent(registrationPage.MANUAL_DELIVERY_ADDRESS_ENTRY_LINK))
                    registrationPage.waitAndClickByElementByJSExecutor(registrationPage.MANUAL_DELIVERY_ADDRESS_ENTRY_LINK,5);
                break;
            case "velode":
                registrationPage.clickByElementByQueryJSExecutor(registrationPage.EXPAND_ADDRESS_LINK_VELO);
                ;
                break;
            default:
                registrationPage.waitForExpectedElement(registrationPage.expandManualAddressFields).click();
        }
    }

    @And("^verify subscription confirmation mail for new user and approve$")
    public void assertConfirmationRequestEmailOnNewsletterSubscriptionForNewUser() {
        String EMAIL_ADDRESS = (String) scenarioContext.getContext(Context.NEW_USER_EMAIL_ID);
        registrationPage.verifyEmailAndReturn(EMAIL_ADDRESS, "newsletter");
    }

    @And("^assert no Success email on newsletter subscription for user$")
    public void assertNoSuccessEmailOnNewsletterSubscriptionForUser() {
        switch (UrlBuilder.getLocale()) {
            //toggle is switched off on vusefr as email is coming from salesforce in RT88, ticket NO. is 770296
            //case "vusefr":
            case "uk":
            case "vuseuk":
                String EMAIL_ADDRESS = (String) scenarioContext.getContext(Context.NEW_USER_EMAIL_ID);
                registrationPage.verifyEmailAndReturn(EMAIL_ADDRESS, "success");
                break;
            default:
        }
    }

    @And("^assert guiding text in address fields$")
    public void assertGuidingTextInAddressFields() {
        switch (UrlBuilder.getLocale()) {
            case "vusede":
                if (registrationPage.doesURLContain("/checkout/")) {
                    assertTrue(registrationPage.waitForExpectedElement(paymentPage.COMPANY_ADDRESS_FIELD).getAttribute("placeholder").equals(UrlBuilder.getMessage("companyGuidingText.key")));
                    assertTrue(registrationPage.waitForExpectedElement(paymentPage.STREET_ADDRESS1_FIELD).getAttribute("placeholder").equals(UrlBuilder.getMessage("street1GuidingText.key")));
                    assertTrue(registrationPage.waitForExpectedElement(paymentPage.STREET_ADDRESS2_FIELD).getAttribute("placeholder").contains(UrlBuilder.getMessage("street2GuidingText.key")));
                } else {
                    assertTrue(registrationPage.waitForExpectedElement(registrationPage.company).getAttribute("placeholder").equals(UrlBuilder.getMessage("companyGuidingText.key")));
                    assertTrue(registrationPage.waitForExpectedElement(registrationPage.streetAddressLine1).getAttribute("placeholder").equals(UrlBuilder.getMessage("street1GuidingText.key")));
                    assertTrue(registrationPage.waitForExpectedElement(registrationPage.streetAddressLine2).getAttribute("placeholder").contains(UrlBuilder.getMessage("street2GuidingText.key")));
                }
                break;
            default:
        }
    }

    @And("^create a new account with mark all marketing consent checks selected$")
    public void createNewAccountWithMarkAllMarketingConsentChecks() throws Throwable {
        System.setProperty("markAllConsent.key", "required");
        batHelper.createNewAccount();
    }

    @And("^create a new account with no marketing consent$")
    public void createNewAccountWithNoMarketingConsentChecks() throws Throwable {
        System.setProperty("noConsent.key", "notRequired");
        batHelper.createNewAccount();
    }

    @And("^create a new account with Loyalty$")
    public void createANewAccountWithLoyalty() throws Throwable {
        batHelper.createANewLoyaltyAccount();
    }

    @And("^create a new account through Newsletter Without Loyalty Signup$")
    public void createANewAccountThroughNewsletterWithoutLoyaltySignup() throws Throwable {
        batHelper.createANewAccountThroughNewsletterWithoutLoyaltySignup();
    }

    @And("^create new account from login page$")
    public void createNewAccountFromLoginPage() {
        batHelper.createNewAccountFromLoginPage();
    }

    @And("^create new account from login page without selecting loyalty checkbox$")
    public void createNewAccountFromLoginPageWithoutSelectingLoyaltyCheckbox() {
        batHelper.createNewAccountFromLoginPageWithLoyalty();
    }

    @And("^create a new account through Newsletter With Loyalty Signup$")
    public void createANewAccountThroughNewsletterWithLoyaltySignup() throws Throwable {
        batHelper.createANewAccountThroughNewsletterWithLoyaltySignup();
    }

    @And("^create a new account with marketing communication checks selected$")
    public void createNewAccountWithMarketingCommunicationChecks() throws Throwable {
        System.setProperty("marketingChecks.key", "required");
        batHelper.createNewAccount();
    }

    @And("^create a new account without selecting marketing communication checks$")
    public void createNewAccountWithoutMarketingCommunicationChecks() throws Throwable {
        System.setProperty("marketingChecks.key", "not required");
        batHelper.createNewAccount();
    }

    @And("^create a new account with email address \"([^\"]*)\"$")
    public void createANewAccountWithEmailAddress(String emailAddress) {
        batHelper.createNewAccount(emailAddress);
    }

    @And("^create a new account for guest$")
    public void createANewAccountForGuest() {
        switch (UrlBuilder.getLocale()) {
            case "pl":
            case "vuseco":
                batHelper.createNewAccountGuest();
                break;
            default:
        }
    }

    @And("^create a new account$")
    public void createANewAccount() throws Throwable {
        if (Props.EYES_ON) {
            UrlBuilder.revisitBATHomePage();
        }
        switch (UrlBuilder.getLocale()) {
            case "epok":
                batHelper.createNewAccountBAT();
                break;
            default:
                batHelper.createNewAccount();
        }
    }


    @And("^create same account after deleting the user$")
    public void createSameAccount() throws Throwable {
        batHelper.createSameAccount();
    }

    @And("^create a new account without confirm Email$")
    public void createANewAccountWithoutConfirmEmail() throws Throwable {
        batHelper.createNewAccountWithoutConfirmEmail();
    }

    @Then("^assert error message of (.*) is displayed for registration$")
    public void assertErrorMessageOfRequiredFieldKeyIsDisplayed(String messageKey, List<String> list) {

        list.forEach(fieldUnderTest -> {
            if (fieldUnderTest.equalsIgnoreCase("termsAndCondition"))
                fieldUnderTest = "accept_conditions";
            if (fieldUnderTest.equalsIgnoreCase("streetAndAddressLine1"))
                fieldUnderTest = "street_1";
            String errorMessageDisplayed = registrationPage.getErrorMessageDisplayedFor(fieldUnderTest);
            assertEquals((errorMessageDisplayed), UrlBuilder.getMessage(messageKey));
        });
    }

    @Then("^assert checkbox is displayed for customers not having a UK/FR number under Mobile Number field$")
    public void assertDontHaveValidUKFRNumberCheckBoxUnderMobileNumberField() {
        assertTrue(registrationPage.waitForExpectedElement(registrationPage.MOBILE_NUMBER_CHECKBOX).isDisplayed());
    }


    @And("^click on Manual Address entry link$")
    public void clickOnManualAddressEntryLink() {
        registrationPage.clickOnManualAddressEntryLink();
    }

    @Then("^user selects the mobile number checkbox$")
    public void userSelectsMobileNumberCheckBox() {
        registrationPage.clickByElementByQueryJSExecutor(registrationPage.MOBILE_NUMBER_CHECKBOX);
    }

    @Then("^assert mobile number field is greyed out and gets populated with '(.*)'$")
    public void assertPhoneNumberFieldGreyedOutAndPopulatedWithDefaultValue(String strExpectedValue) {
        assertTrue(Boolean.parseBoolean(registrationPage.waitForExpectedElement(registrationPage.phoneNumber).getAttribute("readonly")));
        assertTrue(registrationPage.waitForExpectedElement(registrationPage.phoneNumber).getAttribute("value").equalsIgnoreCase(UrlBuilder.getMessage(strExpectedValue)));
    }

    @Then("^assert user is able to update any valid UK/FR number and updated information gets saved$")
    public void assertUserAbleToUpdateAnyValidUKFRNumberAndUpdatedInformationSaved() {
        registrationPage.userUpdatesValidNumberAndSaveAddress();
        assertTrue(accountDashboardPage.getWebDriver().getPageSource().contains(UrlBuilder.getMessage("addressTitleText.key")));
    }

    @Then("^user create a new account with default UK/FR mobile number$")
    public void userCreateNewAccountWithDefaultUKMobileNumber() {
        registrationPage.createNewAccountWithDefaultMobileNumber();
    }

    @And("^create new account with more than 30 characters in address field$")
    public void createANewAccountWithMoreThan30CharacterAddress() throws Throwable {
        String DOB = RandomGenerator.fetchRandomDOBinSpecifiedFormat("dd.MM.yyyy");
        registrationPage.createANewAccountWithSpecifiedUserDetails(emailAddressData, firstNameData, user_last_name, DOB, user_address_post_code);
    }

    @Then("^assert error message is displayed for address field$")
    public void assertErrorMessageForAdressField() throws Throwable {
        assertTrue(registrationPage.waitForExpectedElement(registrationPage.ERROR_MESSAGE_ADDRESS_FIELD, 10).isDisplayed());
    }

    @Then("^assert error message (.*) is displayed for User ID field$")
    public void assertErrorMessageIsDisplayedForUserIDField(String strMessage) {
        assertTrue(registrationPage.waitForExpectedElement(registrationPage.CUSTOMER_ID_FIELD_ERROR, 10).getText().contains("Пожалуйста, введите ваш ИИН"));
    }

    @And("^user enters more than '(.*)' characters in ID field and assert error message$")
    public void userEntersMoreThanCharactersLimitInIDFieldAndAssertErrorMessage(String strLimit) {
        registrationPage.userEntersCharactersLimitInIDField(strLimit);
        assertTrue(registrationPage.waitForExpectedElement(registrationPage.CUSTOMER_ID_FIELD_ERROR).getText().contains("Вы ввели неверный ИИН"));
    }

    @And("^user enters less than '(.*)' characters in ID field and assert error message$")
    public void userEntersLessThanCharactersLimitInIDFieldAndAssertErrorMessage(String strLimit) {
        registrationPage.userEntersCharactersLimitInIDField(strLimit);
        assertTrue(registrationPage.waitForExpectedElement(registrationPage.CUSTOMER_ID_FIELD_ERROR).getText().contains("Вы ввели неверный ИИН"));
    }

    @And("^user enters non-numeric '(.*)' characters in ID field and assert error message$")
    public void userEntersNonNumericCharactersInIDFieldAndAssertErrorMessage(String strLimit) {
        registrationPage.userEntersNonNumericCharactersInIDField(strLimit);
        assertTrue(registrationPage.waitForExpectedElement(registrationPage.CUSTOMER_ID_FIELD_ERROR).getText().contains("Вы ввели неверный ИИН"));
    }

    @And("^complete registration details and create account$")
    public void completeTheRegistrationAndCreateAccount() {
        String emailAddress = registrationPage.emailAddressData;
        scenarioContext.setContext(EMAIL_ID, emailAddress);
        registrationPage.enterRegistrationDetailsAndCreateAccount(emailAddress);
    }

    @And("^user attempt to create an account with age as in table then assert expected message is displayed$")
    public void userAttemptToCreateAnAccountWithAgeAsInTableThenAssertExpectedMessageIsDisplayed(List<Map<String, String>> mapList) {
        mapList.forEach(map -> {
            registrationPage.enterRegistrationDetailsWithMentionedAge(map.get("age"));
            String expectedMessage = UrlBuilder.getMessage(map.get("expected"));
            softAssertions.assertThat(registrationPage.getWebDriver().getPageSource().contains(expectedMessage))
                    .as("Expected Messageis not displayed in the page: " + expectedMessage)
                    .isTrue();
        });
        softAssertions.assertAll();
    }

    @And("^verify email and return to my account page$")
    public void verifyEmailAndReturnToMyAccountPage() {
        registrationPage.verifyEmailAndReturnToMyAccountPage();
    }

    @And("^create a new account with specific postcode '(.*)'$")
    public void createANewAccountWithSpecificPostCode(String strPostCode) throws Throwable {
        registrationPage.createANewAccountWithSpecifiedUserDetails(emailAddressData, firstNameData, lastNameData, UrlBuilder.getMessage("ValidDOB.key"), UrlBuilder.getMessage(strPostCode));
    }

    @And("^login with account created details$")
    public void loginWithDeletedAcoountDetails() {
        registrationPage.loginwithdeleteaccountdetail();
    }

    @And("^assert email template is correct$")
    public void assertEmailTemplateIsCorrect() {
        registrationPage.verifyEmailTemplate();
    }

    @And("^create new account for invite$")
    public void createANewAccountForInvite() {
        batHelper.createNewAccountForInvite();
    }

    @And("^register an account with same email ID on Create Account page when customer doesn't exist on M2$")
    public void registerAccountWithSameEmailIDasFacebookAccountIfNotExistingInM2() {
        registrationPage.registerAccountWithSameEmailIDasFacebookAccountIfNotExistingInM2();
    }

    @And("^assert First name,Last name and email address fields are pre-populated$")
    public void assertFirstNameLastNameEmailAddressFieldsArePrePopulated() {
        assertTrue(!registrationPage.waitForExpectedElement(accountDashboardPage.firstNameField).getAttribute("value").isEmpty());
        assertTrue(!registrationPage.waitForExpectedElement(accountDashboardPage.lastName).getAttribute("value").isEmpty());
        assertTrue(!registrationPage.waitForExpectedElement(registrationPage.email).getAttribute("value").isEmpty());
    }

    @And("^assert Email address field is non editable$")
    public void assertEmailAddressFieldIsNonEditable() {
        assertTrue(registrationPage.waitForExpectedElement(registrationPage.email).getAttribute("readonly").equalsIgnoreCase("true"));
    }

    @And("^assert Password field is removed from account registration form$")
    public void assertPasswordFieldIsRemovedFromAccountRegistrationForm() {
        try {
            assertTrue(registrationPage.waitForExpectedElement(forgotPasswordPage.PASSWORD_INPUT_FIELD).isEnabled());
        } catch (Exception ex) {
            LOG.info("Password field is hidden on Create account page.");
        }
    }

    @And("^add SA ID Numer field$")
    public void useraddSAIDNumber() {
        registrationPage.addSAIDNumber();
    }

    @And("^assert text of \"([^\"]*)\" is displayed for language \"([^\"]*)\"$")
    public void assertTextOfIsDisplayedForLanguage(String messageKey, String language) throws Throwable {
        logininPage.assertTextOfRegisteredCustomersIsDisplayed(messageKey + "-" + language);
    }

    @And("^assert error message of invalid login is displayed for language \"([^\"]*)\"$")
    public void assertTextOfIsDisplayedForinvalidLigin(String language) throws Throwable {
        logininPage.validateLoginErrorMessage();
    }

    @And("^user clicks on delete account$")
    public void userClicksOnDeleteAccount() throws InterruptedException {
        accountDashboardPage.deleteMyAccount();
    }

    @Given("^user creates an account$")
    public void userCreatesAnAccount() {
        RegistrationPageModel registrationPageModel = RegistrationPageModel.builder().build().withDefaultValues();
        registrationPage.populatePersonalInformation(registrationPageModel);
        registrationPage.createEmailAndPassword();
        registrationPage.clickCreateAccountFromElementsList();
        registrationPage.verifyEmailAndReturnToMyAccountPage();
    }

    @And("^the user logs out$")
    public void theUserLogsOut() {
        // temporary until logout link implemented
        registrationPage.getWebDriver().manage().deleteAllCookies();
    }

    @Given("^a new user logs in to the \"([^\"]*)\" website$")
    public void aNewUserLogsInToTheWebsite(String language) throws Throwable {
        // create new account via api
        createANewAccountWithViaApi();
        // navigate to login page
        // until site navigation is implemented
        String url = UrlBuilder.getUrl().replace("en", language) + "/shop/customer/account/login";
        registrationPage.getWebDriver().navigate().to(url);
        scenarioContext.setContext(LANGUAGE, language);
        // select all from cookies
        pdp.avalancheClearPopups();
        ;
        // login
        logininPage.login(scenarioContext.getContext(EMAIL_ID).toString(), scenarioContext.getContext(PASSWORD).toString());
    }

    @When("^I check google tag manager$")
    public void iCheckGoogleTagManager() throws Throwable {
        registrationPage.checkGTM();
    }

    @And("^navigates to the following page and confirms that newsletter is disabled$")
    public void navigatesToTheFollowingPageAndConfirmsThatNewsletterIsDisabled(DataTable pages) throws Throwable {
        List<List<String>> pageList = pages.asLists(String.class);
        String pageName;
        for (List<String> page : pageList) {
            LOG.info(" checking page " + page + " for existence of newsletter");
            pageName = page.get(0);
            switch (pageName) {
                case "login":
                    homePage.clickPersonIcon();
                    break;
                case "plp":
                    paymentPage.navigateToPlp();
                    break;
                case "registration":
                    homePage.clickPersonIcon();
                    logininPage.clickCreateAccountButton();
                    break;
                case "my account":
                    homePage.clickPersonIcon();
                    break;
                case "pdp":
                    paymentPage.navigateToPlp();
                    plp.selectFirstAvailableProduct();
                    break;
                case "basket":
                    paymentPage.navigateToPlp();
                    plp.selectFirstAvailableProduct();
                    homePage.clickBasketIcon();
                    paymentPage.clickOnViewBasketButton();
                    break;
                case "checkout":
                    paymentPage.clickOnCheckoutButton();
                    break;
                default:
                    assertThat(true).as("ERROR invalid page name supplied " + pageName).isFalse();
            }
            registrationPage.ensureNoNewsletter(pageName);
            switch (pageName) {
                case "login":
                    logininPage.userEntersValidSigninDetails("loginValidUser", "loginValidPassword");
                    break;
            }
        }
    }


    @And("^navigates to the following page and confirms that live chat is functioning$")
    public void navigatesToTheFollowingPageAndConfirmsThatLiveChatIsFunctioning(DataTable pages) throws Throwable {
        List<List<String>> pageList = pages.asLists(String.class);
        String pageName;
        for (List<String> page : pageList) {
            pageName = page.get(0);
            LOG.info("Checking for Live Chat on " + pageName);
            switch (pageName) {
                case "login":
                    registrationPage.validateLiveChat(pageName);
                    logininPage.userEntersValidSigninDetails("loginValidUser", "loginValidPassword");
                    break;
                case "plp":
                    registrationPage.validateLiveChat(pageName);
                    plp.addProductToBasket();
                    break;
                default:
                    registrationPage.validateLiveChat(pageName);
                    break;
            }
        }
    }

    @And("^I verify my account through email$")
    public void iVerifyMyAccountThroughEmail() {
        registrationPage.verifyEmailAndReturnToMyAccountPage();
    }

    @And("^assert that you are now on the yoti age estimation page$")
    public void assertThatYouAreNowOnTheYotiAgeEstimationPage() {
        registrationPage.assertThatOnYotiAgeEstimationPage();
    }

    @And("^you cannot log in without completing yoti AV$")
    public void youCannotLogInWithoutCompletingYotiAV() {
        homePage.clickPersonIcon();
        homePage.chooseMyAccountOnAccountDropdown();
        registrationPage.confirmCannotLoginBeforeYotiAV();
    }

    @And("^the form has the following mandatory consents$")
    public void theFormHasTheFollowingMandatoryConsents(DataTable fieldTable) {
        List<List<String>> fields = fieldTable.asLists(String.class);
        String fieldName;
        for (List<String> field : fields) {
            fieldName = field.get(0);
            assertThat(registrationPage.checkboxConsentAsMandatory(fieldName))
                    .as("LOG: field " + fieldName + " should be view as Consent ").isNotNull();
        }
    }

    @And("^the form has the following non mandatory consents$")
    public void theFormHasTheFollowingNonMandatoryConsents(DataTable fieldTable) {
        List<List<String>> fields = fieldTable.asLists(String.class);
        String fieldName;
        for (List<String> field : fields) {
            fieldName = field.get(0);
            assertThat(registrationPage.checkboxConsentAsNOnMandatory(fieldName))
                    .as("LOG: field " + fieldName + " should be view as Consent ").isNotNull();
        }
    }

    @And("^the user navigates to My Account Page$")
    public void theUserNavigatesToMyAccountPage() {
        homePage.clickPersonIcon();
        homePage.chooseMyAccountOnAccountDropdown();
        logininPage.storeUserData();
    }

    @And("^I validate the \"([^\"]*)\" email$")
    public void iValidateTheEmail(String emailtype) throws Throwable {
        registrationPage.validateEmail(emailtype);
    }


    @When("^user signs in to the site custom details mobile$")
    public void userSignsInToTheSiteCustomMobile() {
        String homePage = UrlBuilder.getMessage("customerAccountURL.key");
        Customer customer = (Customer) scenarioContext.getContext(CUSTOMER);
        loginPageSteps.userSignsIn(customer.getEmail(), forgotPasswordPageSteps.validPassword);
        assertTrue(batHelper.checkPageUrlContains(homePage));
    }

    @When("^assert data protection link displayed under registration$")
    public void assertDataProtectionLinkDisplayedUnderRegistration() {
        List<String> expectedDataProtectionUrlLink = Stream.of(UrlBuilder.getMessage("dataProtectionUrl.key").split(","))
                .collect(toCollection(LinkedList::new));
        List<WebElement> dataProtectionElements = registrationPage.getDataProtectionElements();
        actualDataProtectionUrlLink = dataProtectionElements.stream()
                .map(ele -> ele.getAttribute("href")).filter(s -> !s.isEmpty()).collect(Collectors.toList());
        softAssertions.assertThat(actualDataProtectionUrlLink)
                .as("Unexpected Url under follow us section ")
                .hasSameElementsAs(expectedDataProtectionUrlLink);
        softAssertions.assertAll();
    }

    @And("^should get correct '(.*)'$")
    public void shouldGetCorrectDataprotectionTitleKey(String dataProtectionTitle) {
        List<WebElement> dataProtectionElements = registrationPage.getDataProtectionElements();
        List<String> dataProtectionTitles = Stream.of(UrlBuilder.getMessage(dataProtectionTitle).split(","))
                .collect(toCollection(LinkedList::new));
        for (int i = 0; i < dataProtectionElements.size(); i++) {
            dataProtectionElements = registrationPage.getDataProtectionElements();
            dataProtectionElements.get(i).click();
            Assert.assertTrue(registrationPage.getWebDriver().getTitle()
                    .equalsIgnoreCase(dataProtectionTitles.get(i)));
            registrationPage.clickBrowserBackButton();
        }
    }

    @Then("^user enters less than '(.*)' characters in Phone number field and assert error message$")
    public void userEntersLessThanCharactersLimitInPhoneNumberFieldAndAssertErrorMessage(String strLimit) {
        registrationPage.userEntersCharactersLimitInPhoneNumberField(strLimit);
        assertTrue(registrationPage.getTelephoneErrorMessage().contains("Пожалуйста, введите не менее 11 символов."));
    }

    @Then("^user enters non-numeric '(.*)' characters in Phone number field and assert error message$")
    public void userEntersNonNumericCharactersInPhoneNumberFieldAndAssertErrorMessage(String strLimit) {
        registrationPage.userEntersNonNumericCharactersInPhoneNumberField(strLimit);
        assertTrue(registrationPage.getTelephoneErrorMessage().contains("Пожалуйста, укажите номер в следующем формате"));
    }

    @Then("^user enters invalid phone number in Phone number field and assert error message$")
    public void userEntersInvalidPhoneNumberInPhoneNumberFieldAndAssertErrorMessage() {
        registrationPage.userEntersNumberInPhoneNumberField(98765432100L);
        assertTrue(registrationPage.getTelephoneErrorMessage().contains("Пожалуйста, укажите номер в следующем формате"));
    }

    @And("^create a new account with restricted address details '(.*)'$")
    public void createANewAccountWithRestrictedStreetAddress(String strInputText){
        registrationPage.createANewAccountWithRestrictedStreetAddress(strInputText);
    }

    @And("^assert error messages displayed of all mandatory fields$")
    public void assertErrorMessagesDisplayedOfAllMandatoryFields() {
        switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
            case VUSEZA:
                registrationPage.checkRegistrationError("telephone");
                break;
            case VUSEFR:
                registrationPage.checkRegistrationError("firstName");
                registrationPage.checkRegistrationError("lastName");
                registrationPage.checkRegistrationError("email");
                registrationPage.checkRegistrationError("dob");
                registrationPage.checkRegistrationError("addressSearch");
                registrationPage.checkRegistrationError("password");
                registrationPage.checkRegistrationError("confirmPassword");
                break;
            default:
                scenarioContext.setContext(LANGUAGE, "vuseit");
                registrationPage.checkRegistrationError("firstName");
                registrationPage.checkRegistrationError("lastName");
                registrationPage.checkRegistrationError("email");
                registrationPage.checkRegistrationError("dob");
                registrationPage.checkRegistrationError("genda");
                registrationPage.checkRegistrationError("codiceFiscale");
                registrationPage.checkRegistrationError("birthCity");
                registrationPage.checkRegistrationError("addressSearch");
                registrationPage.checkRegistrationError("telephone");
                registrationPage.checkRegistrationError("password");
                registrationPage.checkRegistrationError("confirmPassword");
        }
    }

    @Then("^enter invalid format for email, mobile number, confirm password$")
    public void enterInvalidFormatEmailMobileAndConfirmPassword() {
        registrationPage.waitClearAndEnterText(registrationPage.phoneNumber, "1234567");
        registrationPage.waitClearAndEnterText(registrationPage.email, "test@test");
        registrationPage.waitClearAndEnterText(registrationPage.password, passwordData);
        registrationPage.waitClearAndEnterText(registrationPage.passwordConfirmation, "1234567");
        registrationPage.waitForItemToBeClickableAndClick(registrationPage.M_CREATEACCOUNT_BUTTON_FR,10);
    }

    @Then("^assert Error message for invalid format of email, mobile number and confirm password$")
    public void assertErrorMessageForInvalidEmailMobileNumberConfirmPassword() {
        registrationPage.assertErrorMessageForInvalidEmailMobileNumberConfirmPassword();
    }

    @And("^create a new account with duplicate email address$")
    public void createANewAccountWithDuplicateEmailAddress() {
        registrationPage.createANewAccountWithDuplicateEmailAddress();
    }

    @Then("^assert duplicate email error message displayed$")
    public void assertDeplicateEmailErrorMessageDisplayed() {
        assertTrue(registrationPage.waitForExpectedElement(registrationPage.DUPLICATE_EMAIL_ERROR_MESSAGE, 10).isDisplayed());
    }

    @Then("^validate get \"([^\"]*)\" email for user$")
    public void validateGetEmailForUser(String validateString) throws InterruptedException {
        assertTrue(registrationPage.verifyEmailMessage(validateString));
    }

    @And("^assert DOB error message '(.*)' is displayed$")
    public void assertDOBErrorMessageIsDisplayed(String errorMessage) {
        assertTrue(registrationPage.getDOBErrorMessage().contains(UrlBuilder.getMessage(errorMessage)));
    }
    @And("^assert CodiceFiscale error message '(.*)' is displayed$")
    public void assertCodiceFiscaleErrorMessageIsDisplayed(String errorMessage) {
        assertTrue(registrationPage.getCodiceFiscaleErrorMessage().contains(UrlBuilder.getMessage(errorMessage)));
    }

    @And("^populate signin fields with an existing email$")
    public void populateSigninFieldsWithAnExistingEmail() {
        registrationPage.enterDataAndWait(registrationPage.email, "battest@mailinator.com");
        registrationPage.enterDataAndWait(registrationPage.password, password);
        registrationPage.enterDataAndWait(registrationPage.passwordConfirmation, password);
        registrationPage.checkTsCs();
        if (Locale.valueOf(UrlBuilder.getLocale().toUpperCase()).equals(LYFTSE)) {
            registrationPage.checkPrivacyPolicy();
        }
    }

    @And("^assert exist email error message is displayed$")
    public void assertExistEmailErrorMessageIsDisplayed() {
        assertTrue(registrationPage.getExistEmailErrorMessage().contains(UrlBuilder.getMessage("existEmailerrorMessage.key")));
    }

    @And("^populate signin fields with the password which has 4 characters$")
    public void populateSigninFieldsWithThePasswordWhichHas4Characters() {
        String emailAddress = emailAddressData;
        scenarioContext.setContext(EMAIL_ID, emailAddress);
        registrationPage.enterDataAndWait(registrationPage.email, emailAddress);
        String temp4Password = random(4, ALPHABETS);
        registrationPage.enterDataAndWait(registrationPage.password, temp4Password);
        registrationPage.enterDataAndWait(registrationPage.passwordConfirmation, temp4Password);
        registrationPage.checkTsCs();
        if (Locale.valueOf(UrlBuilder.getLocale().toUpperCase()).equals(LYFTSE)) {
            registrationPage.checkPrivacyPolicy();
        }
    }

    @Then("^assert password error message is displayed$")
    public void assertPasswordErrorMessageIsDisplayed() {
        assertTrue(registrationPage.getPasswordErrorMessage().contains(UrlBuilder.getMessage("passwordErrorMessage.key")));
    }

    @And("^assert TsAndCs error message is displayed$")
    public void assertTsAndCsErrorMessageIsDisplayed() {
        assertTrue(registrationPage.getTsAndCsErrorMessage().contains(UrlBuilder.getMessage("termsAndCondtionError.key")));
    }

    @And("^user click on Delete my account link$")
    public void userClickOnDeleteMyAccountLink() {
        registrationPage.clickUsingJS(registrationPage.DELETE_MY_ACCOUNT_LYFT);
    }

    @And("^Delete my account page is up$")
    public void deleteMyAccountPageIsUp() {
        assertTrue(registrationPage.isElementPresentByby(registrationPage.DELETE_MY_ACCOUNT_DESCRIPTIONLYFT));
    }

    @Then("^Without clicking checkbox click on Delete button verify error message$")
    public void withoutClickingCheckboxCickOnDeleteButtonAndVerifyErrorMessage() {
       registrationPage.withoutClickingCheckboxCickOnDeleteAndVerifyErrorMessage();
    }

    @Then("^click checkbox and delete account verify message$")
    public void clickCheckboxDeleteAccountVerifyMessage() {
        registrationPage.clickCheckboxDeleteAccountVerifyMessage();
    }

    @Then("^login with deleted user detail$")
    public void loginWithDeletedUserDetail() {
        registrationPage.loginWithDeletedUserDetail();
          }
}