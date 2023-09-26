package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;

public class AgeVerificationPage extends PageObject {

  //@Getter
  // ELEMENT MAPPING
  public By ageVerificationHeading = By.cssSelector("#ageVerification > div.step-title");
  public By placeOrderButton = By.cssSelector("#checkout-step-title > div.actions-toolbar > div.primary.next > button > span");
  public final static By AGE_VERIFY_LOGO_POPUP=By.xpath("//body/div[@id='entry-age-gate-pop-up-overlay']//img[contains(@src,'verify-logo')]");
  public final static By AGE_VERIFY_LOGO_FOOTER=By.cssSelector("span.breadcrumbs-logo.age-verify-logo");

  public String getAgeVerificationHeading() {
    return waitForExpectedElement(ageVerificationHeading).getText();
  }

  public void clickPlaceOrderButton() {
    waitForExpectedElement(placeOrderButton,10).click();
  }

}
