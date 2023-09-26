package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CookieSettingsPage extends PageObject {

    public CookieSettingsPage() {
    }


    private static final By COOKIES_ALLOW_ALL_BUTTON = By.cssSelector("#accept-recommended-btn-handler");
    private static final By COOKIES_TYPE_ONE = By.cssSelector("#cookie-preferences > div:nth-child(3) > div.ot-switch.ot-toggle > label");
    private static final By COOKIES_TYPE_TWO = By.cssSelector("#cookie-preferences > div:nth-child(4) > div.ot-switch.ot-toggle > label");
    private static final By COOKIES_TYPE_THREE = By.cssSelector("#cookie-preferences > div:nth-child(5) > div.ot-switch.ot-toggle > label");
    private static final By COOKIES_CONFIRM_CHOICE = By.cssSelector("#cookie-preferences > div.save-preference-btn-container > button");
    public static final By COOKIE_SETTINGS_FOOTER_LINK = By.cssSelector("div.desktop-only.desktop-footer-links > div > div:nth-child(3) > div:nth-child(2) > p:nth-child(5) > a");



    public void verifyAllowallCta() {
        assertTrue(waitForExpectedElement(COOKIES_ALLOW_ALL_BUTTON, 10).isDisplayed());
    }

    public void verifyCookieTypesToggle() {
        assertTrue(waitForExpectedElement(COOKIES_TYPE_ONE, 4).isDisplayed());
        assertTrue(waitForExpectedElement(COOKIES_TYPE_TWO, 4).isDisplayed());
        assertTrue(waitForExpectedElement(COOKIES_TYPE_THREE, 4).isDisplayed());
    }

    public void verifycookieconfirmchoice() {
        assertTrue(waitForExpectedElement(COOKIES_CONFIRM_CHOICE, 10).isDisplayed());
    }

    public void clickallowall() {
        waitForExpectedElement(COOKIES_ALLOW_ALL_BUTTON, 10).click();
    }

    public void togglecookietype() {
        waitForExpectedElement(COOKIES_TYPE_ONE, 10).click();
    }

    public void togglestatechange() {
        assertFalse(waitForExpectedElement(COOKIES_TYPE_ONE, 10).isSelected());
    }

    public void clickconfirmchoice() {
        waitForExpectedElement(COOKIES_CONFIRM_CHOICE, 10).click();
    }

    public void assertcookiepreferencesaved() {
        waitForExpectedElement(COOKIE_SETTINGS_FOOTER_LINK, 10).click();
        assertFalse(waitForExpectedElement(COOKIES_TYPE_ONE, 10).isSelected());
    }

    public void assertallcookietypeselected() {
        waitForExpectedElement(COOKIE_SETTINGS_FOOTER_LINK, 10).click();
        assertFalse(waitForExpectedElement(COOKIES_TYPE_ONE, 10).isSelected());
        assertFalse(waitForExpectedElement(COOKIES_TYPE_TWO, 4).isSelected());
        assertFalse(waitForExpectedElement(COOKIES_TYPE_THREE, 4).isSelected());
    }
}
