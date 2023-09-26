package com.salmon.test.step_definitions.gui.gloIt;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.HomePage;
import com.salmon.test.page_objects.gui.gloIt.AgeVerificationPage;
import cucumber.api.java.en.And;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import static com.salmon.test.framework.helpers.WebDriverHelper.getWebDriver;
import static org.testng.Assert.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class AgeVerificationSteps {
    private final HomePage homePage;
    private final AgeVerificationPage ageVerificationPage;
    private final SoftAssert softAssert;
    public AgeVerificationSteps(HomePage homePage, AgeVerificationPage ageVerificationPage,SoftAssert softAssert) {
        this.homePage = homePage;
        this.ageVerificationPage = ageVerificationPage;
        this.softAssert=softAssert;
    }

    @And("^user rejects over 18 age confirmation option$")
    public void userSelectsOverAgeConfirmationOption() {
        homePage.tryClickIAmOver18();
    }

    @And("^assert accept and deny CTA is displayed$")
    public void assertAcceptAndDenyCTAIsDisplayed() {
        softAssert.assertTrue(homePage.waitForExpectedElement(AgeVerificationPage.BUTTON_AGE_ALLOW).isDisplayed());
        softAssert.assertTrue(homePage.waitForExpectedElement(AgeVerificationPage.BUTTON_AGE_DENY).isDisplayed());
        softAssert.assertAll();
    }

    @And("^assert content paragraph is displayed$")
    public void assertContentParagraphIsDisplayed() {
        assertTrue(homePage.waitForExpectedElement(AgeVerificationPage.AGE_CONTENT_HEADING).isDisplayed());
        assertTrue(homePage.waitForExpectedElement(AgeVerificationPage.AGE_CONTENT).isDisplayed());
    }

    @And("^there are two logos visible glo, velo$")
    public void thereAreTwoLogosVisibleGloVelo() {
        homePage.eyesCheckAgeGate();
        homePage.waitForExpectedElement(AgeVerificationPage.COMBINED_LOGO);
        softAssert.assertTrue(homePage.waitForExpectedElement(AgeVerificationPage.COMBINED_LOGO).getAttribute("src").contains("glo_velo_combined_logo.png"));
        softAssert.assertAll();
    }

    @And("^assert entry age popup is displayed$")
    public void assertEntryAgePopupIsDisplayed() {
        try {
            homePage.eyesCheckAgeGate();
            homePage.waitForExpectedElement(AgeVerificationPage.ENTRY_AGE_POPUP,20);
        } catch (Exception e) {
            homePage.eyesCheckAgeGate();
            homePage.waitForExpectedElement(AgeVerificationPage.ENTRY_AGE_POPUP,15);
        }
        Assert.assertTrue(homePage.waitForExpectedElement(AgeVerificationPage.ENTRY_AGE_POPUP).isDisplayed());
    }

    @And("^user selects not over 18 age confirmation option$")
    public void userSelectsNotOverAgeConfirmationOption() {
        ageVerificationPage.userSelectsNotOver18Years();
    }

    @And("^assert entry age error message displayed$")
    public void assertEntryAgeErrorMessageDisplayed() {
        assertTrue(homePage.waitForExpectedElement(AgeVerificationPage.ENTRY_AGE_ERROR).isDisplayed());
    }

    @And("^user lands on '(.*)'$")
    public void userLandsOnPage(String text) {
        ageVerificationPage.navigateDirectlyToM2UrlsForAgeVerification(text);
    }

    @And("^user selects over 18 age confirmation option on homepage$")
    public void userSelectsOverAgeConfirmationOptionOnHomepage() {
        ageVerificationPage.clickIamOver18();
    }

    @And("^user is redirected to homepage$")
    public void userIsRedirectedToHomepage() {
        homePage.waitForPage(60);
        homePage.assertTrueExpectedTextContainsActualTextWithCustomError((getWebDriver().getCurrentUrl()),UrlBuilder.getMessage("loginUrl.key"));
    }

    @And("^assert entry age popup is displayed on homepage$")
    public void assertEntryAgePopupIsDisplayedOnHomepage() {
        homePage.eyesCheckAgeGate();
        Assert.assertTrue(homePage.waitForExpectedElement(AgeVerificationPage.HOMEPAGE_ENTRY_AGE_POPUP).isDisplayed());
    }

    @And("^age confirmation box is no longer displayed  on homepage$")
    public void ageConfirmationBoxIsNoLongerDisplayedOnHomepage() {
        assertFalse(homePage.getWebDriver().findElement(AgeVerificationPage.HOMEPAGE_ENTRY_AGE_POPUP).isDisplayed());
    }

    @And("^assert entry age error message displayed on homepage$")
    public void assertEntryAgeErrorMessageDisplayedOnHomepage() {
        assertTrue(getWebDriver().findElement(AgeVerificationPage.ENTRY_AGE_ERROR_JP).isDisplayed());
    }

    @And("^verify user lands on M2 '(.*)' after login$")
    public void verifyUserLandsOnMPagesAfterLogin(String text) {
        ageVerificationPage.assertUserLandsOnGivenPagesAfterLogin(text);
    }
}