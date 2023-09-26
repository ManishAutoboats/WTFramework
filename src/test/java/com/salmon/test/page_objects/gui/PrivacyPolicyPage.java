package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrivacyPolicyPage extends PageObject {

  // ELEMENT MAPPING
    public By pageHeading = By.cssSelector("span.base"); // Forgot Your Password

    public String getPageHeading(){
      return waitForExpectedElement(pageHeading).getText();
    }

  public List<String> getExpectedpolicies() {
    List<String> subTitleList = new ArrayList<>();
    String[] storeSubTitle = new String[4];
    for(int i=0;i<4;i++){
      storeSubTitle[i]="deliveryReturnPolicies"+i+".Key";
    }
    Collections.addAll(subTitleList,storeSubTitle);
    return subTitleList;
  }

}
