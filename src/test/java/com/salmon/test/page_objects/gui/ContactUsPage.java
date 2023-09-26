package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.constants.Locale;
import cucumber.api.DataTable;
import org.openqa.selenium.By;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ContactUsPage extends PageObject {

    private static final By MESSAGE_SUBJECT_TXT_BOX = By.cssSelector("input#name.input-text");
    private static final By NAME_TXT_BOX = By.cssSelector("input#name.input-text");
    private static final By EMAIL_TXT_BOX = By.cssSelector("input#email.input-text");
    private static final By MESSAGE_CONTENT_TXT_BOX = By.cssSelector("textarea#comment.input-text");
    private static final By NEWSLETTER_CONSENT_CHK_BOX = By.cssSelector("#save-data-yes");
    private static final By Ts_Cs_CHK_BOX = By.cssSelector("#terms-and-conditions,#is_subscribed");
    private static final By SUBMIT_BUTTON = By.cssSelector(".action.submit.primary");
    private static final By NAME_LABEL = By.cssSelector(".field.name.required>label>span");
    private static final By EMAIL_LABEL = By.cssSelector(".field.email.required>label>span");
    private static final By COMMENT_LABEL = By.cssSelector(".field.comment.required>label>span");


    private static final By NAME_TXT_BOX_VELOPL = By.cssSelector("input#name");
    private static final By EMAIL_TXT_BOX_VELOPL = By.cssSelector("input#email");
    private static final By PHONE_TXT_BOX_VELOPL  = By.cssSelector("input#telephone");
    private static final By MESSAGE_TXT_BOX_VELOPL = By.cssSelector("textarea#comment");
    private static final By Ts_Cs_CHK_BOX_VELOPL = By.cssSelector("#save-data-yes");
    private static final By SUBMIT_BUTTON_VELOPL = By.cssSelector("button#send_info");

    private static final By NAME_REQUIRED_ERROR_VELOPL = By.cssSelector("div#name-error");
    private static final By EMAIL_REQUIRED_ERROR_VELOPL = By.cssSelector("div#email-error");
    private static final By PHONE_REQUIRED_ERROR_VELOPL  = By.cssSelector("div#telephone-error");
    private static final By MESSAGE_REQUIRED_ERROR_VELOPL = By.cssSelector("div#comment-error");

    public void completeForm() {
        switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
            case KZ:
                populateName();
                populateEmail();
                enterText(MESSAGE_CONTENT_TXT_BOX, "testMessageContent");
                break;
            case VELOZA:
                enterText(NAME_TXT_BOX_VELOPL, "testname");
                enterText(EMAIL_TXT_BOX_VELOPL, "test@mailinator.com");
                enterText(PHONE_TXT_BOX_VELOPL, "+27079448163");
                enterText(MESSAGE_TXT_BOX_VELOPL, "testMessageSubject");
                break;
            case VELOPL:
                enterText(NAME_TXT_BOX_VELOPL, "testname");
                enterText(EMAIL_TXT_BOX_VELOPL, "test@mailinator.com");
                enterText(PHONE_TXT_BOX_VELOPL, "7944816325");
                enterText(MESSAGE_TXT_BOX_VELOPL, "testMessageSubject");
                break;
            case VUSEIT:
                enterText(MESSAGE_SUBJECT_TXT_BOX, "testMessageSubject");
                enterText(MESSAGE_CONTENT_TXT_BOX, "testMessageContent");
                break;
            default:
                waitClearAndEnterText(MESSAGE_SUBJECT_TXT_BOX, "testMessageSubject");
                waitClearAndEnterText(EMAIL_TXT_BOX, "test@mailinator.com");
                waitClearAndEnterText(MESSAGE_CONTENT_TXT_BOX, "testMessageContent");
        }
    }

    public void agreeToConsent() {
        switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
            case KZ:
                if (!waitForExpectedElement(Ts_Cs_CHK_BOX).isSelected())
                clickByElementByQueryJSExecutor(Ts_Cs_CHK_BOX);
                break;
            case IT:
            case VUSEIT:
                break;
            case VELOZA:
                break;
            case VELOPL:
                if (!waitForExpectedElement(Ts_Cs_CHK_BOX_VELOPL).isSelected())
                    clickByElementByQueryJSExecutor(Ts_Cs_CHK_BOX_VELOPL);
                break;
            default:
                scrollElementIntoView(NEWSLETTER_CONSENT_CHK_BOX);
                clickByElementByQueryJSExecutor(NEWSLETTER_CONSENT_CHK_BOX);
        }
    }

    public void clickSubmitButton() {
        switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
            case VELOZA:
            case VELOPL:
                scrollElementIntoView(SUBMIT_BUTTON_VELOPL);
                clickByElementByQueryJSExecutor(SUBMIT_BUTTON_VELOPL);
                break;
            default:
                scrollElementIntoView(SUBMIT_BUTTON);
                clickByElementByQueryJSExecutor(SUBMIT_BUTTON);
        }
    }
    public void completeFormWithout(String field) {
        switch (field) {
            case "name":
                waitForExpectedElement(NAME_TXT_BOX).clear();
                populateEmail();
                enterText(MESSAGE_CONTENT_TXT_BOX, "testMessageContent");
                break;
            case "email":
                populateName();
                waitForExpectedElement(EMAIL_TXT_BOX).clear();
                enterText(MESSAGE_CONTENT_TXT_BOX, "testMessageContent");
                break;
            case "message":
                populateName();
                populateEmail();
                waitForExpectedElement(MESSAGE_CONTENT_TXT_BOX).clear();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + field);
        }

    }


        public void completeFormWithouts(DataTable field) {
            List<List<String>> fieldList = field.asLists(String.class);
            String fieldName;
            enterText(NAME_TXT_BOX_VELOPL, "testname");
            enterText(EMAIL_TXT_BOX_VELOPL, "test@mailinator.com");
            enterText(PHONE_TXT_BOX_VELOPL, "7944816325");
            enterText(MESSAGE_TXT_BOX_VELOPL, "testMessageSubject");

            for (List<String> name: fieldList) {
                LOG.info(" checking Field "  + name + " to check required field error message");
                fieldName = name.get(0);
                switch (fieldName) {
                case "name":
                    waitForExpectedElement(NAME_TXT_BOX_VELOPL).clear();
                    String nameErrorValidationMessage = waitForExpectedElement(NAME_REQUIRED_ERROR_VELOPL).getText();
                    LOG.info("Error message provided : " + nameErrorValidationMessage );
                    assertTrueWithCustomError(nameErrorValidationMessage,UrlBuilder.getMessage("contactUsPageNameError.key"));
                    break;
                case "email":
                    waitForExpectedElement(EMAIL_TXT_BOX_VELOPL).clear();
                    String emailErrorValidationMessage = waitForExpectedElement(EMAIL_REQUIRED_ERROR_VELOPL).getText();
                    LOG.info("Error message provided : " + emailErrorValidationMessage );
                    assertTrueWithCustomError(emailErrorValidationMessage,UrlBuilder.getMessage("contactUsPageEmailError.key"));
                    break;
                    case "phone":
                        waitForExpectedElement(PHONE_TXT_BOX_VELOPL).clear();
                        String phoneErrorValidationMessage = waitForExpectedElement(PHONE_REQUIRED_ERROR_VELOPL).getText();
                        LOG.info("Error message provided : " + phoneErrorValidationMessage );
                        assertTrueWithCustomError(phoneErrorValidationMessage,UrlBuilder.getMessage("contactUsPagePhoneError.key"));
                        break;

                    case "message":
                        waitForExpectedElement(MESSAGE_TXT_BOX_VELOPL).clear();
                        clickByElementByQueryJSExecutor(SUBMIT_BUTTON_VELOPL);
                        String messageErrorValidationMessage = waitForExpectedElement(MESSAGE_REQUIRED_ERROR_VELOPL).getText();
                        LOG.info("Error message provided : " + messageErrorValidationMessage );
                        assertTrueWithCustomError(messageErrorValidationMessage,UrlBuilder.getMessage("contactUsPageMessageError.key"));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + field);
            }


    }}

    private void populateEmail() {
        if (getValue(EMAIL_TXT_BOX).isEmpty()) {
            enterText(EMAIL_TXT_BOX, "test@mailinator.com");
        }
    }

    private void populateName() {
        if (getValue(NAME_TXT_BOX).isEmpty()) {
            enterText(NAME_TXT_BOX, "testFirstName");
        }
    }

    public String getContactNameLabel() {
       return waitForExpectedElement(NAME_LABEL).getText();
    }
    public String getContactEmailLabel() {
        return waitForExpectedElement(EMAIL_LABEL).getText();
    }
    public String getContactCommentLabel() {
        return waitForExpectedElement(COMMENT_LABEL).getText();
    }
}