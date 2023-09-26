package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.AssertJUnit.assertTrue;

public class InVypeFriendPageSteps {

    private InVypeFriendPage invypePage;
    private LogininPage loginPage;
    private OrderSuccessPage orderSuccessPage;

    public InVypeFriendPageSteps(LogininPage loginPage,InVypeFriendPage invypePage,OrderSuccessPage orderSuccessPage)
    {
        this.invypePage = invypePage;
        this.loginPage = loginPage;
        this.orderSuccessPage=orderSuccessPage;
    }

    @And("^assert InVype a friend link is displayed on header and footer$")
    public void verifyInvypeAFriendOnHeaderAndFooterIsDisplayed() {
        invypePage.assertInvypeAFriendLinkIsDisplayed();
    }

    @And("^assert '(.*)' is opened if user clicks on the InVypeLnk$")
    public void assertPageisOpened(String pageTitle) {
        invypePage.assertMGMPublicLandingPageIsOpened(pageTitle);
    }

    @And("^assert '(.*)' '(.*)' '(.*)' text is displayed$")
    public void assertTextIsDisplayed(String register, String orderNumber, String emailConfirmation) {
        invypePage.assertTextIsDisplayedAtOrderConfirmation(register, orderNumber, emailConfirmation);
    }

    @And("assert text for '(.*)' '(.*)' is displayed$")
    public void assertTextForWhatsappAndEmailButtonIsDisplayed(String whatsapp, String Email) {
        invypePage.assertTextForWhatsappAndEmailButton(whatsapp, Email);
    }

    @When("^Logged in user navigate to InVype a friend link$")
    public void loggedInUserNavigateToInVypeLink() {
        invypePage.navigateToInvypeFromLoggedinUser();
    }

    @Then("^user navigate to MGM private landing page$")
    public void userNavigateToMGMPrivateLandingPage() {
        invypePage.assertPrivateLandingPageIsOpened();
    }

    @And("^assert Generate My Code Option is displayed$")
    public void assertGenerateMyCodeButton() {
        invypePage.assertGenerateMyCodeIsDisplayed();
    }

    @And("^verify guest user navigate to '(.*)' if action perform on Generate My Code button$")
    public void clickOnButtonAndVerifyLogInPageIOpened(String pageTitle) {
        invypePage.assertLoginPageIsOpened(pageTitle);
    }

    @When("^user signs in with credentials '(.*)' '(.*)'$")
    public void clickOnButtonAndVerifyLogInPageIOpened(String userName, String passWord) {
        loginPage.loginWithConfigProperties(userName, passWord);
    }

    @And("^assert Whatsapp button and email option is displayed$")
    public void clickOnButtonAndVerifyLogInPageIOpened() {
        invypePage.assertOptionsOnMGMPrivateLandingPage();
    }

    @And("^assert Whatsapp page is Opened$")
    public void assertWatsAppPageIOpened() {
        invypePage.assertwhatsAppPageShareisOpened();
    }

    @And("^user fetch Referee name and customized link$")
    public void fetchUserNameAndLink() {
        invypePage.fetchUserNameandCustomizedLink();
    }

    @And("^assert Refer code option is displayed for guest user on Sign Up page$")
    public void referCodeOptionIsDisplayed() {
        invypePage.assertReferCodeOptionIsDisplayed();
    }

    @And("^assert Referee name is displayed when user navigate to copied link$")
    public void refereeNameIsDisplayed() {
        invypePage.assertRefreeNameOnSignUpPage();
    }

    @And("^assert Invalid code error message is displayed$")
    public void errorMesageIsDisplayed() {
        invypePage.assertErrorMesageIsDisplayed();
    }

    @And("^assert text of error message should be '(.*)'$")
    public void assertErrorMessageText(String message) {
        invypePage.assertReferErrorMessageText(message);
    }

    @Then("^user fetch the telephone number and enter duplicate value$")
    public void fetchTelephoneNumber() {
        invypePage.fetchTelephoneNumberAndEnterDuplicateValue();
    }

    @And("^verify the request code button and validate code button is available$")
    public void verifyRequestCodeAndValidateCodeButtonIsAvailable() {
        invypePage.verifyRequestCodeAndVerifyCodeButton();
    }

    @And("^verify messages when user enter the telephone number$")
    public void verifyMessageForTelephoneNumber() {
        invypePage.verifyMessageForTelephoneNumber();
    }

    @And("^assert steps followed to invite a friend$")
    public void assertInviteFriendStepsToFollow() {
        switch (UrlBuilder.getLocale()) {
            case "glode":
                assertTrue(invypePage.getWebDriver().findElements(invypePage.STEPS_TO_INVITE_FRIENDS).size()==3);
                break;
            default:
            assertTrue(invypePage.getWebDriver().findElements(invypePage.STEPS_TO_INVITE_FRIENDS).size() == 3);
        }
    }

    @And("^assert referrer link appended with voucher code '(.*)' in text field$")
    public void assertReferrerLinkAppendedWithVoucherCode(String strExpectedLink) {
        invypePage.assertReferrerLinkAppendedWithVoucherCode(strExpectedLink);
    }

    @And("^assert CTA for Copy link and Email Link on the page$")
    public void assertCTAForCopyAndEmailLinks() {
        switch (UrlBuilder.getLocale()) {
            case "velode":
                assertTrue(invypePage.waitForExpectedElement(invypePage.REFERRER_COPY_LINK_VELO).isDisplayed());
                assertTrue(invypePage.waitForExpectedElement(invypePage.COPY_EMAIL_LINK_VELO).isDisplayed());
                assertTrue(invypePage.waitForExpectedElement(invypePage.COPY_WHATSAPP_LINK_VELO).isDisplayed());
                assertTrue(invypePage.waitForExpectedElement(invypePage.COPY_PAGE_HEADING_VELO).isDisplayed());
                break;
            default:
                assertTrue(invypePage.waitForExpectedElement(invypePage.REFERRER_COPY_LINK).isDisplayed());
                assertTrue(invypePage.waitForExpectedElement(invypePage.COPY_EMAIL_LINK).isDisplayed());
        }
    }

    @And("^assert error message when logged-in user tries to access the referrer link$")
    public void assertErrorMessageWhenUserTriesToAccessReferrerLink() {
        invypePage.assertErrorMessageWhenUserTriesToAccessReferrerLink();
    }

    @And("^new user accesses the referrer link and successful message is displayed$")
    public void assertSuccessfulMessageWhenNewUserAccessesReferrerLink() {
        invypePage.assertSuccessfulMessageWhenNewUserAccessesReferrerLink();
    }

    @And("^assert discount applied in basket using the redeemed voucher code$")
    public void assertDiscountAppliedInBasketUsingRedeemedVoucherCode() {
        invypePage.assertDiscountAppliedInBasketUsingRedeemedVoucherCode();
    }

    @And("^assert user is navigated to '(.*)' Invite Your Friends page$")
    public void assertUserIsNavigatedToInviteYourFriendsPage(String expectedTitle) {
        assertTrue(orderSuccessPage.getCurrentPageTitle().toLowerCase().contains(UrlBuilder.getMessage(expectedTitle).toLowerCase()));
    }

    @And("^assert discount applied in basket is not displayed$")
    public void assertDiscountAppliedInBasketIsNotDisplayed() {
        invypePage.assertDiscountAppliedInBasketIsNotDisplayed();
    }

    @And("^assert discount applied in cart is not displayed$")
    public void assertDiscountAppliedInCartIsNotDisplayed() {
        invypePage.assertDiscountAppliedInCartIsNotDisplayed();
    }

    @When("^user clicks on shop now button$")
    public void userClickOnShopButton() {
        invypePage.userClicksOnShopButton();
    }

    @Then("^user should be landed on '(.*)'$")
    public void userShouldBeLandedOnExpectedPage(String expectedTitle) {
        assertThat(invypePage.getCurrentPageTitle()).contains(UrlBuilder.getMessage(expectedTitle));
    }

    @And("^assert CTA via whatsapp should displayed and via email should not displayed$")
    public void assertCTAViaWhatsappAndViaEmailShouldDisplayed() {
        assertTrue(invypePage.isWhatsappCTADisplayed());
        assertTrue(!invypePage.isEmailCTADisplayed());
        assertThat(invypePage.getWhatsappCTAText()).isEqualTo(UrlBuilder.getMessage("viaWhatsappText.key"));
    }

    @And("^user copys referrer link$")
    public void copyReferrerLink() {
        invypePage.getReferrerLink();
    }

    @And("^new user accesses the referrer link url$")
    public void accessReferrerLinkUrl() {
        invypePage.accessesReferrerLinkUrl();
    }

    @And("^your invitation page is displayed$")
    public void invitationPageIsDisplayed() {
        assertTrue(invypePage.invitationPageIsDisplayed());
    }


    @When("^click on via  whatsapp button$")
    public void clickOnViaWhatsappButton() {
        invypePage.clickOnViaWhatsAppButton();
    }

    @And("^assert CTA via whatsapp web page displayed correctly$")
    public void assertCTAViaWhatsappWebPageDisplayedCorrectly() {
        invypePage.waitForExpectedElement(invypePage.ACTION_BUTTON);
        invypePage.getWebDriver().getPageSource().contains(UrlBuilder.getMessage("contextOnWhatsapp.key"));
    }
}
