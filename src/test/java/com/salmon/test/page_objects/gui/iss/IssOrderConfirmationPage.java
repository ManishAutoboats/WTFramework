package com.salmon.test.page_objects.gui.iss;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;

public class IssOrderConfirmationPage extends PageObject {

    private static final By CONFIRMATION_PAYMENT_MESSAGE = By.cssSelector("#maincontent > div.columns > div > div.checkout-success > div:nth-child(3) > div > div > p");
    private static final By EMAIL_CONFIRMATION_MESSAGE = By.cssSelector("p.checkout-message");
    private static final By GO_TO_MY_ACCOUNT_CTA = By.cssSelector("#maincontent > div.columns > div > div.checkout-success > div.actions-toolbar > div > a.action.primary.account");
    private static final By RETURN_TO_HOME_CTA = By.cssSelector("#maincontent > div.columns > div > div.checkout-success > div.actions-toolbar > div > a.action.secondary.continue");

    public boolean futurePaymentsDisplayed() {
        return waitForExpectedElement(CONFIRMATION_PAYMENT_MESSAGE).getText().toLowerCase().contains(UrlBuilder.getMessage("futurePaymentsText").toLowerCase());
    }

    public boolean orderConfirmationEmailSentDisplayed()  {
        LOG.info(waitForExpectedElement(EMAIL_CONFIRMATION_MESSAGE).getText().toLowerCase());
        LOG.info(" should contain :");
        LOG.info(UrlBuilder.getMessage("orderConfirmationToEmailMessage").toLowerCase());
        return waitForExpectedElement(EMAIL_CONFIRMATION_MESSAGE).getText().equalsIgnoreCase(UrlBuilder.getMessage("orderConfirmationToEmailMessage"));
    }

    public boolean firstPaymentMessageDisplayed() {
        return waitForExpectedElement(CONFIRMATION_PAYMENT_MESSAGE).getText().toLowerCase().contains(UrlBuilder.getMessage("firstPaymentMessage").toLowerCase());
    }

    public boolean goToMyAccountButtonDisplayed() {
        return waitForExpectedElement(GO_TO_MY_ACCOUNT_CTA).isDisplayed();
    }

    public boolean returnToHomeLinkDisplayed() {
        return waitForExpectedElement(RETURN_TO_HOME_CTA).isDisplayed();
    }

    public void clickOnGoToMyAccount() {
        waitForExpectedElement(GO_TO_MY_ACCOUNT_CTA).click();
    }

    public void clickOnReturnToHome() {
        waitForElementToAppearAndDisappear(LOADER,3,5);
        waitForExpectedElement(RETURN_TO_HOME_CTA).click();
    }

    public boolean isOnHomePage() {
        return webDriver.getCurrentUrl().equals("https://www-" + UrlBuilder.ENVIRONMENT + "-global-" + UrlBuilder.SITE + ".non-prod.marketing.bat.net/" + UrlBuilder.getEndPoints(UrlBuilder.LOCALE) + "/");
    }
}
