package com.salmon.test.page_objects.gui.iss;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;

public class IssSendToCustomerOverlayPage extends PageObject  {

    private IssSubscriptionDetailsPage issSubscriptionDetailsPage;
    public IssSendToCustomerOverlayPage(IssSubscriptionDetailsPage issSubscriptionDetailsPage) {
        this.issSubscriptionDetailsPage = issSubscriptionDetailsPage;
    }

    private static final By CONSENT_RADIO = By.cssSelector("body  div > label > span");
    private static final By CONSENT_TEXT = By.cssSelector("body  div._2dI886I1JQ7gJBWgJApIY5");
    private static final By EMAIL_ADDRESS_FIELD = By.cssSelector("input[name='email']");
    private static final By EMAIL_ADDRESS_FIELD_HEADING = By.cssSelector("form div  label");
    private static final By SUBMIT_BUTTON = By.cssSelector("body div._2FlidMe6OZa2fx8RO_8VQN > button");
    private static final By SUBMIT_BUTTON_DISABLED = By.cssSelector("body div._2FlidMe6OZa2fx8RO_8VQN > button.disabled");
    private static final By SUBMIT_BUTTON_ENABLED = By.cssSelector("body div._2FlidMe6OZa2fx8RO_8VQN > button[class*='btn']:not([class*='disabled'])");
    private static final By SUCCESS_TITLE = By.cssSelector("div.titelText > h1");
    private static final By EMAIL_SENT_MESSAGE = By.cssSelector("body div._2-HKSImuyNMsTZoDLgSOrf");
    private static final By FINISH_SUBSCRIPTION_BUTTON = By.cssSelector("body  div._2KFNhTYLpEPxupAalh3eAc > button");
    private static final By EMAIL_STATUS_BLOCK = By.cssSelector("#app > main div._23qTmD-JluTeBlo7Sy_N4K > div");
    private static final By EMAIL_STATUS_BLOCK_ERROR = By.cssSelector("body > div:nth-child(9) > div > div > div:nth-child(2) > div._2gal8oHpN0htj_WlBKXn6A");
    private static final By RETRIEVE_QUOTE_QR_CODE_SRC_ATTRIBUTE = By.cssSelector("img[alt='Qr Code']");


        public boolean isSendToCustomerOverlayDisplayed() {
            return returnHeaderText().equalsIgnoreCase(UrlBuilder.getMessage("sendToCustomerOverlayHeading"));
        }

        public boolean isConsentWithRadioDisplayed() {
            return waitForExpectedElement(CONSENT_RADIO).isDisplayed();
        }

        public boolean isConsentTextDisplayed() {
            return waitForExpectedElement(CONSENT_TEXT).getText().equalsIgnoreCase(UrlBuilder.getMessage("sendTocustomerOverlayConsentText"));
        }

        public boolean isEmailAddressTextFieldDisplayed() {
            return waitForExpectedElement(EMAIL_ADDRESS_FIELD).isDisplayed()
                    && waitForExpectedElement(EMAIL_ADDRESS_FIELD_HEADING).getText().equalsIgnoreCase(UrlBuilder.getMessage("sendToCustomerOverlayEmailHeading"));
        }

        public boolean isSubmitButtonDisplayed() {
            return waitForExpectedElement(SUBMIT_BUTTON).isDisplayed();
        }

        public boolean IsSubmitButtonDisabled() {
            return waitForExpectedElement(SUBMIT_BUTTON_DISABLED).isDisplayed();
        }

        public void clickconsentRadioButton() {
            waitForElementToAppearAndDisappear(LOADER, 3, 3);
            try {
                waitForExpectedElement(CONSENT_RADIO, 10).click();
            } catch (Exception e) {
                clickUsingJS(CONSENT_RADIO);
            }
        }

        public void enterEmailAddress(String email) {
            waitForExpectedElement(EMAIL_ADDRESS_FIELD).click();
            waitForExpectedElement(EMAIL_ADDRESS_FIELD);
            persistentClear(EMAIL_ADDRESS_FIELD, webDriver);
            waitForExpectedElement(EMAIL_ADDRESS_FIELD).sendKeys(email);
        }

        public boolean submitCtaIsEnabled() {
            return waitForExpectedElement(SUBMIT_BUTTON_ENABLED, 30).isDisplayed();
        }

        public void clickSubmitButton() {
            waitForExpectedElement(SUBMIT_BUTTON_ENABLED).click();
        }

        public boolean isSuccessFeedbackGiven() {
           return waitForExpectedElement(SUCCESS_TITLE).getText().equalsIgnoreCase(UrlBuilder.getMessage("sendToCustomerOverlaySuccessTitle"))
                   && waitForExpectedElement(EMAIL_SENT_MESSAGE).getText().equalsIgnoreCase(UrlBuilder.getMessage("sendToCustomerOverlaySuccessMessage"));
        }

        public boolean isFinishSubscriptionButtonDisplayed() {
            return waitForExpectedElement(FINISH_SUBSCRIPTION_BUTTON).isDisplayed();
        }

        public void clickFinishSubscription() {
            waitForExpectedElement(FINISH_SUBSCRIPTION_BUTTON);
            clickUsingJS(FINISH_SUBSCRIPTION_BUTTON);
        }

        public boolean isEmailErrorReceived() {
            String actualErrorMessage = waitForExpectedElement(EMAIL_STATUS_BLOCK_ERROR, 5).getText();
            return actualErrorMessage.equalsIgnoreCase(UrlBuilder.getMessage("sendToCustomerOverlayFailureMessage"));
        }

    public boolean isEmailSuccessReceived() {
            waitForExpectedElement(EMAIL_STATUS_BLOCK);
            String message = waitForExpectedElement(EMAIL_STATUS_BLOCK, 10).getText();
            LOG.info("message = "+message);
            return message.equalsIgnoreCase(UrlBuilder.getMessage("sendToCustomerOverlaySuccessMessage"));
    }

    public void scanQrToRetrieveQuote() {
        String qrCodeSrcAttribute = waitForExpectedElement(RETRIEVE_QUOTE_QR_CODE_SRC_ATTRIBUTE).getAttribute("src");
        String x = issSubscriptionDetailsPage.decodeQRCode(qrCodeSrcAttribute.split(",")[1]);
    }
}
