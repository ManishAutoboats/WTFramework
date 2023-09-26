package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.*;
import com.salmon.test.page_objects.gui.constants.Locale;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.StaleElementReferenceException;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class VuseCarePageSteps {

    private VuseCarePage vuseCarePage;
    private HomePage homePage;
    private BasketPage basketPage;
    private PDP pdp;
    private PLP plp;
    private SearchResult searchResult;
    private GlobalSubscriptionsPage globalSubscriptionsPage;

    public VuseCarePageSteps(VuseCarePage vuseCarePage, HomePage homePage, PDP pdp, SearchResult searchResult, PLP plp, BasketPage basketPage, GlobalSubscriptionsPage globalSubscriptionsPage) {
        this.vuseCarePage = vuseCarePage;
        this.homePage = homePage;
        this.pdp = pdp;
        this.plp = plp;
        this.searchResult= searchResult;
        this.basketPage= basketPage;
        this.globalSubscriptionsPage = globalSubscriptionsPage;
    }

    @And("^assert Register Now link is displayed$")
    public void assertRegisterNowLinkIsDisplayed() {
        assertTrue(vuseCarePage.waitForExpectedElement(vuseCarePage.LINK_REGISTER_NOW).isDisplayed());
    }

    @And("^assert Create Profile link is displayed$")
    public void assertCreateProfileLinkIsDisplayed() {
        assertTrue(vuseCarePage.waitForExpectedElement(vuseCarePage.LINK_CREATE_PROFILE).isDisplayed());
    }

    @And("^assert FAQs section in accordion style$")
    public void assertFAQSectionInAccordinaStyle() {
        assertTrue(vuseCarePage.getWebDriver().findElements(vuseCarePage.FAQS_SECTION_ACCORDIAN).size()>0);
        vuseCarePage.clickByElementByQueryJSExecutor(vuseCarePage.FAQS_ACCORDIAN_EXPAND);
        assertTrue(vuseCarePage.waitForExpectedElement(vuseCarePage.FAQS_SECTION_EXPANDED).isDisplayed());
    }

    @And("^user clicks on Register Now link from Vuse Care Registration block$")
    public void userClicksOnRegisterNowLinkFromVuseCareRegistrationBlock() {
        vuseCarePage.waitForItemToBeClickableAndClick(vuseCarePage.LINK_REGISTER_NOW);
    }

    @And("^user clicks on Create Profile link Vuse Care Profile block$")
    public void userClicksOnRegisterNowLinkFromVuseCareProfileBlock() {
        vuseCarePage.clickFirstElementByQueryJSExecutor(vuseCarePage.LINK_CREATE_PROFILE);
    }

    @And("^user clicks on '(.*)' option$")
    public void userClickOnTheOption(String option) {
        vuseCarePage.userClickOnTheOption(option);
    }

    @And("^assert terms and condition and subscribe newsletter checkox is displayed$")
    public void assertTermsAndConditionsAndSubscribeCheckboxIsDisplayed() {
        assertTrue(vuseCarePage.waitForExpectedElement(vuseCarePage.TERMS_AND_CONDITIONS_CHECKBOX).isDisplayed());
        assertTrue(vuseCarePage.waitForExpectedElement(vuseCarePage.TERMS_AND_CONDITIONS_LINK).isDisplayed());
        assertTrue(vuseCarePage.waitForExpectedElement(vuseCarePage.SUBSCRIBE_NEWSLETTER_CHECKBOX).isDisplayed());
    }

    @And("^user try to register for vuse care$")
    public void userTryingToRegisterForVuseCare() {
        vuseCarePage.userTryToRegisterForVuseCare();
    }

    @And("^verify subscribe checkbox is not displayed$")
    public void verifySubsribeOptionIsNotDisplayed() {
        assertTrue(vuseCarePage.getWebDriver().findElements(vuseCarePage.SUBSCRIBE_NEWSLETTER_CHECKBOX).size()<=0);
    }

    @Then("^assert user is registered successfully$")
    public void assertUserIsRegisteredSuccessfully() {
        assertTrue(vuseCarePage.waitForExpectedElement(vuseCarePage.VUSE_CARE_SUCCESS_MESSAGE,10).isDisplayed());
        assertTrue(vuseCarePage.waitForExpectedElement(vuseCarePage.REGISTERED_FORVUSE_CARE,10).isDisplayed());
    }

    @And("^assert opt out for vuse care option and confirm CTA is displayed$")
    public void assertOptOutAndConfirmOption() {
        assertTrue(vuseCarePage.waitForExpectedElement(vuseCarePage.OPT_OUT_VUSE_CHECKBOX,10).isDisplayed());
        assertTrue(vuseCarePage.waitForExpectedElement(vuseCarePage.DISABLED_CONFIRM_BUTTON,10).isDisplayed());
    }

    @Then("^user opt out for vuse care and click on confirm CTA$")
    public void userOptOutAndClickOnConfirmOption() {
        vuseCarePage.userOptOutAndClickOnConfirmOption();
    }

    @And("^assert confirmation option with yes and no is displayed$")
    public void assertYesAndNoOptionIsDisplayed() {
        assertTrue(vuseCarePage.waitForExpectedElement(vuseCarePage.YES_CANCEL_VUSE_CARE_BUTTON).isDisplayed());
        assertTrue(vuseCarePage.waitForExpectedElement(vuseCarePage.NO_CANCEL_VUSE_CARE_BUTTON).isDisplayed());
    }

    @And("^assert user is not registered for vuse care anymore$")
    public void assertUserIsNotRegisteredForVuseCare() {
        assertTrue(vuseCarePage.waitForExpectedElement(vuseCarePage.TERMS_AND_CONDITIONS_CHECKBOX).isDisplayed());
        assertTrue(vuseCarePage.waitForExpectedElement(vuseCarePage.VUSE_CARE_NOT_REGISTERED_MESSAGE,8).isDisplayed());
        assertTrue(vuseCarePage.waitForExpectedElement(vuseCarePage.TERMS_AND_CONDITIONS_LINK).isDisplayed());
        if(vuseCarePage.getWebDriver().findElements(vuseCarePage.SUBSCRIBE_NEWSLETTER_CHECKBOX).size()>0)
            assertTrue(vuseCarePage.waitForExpectedElement(vuseCarePage.SUBSCRIBE_NEWSLETTER_CHECKBOX).isDisplayed());
        else assertTrue(vuseCarePage.getWebDriver().findElements(vuseCarePage.SUBSCRIBE_NEWSLETTER_CHECKBOX).size()<=0);
    }

    @And("^user user navigate to learn more from pdp$")
    public void userNavigateToLearnMoreFromPdp() {
        vuseCarePage.openLearMorePopup();
    }

    @And("^verify the content of the learn more popup$")
    public void verifyContentOfTheLearnMorePopup() {
        assertTrue(vuseCarePage.waitForExpectedElement(vuseCarePage.LEARN_MORE_HEADING).isDisplayed());
        assertTrue(vuseCarePage.quantityOfItem(vuseCarePage.SUBSCRIBE_ITEM_QUANTITY)==0);
        assertTrue(vuseCarePage.getWebDriver().findElements(vuseCarePage.BRONZE_SUBSCRIPTON_ACTIVE).size()<=0);
        assertTrue(vuseCarePage.getWebDriver().findElements(vuseCarePage.GOLD_SUBSCRIPTON_ACTIVE).size()<=0);
        assertTrue(vuseCarePage.getWebDriver().findElements(vuseCarePage.SILVER_SUBSCRIPTON_ACTIVE).size()<=0);
    }

    @And("^user adds '(.*)' item into the cart$")
    public void addItembasedOnInput(String quantity) {
        vuseCarePage.addItembasedOnInput(quantity);
    }

    @And("^verify the content of the learn more popup for '(.*)' item$")
    public void verifyContentOfTheLearnMorePopupForGivenItem(String item) {
        vuseCarePage.navigateToLearnMore();
        vuseCarePage.waitForAjaxElementNotToBePresent(vuseCarePage.getWebDriver(),10);
        assertTrue(vuseCarePage.waitForExpectedElement(vuseCarePage.LEARN_MORE_HEADING,5).isDisplayed());
        try {
            assertTrue(vuseCarePage.quantityOfItem(vuseCarePage.SUBSCRIBE_ITEM_QUANTITY) == vuseCarePage.quantityOfItem(vuseCarePage.BASKET_QUANTITY));
        }catch(StaleElementReferenceException e){
            assertTrue(vuseCarePage.quantityOfItem(vuseCarePage.SUBSCRIBE_ITEM_QUANTITY_MODAL_WINDOW) == vuseCarePage.quantityOfItem(vuseCarePage.BASKET_QUANTITY_ON_PAGE));
        }
        if (item.equals("5")) {
            assertTrue(vuseCarePage.waitForExpectedElement(vuseCarePage.BRONZE_SUBSCRIPTON_ACTIVE).isDisplayed());
            assertTrue(vuseCarePage.getWebDriver().findElements(vuseCarePage.GOLD_SUBSCRIPTON_ACTIVE).size() <= 0);
            assertTrue(vuseCarePage.getWebDriver().findElements(vuseCarePage.SILVER_SUBSCRIPTON_ACTIVE).size() <= 0);
        } else if (item.equals("10")) {
            assertTrue(vuseCarePage.getWebDriver().findElements(vuseCarePage.BRONZE_SUBSCRIPTON_ACTIVE).size() <= 0);
            assertTrue(vuseCarePage.getWebDriver().findElements(vuseCarePage.GOLD_SUBSCRIPTON_ACTIVE).size() <= 0);
            assertTrue(vuseCarePage.waitForExpectedElement(vuseCarePage.SILVER_SUBSCRIPTON_ACTIVE).isDisplayed());
        } else {
            assertTrue(vuseCarePage.waitForExpectedElement(vuseCarePage.GOLD_SUBSCRIPTON_ACTIVE).isDisplayed());
            assertTrue(vuseCarePage.getWebDriver().findElements(vuseCarePage.BRONZE_SUBSCRIPTON_ACTIVE).size() <= 0);
            assertTrue(vuseCarePage.getWebDriver().findElements(vuseCarePage.SILVER_SUBSCRIPTON_ACTIVE).size() <= 0);
        }
    }

    @And("^user clicks on Register for Vuse Care libk without accepting TCs$")
    public void userClicksOnRegisterForVuseCareLinkWithoutTCsCheck() {
        vuseCarePage.waitForItemToBeClickableAndClick(vuseCarePage.REGISTER_FOR_VUSE_CARE_BTN,5);
    }

    @And("^assert error message '(.*)' is displayed$")
    public void assertErrorMessageIsDisplayed(String strErrorMsg) {
        assertTrue(vuseCarePage.waitForExpectedElement(vuseCarePage.TERMS_CONDITIONS_CONSENT_ERROR).getText().contains(UrlBuilder.getMessage(strErrorMsg)));
    }

    @Then("^modal window should have the correct subscribe package when appropriate quantity is added for PDP$")
    public void correctDiscountWhenAppropriateQuantityIsAddedFromPdp(DataTable table) throws Throwable {
            List<List<String>> cartItems = table.raw();
            for (int i = 0; i < cartItems.size(); i++) {
                int totalItemQty = 0;
                vuseCarePage.userClickOnTheOption("Subscribe");
                vuseCarePage.userClickOnTheOption("Pods subscribe");
                searchResult.selectSearchResult();
                totalItemQty = totalItemQty + Integer.parseInt(cartItems.get(i).get(0));
                globalSubscriptionsPage.clickGlobalSubsOption();
                pdp.selectProductColorStrengthFromList(pdp.btnProductStrength);
                homePage.enterItemQuantityPDP(cartItems.get(i).get(0));
                pdp.clickAddToCartButton();
                vuseCarePage.navigateToLearnMore();
                String quantity = Integer.toString(totalItemQty);
                verifyContentOfTheLearnMorePopupForGivenItem(quantity);
                vuseCarePage.clickByElementByQueryJSExecutor(vuseCarePage.CLOSE_ICON_MODAL_WNDOW);
                homePage.emptyBasket();
            }
    }

    @Then("^modal window should have the correct subscribe package when appropriate quantity is added for PLP$")
    public void correctDiscountWhenAppropriateQuantityIsAddedFromPlp(DataTable table) throws Throwable {
        List<List<String>> cartItems = table.raw();
        for (int i = 0; i < cartItems.size(); i++) {
            int totalItemQty = 0;
            vuseCarePage.userClickOnTheOption("Subscribe");
            vuseCarePage.userClickOnTheOption("Pods subscribe");
            vuseCarePage.clickSuscriptionRadioButtonIfPresent();
            totalItemQty = totalItemQty + Integer.parseInt(cartItems.get(i).get(0));
            plp.selectStrengthAndAddQty(cartItems.get(i).get(0));
            vuseCarePage.navigateToLearnMore();
            String quantity = Integer.toString(totalItemQty);
            verifyContentOfTheLearnMorePopupForGivenItem(quantity);
            vuseCarePage.clickByElementByQueryJSExecutor(vuseCarePage.CLOSE_ICON_MODAL_WNDOW);
            homePage.emptyBasket();
        }
    }

    @Then("^modal window should have the correct subscribe package when appropriate quantity is added for cart page$")
    public void correctDiscountWhenAppropriateQuantityIsAddedToBasket(DataTable table) throws Throwable {
        List<List<String>> cartItems = table.raw();
        for (int i = 0; i < cartItems.size(); i++) {
            int totalItemQty = 0;
            vuseCarePage.userClickOnTheOption("Subscribe");
            vuseCarePage.userClickOnTheOption("Pods subscribe");
            vuseCarePage.clickSuscriptionRadioButtonIfPresent();
            searchResult.selectSearchResult();
            totalItemQty = totalItemQty + Integer.parseInt(cartItems.get(i).get(0));
            globalSubscriptionsPage.clickGlobalSubsOption();
            pdp.selectProductColorStrengthFromList(pdp.btnProductStrength);
            pdp.clickAddToCartButton();
            homePage.waitForExpectedElement(homePage.VIEW_BASKET).click();
            basketPage.enterItemQuantityCart(cartItems.get(i).get(0));
            String quantity = Integer.toString(totalItemQty);
            verifyContentOfTheLearnMorePopupForGivenItem(quantity);
            vuseCarePage.clickByElementByQueryJSExecutor(vuseCarePage.CLOSE_ICON_MODAL_WNDOW);
            homePage.emptyBasket();
        }
    }

    @And("^verify description is present$")
    public void verifyDescriptionIsPresent() {
        assertTrue(vuseCarePage.isElementDisplayedBySeconds(vuseCarePage.PRODUCT_DESCRIPTION,5));
    }

    @And("^user click on learn more from pdp$")
    public void userClickLearnMorePDP() {
        vuseCarePage.clickUsingJS(vuseCarePage.EXTEND_DESCRIPTION_LINK);
    }

    @Then("^verify the content of the learn more pdp$")
    public void verifyContentOfLearnMorePDP() {
        switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
            case VUSEIT:
                assertTrue(vuseCarePage.isElementPresent(vuseCarePage.EXTENDED_DESCRIPTION_VUSEIT,5));
                break;
            default:
                assertTrue(vuseCarePage.isElementPresent(vuseCarePage.EXTENDED_DESCRIPTION,5));
        }
    }

    @And("^users clicks on reduire from bottom description$")
    public void userClickReduireFromBottomDescription() {
        vuseCarePage.clickUsingJS(vuseCarePage.REDUIRE_DESCRIPTION_LINK);
    }
}
