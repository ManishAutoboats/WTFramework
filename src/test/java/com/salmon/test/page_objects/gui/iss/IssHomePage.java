package com.salmon.test.page_objects.gui.iss;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.models.api.iss.requests.GetHandoverRequest;
import com.salmon.test.models.api.iss.responses.GetHandoverResponse;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.services.iss.InStoreSubscriptionServices;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

public class IssHomePage extends PageObject  {

    private static final By STORE_DROPDOWN = By.cssSelector("span.icon__down-arrow");
    private static  final By STORE_LIST_ITEM = By.cssSelector("ul.selection__options > li");
    private static final By STAFF_ID_FIELD = By.cssSelector("input#username");
    private static final By PASSWORD_FIELD = By.cssSelector("input[name='password']");
    private static final By SIGN_IN_CTA = By.cssSelector("button.btn.btn__primary");
    private static final By LOGIN_ERROR_MESSAGE = By.cssSelector("div.message.message__error");
    private static final By LOGGED_IN_USER = By.cssSelector("div.user > p > span");
    private static final By LOGO = By.cssSelector("div.logo >  svg");
    private static final By SIGN_OUT_LINK = By.cssSelector("#app > header > div > div.header__right > div.signout_link");
    private static final By CREATE_SUBSCRIPTION_FORM_TEXT = By.cssSelector("form.form");
    private static final By AGE_CHALLENGE_ACCEPT = By.cssSelector("label > span");
    private static final By START_SUBSCRIPTION_BUTTON = By.cssSelector("button.btn.btn__primary");
    private static final By STAFF_ID_LABEL = By.cssSelector("#app > main > form > div > div:nth-child(3) > label");
    private static final By SCAN_CODE_CTA = By.cssSelector("#app > main > div > div > div.U9lEC893eAQ_BHm7wOaiD > span:nth-child(1)");
    private static final By SCAN_CODE_OVERLAY = By.cssSelector("#app > main > div > div > div._3v-sbJHHUzqYijADRtUbub._1AN6d1WwxLkWvKG1pyaoCx._1Y1ytCHlmsz88II7eZdLrB > div > div > section > section > div");
    private static final By CANCEL_SCAN_CTA = By.cssSelector("div > div > button");
    public static final By APP_FLAVOUR = By.cssSelector("div.flavour__price");
    public static final By CHANGE_PASSWORD_LINK = By.cssSelector("#app > header > div > div.header__right > div._39QdN5bKPGWW6RZNvAfTAa");
    public static final By CURRENT_PASSWORD_FIELD = By.cssSelector("input[name='currentPassword']");
    public static final By NEW_PASSWORD_FIELD = By.cssSelector("input[name='newPassword']");
    public static final By CONFIRM_PASSWORD_FIELD = By.cssSelector("input[name='confirmPassword']");
    public static final By CHANGE_PASSWORD_BUTTON = By.cssSelector("button.btn.btn__primary");
    private ScenarioContext scenarioContext;
    private InStoreSubscriptionServices inStoreSubscriptionServices;

    public IssHomePage(ScenarioContext scenarioContext, InStoreSubscriptionServices inStoreSubscriptionServices) {
        this.scenarioContext = scenarioContext;
        this.inStoreSubscriptionServices = inStoreSubscriptionServices;
    }

    public void selectStoreDropdownButton() {
        waitForExpectedElement(STORE_DROPDOWN).click();
    }

    public List<WebElement> getStoreLists() { return waitForExpectedElements(STORE_LIST_ITEM); }

    public void selectStoreFromDropdown() {
        try {
        waitForExpectedElements(STORE_LIST_ITEM).get(1).click();
        }
            catch (Exception e) {
            waitForElementToAppearAndDisappear(LOADER,3,7);
            waitForExpectedElements(STORE_LIST_ITEM).get(1).click();
        }
    }

    public void enterUsername(String username) { waitForExpectedElement(STAFF_ID_FIELD).sendKeys(username); }

    public void enterPassword(String password) { waitForExpectedElement(PASSWORD_FIELD).sendKeys(password); }

    public void clickSignInCTA() {
        waitForExpectedElement(SIGN_IN_CTA).click();
        waitForElementToAppearAndDisappear(LOADER, 3,3);
    }

    public String getQuoteId() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
            return jsExecutor.executeScript("return localStorage.quote_id").toString().replaceAll("\"","");
    }

    public String getBearerToken() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        return "BEARER " +jsExecutor.executeScript("return localStorage.token").toString().replaceAll("\"","");
    }

    public String getLoginErrorMessage() { return waitForExpectedElement(LOGIN_ERROR_MESSAGE).getText(); }

    public String getLoggedInUsername(String user) { return waitForExpectedElement(LOGGED_IN_USER).getText(); }

    public boolean isLogoVisible() { return webDriver.findElement(LOGO).isDisplayed(); }

    public boolean isSignOutLinkVisible() { return webDriver.findElement(SIGN_OUT_LINK).isDisplayed(); }

    public boolean isOnChallengeScreen() {
        waitForElementToAppearAndDisappear(LOADER,3,3);
        return waitForExpectedElement(CREATE_SUBSCRIPTION_FORM_TEXT).getText().toLowerCase().contains(UrlBuilder.getMessage("challengeText").toLowerCase());
    }

    public void clickOnSignOut() {
        waitForElementToAppearAndDisappear(LOADER, 3, 3);
        waitForExpectedElement(SIGN_OUT_LINK).click(); }

    public boolean pageHeadingIs(String pageHeadingKey) {
        String pageHeading = UrlBuilder.getMessage(pageHeadingKey);
        waitForElementToAppearAndDisappear(LOADER, 3,20);
        waitForExpectedElement(By.cssSelector("h1"));
        return waitForExpectedElement(By.cssSelector("h1")).getText().equalsIgnoreCase(pageHeading);
    }

    public void acceptAgeChallenge() {
        try {
            waitForExpectedElement(AGE_CHALLENGE_ACCEPT).click();
        } catch (Exception e) {
            waitForExpectedElement(AGE_CHALLENGE_ACCEPT, 10);
            clickByElementByQueryJSExecutor(AGE_CHALLENGE_ACCEPT);
        }
    }

    public void clickStartSubscription() { waitForExpectedElement(START_SUBSCRIPTION_BUTTON).click(); }

    public String getStaffIdLabel() { return waitForExpectedElement(STAFF_ID_LABEL).getText(); }

    public boolean isScanCodeCtaDisplayed() {
        return waitForExpectedElement(SCAN_CODE_CTA).isDisplayed();
    }

    public void clicksOnScanCodeCta() {
        waitForExpectedElement(SCAN_CODE_CTA).click();
    }

    public boolean isScanCodeOverlayIsDisplayed() { return waitForExpectedElement(SCAN_CODE_OVERLAY).isDisplayed(); }

    public void clickCancelScan() {
        waitForExpectedElement(CANCEL_SCAN_CTA).click();
    }

    public void clickChangePassword() {
        waitForExpectedElement(CHANGE_PASSWORD_LINK).click();
    }

    public boolean validChangePasswordPage() {
        String expectedPageheading = UrlBuilder.getMessage("changePasswordHeading");
        assertThat(pageHeadingIs("changePasswordHeading"))
                .as("ERROR validChangePasswordPage: invalid page heading expected heading was "+expectedPageheading).isTrue();
        assertThat(waitForExpectedElement(CURRENT_PASSWORD_FIELD).isDisplayed())
                .as("ERROR validChangePasswordPage: current password field not displayed").isTrue();
        assertThat(waitForExpectedElement(NEW_PASSWORD_FIELD).isDisplayed())
                .as("ERROR validChangePasswordPage: new password field not displayed").isTrue();
        assertThat(waitForExpectedElement(CONFIRM_PASSWORD_FIELD).isDisplayed())
                .as("ERROR validChangePasswordPage: confirm password field not displayed").isTrue();
        return true;
    }

    public void enterChangePassword(String passwordType, String passwordStatus) {
        String input = null;
        By passwordField = null;
        switch (passwordStatus.toLowerCase()) {
            case "null":
                input = "";
                break;
            case "spaces":
                input = "   ";
                break;
            case "incorrect":
                input = "incorrect";
                break;
            case "valid":
                input = UrlBuilder.getMessage("loginValidPassword.key");
                break;
            case "correct":
                input = UrlBuilder.getMessage("inStoreSubPassword");
                break;
            default:
                assertThat(true).as("ERROR enterChangePassword: invalid password status of "+passwordStatus+" supplied").isFalse();
        }
        switch (passwordType.toLowerCase()) {
            case "current":
                passwordField = CURRENT_PASSWORD_FIELD;
                break;
            case "new":
                passwordField = NEW_PASSWORD_FIELD;
                break;
            case "confirm":
                passwordField = CONFIRM_PASSWORD_FIELD;
                break;
            default:
                assertThat(true).as("ERROR enterChangePassword: invalid password type of "+passwordType+" supplied").isFalse();
        }
        waitForExpectedElement(passwordField).sendKeys(input);
    }

    public void submitChangePassword() {
        waitForExpectedElement(CHANGE_PASSWORD_BUTTON).click();
    }

    public void validateErrorMessage(String expectedErrorMessage) {
        String actualErrorMessage = getLoginErrorMessage();
        assertThat(expectedErrorMessage.equalsIgnoreCase(actualErrorMessage))
                .as("ERROR validateErrorMessage expected error message was "+expectedErrorMessage+" but got "+actualErrorMessage).isTrue();
    }
}
