package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.page_objects.gui.LogininPage;
import com.salmon.test.page_objects.gui.MailinatorPage;
import com.salmon.test.page_objects.gui.RegistrationPage;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.constants.Locale;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.gui.cucumberContext.TestContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.salmon.test.framework.PageObject.LOADER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertTrue;

public class MailinatorSteps {

    private MailinatorPage mailinatorPage;
    private ScenarioContext scenarioContext;
    private LogininPage loginPage;
    private RegistrationPage registrationPage;
    private String emailId;
    private SoftAssertions softAssertions;

    private static final By EMAIL_CONFIRMATION_MESSAGE = By.cssSelector("tr.even.pointer.ng-scope");
    private static final By EMAIL_RECEIVED = By.cssSelector("a.ng-binding,div#inboxpane a b");
    private static final By EMAIL_SENDER = By.cssSelector("tbody > tr:nth-child(3) > td:nth-child(2) > b");

    public MailinatorSteps(TestContext testContext, MailinatorPage mailinatorPage, LogininPage loginPage, RegistrationPage registrationPage,SoftAssertions softAssertions) {
        scenarioContext = testContext.getScenarioContext();
        this.mailinatorPage = mailinatorPage;
        this.loginPage = loginPage;
        this.registrationPage = registrationPage;
        emailId = (String) scenarioContext.getContext(Context.EMAIL_ID);
        this.softAssertions = softAssertions;
    }

    @And("^user should receive a email with \"([^\"]*)\" to registered email$")
    public void userShouldReceiveAEmailWithText(String key) {
        mailinatorPage.clickLatestEmailReceivedAndSwitchToMessageBodyIframe(emailId);
        loginPage.assertTextOfRegisteredCustomersIsDisplayed(key);
        mailinatorPage.getWebDriver().switchTo().defaultContent();
        mailinatorPage.getWebDriver().navigate().back();
    }

    @And("^user should receive a newsletter \"([^\"]*)\" confirmation email$")
    public void userShouldReceiveANewsletterSubscriptionConfirmationEmail(String input) {
        String key = input.equals("subscribed") ? "newsletterSuccessEmailSubject.key" : "newsletterUnsubscribedEmailSubject.key";
        String expected = input.equals("subscribed") ? "newsletterSuccessEmailContentTxt.key" : "newsletterUnsubscribedEmailContentTxt.key";

        List<WebElement> receivedEmailElements = mailinatorPage.getReceivedEmailElements(emailId);
        mailinatorPage.clickOnEmailWithSubject(receivedEmailElements, UrlBuilder.getMessage(key));
//        mailinatorPage.switchToMessageBodyIframe();
        mailinatorPage.waitForAjaxElementNotToBePresent(mailinatorPage.getWebDriver(), 10);
        assertThat(mailinatorPage.getWebDriver().getPageSource()).contains(UrlBuilder.getMessage(expected));
    }

    @And("^user should receive a order confirmation email with order number$")
    public void userShouldReceiveOrderConfirmationEmailWithOrderNumber() throws InterruptedException {
        assertEmailWithSubject(UrlBuilder.getMessage("orderConfirmationEmailSubject.key"));
    }

    @And("^user should receive a Trial order confirmation email with order number$")
    public void userShouldReceiveTrialOrderConfirmationEmailWithOrderNumber() {
        assertEmailWithSubject(UrlBuilder.getMessage("trialOrderConfirmationEmailSubject.key"));
    }

    @And("^Loyalty user should receive a order confirmation email with order number$")
    public void loyaltyUserShouldReceiveOrderConfirmationEmailWithOrderNumber() throws InterruptedException {
        assertEmailWithSubjectAndEmail(UrlBuilder.getMessage("orderConfirmationEmailSubject.key"),UrlBuilder.getMessage("loginLoyaltyEmail.key"));
    }

    private void assertEmailWithSubjectAndEmail(String subject,String emailId) {
        String orderNumber = (String) scenarioContext.getContext(Context.ORDER_NUMBER);
        WebDriverWait wait = new WebDriverWait(loginPage.getWebDriver(), 60);
        mailinatorPage.getInbox(emailId);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*"), subject));
        List<WebElement> receivedEmailElements = mailinatorPage.getReceivedEmailElements(emailId);
        mailinatorPage.clickOnEmailWithSubject(receivedEmailElements, subject);
        mailinatorPage.switchToMessageBodyIframe();
        mailinatorPage.waitForAjaxElementNotToBePresent(mailinatorPage.getWebDriver(), 10);
        assertThat(mailinatorPage.getWebDriver().getPageSource()).contains(orderNumber);
        assertThat(mailinatorPage.getWebDriver().getPageSource()).contains("Insider Club");
    }

    private void assertEmailWithSubject(String subject) {
        String orderNumber = (String) scenarioContext.getContext(Context.ORDER_NUMBER);
        WebDriverWait wait = new WebDriverWait(loginPage.getWebDriver(), 60);
        mailinatorPage.getInbox(emailId);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*"), subject));
        List<WebElement> receivedEmailElements = mailinatorPage.getReceivedEmailElements(emailId);
        mailinatorPage.clickOnEmailWithSubject(receivedEmailElements, subject);
        mailinatorPage.switchToMessageBodyIframe();
        mailinatorPage.waitForAjaxElementNotToBePresent(mailinatorPage.getWebDriver(), 10);
        try {
            assertThat(mailinatorPage.getWebDriver().getPageSource()).contains(orderNumber);
        } catch (Exception e) {
            loginPage.waitForElementToAppearAndDisappear(LOADER, 30, 5);
            receivedEmailElements = mailinatorPage.getReceivedEmailElements(emailId);
            mailinatorPage.clickOnEmailWithSubject(receivedEmailElements, subject);
            mailinatorPage.switchToMessageBodyIframe();
            mailinatorPage.waitForAjaxElementNotToBePresent(mailinatorPage.getWebDriver(), 10);
            assertThat(mailinatorPage.getWebDriver().getPageSource()).contains(orderNumber);
        }
    }

    @And("^confirm newsletter subscription link from email$")
    public void confirmNewsletterSubcriptionLinkFromMailinator() {
        registrationPage.verifyEmailAndReturnToMyAccountPage();
    }

    @Then("^this information should be sent from (.*)$")
    public void verifyEmailInformationSentFrom(String expectedText) {
        if (!Locale.valueOf(UrlBuilder.getLocale().toUpperCase()).equals(Locale.VUSEZA)) {
            //Redirect to Mailinator
            mailinatorPage.getWebDriver().get("https://www.mailinator.com/v3/index.jsp?zone=public&query=battest#/#inboxpane");
            mailinatorPage.waitForExpectedElement(EMAIL_CONFIRMATION_MESSAGE, 5);
            //open the received mail
            mailinatorPage.waitForExpectedElement(EMAIL_RECEIVED, 20);//add for FF
            mailinatorPage.getWebDriver().findElement(EMAIL_RECEIVED).click();
            mailinatorPage.waitForPage();
            switch (UrlBuilder.getLocale()) {
                case "vuseuk":
                case "vuseza":
                    assertTrue(mailinatorPage.waitForExpectedElement(EMAIL_SENDER, 10).getText().equalsIgnoreCase(UrlBuilder.getMessage(expectedText)));
                    break;
            }
        }
    }

    @And("^select the \"([^\"]*)\" email$")
    public void selectEmail(String key){
        mailinatorPage.clickOnEmailWithSubject(key);
    }

    @And("^the links on the email will take user back to website$")
    public void linkOnMailPointToCorrectWebSite() throws InterruptedException {
        List<String> linkUrlList = mailinatorPage.getAllLinkOnEmail();
        switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
            case LYFTSE:
            case IT:
                linkUrlList.forEach(s -> {
                    softAssertions.assertThat(s.contains(UrlBuilder.url.split("https://")[1]))
                            .as("The actual url: "+ s + " doesn't contain " +  UrlBuilder.url.split("https://")[1])
                            .isTrue();
                });
                softAssertions.assertAll();
                break;
            default:
                linkUrlList.forEach(s -> {
                    softAssertions.assertThat(s.contains(UrlBuilder.url));
                });
                break;
        }
        softAssertions.assertAll();
    }

}
