package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class InVypeFriendPage extends PageObject {

    private OrderSuccessPage orderSuccessPage;
    public String strReferrerLinkUrl;

    public InVypeFriendPage(OrderSuccessPage orderSuccessPage) {
        this.orderSuccessPage = orderSuccessPage;
    }

    public static final By INVYPE_A_FRIEND_ON_HEADER = By.cssSelector("li[class*='invype-a-friend-public']>a");
    public static final By INVYPE_A_FRIEND_ON_FOOTER = By.cssSelector("ul.footer-menu:nth-child(2) li.userLoggout:nth-child(1) > a:nth-child(1)");
    public static final By VYPE_LOGO = By.cssSelector("a.logo");
    public static final By INVYPE_A_FRIEND_ON_SIDEBAR = By.cssSelector("div#account-nav>ul>li:nth-child(6)>a");
    public static final By ORDER_NUMBER_TEXT = By.cssSelector("div.checkout-success>p:nth-child(1)");
    public static final By EMAIL_CONFIRMATION_TEXT_ON_CONFIRMATION_PAGE = By.cssSelector("div.checkout-success>p:nth-child(2)");
    public static final By REGISTER_TEXT_ON_CONFIRMATION_PAGE = By.cssSelector("a.action.primary.continue>span");
    public static final By WHATSAPP_BUTTON = By.cssSelector("div.icon-container.whatsapp>a");
    public static final By EMAIL_BUTTON = By.cssSelector("div.icon-container.email>a");
    public static final By MY_CUSTOM_CODE = By.cssSelector("div.mgm-code");
    public static final By MY_CUSTOM_LINK = By.cssSelector("div.signup-link>input");
    public static final By LOGOUT_ON_SIDEBAR = By.cssSelector("div#account-nav>ul>li:nth-child(8)>a");
    public static final By INVYPE_A_FRIEND_PRIVATE = By.cssSelector("li[class*='invype-a-friend-private']>a");
    public static final By GENERATE_CODE_BUTTON_GUEST_USER = By.cssSelector("div.mgm-btn>a");
    public static final By ACCOUNT_ICON = By.cssSelector("div.header-vype.row div.column.actions-primary.desktop-only.row:nth-child(6) > div.column:nth-child(1)");
    public static final By USER_NAME_FIELD = By.cssSelector("div.columns>div:nth-child(1)>a");
    public static final By SIGNUP_BUTTON = By.cssSelector("div.block-content div.actions-toolbar:nth-child(2) div.primary > a.action.create.primary");
    public static final By REFERRAL_CODE = By.cssSelector("input#referal_code");
    public static final By REFERAL_CODE_ERROR_MESSAGE = By.cssSelector("p.referal-error");
    public static final By REFEREE_BOX = By.cssSelector("div.referee-box");
    public static final By REFEREE_NAME = By.cssSelector("#referee_name");
    public static final By MOBILE_NUMBER_FELD = By.cssSelector("#mobile");
    public static final By WHATSAPP_BUTTON_TEXT = By.cssSelector("a.icons.whatsapp-icon>span");
    public static final By EMAIL_BUTTON_TEXT = By.cssSelector("a.icons.mail-icon>span");
    public static final By CUSTOM_LINK_COPY_BUTTON = By.cssSelector("#signUp");
    public final static By TELEPHONE_NUMBER = By.cssSelector("div.box.box-address-billing>div>address>a");
    public static final By REQUEST_CODE_BUTTON = By.cssSelector("button.validate-mobile.action.submit");
    public static final By VERIFY_CODE_BUTTON = By.cssSelector("button.verify-code.action.submit");
    public static final By SMS_SENT_MESSAGE = By.cssSelector("#mgm_validate>div");
    public static final By THANKS_FOR_SHARING_NUMBER_MESSAGE = By.cssSelector("div.note");
    public static final By INVALID_ERROR_CODE = By.cssSelector("#code-error");
    public static final By VALIDATE_INPUT_FIELD = By.cssSelector("#code");
    public static final By TELEPHONE_NUMBER_ERROR = By.cssSelector("#mobile-error");
    public static final By VIA_WHATSAPP_CTA=By.cssSelector("a.icon-glo-whatsapp.action.primary");
    public static final By VIA_EMAIL_CTA=By.cssSelector("a.icon-glo-envelope.action.primary");
    public static final By ACTION_BUTTON=By.cssSelector("#action-button");

    public static final By STEPS_TO_INVITE_FRIENDS = By.cssSelector("ol.box-content__ol > li.box-content__li,div.box-title.box-title__li.box-title__li");
    private static final By TEXT_FIELD_REFERRER_LINK = By.cssSelector("div.refer-a-friend-input > input");
    public static final By REFERRER_COPY_LINK = By.cssSelector("div.refer-a-friend-input > a.link-icon,div.refer-a-friend-input .input-text");
    public static final By COPY_EMAIL_LINK = By.cssSelector("div.refer-a-friend-input >input");
    private static final By REFERRER_LINK_ACCESS_ERROR = By.cssSelector("div.message-error.error.message");
    private static final By REFERRER_LINK_ACCESS_SUCCESS = By.cssSelector("div.message-success.success.message");
    private static final By VIEW_BASKET_APPLIED_VOUCHER = By.cssSelector("tr.totals > th > span.title");
    private static final By VIEW_BASKET_APPLIED_DISCOUNT = By.cssSelector("tr.totals:nth-child(2) > td.amount span.price");
    private static final By CART_APPLIED_VOUCHER = By.cssSelector("span.label.discount_title");
    private static final By CART_APPLIED_DISCOUNT = By.cssSelector("span.price-wrapper.discount_value_formatted");
    private static final By VIEW_BASKET_APPLIED_VOUCHER_VUSEDE = By.cssSelector("tr.total-rules.amasty-rules>th>span");
    private static final By VIEW_BASKET_APPLIED_DISCOUNT_VUSEDE = By.cssSelector("tr.total-rules.amasty-rules>td>span");
    private static final By SHOP_NOW_VUSEDE = By.cssSelector("a[href='e-zigaretten/']");
    private static final By ADD_MORE_ITEM_VUSEDE = By.cssSelector("div.control.qty>input:nth-child(3)");
    private static final By ITEMS_PRICE_VUSEDE = By.cssSelector("a.action.showcart>span:nth-child(2)>span");
    private static final By TEXT_FIELD_REFERRER_LINK_VELO = By.cssSelector("div.tell-a-friend-input > input");
    public static final By REFERRER_COPY_LINK_VELO = By.cssSelector("div.tell-a-friend-input > a");
    public static final By COPY_EMAIL_LINK_VELO = By.cssSelector("div.contact-icons > a.mail-icon");
    public static final By COPY_WHATSAPP_LINK_VELO = By.cssSelector("div.contact-icons > a.mail-icon");
    public static final By COPY_PAGE_HEADING_VELO = By.cssSelector(" div.block.block-tellafriend > div > div > strong > div");
    private static final By TEXT_FIELD_REFERRER_LINK_ZA = By.cssSelector("div.invite-friend-referral-link > div.invite-a-friend-input > input");

    public void assertInvypeAFriendLinkIsDisplayed() {
        assertTrue(waitForExpectedElement(INVYPE_A_FRIEND_ON_HEADER, 10).isDisplayed());
        assertTrue(waitForExpectedElement(INVYPE_A_FRIEND_ON_FOOTER, 10).isDisplayed());
    }

    public void assertMGMPublicLandingPageIsOpened(String pageTitle) {
        assertPageTitle(INVYPE_A_FRIEND_ON_HEADER, pageTitle);
        waitForExpectedElement(VYPE_LOGO, 10).click();
        assertPageTitle(INVYPE_A_FRIEND_ON_FOOTER, pageTitle);
    }

    public void assertPageTitle(By webElement, String pageTitle) {
        waitForExpectedElement(webElement, 10).click();
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        assertTrue(getCurrentPageTitle().toUpperCase().contains(UrlBuilder.getMessage(pageTitle).toUpperCase()));
    }

    public void assertTextIsDisplayedAtOrderConfirmation(String orderNumber, String emailConfirmation, String register) {
        assertTextOfWebElement(ORDER_NUMBER_TEXT, orderNumber);
        assertTextOfWebElement(EMAIL_CONFIRMATION_TEXT_ON_CONFIRMATION_PAGE, emailConfirmation);
        assertTextOfWebElement(REGISTER_TEXT_ON_CONFIRMATION_PAGE, register);
    }

    public void assertTextForWhatsappAndEmailButton(String whatsapp, String email) {
        assertTextOfWebElement(WHATSAPP_BUTTON_TEXT, whatsapp);
        assertTextOfWebElement(EMAIL_BUTTON_TEXT, email);
    }

    public void assertReferErrorMessageText(String message) {
        assertTextOfWebElement(REFERAL_CODE_ERROR_MESSAGE, message);
    }

    public void assertTextOfWebElement(By by, String expected) {
        waitForExpectedElement(by, 10);
        LOG.info("actual is " + getTextFor(by));
        LOG.info("expected is " + UrlBuilder.getMessage(expected).trim());
        assertTrue(getTextFor(by).contains(UrlBuilder.getMessage(expected).trim()));
    }

    public void navigateToInvypeFromLoggedinUser() {
        waitForExpectedElement(VYPE_LOGO, 10).click();
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        waitForExpectedElement(INVYPE_A_FRIEND_PRIVATE, 10).click();
    }

    public void assertPrivateLandingPageIsOpened() {
        assertTrue(waitForExpectedElement(WHATSAPP_BUTTON, 10).isDisplayed());
    }

    public void assertGenerateMyCodeIsDisplayed() {
        assertTrue(waitForExpectedElement(GENERATE_CODE_BUTTON_GUEST_USER, 10).isDisplayed());
    }

    public void assertLoginPageIsOpened(String pageTitle) {
        assertPageTitle(GENERATE_CODE_BUTTON_GUEST_USER, pageTitle);
    }

    public void assertOptionsOnMGMPrivateLandingPage() {
        assertTrue(waitForExpectedElement(EMAIL_BUTTON, 10).isDisplayed());
        assertTrue(waitForExpectedElement(MY_CUSTOM_CODE, 10).isDisplayed());
        assertTrue(waitForExpectedElement(MY_CUSTOM_LINK, 10).isDisplayed());
    }

    public void assertwhatsAppPageShareisOpened() {
        waitForExpectedElement(WHATSAPP_BUTTON, 10).click();
        getBrowserByPageTitle("Share on Whatsapp");
    }

    public void fetchUserNameandCustomizedLink() {
        String userName = waitForExpectedElement(USER_NAME_FIELD, 10).getText().trim();
        System.setProperty("InVypeFriendUserName.key", userName);
        waitForExpectedElement(INVYPE_A_FRIEND_ON_SIDEBAR, 10).click();
        waitForExpectedElement(CUSTOM_LINK_COPY_BUTTON, 10).click();
        String strCopiedURL = waitForExpectedElement(By.cssSelector("input#signupUrl")).getAttribute("value");
        System.setProperty("customizedURL.key", strCopiedURL);
        waitForExpectedElement(LOGOUT_ON_SIDEBAR, 10).click();
    }

    public void assertRefreeNameOnSignUpPage() {
        getWebDriver().navigate().to(System.getProperty("customizedURL.key"));
        assertEquals(getValue(REFEREE_NAME), System.getProperty("InVypeFriendUserName.key"));
    }

    public void assertErrorMesageIsDisplayed() {
        waitForExpectedElement(REFERRAL_CODE, 10).sendKeys("HDDDD");
        assertTrue(waitForExpectedElement(REFERAL_CODE_ERROR_MESSAGE, 10).isDisplayed());
    }

    public void assertReferCodeOptionIsDisplayed() {
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        waitForExpectedElement(ACCOUNT_ICON, 10).click();
        waitForExpectedElement(SIGNUP_BUTTON, 10).click();
        assertTrue(waitForExpectedElement(REFERRAL_CODE, 10).isDisplayed());
        assertTrue(waitForExpectedElement(REFEREE_BOX, 10).isDisplayed());
    }

    public void fetchTelephoneNumberAndEnterDuplicateValue() {
        String telephoneNumber = getTextFor(TELEPHONE_NUMBER);
        waitForExpectedElement(INVYPE_A_FRIEND_PRIVATE, 10).click();
        enterDataAndClickOnButton(MOBILE_NUMBER_FELD, REQUEST_CODE_BUTTON, SMS_SENT_MESSAGE, telephoneNumber);
    }

    public void enterDataAndClickOnButton(By inputField, By buttonToClick, By elementToVerify, String strEnterData) {
        try {
            waitForExpectedElement(inputField, 10).clear();
            waitForExpectedElement(inputField, 10).sendKeys(strEnterData);
            waitForExpectedElement(buttonToClick, 10).click();
            assertTrue(waitForExpectedElement(elementToVerify, 10).isDisplayed());
        } catch (Exception ex) {
            LOG.info("Failed to enter data in text-field due to error: " + ex.getMessage());
        }
    }

    public void verifyRequestCodeAndVerifyCodeButton() {
        assertTrue(waitForExpectedElement(REQUEST_CODE_BUTTON, 10).isDisplayed());
        assertTrue(waitForExpectedElement(VERIFY_CODE_BUTTON, 10).isDisplayed());
    }

    public void verifyMessageForTelephoneNumber() {
        assertTrue(waitForExpectedElement(THANKS_FOR_SHARING_NUMBER_MESSAGE, 10).isDisplayed());
        enterDataAndClickOnButton(VALIDATE_INPUT_FIELD, VERIFY_CODE_BUTTON, INVALID_ERROR_CODE, "2222");
        enterDataAndClickOnButton(MOBILE_NUMBER_FELD, REQUEST_CODE_BUTTON, TELEPHONE_NUMBER_ERROR, "345678");
    }

    public void assertReferrerLinkAppendedWithVoucherCode(String strExpectedLink) {
        String strReferrerLink = "";
        String[] strVoucherCode;
        switch (UrlBuilder.getLocale()) {
            case "velode":
                strReferrerLink = waitForExpectedElement(TEXT_FIELD_REFERRER_LINK_VELO).getAttribute("value");
            case "vuseza":
                strReferrerLink = waitForExpectedElement(TEXT_FIELD_REFERRER_LINK_ZA).getAttribute("value");
                break;
            default:
                strReferrerLink = waitForExpectedElement(TEXT_FIELD_REFERRER_LINK).getAttribute("value");
        }
        if (!strReferrerLink.isEmpty()) {
            System.setProperty("referrerLink.key", strReferrerLink);
            assertTrue(strReferrerLink.contains(UrlBuilder.getMessage(strExpectedLink)));
        }
        switch (UrlBuilder.getLocale()) {
            case "vusede":
                strVoucherCode = strReferrerLink.split("voucherCode=");
                if (strVoucherCode.length != 0)
                    System.setProperty("referFriendVoucherCode.key", strVoucherCode[1]);
                break;
            case "vuseza":
                strVoucherCode = strReferrerLink.split("code/");
                if (strVoucherCode.length != 0)
                    System.setProperty("referFriendVoucherCode.key", strVoucherCode[1]);
                break;
            default:
        }
    }

    public void assertErrorMessageWhenUserTriesToAccessReferrerLink() {
        getWebDriver().navigate().to(System.getProperty("referrerLink.key"));
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        assertTrue(waitForExpectedElement(REFERRER_LINK_ACCESS_ERROR, 10).getText().equals(UrlBuilder.getMessage("errorRedeemVoucherMessage.key")));
    }

    public void assertSuccessfulMessageWhenNewUserAccessesReferrerLink() {
        waitForAjaxElementNotToBePresent(getWebDriver(), 5);
        getWebDriver().navigate().to(System.getProperty("referrerLink.key"));
        if (!UrlBuilder.getLocale().equals("vuseza")) {
            waitForAjaxElementNotToBePresent(getWebDriver(), 5);
            assertTrue(waitForExpectedElement(REFERRER_LINK_ACCESS_SUCCESS, 10).getText().equals(UrlBuilder.getMessage("successRedeemVoucherMessage.key")));
        }
    }

    public void assertDiscountAppliedInBasketUsingRedeemedVoucherCode() {
        if (UrlBuilder.getLocale().equalsIgnoreCase("vusede")) {
            waitForAjaxElementNotToBePresent(getWebDriver(), 5);
            if (UrlBuilder.getLocale().equalsIgnoreCase("vusede")) {
                Float price;
                int i = 4;
                do {
                    waitForAjaxElementNotToBePresent(getWebDriver(), 10);
                    clickByElementByQueryJSExecutor(BasketPage.QTY_DROPDOWN_SELECT_BASKET_PAGE);
                    clickByElementByQueryJSExecutor(BasketPage.QTY_DROPDOWN_MORE_VALUE_BASKET_PGE);
                    waitForExpectedElement(BasketPage.INPUT_QTY_MINI_BASKET,10).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
                    waitForExpectedElement(BasketPage.INPUT_QTY_MINI_BASKET).sendKeys(Integer.toString(i));
                    waitForExpectedElement(BasketPage.INPUT_QTY_MINI_BASKET).sendKeys(Keys.ENTER);
                    waitForAjaxElementNotToBePresent(getWebDriver(), 10);
                    price = (Float.valueOf(waitForExpectedElement(ITEMS_PRICE_VUSEDE, 10).getText().split(" ")[0].replace(",", ".")));
                    i++;
                }
                while (price < 20.0);
            }
            waitForAjaxElementNotToBePresent(getWebDriver(), 10);
            String strVoucherCode = waitForExpectedElement(VIEW_BASKET_APPLIED_VOUCHER_VUSEDE, 10).getText();
            assertTrue(strVoucherCode.equalsIgnoreCase(UrlBuilder.getMessage("referFriendVoucherCode.key")));
            Float intDiscountPrice = (Float.valueOf(waitForExpectedElement(VIEW_BASKET_APPLIED_DISCOUNT_VUSEDE, 10).getText().split(" ")[0].replace(",", ".")));
            assertTrue(Math.signum(intDiscountPrice) == -1);
        } else {
            waitForAjaxElementNotToBePresent(getWebDriver(), 5);
            assertTrue(waitForExpectedElement(VIEW_BASKET_APPLIED_VOUCHER_VUSEDE, 10).getText().contains(UrlBuilder.getMessage("referFriendVoucherCode.key")));
            Float intDiscountPrice = (Float.valueOf(waitForExpectedElement(VIEW_BASKET_APPLIED_DISCOUNT_VUSEDE, 10).getText().split(" ")[0].replace(",", ".")));
            assertTrue(Math.signum(intDiscountPrice) == -1);
        }
    }

    public void assertDiscountAppliedInCartIsNotDisplayed() {
        assertFalse(getWebDriver().findElements(CART_APPLIED_VOUCHER).size() > 0);
    }

    public void assertDiscountAppliedInBasketIsNotDisplayed() {
        waitForAjaxElementNotToBePresent(getWebDriver(), 5);
        assertFalse(getWebDriver().findElements(VIEW_BASKET_APPLIED_VOUCHER).size() > 0);
    }

    public void userClicksOnShopButton() {
        waitForExpectedElement(SHOP_NOW_VUSEDE, 5).click();
    }

    public boolean isWhatsappCTADisplayed(){
        return waitForExpectedElement(VIA_WHATSAPP_CTA).isDisplayed();
    }

    public boolean isEmailCTADisplayed(){
        try {
            return waitForExpectedElement(VIA_EMAIL_CTA).isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public String getWhatsappCTAText(){
        return waitForExpectedElement(VIA_WHATSAPP_CTA).getAttribute("innerHTML").trim();
    }

    public String getEmailCTAText(){
        return waitForExpectedElement(VIA_EMAIL_CTA).getAttribute("innerHTML").trim();
    }

    public void getReferrerLink() {
        switch (UrlBuilder.getLocale()) {
            case "velode":
                strReferrerLinkUrl = waitForExpectedElement(TEXT_FIELD_REFERRER_LINK_VELO).getAttribute("value");
                break;
            case "vuseza":
                strReferrerLinkUrl = waitForExpectedElement(TEXT_FIELD_REFERRER_LINK_ZA).getAttribute("value");
                break;
            default:
                strReferrerLinkUrl = waitForExpectedElement(TEXT_FIELD_REFERRER_LINK).getAttribute("value");
        }
    }

    public void accessesReferrerLinkUrl() {
        waitForAjaxElementNotToBePresent(getWebDriver(), 5);
        getWebDriver().navigate().to(strReferrerLinkUrl);
    }

    public boolean invitationPageIsDisplayed() {
        waitForAjaxElementNotToBePresent(getWebDriver(), 5);
        return getWebDriver().getTitle().equals(UrlBuilder.getMessage("invitationPageTitle.key"));
    }

    public void clickOnViaWhatsAppButton(){
        waitForExpectedElement(VIA_WHATSAPP_CTA).click();
    }
}
