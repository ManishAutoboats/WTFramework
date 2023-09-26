package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TermsAndConditions extends PageObject {

  // ELEMENT MAPPING
  private By termsAndConditionsPageHeading = By.cssSelector("h1.page-title");
  public By logo = By.cssSelector("a.logo");
  private static final By LOYALTY_PAGE_SUB_HEADING_IT = By.cssSelector(".page-title-wrapper ~ p:nth-child(2)");
  private static final By LOYALTY_PAGE_SUB_HEADING = By.cssSelector("li.item.cms_page");

  public String getTermsAndConditionsPageHeading() {
    return waitForExpectedElement(termsAndConditionsPageHeading).getText().trim();
  }

  public String getTermsAndConditionsPageSubHeading(){
    if ("vuseit".equals(UrlBuilder.getLocale())) {
      return waitForExpectedElement(LOYALTY_PAGE_SUB_HEADING_IT).getText().trim();
    }
    return waitForExpectedElement(LOYALTY_PAGE_SUB_HEADING).getText().trim();

  }

  public List<String> getExpectedSubTitles() {
    List<String> subTitleList = new ArrayList<>();
    subTitleList.add(UrlBuilder.getMessage("IntroductionText.key"));
    subTitleList.add(UrlBuilder.getMessage("InfoAboutUsText.Key"));
    subTitleList.add(UrlBuilder.getMessage("AccessOurWebSiteText.key"));
    subTitleList.add(UrlBuilder.getMessage("OurContentText.key"));
    subTitleList.add(UrlBuilder.getMessage("YourPrivacyText.key"));
    subTitleList.add(UrlBuilder.getMessage("LisbilityText.key"));
    subTitleList.add(UrlBuilder.getMessage("EventsBeyoundText.key"));
    subTitleList.add(UrlBuilder.getMessage("OtherImportantText.key"));
    return subTitleList;
  }

}
