package com.salmon.test.step_definitions.gui;

import com.salmon.test.page_objects.gui.AgeVerificationPage;
import cucumber.api.java.en.And;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class AgeVerificationPageSteps {

  private AgeVerificationPage ageVerificationPage;

  public AgeVerificationPageSteps(AgeVerificationPage ageVerificationPage) {
    this.ageVerificationPage = ageVerificationPage;
  }

  @And("^Age Verificaiton page details confirmed$")
  public void paymentPageDetailsConfirmed() throws Throwable {
    Thread.sleep(5000);
    assertEquals(ageVerificationPage.getAgeVerificationHeading(),"Age Verification");
  }

  @And("^Click place order$")
  public void paymentPageNextPageSelected() throws Throwable {
    ageVerificationPage.clickPlaceOrderButton();
    Thread.sleep(4000);
  }

  @And("^assert Age verification logo is displayed on Age Gate pop-up$")
  public void assertAgeVerificationLogoIsDisplayedOnAgeGatePopUp() throws Throwable {
   assertTrue(ageVerificationPage.waitForExpectedElement(ageVerificationPage.AGE_VERIFY_LOGO_POPUP).isDisplayed());
  }

  @And("^assert Age verification logo is displayed on home page Footer$")
  public void assertAgeVerificationLogoIsDisplayedOnHomePageFooter() throws Throwable {
    ageVerificationPage.scrollToElement(ageVerificationPage.AGE_VERIFY_LOGO_FOOTER);
    assertTrue(ageVerificationPage.waitForExpectedElement(ageVerificationPage.AGE_VERIFY_LOGO_FOOTER).isDisplayed());
  }
}