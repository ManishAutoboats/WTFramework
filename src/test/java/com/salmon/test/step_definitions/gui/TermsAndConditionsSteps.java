package com.salmon.test.step_definitions.gui;
import static org.assertj.core.api.Assertions.assertThat;
import com.salmon.test.page_objects.gui.TermsAndConditions;
import cucumber.api.java.en.And;

import static org.assertj.core.api.Assertions.assertThat;

public class TermsAndConditionsSteps {

  private TermsAndConditions termsAndConditions;
  public TermsAndConditionsSteps(TermsAndConditions termsAndConditions) {
    this.termsAndConditions = termsAndConditions;
  }

  @And("^get page h1 title and assert it says '(.*)'$")
  public void shippingPageNextPageSelected(String expectedTitle) {
    termsAndConditions.assertTrueWithCustomError(expectedTitle, termsAndConditions.getTermsAndConditionsPageHeading());
  }

  @And("^assert Terms and Conditions page heading '(.*)'$")
  public void TermsAndConditionsPageHeading(String expectedTitle) {
    assertThat(termsAndConditions.getTermsAndConditionsPageHeading().equals(expectedTitle));
  }

  @And("^assert Terms and Conditions sub heading '(.*)'$")
  public void assertTermsAndConditionsSubHeading(String expectedTitle) {
    assertThat(termsAndConditions.getTermsAndConditionsPageSubHeading().equals(expectedTitle));
  }
}