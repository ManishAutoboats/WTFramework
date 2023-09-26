package com.salmon.test.step_definitions.gui.epok;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.epok.EpokForgotPasswordPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class EpokForgotPassowordSteps {
	
	  private EpokForgotPasswordPage epokforgotPasswordPage;
	    public EpokForgotPassowordSteps(EpokForgotPasswordPage epokforgotPasswordPage) {
	        this. epokforgotPasswordPage =  epokforgotPasswordPage;
	    }

	  @And("^Epok testingBreakpoint$")
	  public void testingbreakpoint() throws Throwable {
	    Thread.sleep(3000);
	  }

	  @Then("^Epok user is taken to forgot your password page$")
	  public void userIsTakenToForgotYourPasswordPage() {
	    epokforgotPasswordPage.assertTrueWithCustomError(UrlBuilder.getMessage("forgottenPasswordExpectedPageHeader"),epokforgotPasswordPage.getPageHeading());
	    epokforgotPasswordPage.assertTrueWithCustomError(UrlBuilder.getMessage("forgottenPasswordExpectedPageTitle"),epokforgotPasswordPage.getWebDriver().getTitle());
	  }

}
