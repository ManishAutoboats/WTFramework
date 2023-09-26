package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import java.util.HashMap;
import java.util.Map;

import static com.salmon.test.page_objects.gui.constants.Context.LANGUAGE;

public class NavigationSteps {

    private boolean saveEyesStatus;
    private ScenarioContext scenarioContext;

    public NavigationSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }


    @Given("^i navigate to the BAT \"(.*?)\" page$")
    public void i_navigate_to_the_Salmon_page(String pageName) {
        if (pageName.equalsIgnoreCase("HOME")) {
            UrlBuilder.startAtHomePage();
        }
    }

    @Given("^i navigate to the BAT Mobile \"(.*?)\" page$")
    public void i_navigate_to_the_Salmon_mobile_page(String pageName) {
        if (pageName.equalsIgnoreCase("HOME")) {
            UrlBuilder.startAtMobileHomePage();
        }
    }

    @Given("^user navigates to BAT home page$")
    public void userNavigatesToTheBatHomePage() {
        UrlBuilder.startBATHomePage();
    }

    @Given("^user navigates to Device Trial landing page$")
    public void userNavigatesToTheDeviceTrialLandingPage() {
        UrlBuilder.startDeviceTrialLandingPage();
    }

    @When("^user navigates to BAT \"([^\"]*)\" page via URL$")
    public void userNavigatesToBATPage(String pageName) {
        Map<String, String> map = new HashMap<>();
        map.put("Store Locator", "StoreLocatorUrl.key");
        map.put("Blog", "BlogUrl.key");
        map.put("Bundles", "BundlesUrl.key");
        map.put("Contact Us", "ContactUsUrl.key");
        map.put("404", "404PageUrl.key");
        map.put("No Result Search", "NoResultSearchUrl.key");
        map.put("FAQs", "terms&ConditionsFAQsURL.key");
        map.put("Terms and Conditions", "Terms&ConditionsUrl.key");
        map.put("About", "AboutUrl.key");
        map.put("Subscriptions Terms&Conditions", "SubscriptionsTerms&ConditionsUrl.key");
        map.put("Delivery & Returns", "DeliveryReturnUrl.key");
        map.put("Cookie Notice", "CookieNoticeUrl.key");
        map.put("Privacy Notice", "PrivacyNoticeUrl.key");
        map.put("Terms of Sale", "terms&ConditionsOfSaleURL.key");
        map.put("Warranty", "guaranteeUrl.key");
        map.put("Accessibility", "AccessibilityUrl.key");
        map.put("Satisfaction Guaranteed", "SatisfactionUrl.key");
        map.put("What is Vaping", "WhatIsVapingUrl.key");
        map.put("Nicotine Salts", "NicotineSaltsUrl.key");
        map.put("Disposal", "DisposalOfProductsUrl.key");
        map.put("Compare Devices", "CompareDevicesUrl.key");
        map.put("Cookie Settings", "CookieSettingsUrl.key");
        map.put("Cookie Policy", "CookiePolicyUrl.key");
        map.put("Privacy Policy", "PrivacyPolicyUrl.key");
        map.put("Try Glo for Free", "TryGloForFree.key");
        map.put("Terms of Use", "TermsOfUseUrl.key");
        map.put("Terms of Trade", "termsOfTradeURL.key");
        map.put("Imprint", "ImprintUrl.key");
        map.put("Withdrawal", "WithdrawalUrl.key");
        map.put("Shipping and Payment", "ShippingAndPaymentUrl.key");
        map.put("Devices Plp", "DevicesPlp.key");
        map.put("Business information", "BusinessInformationURL.key");
        map.put("Epod 2 skin", "epod2SkinUrl.key");
        UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage(map.get(pageName)));
    }

    @Given("^turn off Eyes$")
    public void turnOffEyes() {
        saveEyesStatus = Props.EYES_ON;
        Props.EYES_ON = false;
    }

    @And("^recover Eyes status$")
    public void recoverEyesStatus() {
        Props.EYES_ON = saveEyesStatus;
    }

    @Given("^user navigates to Free Device Trial landing page$")
    public void userNavigatesToFreeDeviceTrialLandingPage() {
        UrlBuilder.startFreeDeviceTrialLandingPage();
    }
    @And("^user navigates back to Free Device Trial landing page$")
    public void userNavigatesBackToFreeDeviceTrialLandingPage() {
        UrlBuilder.revisitDeviceTrialLandingPage();
    }

    @Given("^user is at the In Store Subs home page$")
    public void userIsAtTheInStoreSubsHomePage() {
        UrlBuilder.startInStoreSubsHomePage();
    }

    @Given("^user resumes ISS home page$")
    public void userResumesISSHomePage() {
        UrlBuilder.resumeInStoreSubsHomePage();
    }

    @Given("^user revisits BAT home page$")
    public void userRevisitsBATHomePage() {
        UrlBuilder.revisitBATHomePage();
    }

    @Given("^user navigates to BAT home page for language \"([^\"]*)\"$")
    public void userNavigatesToBATHomePageForLanguage(String language) throws Throwable {
        scenarioContext.setContext(LANGUAGE, language);
        UrlBuilder.startAvalancheHomePage(language);
    }

    @Given("^user navigates to Flavour Tool landing page$")
    public void userNavigatesToFlavourToolLandingPage() {
        UrlBuilder.startFlavourToolsLandingPage();
    }

    @And("^user navigate to \"([^\"]*)\" page$")
    public void userNavigateToPage(String pageEndUrl)  {
        UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage(pageEndUrl));
    }
}
