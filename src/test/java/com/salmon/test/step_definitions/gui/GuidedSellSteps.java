package com.salmon.test.step_definitions.gui;

import com.salmon.test.page_objects.gui.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.DataTable;
import java.util.List;
import static org.testng.AssertJUnit.assertTrue;

public class GuidedSellSteps {

    private GuidedSellPage guidedSellPage;
    private PLP guidedSellPLP;
    private PDP pdpGuidedSell;

    public GuidedSellSteps(GuidedSellPage guidedSellPage,
                           PLP guidedSellPLP, PDP pdpGuidedSell) {
        this.guidedSellPage = guidedSellPage;
        this.pdpGuidedSell = pdpGuidedSell;
        this.guidedSellPLP = guidedSellPLP;
    }

    @And("^user select options from Step1 question page$")
    public void selectDeviceOptions(DataTable dataTable) throws Throwable {
        List<List<String>> devicesList = dataTable.raw();
        for (List<String> strings : devicesList) {
            guidedSellPage.selectDeviceOptions(strings.get(0));
        }
    }

    @And("^user select option '(.*)' from Step1 question page$")
    public void userSelectDeviceOptions(String device) throws Throwable {
        guidedSellPage.selectDeviceOptions(device);
    }

    @Then("^options on step1 question page display correctly$")
    public void assertAllOptionsOnStep1() throws Throwable {
        assertTrue(guidedSellPage.displayAllDeviceOptions());
    }

    @And("^user click Next button on Step1 question page$")
    public void clickDeviceNextBtn() throws Throwable {
        guidedSellPage.clickDeviceNextBtn();
    }

    @Then("^confirm '(.*)' on Step1 is selected$")
    public void assertSelectedOptions(String option1) throws Throwable {
        assertTrue(guidedSellPage.verifySelectedOptions(option1));
    }

    @Then("^confirm '(.*)' on Step1 is not selected$")
    public void assertNotSelectedOptions(String option1) throws Throwable {
        assertTrue(guidedSellPage.verifyNotSelectedOptions(option1));
    }

    @Then("^options on step2 question page display correctly$")
    public void assertAllOptionsOnStep2() throws Throwable {
        assertTrue(guidedSellPage.displayAllFlavourOptions());
    }

    @And("^user select options from Step2 question page$")
    public void selectFlavourOptions(DataTable dataTable) throws Throwable {
        List<List<String>> flavourList = dataTable.raw();
        for (int i = 0; i < flavourList.size(); i++) {
            guidedSellPage.selectFlavourOptions(flavourList.get(i).get(0));
        }
    }

    @And("^user select option '(.*)' from Step2 question page$")
    public void userSelectFlavourOptions(String flavour) throws Throwable {
        guidedSellPage.selectFlavourOptions(flavour);
    }

    @And("^user click Next button on Step2 question page$")
    public void clickFlavourNextBtn() throws Throwable {
        guidedSellPage.clickFlavourNextBtn();
    }

    @And("^user click Back button on Step2 question page$")
    public void clickDeviceBackBtn() throws Throwable {
        guidedSellPage.clickDeviceBackBtn();
    }

    @And("^confirm '(.*)' on Step2 is selected$")
    public void assertSelectedFlavourOptions(String option) throws Throwable {
        assertTrue(guidedSellPage.verifySelectedFlavourOptions(option));
    }

    @Then("^options on step3 question page display correctly$")
    public void assertAllOptionsOnStep3() throws Throwable {
        assertTrue(guidedSellPage.displayAllStrengthOptions());
    }

    @And("^user select options from Step3 question page$")
    public void selectStrengthOptions(DataTable dataTable) throws Throwable {
        List<List<String>> strengthList = dataTable.raw();
        for (int i = 0; i < strengthList.size(); i++) {
            guidedSellPage.selectStrengthsOptions(strengthList.get(i).get(0));
        }
    }

    @And("^user select option '(.*)' from Step3 question page$")
    public void userSelectStrengthsOptions(String strength) throws Throwable {
        guidedSellPage.selectStrengthsOptions(strength);
    }

    @And("^user click Find button on Step3 question page$")
    public void clickFindVuseBtn() throws Throwable {
        guidedSellPage.clickFindVuseBtn();
    }

    @And("^user click Back button on Step3 question page$")
    public void clickFlavourBackBtn() throws Throwable {
        guidedSellPage.clickFlavourBackBtn();
    }

    @Then("^confirm '(.*)' on Step3 is not display$")
    public void assertNotDisplayStrengthOption(String option) throws Throwable {
        assertTrue(guidedSellPage.verifyNotDisplayStrengthOption(option));
    }

    @Then("^user get recommended device '(.*)'$")
    public void getDeviceOnResultPage(String device) throws Throwable {
        assertTrue(guidedSellPLP.verifyDeviceOnResultPage(device));
    }

    @Then("^user get correct flavour gruop '(.*)'$")
    public void assertFlavourGroup(String flavour) throws Throwable {
        assertTrue(guidedSellPLP.verifyFlavourGroup(flavour));
    }

    @Then("^all flavour groups are not returned$")
    public void assertNotExistFlavourGroup() throws Throwable {
        assertTrue(guidedSellPLP.verifyNotExistFlavourGroup());
    }

    @And("^from guided sell add second product to cart$")
    public void fromGuidedSellPLPClickAddToCart() throws Throwable {
         guidedSellPLP.addProductToBasket();
    }

    @And("^from guided sell PLP switch to another flavour tab$")
    public void fromGuidedSellPLPSwitchFavourateTab() throws Throwable {
        guidedSellPLP.clickSwitchFavourateTab();
    }

    @And("^confirm the selected flavour tab is active$")
    public void assertSelectedFlavourTabActive() throws Throwable {
        assertTrue(guidedSellPLP.verifyActiveFlavourGroup());
    }

    @And("^from guided sell PLP view all devices$")
    public void clickGuidedSellPLPViewAllDevices() throws Throwable {
        guidedSellPLP.clickViewAllDevicesBtn();
    }

    @Then("^all devices or flavours are returned$")
    public void assertPLPViewAllDevices() throws Throwable {
        assertTrue(guidedSellPLP.verifyViewAllDevicesDisplay());
    }

    @And("^from guided sell PLP view all flavours$")
    public void fromGuidedSellPLPViewAllFlavours() throws Throwable {
        guidedSellPLP.clickViewAllFlavoursBtn();
    }

    @And("^from guided sell PLP click Start Again$")
    public void fromGuidedSellPLPClickStartAgain() throws Throwable {
        guidedSellPLP.clickStartAgainBtn();
    }

    @Then("^confirm guided sell start$")
    public void confirmGuidedSellStart() throws Throwable {
        assertTrue(guidedSellPage.confirmGuidedSellStart());
    }

    @Then("^user click Let us Get Start$")
    public void clickLetGetStartBtn() throws Throwable {
        guidedSellPage.clickLetGetStartBtn();
    }

    @And("^user start guided sell by clicking navigation menu$")
    public void userClicksGuidedSellMenu() throws Throwable {
        guidedSellPage.clickGuidedSellMenu();

    }

    @And("^user clicks LET US GET STARTED link$")
    public void userStartGuidedSell() throws Throwable {
        guidedSellPage.startGuidedSell();
    }

    @And("^user click deferred payment CTA$")
    public void clickDeferredPaymentLink() throws Throwable {
        guidedSellPage.clickDeferredPaymentCTA();
    }

    @And("^user click learn more CTA$")
    public void clickLearnMoreLink() throws Throwable {
        guidedSellPage.clickLearnMoreCTA();
    }

    @Then("^assert guided sell popup is displayed$")
    public void assertGuidedSellPopUpIsDisplayed() {
        assertTrue(guidedSellPage.isGuidedSellPopUpDisplayed());
    }

    @And("^click popup redirect CTA on popup page$")
    public void clickPopupRedirectLinkOnPopupPage() throws Throwable {
        guidedSellPage.clickPopupRedirectCTA();
    }

    @And("^assert FDT links Deferred payment and Learn More are not displayed$")
    public void assertFDTLinksDeferredPaymentAndLearnMoreAreNotDisplayed() throws Throwable {
        assertTrue(guidedSellPage.getWebDriver().findElements(guidedSellPage.DEFERRED_PAYMENT_CTA).size()==0);
        assertTrue(guidedSellPage.getWebDriver().findElements(guidedSellPage.LEARN_MORE_CTA).size()==0);
    }

    @And("^assert FDT links Deferred payment and Learn More are displayed$")
    public void assertFDTLinksDeferredPaymentAndLearnMoreAreDisplayed() {
        guidedSellPage.waitForExpectedElement(guidedSellPage.DEFERRED_PAYMENT_CTA,10);
        assertTrue(guidedSellPage.getWebDriver().findElements(guidedSellPage.DEFERRED_PAYMENT_CTA).size() > 0);
        assertTrue(guidedSellPage.getWebDriver().findElements(guidedSellPage.LEARN_MORE_CTA).size() > 0);
    }
}
