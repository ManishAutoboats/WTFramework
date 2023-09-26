package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.InsiderClubPage;
import com.salmon.test.page_objects.gui.PaymentPage;
import com.salmon.test.page_objects.gui.RegistrationPage;
import cucumber.api.java.en.And;
import org.testng.Assert;
import org.testng.AssertJUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.registerCustomDateFormat;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class InsiderClubSteps extends PageObject {

    private InsiderClubPage insiderClubPage;
    private RegistrationPage registrationPage;
    private PaymentPage paymentPage;
    public InsiderClubSteps(InsiderClubPage insiderClubPage,RegistrationPage registrationPage,PaymentPage paymentPage) {
        this.insiderClubPage = insiderClubPage;
        this.registrationPage=registrationPage;
        this.paymentPage=paymentPage;
    }

    @And("^Click on 'Register Now' on Insiders Club Page$")
    public void clickOnRegisterNowOnInsidersClubPage() {
        insiderClubPage.clickOnRegisterNowButton();
    }

    @And("^assert '(.*)' label is displayed at the bottom of loyalty page$")
    public void assertTermsAndConditionLabelIsDisplayedAtTheBottomOfLoyaltyPage(String expectedText) {
        assertThat(insiderClubPage.getTermsAndConditionsLabelText().equals(UrlBuilder.getMessage(expectedText)));
    }

    @And("^click 'Learn More' to navigate to 'Terms and Condition' page$")
    public void clickLearnMoreToNavigateToTermsAndConditionPage() {
        insiderClubPage.clickLearnMoreToNavigateToTermsAndConditionPage();
    }

    @And("^click on 'Terms and Conditions' link from footer$")
    public void clickOnTermsAndConditionsLinkFromFooter() {
        insiderClubPage.clickOnTermsAndConditionsLinkFromFooter();
    }

    @And("^assert Loyalty signup checkbox is displayed with Loyalty TCs link$")
    public void assertLoyaltySignUpCheckBoxDisplayedWithLoyaltyTCsLink() {
        assertTrue(insiderClubPage.waitForExpectedElement(insiderClubPage.LOYALTY_SIGN_UP_CHECKBOX,10).isDisplayed());
        assertTrue(insiderClubPage.waitForExpectedElement(insiderClubPage.TERMS_AND_CONDITIONS_LINK).isDisplayed());
    }
}
