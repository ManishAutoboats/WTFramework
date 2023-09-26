package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.page_objects.gui.HomePage;
import com.salmon.test.page_objects.gui.RegistrationPage;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.gui.cucumberContext.TestContext;
import com.salmon.test.page_objects.gui.models.NewsLetterPageModel;
import com.salmon.test.page_objects.gui.newsLetter.NewsLetterPage;
import com.salmon.test.page_objects.gui.newsLetter.NewsLetterPageLyft;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static com.salmon.test.page_objects.gui.constants.Context.EMAIL_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class NewsLetterSteps extends PageObject {

    private NewsLetterPage newsLetterPage;
    private SoftAssertions softAssertions;
    private ScenarioContext scenarioContext;
    private RegistrationPage registrationPage;

    public NewsLetterSteps(TestContext testContext, SoftAssertions softAssertions,RegistrationPage registrationPage) {
        scenarioContext = testContext.getScenarioContext();
        this.newsLetterPage = testContext.getPageObjectManager().getNewsLetterPage();
        this.softAssertions = softAssertions;
        this.registrationPage = registrationPage;
    }

    @When("^user enter newsletter details and submits the form with (.*)$")
    public void userFillsTheNewsletterDetailsAndSubmitsTheForm(String emailKey) {
        String email = emailKey.contains("registered email") ? (String) scenarioContext.getContext(EMAIL_ID)
                : emailKey.contains("random email") ? RandomGenerator.randomEmailAddress(10)
                : UrlBuilder.getMessage(emailKey);

        NewsLetterPageModel newsLetterPageModel = NewsLetterPageModel.builder().build().withDefaultValues();
        newsLetterPageModel.setEmail(email);
        newsLetterPage.completeFormAndSubmit(newsLetterPageModel);
    }

    @And("^user enters newsletter details and submits the form with age as in table then assert expected is displayed$")
    public void userEntersDetailsAndSubmitsTheFormWithAgeAsInTableThenAssertExpectedIsDisplayed(List<Map<String, String>> mapList) {
        NewsLetterPageModel newsLetterPageModel = NewsLetterPageModel.builder().build().withDefaultValues();
        mapList.forEach(map -> {
            newsLetterPageModel.setDob(getDateOfBirth(map.get("age")));
            newsLetterPage.completeFormAndSubmit(newsLetterPageModel);
            newsLetterPage.waitForAjaxElementNotToBePresent(newsLetterPage.getWebDriver(), 2);
            String expectedMessage = UrlBuilder.getMessage(map.get("expected"));
            softAssertions.assertThat(newsLetterPage.getWebDriver().getPageSource().contains(expectedMessage))
                    .as("Expected Message: "+ expectedMessage + " :is not displayed in the page")
                    .isTrue();
        });
        softAssertions.assertAll();
    }

    private NewsLetterPageModel.DateOfBirth getDateOfBirth(String key) {
        int numberOfYears = Integer.parseInt(key.substring(0, key.indexOf("-")));
        LocalDate dob = LocalDate.now().minusYears(numberOfYears);
        String format = dob.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return NewsLetterPageModel.withDateOfBirth(format);
    }

    @When("^user attempts to submit the newsletter form without mandatory fields$")
    public void userAttemptsToSubmitTheNewsletterFormWithoutField() {
        newsLetterPage.submitEmptyForm();
    }

    @Then("^assert error message of (.*) is displayed for$")
    public void assertErrorMessageOfRequiredFieldKeyIsDisplayed(String messageKey, List<String> list) {
        list.forEach(fieldUnderTest -> {
            if (fieldUnderTest.equalsIgnoreCase("email")) fieldUnderTest = "newsletter";
            if (fieldUnderTest.equalsIgnoreCase("termsAndCondition")) fieldUnderTest = "store_my_data";
            String errorMessageDisplayed = newsLetterPage.getErrorMessageDisplayedFor(fieldUnderTest);
            assertThat(errorMessageDisplayed).isEqualTo(UrlBuilder.getMessage(messageKey));
        });
    }

    @Then("^assert newsletter sign up link is displayed$")
    public void assertNewsletterSignUpLinkIsDisplayed() {
        assertThat(newsLetterPage.isNewsLetterSignUpLinkOrButtonDisplayed()).isTrue();
    }

    @And("^assert country code was already entered as a prefix in the field$")
    public void assertCountryCodeisDisplayedAsPrefix() {
        assertTrueExpectedTextContainsActualTextWithCustomError("+44",getAttribute(NewsLetterPage.PHONE_NUMBER_TXT_BOX,"placeholder"));
    }

    @And("^assert country code was already entered as a prefix in Newsletter$")
    public void assertCountryCodeisDisplayedAsPrefixInNewsLetter() {
        assertTrueExpectedTextContainsActualTextWithCustomError("+44",getAttribute(NewsLetterPage.PHONE_NUMBER_TXT_BOX,"placeholder"));
        newsLetterPage.closeNewsLetterPopup();
    }

    @And("^assert country code was already entered as a prefix in registration page$")
    public void assertCountryCodeisDisplayedAsPrefixInRegistrationPage() {
        if(UrlBuilder.getLocale().equals("vuseit")){
            assertTrueExpectedTextContainsActualTextWithCustomError("+39",getAttribute(NewsLetterPage.PHONE_NUMBER_TXT_BOX,"placeholder"));
            newsLetterPage.assertCountryCodeisDisplayedAsPrefixInRegistrationPage();
        }
        else if(UrlBuilder.getLocale().equals("vuseza")) {
            assertTrueExpectedTextContainsActualTextWithCustomError("+27", getAttribute(NewsLetterPage.PHONE_NUMBER_TXT_BOX, "placeholder"));
            newsLetterPage.assertCountryCodeisDisplayedAsPrefixInRegistrationPage();
        }
        else{
            assertTrueExpectedTextContainsActualTextWithCustomError("+44",getAttribute(NewsLetterPage.PHONE_NUMBER_TXT_BOX,"placeholder"));
            newsLetterPage.assertCountryCodeisDisplayedAsPrefixInRegistrationPage();
            assertTrue(waitForExpectedElement(NewsLetterPage.PHONE_NUMBER_FIELD_ERROR).isDisplayed());
        }

    }

    @And("^verify the email and sms consent opton$")
    public void assertEmailAndSmsConsentOption() {
        if(UrlBuilder.getLocale().equals("vuseit")){
            assertTrue(isElementDisplayedBySeconds(NewsLetterPage.EMAIL_CONSENT_CHK_BOX_IT,10));
            assertTrue(isElementDisplayedBySeconds(NewsLetterPage.MOBILE_SOCIAL_CONSENT_CHK_BOX_IT,10));
        }
        if(UrlBuilder.getLocale().equals("vuseza")){
            assertTrue(isElementDisplayedBySeconds(NewsLetterPage.EMAIL_SMS_CONSENT_ZA,10));
        }
        else {
            assertTrue(waitForExpectedElement(NewsLetterPage.EMAIL_CONSENT_CHK_BOX).isDisplayed());
            assertTrue(waitForExpectedElement(NewsLetterPage.MOBILE_SOCIAL_CONSENT_CHK_BOX).isDisplayed());
        }
    }

    @And("^assert '(.*)' is displayed in newsletter popup$")
    public void assertEmailIsDisplayedInNewsLetterPopup(String emailID) {
        newsLetterPage.assertEmailIsDisplayedInNewsLetterPopup(registrationPage.emailAddressData);
        assertTrueExpectedTextContainsActualTextWithCustomError("+44",getAttribute(NewsLetterPage.PHONE_NUMBER_TXT_BOX,"placeholder"));
    }

    @When("^user select Marketing preference consent option$")
    public void userSelectMarketingPreferenceConsentOption() {
        newsLetterPage.userSelectMarketingPreferenceConsent();
    }

    @And("^User fill details of the subscription pop up$")
    public void fillUserDetailsinSubscriptionPopup(){
        newsLetterPage.userSelectMarketingPreferenceConsent();
        newsLetterPage.fillSubscriptionFeilds();
    }

    @And("^Get email address from my account page$")
    public void getEmailAddress(){
        newsLetterPage.getEmailAddress();
    }

    @And("^verify consent option has been selected$")
    public void verifyConsentOptonIsSelected() {
        if(UrlBuilder.getLocale().equals("vuseit")){
            assertTrue(waitForExpectedElement(NewsLetterPage.EMAIL_CONSENT_CHK_BOX).isSelected());
            assertTrue(waitForExpectedElement(NewsLetterPage.MOBILE_SOCIAL_NEWSLETTER_CHK_BOX_IT).isSelected());
        }
        else if(UrlBuilder.getLocale().equals("vuseza")){
            assertTrue(waitForExpectedElement(NewsLetterPage.EMAIL_SMS_CONSENT_ZA).isSelected());
        }else {
            assertTrue(waitForExpectedElement(NewsLetterPage.EMAIL_CONSENT_CHK_BOX).isSelected());
            assertTrue(waitForExpectedElement(NewsLetterPage.MOBILE_SOCIAL_CONSENT_CHK_BOX).isSelected());
       }
    }

    @And("^assert error is displayed on NewsLetter Signup$")
    public void assertErrorMessageOnNewsLetterSignup() {
        newsLetterPage.assertErrorMessageOnNewsLetterSignup();
        assertTrue(waitForExpectedElement(NewsLetterPage.NEWSLETTER_ERROR_MSSG).isDisplayed());
    }
    @And("^assert message about NewsLetter Subscription is displayed$")
    public void assertMessageAboutNewsLetterSubscription() {
        newsLetterPage.assertMessageAboutNewsLetterSubscription();
    }

    @Then("^verify the email and sms consent option is present$")
    public void assertEmailAndSmsConsentOptionIsNotPresent() {
        assertTrue(isElementDisplayedBySeconds(HomePage.NEWSLETTER_EMAIL_TICKBOX,10));
        assertTrue(isElementDisplayedBySeconds(NewsLetterPage.NEWSLETTER_SMS_TICKBOX,10));
        assertTrue(isElementDisplayedBySeconds(NewsLetterPage.EMAIL_CONSENT_CHK_BOX_UK,10));
        assertTrue(isElementDisplayedBySeconds(NewsLetterPage.EMAIL_CONSENT_CHK_BOX_UK,10));
    }

    @Then("^assert status of Subscription checkbox and verify NL subscription email if checkbox is not selected$")
    public void assertSubscriptionCheckboxSelectionAndVerifyNLSubscriptionEmailIfCheckboxNotSelected() {
        registrationPage.assertSubscriptionCheckboxSelectionAndVerifyNLSubscriptionEmailIfNotChecked();
    }

    @And("^assert DOB field is not present$")
    public void assertDOBFieldIsNotPresent() {
        assertTrue(newsLetterPage.getWebDriver().findElements(newsLetterPage.NEWSLETTER_DOB_FIELD).size()==0);
    }

    @And("^assert DOB error message '(.*)' is displayed on NewsLetter Signup$")
    public void assertDOBErrorMessageIsDisplayedOnNewsletterSignUp(String strErrorMessage) {
        newsLetterPage.assertErrorMessageOnNewsLetterSignup();
        assertTrue(waitForExpectedElement(NewsLetterPage.NEWSLETTER_DOB_ERROR,5).getText().contains(UrlBuilder.getMessage(strErrorMessage)));
    }

    @And("^user closes the newsletter pop-up$")
    public void userClosesTheNewsletterPopUp() {
        newsLetterPage.closeNewsLetterPopup();
    }

    @And("^populate DOB field with '(.*)' on Newsletter Pop-up$")
    public void populateDOBFieldWithUnderAgeOnNewsletterPopUp(String DOBToEnter) {
        newsLetterPage.waitClearAndEnterText(newsLetterPage.NEWSLETTER_DOB_FIELD,UrlBuilder.getMessage(DOBToEnter));
    }

    @Then("^assert Newsletter pop up is displayed$")
    public void assertNewsletterPopUpIsDisplayed() {
        newsLetterPage.isNewsLetterPopUpDisplayed();
    }

    @When("^user click on signup button$")
    public void userClickOnSignupButton() {
        newsLetterPage.clickOnNewsletterSignUpButton();
    }

    @Then("^user should be redirected to third party site to register for newsletter$")
    public void userShouldBeRedirectedToThirdPartySiteToRegisterForNewsletter() {
        waitForAjaxElementNotToBePresent(getWebDriver(),10);
        assertThat(doesURLContain("https://experienciavuse.com/newsletter")).isTrue();
    }

    @And("^enter the required details and signup for newsletter$")
    public void userEnterDetailsOnNewsletterAndSignup() {
        newsLetterPage.userEnterDetailsOnNewsletterAndSignup();
    }

    @Then("^newsletter subscription is successful$")
    public void newsletterSubscriptionIsSuccess() {
        assertTrue(isElementDisplayedBySeconds(NewsLetterPage.NEWSLETTER_SUCCESS_BLOCK,5));
        assertTrue(isElementDisplayedBySeconds(NewsLetterPage.NEWSLETTER_SUCCESS_MESSAGE,5));
    }

    @And("^enter the required details and signup for newsletter guest user$")
    public void userEnterDetailsOnNewsletterAndSignupGuestUser() {
        newsLetterPage.userEnterDetailsOnNewsletterAndSignupGuestUser();
    }

    @And("^user select the newsletter subscription checkbox and submit$")
    public void selectNewsletterSubscriptionAndSubmit() {
        newsLetterPage.selectNewsletterSubscriptionAndSubmit();
    }

    @And("^verify subscription mail is displayed$")
    public void verifySubscriptionMailDisplayed() {
        registrationPage.verifySubscriptionMailDisplayed();
    }

    @And("^enter newsletter details and submits the form with (.*) dob$")
    public void verifySubscriptionMailDisplayed(String dob) {
        registrationPage.enterNewsLetterDetail(dob);
    }

    @When("^without any field filled click on newsletter subscribe$")
    public void clickNewsletterSubscribeWithoutAnyDetails() {
        waitForExpectedElement(newsLetterPage.NEWSLETTER_SUBSCRIBE);
        clickUsingJS(newsLetterPage.NEWSLETTER_SUBSCRIBE);
    }

    @When("^assert error message for newsletter without filling any details$")
    public void assertErrorMessageForNewsletterWithoutAnyDetailsFilled() {
        newsLetterPage.assertErrorMessageForNewsletterWithoutAnyDetailsFilled();
    }
}
