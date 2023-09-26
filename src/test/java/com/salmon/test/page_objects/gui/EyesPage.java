package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;

public class EyesPage extends PageObject {
    private final static By NEWSLETTER_SIGNUP_BUTTON = By.cssSelector(".action.subscribe.primary");
    public final static By NEWSLETTER_SIGNUP_BLOCK = By.cssSelector(".newsletter-form-container");
    public final static By COOKIE_SETTINGS_POPUP = By.cssSelector(".ot-main-content");
    private final static By FAQ_EXPAND_BUTTONS = By.cssSelector(".expand.material-icons");
    private final static By SEND_BUTTON = By.cssSelector(".action.submit.primary");
    private final static By EMAIL = By.cssSelector("#email");

    public void clickNewsletterSignupButton() {
        waitForExpectedElement(NEWSLETTER_SIGNUP_BUTTON).click();
    }

    public void expandAllFAQs() {
        webDriver.findElements(FAQ_EXPAND_BUTTONS).forEach(this::clickElementByQueryJSExecutor);
    }

    public void clickSendButtonOnContactUsPage() {
        waitForExpectedElement(SEND_BUTTON).click();
    }

    public void inputEmailOnContactUsPage(String value) {
        waitForExpectedElement(EMAIL).sendKeys(value);
    }
}
