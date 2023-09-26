package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;

import static com.salmon.test.framework.helpers.WebDriverHelper.getWebDriver;

public class FeedBackPage extends PageObject {
    private static final By NAME_TXT_BOX = By.cssSelector("input#name.input-text");
    private static final By TELEPHONE_TXT_BOX = By.cssSelector("input#telephone.input-text");
    private static final By COMMENT = By.cssSelector("#comment");
    private static final By CONFIRM = By.cssSelector("input#confirm.input-text");
    private static final By EMAIL_TXT_BOX = By.cssSelector("input#email.input-text");
    private static final By PRODUCT_DROPDOWN = By.cssSelector("select#product.input-text");
    private static final By RATE_PRODUCT_DROPDOWN = By.cssSelector("select#rating.input-text");
    private static final By MESSAGE_CONTENT_TXT_BOX = By.cssSelector("textarea#comment.input-text");
    private static final By SUBMIT_BUTTON = By.cssSelector(".action.submit.primary");
    private static final By INVALID_EMAIL_ERROR_MESSAGE = By.cssSelector("#email-error");
    private static final By INVALID_TELEPHONE_ERROR_MESSAGE = By.cssSelector("#telephone-error");

    public void completeForm() {
        switch (UrlBuilder.getLocale()) {
            case "vusefr":
                enterText(TELEPHONE_TXT_BOX, "6623456789");
                enterText(MESSAGE_CONTENT_TXT_BOX, "testContenuMessage");
                break;
            case "vuseuk":
            case "vuseza":
                enterText(TELEPHONE_TXT_BOX, "7123456789");
                enterText(MESSAGE_CONTENT_TXT_BOX, "testContenuMessage");
                break;
            case "vuseco":
                waitClearAndEnterText(NAME_TXT_BOX, UrlBuilder.getMessage("contactName.key"));
                waitClearAndEnterText(TELEPHONE_TXT_BOX, UrlBuilder.getMessage("contactTelephone.key"));
                waitClearAndEnterText(EMAIL_TXT_BOX, UrlBuilder.getMessage("contactEmail.key"));
                waitClearAndEnterText(COMMENT, UrlBuilder.getMessage("contactComment.key"));
                break;
            default:
                completeFormWithoutSelectProduct();
                selectValueFromDropDownByIndex(1, PRODUCT_DROPDOWN);
        }
    }

    public void completeFormWithoutSelectProduct() {
        waitClearAndEnterText(NAME_TXT_BOX, "testFirstName");
        waitClearAndEnterText(TELEPHONE_TXT_BOX, "1234567890");
        waitClearAndEnterText(EMAIL_TXT_BOX, "test@mailinator.com");
        selectValueFromDropDownByIndex(1, RATE_PRODUCT_DROPDOWN);
        waitClearAndEnterText(MESSAGE_CONTENT_TXT_BOX, "testMessageContent");
    }

    public void agreeToConsent() {
        if ("vusefr".equals(UrlBuilder.getLocale())) {
        } else {
            clickByElementByQueryJSExecutor(CONFIRM);
        }
    }

    public void clickSubmitButton() {
        clickByElementByQueryJSExecutor(SUBMIT_BUTTON);
    }

    public void enterInvalidEmailAndTelephoneFormat() {
        waitClearAndEnterText(TELEPHONE_TXT_BOX, "1234567");
        waitClearAndEnterText(EMAIL_TXT_BOX, "test@test");
        waitClearAndEnterText(MESSAGE_CONTENT_TXT_BOX, "testMessageContent");
    }

    public void assertErrorMessageForInvalidEmailTelephoneFormat() {
      String expectedEmailErrorMessage = UrlBuilder.getMessage("loginInvalidEmailErrorMsg.key");
      assertTrueWithCustomError(expectedEmailErrorMessage,waitForExpectedElement(INVALID_EMAIL_ERROR_MESSAGE,10).getText());
      String expectedTelephoneErrorMessage = UrlBuilder.getMessage("errorTelephonerequired.key");
      assertTrueWithCustomError(expectedTelephoneErrorMessage,waitForExpectedElement(INVALID_TELEPHONE_ERROR_MESSAGE,10).getText());
    }
}
