package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;

public class ReferAFriendPage extends PageObject {

    private static final By INVITATION_SEND_LINK = By.cssSelector(".action.send.primary");
    private static final By RAF_EMAIL_TXT = By.cssSelector("#email_0");
    private static final By SUBMIT_BUTTON = By.cssSelector(".action.submit.primary");
    private static final By FIRST_NAME = By.cssSelector("input#firstname");
    private static final By LAST_NAME = By.cssSelector("input#lastname");
    private static final By DOB = By.cssSelector("input#dob");
    private static final By GENDER = By.cssSelector("select#gender");
    private static final By PASSWORD = By.cssSelector("input#password");
    private static final By CONFIRM_PASSWORD = By.cssSelector("input#password-confirmation");
    private static final By CONSENT = By.cssSelector("#register-input-custom");

    public void clickSendInvitationLink() {
        waitForExpectedElement(INVITATION_SEND_LINK, 10).click();
    }

    public void enterEmailAddressOfFriend(String email) {
        waitForExpectedElement(RAF_EMAIL_TXT).sendKeys(email);
    }

    public void clickOnSubmitInvitationButton() {
        waitForExpectedElement(SUBMIT_BUTTON, 10).click();
    }

    public void createAccountWithRAFLink() {
        waitForExpectedElement(FIRST_NAME).sendKeys("asdf");
        waitForExpectedElement(LAST_NAME).sendKeys("asdf");
        waitForExpectedElement(DOB).sendKeys("03/06/1980");
        selectValueFromDropDownByIndex(1, GENDER);
        waitForExpectedElement(PASSWORD).sendKeys("Pa55w@rd12345");
        waitForExpectedElement(CONFIRM_PASSWORD).sendKeys("Pa55w@rd12345");
        clickByElementByQueryJSExecutor(CONSENT);
        waitForExpectedElement(SUBMIT_BUTTON).click();
    }
}
