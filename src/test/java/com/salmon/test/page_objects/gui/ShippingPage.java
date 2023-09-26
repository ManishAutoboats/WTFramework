package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;

import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;

public class ShippingPage extends PageObject {

  //@Getter
  // ELEMENT MAPPING
  public By shippingAddressHeading = By.cssSelector("#shipping > div.step-title,h3.selected-address__heading");
  public By addNewAddressButton = By.cssSelector("button.action.action-show-popup > span:nth-child(1),div.selected-address__container > a.selected-address__change-button.alink");
  public static final By ADD_NEW_ADDRESS_FR = By.cssSelector("div.selected-address__container > a");
  public By shippingMethodsHeading = By.cssSelector("#opc-shipping_method > div > div.step-title");
  public By orderSummaryHeading = By.cssSelector("#opc-sidebar > div.opc-block-summary > span");
  public By shippingNextButton = By.cssSelector("#shipping-method-buttons-container > div > button > span");
  public By creditCardOption = By.cssSelector("input#worldpay_cc.radio");
  public By MXCreditCardOption = By.cssSelector("#tns_hosted");
  public By COCreditCardOption = By.cssSelector("div#checkout-payment-method-load div:nth-child(2) > div.payment-method-title.field.choice > label");
  private static final By SHIPPING_ADDRESS =By.cssSelector("input#input_uid_13,.selected-address__container");
  private static final By SHIPPING_ADDRESS_VUSECO =By.cssSelector("div.selected-address__container");
  public static final By PAGE_HEADER_ANNOUNCEMENT_CSS = By.cssSelector(".header-announcement");
  public static final By PAGE_HEADER_ANNOUNCEMENT_CSS_KZ = By.cssSelector("div.header-assurance > div > div > div > p");
  public static final By PAGE_HEADER_ANNOUNCEMENT_CSS_ZA = By.cssSelector("div.header-assurance > div > div > div > div:nth-child(1) > p.with-shipping-price");
  public static final By PAGE_HEADER_ANNOUNCEMENT_REMAIN_ZA = By.cssSelector("div.header-assurance > div > div > div > div:nth-child(1) > p.with-remaining-shipping-price.d-none");
  public static final By PAGE_HEADER_ANNOUNCEMENT_REMAIN_PRICE_ZA = By.cssSelector("p.with-remaining-shipping-price.d-none > span > span.shipping-price-cal");
  public By orderSummaryHeadingMobile = By.cssSelector("#opc-sidebar > div.opc-block-summary");

  public String getShippingAddressHeading(){
    return waitForExpectedElement(shippingAddressHeading).getText().toLowerCase();
  }

  public boolean isAddNewShippingAddressPresent()
  {
      switch (UrlBuilder.getLocale()) {
        case "mx":
        case "vusemx":
            waitForExpectedElement(MXCreditCardOption, 60).isDisplayed();
          break;
        case "vuseza":
          waitForExpectedElement(SHIPPING_ADDRESS,60).isDisplayed();
          break;
        case "fr":
          waitForExpectedElement(ADD_NEW_ADDRESS_FR,10).isDisplayed();
          break;
        case "vuseco":
          waitForPage();
          waitForExpectedElement(ADD_NEW_ADDRESS_FR,60).isDisplayed();
          break;
        default:
          waitForPage();
          waitForExpectedElement(creditCardOption, 60).isDisplayed();
      }
      if (UrlBuilder.getLocale().equalsIgnoreCase("vusefr")||UrlBuilder.getLocale().equalsIgnoreCase("vuseco") || UrlBuilder.getLocale().equalsIgnoreCase("vuseza")){
        return waitForExpectedElement(ADD_NEW_ADDRESS_FR,10).isDisplayed();
      } else {
        return waitForExpectedElement(addNewAddressButton,10).isDisplayed();
      }

  }

  public String getShippingMethodsHeading(){
    return waitForExpectedElement(shippingMethodsHeading).getText().toLowerCase();
  }

  public Boolean isOrderSummaryPresent(){
    return waitForExpectedElement(orderSummaryHeading).isDisplayed();
  }

  public void shippingClickNextButton(){
    clickByElementByQueryJSExecutor(shippingNextButton);
  }


  public String getShippingBarMessage() {
      switch (UrlBuilder.getLocale()){
        case "kz":
          return waitForExpectedElement(PAGE_HEADER_ANNOUNCEMENT_CSS_KZ).getText();
        case "vuseza":
          return waitForExpectedElement(PAGE_HEADER_ANNOUNCEMENT_CSS_ZA).getText();
        default:
          scrollToElement(PAGE_HEADER_ANNOUNCEMENT_CSS);
          return waitForExpectedElement(PAGE_HEADER_ANNOUNCEMENT_CSS).getText();
    }
  }

  public String getShippingBarMessageRemain() {
    if ("vuseza".equals(UrlBuilder.getLocale())) {
      return waitForExpectedElement(PAGE_HEADER_ANNOUNCEMENT_REMAIN_ZA).getText();
    }
    return waitForExpectedElement(PAGE_HEADER_ANNOUNCEMENT_CSS_KZ).getText();
  }

  public String getRemainingPriceMessage() {
      return waitForExpectedElement(PAGE_HEADER_ANNOUNCEMENT_REMAIN_PRICE_ZA).getText();
  }

  public Boolean isOrderSummaryPresentMobile(){
    return waitForExpectedElement(orderSummaryHeadingMobile).isDisplayed();
  }
}
