package com.salmon.test.page_objects.gui.newsLetter;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.page_objects.gui.RegistrationPage;
import com.salmon.test.page_objects.gui.constants.Locale;
import com.salmon.test.page_objects.gui.constants.Site;
import com.salmon.test.page_objects.gui.models.NewsLetterPageModel;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import java.lang.invoke.SwitchPoint;

import static com.salmon.test.enums.PermittedCharacters.ALPHABETS;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;
import static org.testng.Assert.assertTrue;

public abstract class NewsLetterPage extends PageObject {

    By NEWSLETTER_HREF = By.cssSelector("a[href*='newsletter'],button.action.subscribe.primary");
    By NEWSLETTER_FIRSTNAME = By.cssSelector("#firstname");
    By NEWSLETTER_SURNAME = By.cssSelector("#lastname");
    By SUBSCRIBE_BUTTON = By.cssSelector("div.actions > button.action.subscribe.primary");
    private final static By NEWSLETTER_CLOSE_BUTTON = By.cssSelector(" div.os-content header.modal-header > button.action-close");
    private final static By NEWSLETTER_EMAIL_FIELD = By.cssSelector("input.validate-required-email-newsletter");
    private final static By NEWSLETTER_EMAIL_FIELD_IT = By.cssSelector("input#newsletter");
    public final static By NEWSLETTER_ERROR_MSSG= By.cssSelector("div#newsletter_error");
    public final static By EMAIL_CONSENT_CHK_BOX = By.cssSelector("input#consent_email_marketing");
    public final static By EMAIL_CONSENT_CHK_BOX_IT = By.cssSelector("input#email_marketing");
    public final static By btnNewsLetterSubscribeUK = By.cssSelector("button.action.subscribe.primary > span");
    public static final By MOBILE_SOCIAL_CONSENT_CHK_BOX = By.cssSelector("input#consent_mobile");
    public static final By MOBILE_SOCIAL_CONSENT_CHK_BOX_IT= By.cssSelector("[name=mob_marketing]");
    public static final By MOBILE_SOCIAL_NEWSLETTER_CHK_BOX_IT= By.cssSelector("#mobile_consent");
    private static final By SUBSCRIPTION_SAVE_BTN = By.cssSelector("button.action.save.primary");
    private static final By SUBSCRIPTION_SAVE_BTN_IT = By.cssSelector("button#subscribe_news");
    private static final By NEWSLETTER_EMAIL_TICKBOX = By.cssSelector("input#email_marketing");
    private static final By NEWSLETTER_SIGNUP_BUTTON = By.cssSelector("[id=subscribe_news] > span");
    public static final By PHONE_NUMBER_FIELD_ERROR = By.cssSelector("#telephone-error");
    public static final By PHONE_NUMBER_TXT_BOX = By.cssSelector("input#telephone");
    private final static By CREATEACCOUNT_BUTTON = By.cssSelector("button.action.submit.primary,button.submit");
    private final static By NEWSLETTER_SUBSCRIPTION_MESSAGE = By.cssSelector("div.page.messages > div:nth-child(2) > div > div > div");
    public final static By elePageHeading = By.cssSelector("span.base");
    public final static By CONSENT_ALL = By.cssSelector("input#consent_select_all");
    public final static By THIRD_PARTY_CONSENT_CHK_BOX_GLOPL = By.cssSelector("input#consent_third_parties");
    public final static By EMAIL_CONSENT_CHK_BOX_UK = By.cssSelector("input#newsletter");
    public static final By MOBILE_SOCIAL_CONSENT_CHK_BOX_UK = By.cssSelector("input#telephone");
    public static final By NEWSLETTER_SMS_TICKBOX = By.cssSelector("input#mob_marketing");
    public static final By NEWSLETTER_SMS_VUSEUK = By.cssSelector("div.marketing-method.marketing-method--last input");
    public static final By NEWSLETTER_EMAIL_VUSEUK = By.cssSelector("input[name='consent_email_marketing']");
    public static final By NEWSLETTER_ON_MYACCOUNT = By.cssSelector("#account-nav ul li:nth-child(5) a");
    public static final By NEWSLETTER_ON_MYACCOUNT_VUSECO = By.cssSelector("#account-nav ul li:nth-child(8) a");
    public final static By NEWSLETTER_DOB_FIELD = By.cssSelector("input#newsletter_dob");
    public final static By NEWSLETTER_DOB_ERROR = By.cssSelector("div#newsletter_dob-error");
    private static final By SIGN_UP_BTN_VUSECO = By.cssSelector(".btn.btn--small.action.subscribe.primary");
    private static final By SIGN_UP_BTN_VELOZA = By.cssSelector("bat-cta-default > div > button");
    private static final By SIGN_UP_CLOSE_VELOZA = By.cssSelector("#news-letter-signup > div > button");
    private static final By NEWSLETTER_POP_UP = By.cssSelector(".newsletter-popup");
    private static final By NEWSLETTER_CHK_BOX = By.cssSelector("[name=policies_marketing]");
    private static final By EMAIL_LABEL = By.cssSelector("p.account-info__email");
    private static final By COLSE_POPUP = By.cssSelector("button#close_popup");
    public static final By EMAIL_SMS_CONSENT_ZA = By.cssSelector("input#subscription");
    private static final By SAVE_CONSENT_ZA = By.cssSelector("#form-validate > fieldset > div.actions-toolbar > div > button");
    private final static By NEWSLETTER_MOBILE_FIELD_IT = By.cssSelector("#mobilenumber");
    private static final By NEWSLETTER_AGREE_CHK_BOX = By.cssSelector("div.newsletter-agree.required > div > label");
    public final static By NEWSLETTER_SUCCESS_BLOCK = By.cssSelector("#modal-content-1 > div > div");
    public final static By NEWSLETTER_SUCCESS_MESSAGE = By.cssSelector("#success-block > div.message.success");
    private final static By NEWSLETTER_SUBMIT = By.cssSelector("#form-validate > fieldset > div.actions-toolbar > div > button");
    private final static By NEWSLETTER_CHK_BOX_DE = By.cssSelector("#form-validate > fieldset > div.field.choice > label");
    public final static By NEWSLETTER_SUBSCRIBE = By.cssSelector("#newsletter-validate-detail > div.actions > button");
    private static final By NEWSLETTER_FIRSTNAME_ERROR = By.cssSelector("#firstname-error");
    private static final By NEWSLETTER_LASTNAME_ERROR = By.cssSelector("#lastname-error");
    private static final By NEWSLETTER_EMAIL_ERROR = By.cssSelector("#newsletter-error");
    private static final By NEWSLETTER_BANKID_ERROR = By.cssSelector("#bank_id-error");



    public static String email;

    public abstract void completeFormAndSubmit(NewsLetterPageModel newsLetterPageModel);

    public void submitEmptyForm() {
        waitForExpectedElement(NEWSLETTER_FIRSTNAME).clear();
        waitForExpectedElement(NEWSLETTER_SURNAME).clear();
        waitForExpectedElement(SUBSCRIBE_BUTTON).click();
    }

    public String getErrorMessageDisplayedFor(String fieldUnderTest) {
        By by = By.cssSelector("div[for=\"" + fieldUnderTest + "\"]");
        scrollElementIntoView(by);
        return getTextFor(by);
    }

    public boolean isNewsLetterSignUpLinkOrButtonDisplayed() {
        return getWebDriver().findElement(NEWSLETTER_HREF).isDisplayed();
    }

    public static NewsLetterPage getInstance() {
        NewsLetterPage newsLetterPage;

        switch (Site.valueOf(UrlBuilder.getSite().toUpperCase())) {
            case GLO:
            case VELO:
            case VUSE:
                newsLetterPage = new NewslettersPage();
                break;
            case LYFT:
                newsLetterPage = new NewsLetterPageLyft();
                break;
            case VYPE:
                newsLetterPage = new NewsLetterPageVype();
                break;
            default:
                throw new IllegalStateException("Unexpected Site: " + Site.valueOf(UrlBuilder.getSite().toUpperCase()));
        }

        return newsLetterPage;
    }

    public void closeNewsLetterPopup(){
        waitForExpectedElement(NEWSLETTER_CLOSE_BUTTON,10).click();
    }

    public void assertCountryCodeisDisplayedAsPrefixInRegistrationPage(){
        waitForExpectedElement(PHONE_NUMBER_TXT_BOX,10).sendKeys("1234567899");
        switch (UrlBuilder.getLocale()) {
            case "vuseit":
            case "vuseza":
                clickByElementByQueryJSExecutor(CREATEACCOUNT_BUTTON);
                break;
            default:
                waitForExpectedElement(CREATEACCOUNT_BUTTON,10).click();
        }
    }

    public void assertEmailIsDisplayedInNewsLetterPopup(String emailID){
        clickByElementByQueryJSExecutor(btnNewsLetterSubscribeUK);
        assertTrueExpectedTextContainsActualTextWithCustomError(emailID,getValue(NEWSLETTER_EMAIL_FIELD));
    }

    public void userSelectMarketingPreferenceConsentOption(){
        if (waitForExpectedElement(EMAIL_CONSENT_CHK_BOX,10).isSelected()) {
            clickUsingJS(EMAIL_CONSENT_CHK_BOX);
            clickUsingJS(SUBSCRIPTION_SAVE_BTN);
            waitForExpectedElement(By.linkText(UrlBuilder.getMessage("newsLetterText.key")), 10);
            clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("newsLetterText.key")));
            waitForExpectedElement(elePageHeading, 30);
        }
        if (!waitForExpectedElement(EMAIL_CONSENT_CHK_BOX,10).isSelected()) {
            clickUsingJS(EMAIL_CONSENT_CHK_BOX);
        }
        if (!waitForExpectedElement(MOBILE_SOCIAL_CONSENT_CHK_BOX,10).isSelected()) {
            clickUsingJS(MOBILE_SOCIAL_CONSENT_CHK_BOX);
        }
        clickUsingJS(SUBSCRIPTION_SAVE_BTN);
    }

    public void userSelectMarketingPreferenceConsentOptionForIT(){
        if (waitForExpectedElement(EMAIL_CONSENT_CHK_BOX_IT,10).isSelected()) {
            clickUsingJS(EMAIL_CONSENT_CHK_BOX_IT);
            clickUsingJS(SUBSCRIPTION_SAVE_BTN_IT);
            waitForExpectedElement(By.linkText(UrlBuilder.getMessage("newsLetterText.key")), 10);
            clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("newsLetterText.key")));
            waitForExpectedElement(elePageHeading, 30);
        }
        if (!waitForExpectedElement(EMAIL_CONSENT_CHK_BOX_IT,10).isSelected()) {
            clickUsingJS(EMAIL_CONSENT_CHK_BOX_IT);
        }
        if (!waitForExpectedElement(MOBILE_SOCIAL_CONSENT_CHK_BOX_IT,10).isSelected()) {
            clickUsingJS(MOBILE_SOCIAL_CONSENT_CHK_BOX_IT);
        }
    }

    public void userSelectMarketingPreferenceConsent(){
        if(UrlBuilder.getLocale().equals("vuseit")){
            userSelectMarketingPreferenceConsentOptionForIT();
        }
        else if(UrlBuilder.getLocale().equals("vuseza")){
            clickUsingJS(EMAIL_SMS_CONSENT_ZA);
            clickUsingJS(SAVE_CONSENT_ZA);
        }
        else
            userSelectMarketingPreferenceConsentOption();
    }

    public void assertErrorMessageOnNewsLetterSignup(){
        clickUsingJS(NEWSLETTER_EMAIL_TICKBOX);
        clickUsingJS(NEWSLETTER_SIGNUP_BUTTON);
        waitForAjaxElementNotToBePresent(getWebDriver(),10);
    }
    public void assertMessageAboutNewsLetterSubscription() {
        waitForExpectedElement(NEWSLETTER_SUBSCRIPTION_MESSAGE);
        assertTrue(waitForExpectedElement(NEWSLETTER_SUBSCRIPTION_MESSAGE, 10).isDisplayed());
    }

    public boolean isNewsLetterPopUpDisplayed() {
        return waitForExpectedElement(NEWSLETTER_POP_UP, 10).isDisplayed();
    }

    public void clickOnNewsletterSignUpButton() {
        switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
            case VUSECO:
                waitForExpectedElement(SIGN_UP_BTN_VUSECO).click();
                switchBetweenWindowTabs(1);
                break;
            case VELOZA:
                waitForAjaxElementNotToBePresent(webDriver,3);
                scrollToPageBottom();
                waitForExpectedElement(SIGN_UP_BTN_VELOZA).click();
                waitForExpectedElement(SIGN_UP_CLOSE_VELOZA).click();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + Locale.valueOf(UrlBuilder.getLocale().toUpperCase()));
        }
    }

    public void fillSubscriptionFeilds(){
        enterDataAndWait(NEWSLETTER_EMAIL_FIELD_IT,email);
        clickUsingJS(NEWSLETTER_CHK_BOX);
        enterDataAndWait(NEWSLETTER_DOB_FIELD,"20/03/1992");
        Actions action = new Actions(getWebDriver());
        action.sendKeys(Keys.ENTER).build().perform();
        clickUsingJS(SUBSCRIPTION_SAVE_BTN_IT);
        clickUsingJS(COLSE_POPUP);

    }

    public void getEmailAddress(){
         email = getTextFor(EMAIL_LABEL);
    }

    public void userEnterDetailsOnNewsletterAndSignup() {
        String emailAddressData = RandomGenerator.randomEmailAddress(6);
        String mobilenumber = UrlBuilder.getMessage("phoneNumberWOCountryCode.key");
        enterDataAndWait(NEWSLETTER_EMAIL_FIELD_IT,emailAddressData);
        enterDataAndWait(NEWSLETTER_MOBILE_FIELD_IT,mobilenumber);
        clickUsingJS(NEWSLETTER_AGREE_CHK_BOX);
        waitForItemToBeClickableAndClick(NEWSLETTER_SIGNUP_BUTTON,5);
    }

    public void userEnterDetailsOnNewsletterAndSignupGuestUser() {
        String firstNameData = random(6, ALPHABETS);
        String lastNameData = random(6, ALPHABETS);
        String emailAddressData = RandomGenerator.randomEmailAddress(6);
        String mobilenumber = UrlBuilder.getMessage("phoneNumberWOCountryCode.key");
        enterDataAndWait(NEWSLETTER_FIRSTNAME,firstNameData);
        enterDataAndWait(NEWSLETTER_SURNAME,lastNameData);
        enterDataAndWait(NEWSLETTER_EMAIL_FIELD_IT,emailAddressData);
        enterDataAndWait(NEWSLETTER_MOBILE_FIELD_IT,mobilenumber);
        clickUsingJS(NEWSLETTER_AGREE_CHK_BOX);
        waitForItemToBeClickableAndClick(NEWSLETTER_SIGNUP_BUTTON,5);
    }

    public void selectNewsletterSubscriptionAndSubmit() {
        waitForItemToBeClickableAndClick(NEWSLETTER_CHK_BOX_DE,4);
        waitForItemToBeClickableAndClick(NEWSLETTER_SUBMIT,2);
    }

    public void assertErrorMessageForNewsletterWithoutAnyDetailsFilled() {
        //verify error on every mandatory field
        assertTrue(isElementDisplayedBySeconds(NEWSLETTER_FIRSTNAME_ERROR,5));
        assertTrue(isElementDisplayedBySeconds(NEWSLETTER_LASTNAME_ERROR,5));
        assertTrue(isElementDisplayedBySeconds(NEWSLETTER_EMAIL_ERROR,5));
        assertTrue(isElementDisplayedBySeconds(NEWSLETTER_DOB_ERROR,5));
        assertTrue(isElementDisplayedBySeconds(NEWSLETTER_BANKID_ERROR,5));
    }
}
