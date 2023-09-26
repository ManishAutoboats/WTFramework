package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.*;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.gui.cucumberContext.TestContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.testng.Assert;

import static com.salmon.test.page_objects.gui.constants.Context.SHIPPING_THRESHOLD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class ShippingPageSteps {

  private ShippingPage shippingPage;
  private PLP plp;
  private BasketPage basketPage;
  private ScenarioContext scenarioContext;
  private String shippingBarMessage;
  private float shippingThreshold;
  public AddNewAddressPage addNewAddressPage;
  private HomePage homePage;
  private BATHelper batHelper;

  public ShippingPageSteps(TestContext testContext, ShippingPage shippingPage, PLP plp, BasketPage basketPage, HomePage homePage,AddNewAddressPage addNewAddressPage, BATHelper batHelper) {
    this.scenarioContext = testContext.getScenarioContext();
    this.shippingPage = shippingPage;
    this.basketPage = basketPage;
    this.plp = plp;
    this.addNewAddressPage = addNewAddressPage;
    this.homePage = homePage;
    this.batHelper = batHelper;
  }

  @And("^Shipping page details confirmed$")
  public void shippingPageDetailsConfirmed() {
    switch (UrlBuilder.getLocale()) {
      case "vuseco":
      case "vuseza":
      case "lyftse":
        shippingPage.waitForPage();
        assertTrue(shippingPage.isAddNewShippingAddressPresent());
        assertTrue(shippingPage.isOrderSummaryPresent());
        break;
      case "fr":
      case "vusefr":
        shippingPage.waitForPage();
        assertTrue(shippingPage.isAddNewShippingAddressPresent());
        if(UrlBuilder.isDesktop()){
        assertTrue(shippingPage.isOrderSummaryPresent());}
        else{
          assertTrue(shippingPage.isOrderSummaryPresentMobile());}
        break;
      default:
        shippingPage.waitForElementToAppearAndDisappear(shippingPage.LOADER, 3, 5);
        assertTrue(shippingPage.isAddNewShippingAddressPresent());
        assertTrue(shippingPage.isOrderSummaryPresent());
        assertEquals(shippingPage.getShippingAddressHeading(), UrlBuilder.getMessage("ShippingAddressHeading.key").toLowerCase());
        assertEquals(shippingPage.getShippingMethodsHeading(), UrlBuilder.getMessage("ShippingMethodHeading.key").toLowerCase());
    }
    }

  @And("^Shipping page - next page selected$")
  public void shippingPageNextPageSelected() {
    shippingPage.shippingClickNextButton();
  }

  @Then("^assert shipping bar message displayed on top of the page$")
  public void assertShippingBarMessageDisplayedOnTopOfThePage() {
    shippingBarMessage = shippingPage.getShippingBarMessage();
    assertThat(shippingPage.getShippingBarMessage())
            .contains(UrlBuilder.getMessage("freeShippingThresholdMsg.key"));
  }

  @And("^fetch free shipping threshold price from the message displayed$")
  public void fetchFreeShippingThresholdPriceFromTheMessageDisplayed() {
    shippingThreshold = Float.parseFloat(shippingBarMessage.replaceAll("[\\D]", ""));
    scenarioContext.setContext(SHIPPING_THRESHOLD, shippingThreshold);
  }

  @Then("^assert shipping bar msg changes based on cart total price by increasing product quantity$")
  public void assertShippingBarMessageChangesBasedOnTheCartTotalPrice() throws Throwable {
    float diff, basketTotal = basketPage.getBasketTotal();
    while (basketTotal < shippingThreshold) {
      diff = shippingThreshold - basketTotal;
      String shippingBarMessage = shippingPage.getShippingBarMessage();
      shippingPage.scrollToPageTop();
      assertThat(shippingBarMessage)
              .contains(UrlBuilder.getMessage("freeShippingThresholdCountdownMsg.key"));
      assertThat(Float.valueOf(shippingBarMessage.replaceAll("[\\D]", "")))
              .isEqualTo(diff);
      if (UrlBuilder.getLocale().equals("kz")){
        int actualQty = Integer.parseInt(shippingPage.getTextFor(PLP.PRODUCT_ACTUAL_QTY))+1;
        String strQuantity = String.valueOf(actualQty);
        plp.userEntersQuantityInTextFieldAndClicksUpdate(PLP.MINICART_PRODUCTS_QTY, strQuantity, PLP.MINICART_QTY_UPDATE_KZ);
        plp.waitForAjaxElementNotToBePresent(plp.getWebDriver(), 15);
      } else {
        int actualQty = Integer.parseInt(shippingPage.waitForExpectedElement(PLP.MINICART_PRODUCTS_QTY).getAttribute("data-item-qty"));
        String strQuantity = String.valueOf(actualQty + 1);
        plp.userEntersQuantityInTextFieldAndClicksUpdate(PLP.MINICART_PRODUCTS_QTY, strQuantity, PLP.MINICART_QTY_UPDATE);
      }
      plp.waitForAjaxElementNotToBePresent(plp.getWebDriver(), 15);
      basketTotal = basketPage.getBasketTotal();
    }
    assertThat(shippingPage.getShippingBarMessage())
            .contains(UrlBuilder.getMessage("freeShippingMsg.key"));
  }

  @And("^Add a new billing address$")
  public void addANewBillingAddress() {
    addNewAddressPage.populateAllAddressFieldsExceptForFirstAndLastName();
  }

  @And("^assert new billing address has been added '(.*)' '(.*)' '(.*)'$")
  public void assertNewBillingAddressHasBeenAdded(String streetAndHouseNumber, String city, String postal) {
    addNewAddressPage.validateNewlyAddedBillingAddress(UrlBuilder.getMessage(streetAndHouseNumber), UrlBuilder.getMessage(city), UrlBuilder.getMessage(postal));
  }

  @And("^Edit newly added billing address '(.*)' '(.*)' '(.*)'$")
  public void editNewlyAddedBillingAddress(String streetAndHouseNumber, String city, String postal) {
    addNewAddressPage.EditBillingAddress(UrlBuilder.getMessage(streetAndHouseNumber), UrlBuilder.getMessage(city), UrlBuilder.getMessage(postal));
  }

  @And("^Enter details manually on Delivery address Edit window$")
  public void EnterDetailsManuallyOnDeliveryAddressEditWindow() {
    addNewAddressPage.EnterAddressManuallyOnCheckoutPage(UrlBuilder.getMessage("updatedStreetAndHouseNumber.key"), UrlBuilder.getMessage("updatedCity.key"), UrlBuilder.getMessage("updatedPostal.key"), UrlBuilder.getMessage("telephone.key"));
  }

  @Then("^assert shipping bar msg update$")
  public void assertShippingBarMessageUpdate() throws Throwable {
      int diff, basketTotal = basketPage.verifyProductPrice();
      if (basketTotal < shippingThreshold) {
          diff = (int) (shippingThreshold - basketTotal);
          String shippingBarMessage = shippingPage.getShippingBarMessageRemain();
          assertThat(shippingBarMessage)
                  .contains(UrlBuilder.getMessage("freeShippingThresholdCountdownMsg.key"));
          String remainingPriceMessage = shippingPage.getRemainingPriceMessage();
          assertThat(Float.valueOf(remainingPriceMessage)).isEqualTo(diff);
      }else {
          assertThat(shippingPage.getShippingBarMessageRemain())
                  .contains(UrlBuilder.getMessage("freeShippingMsg.key"));
          }
  }
}