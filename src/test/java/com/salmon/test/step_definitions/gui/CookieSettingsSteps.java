package com.salmon.test.step_definitions.gui;

import com.salmon.test.page_objects.gui.*;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class CookieSettingsSteps {


    private CookieSettingsPage cookieSettings;
    private HomePage homePage;

    public CookieSettingsSteps(CookieSettingsPage cookieSettings, HomePage homePage) {
        this.cookieSettings = cookieSettings;
        this.homePage = homePage;

    }

    @Then("^assert Cookie settings link and click it$")
    public void userSelectCookieSettingsLink() {
        homePage.assertCookieSettingsLinkCTAInFooter();

    }

    @Then("^user can see the allow all CTA$")
    public void userCanSeeTheAllowAllCTA() {
        cookieSettings.verifyAllowallCta();
    }

    @Then("^user can see the on/off toggle for each individual cookie type$")
    public void userCanSeeToggleForCookieTypes() {
        cookieSettings.verifyCookieTypesToggle();
    }

    @Then("^user can see the confirm my choices CTA$")
    public void userCanSeeConfirmChoice() {
        cookieSettings.verifycookieconfirmchoice();
    }

    @When("^user interact with the allow all CTA$")
    public void userClickAllowAll() {
        cookieSettings.clickallowall();
    }

    @When("^user interact with the on/off toggle for a cookie type$")
    public void userToggleACookieType() {
        cookieSettings.togglecookietype();
    }

    @Then("^the toggle changes to an active state$")
    public void toggleStateChange() {
        cookieSettings.togglestatechange();
    }

    @When("^user interact with the save my preferences CTA$")
    public void userClickConfirmChoice() {
        cookieSettings.clickconfirmchoice();
    }

    @Then("^my cookie preferences are saved$")
    public void cookiePreferencesSaved() {
        cookieSettings.assertcookiepreferencesaved();
    }

    @Then("^all cookie types are toggled to the active state$")
    public void verifyAllCookieTypeSelected() {
        cookieSettings.assertallcookietypeselected();
    }
}
