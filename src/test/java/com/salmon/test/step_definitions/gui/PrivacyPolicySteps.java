package com.salmon.test.step_definitions.gui;

import com.salmon.test.page_objects.gui.PrivacyPolicyPage;
import cucumber.api.java.en.Then;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class PrivacyPolicySteps {

  private PrivacyPolicyPage privacyPolicyPage;

  public PrivacyPolicySteps(PrivacyPolicyPage privacyPolicyPage) {
    this.privacyPolicyPage = privacyPolicyPage;
  }

  @Then("^assert Privacy and Cookie Policy header$")
  public void getPrivacyPolicyHeader() {
    assertFalse(privacyPolicyPage.getWebDriver().getTitle().isEmpty());
  }

}