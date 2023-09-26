package com.salmon.test.page_objects.gui.admin;

import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;

public class DashBoardPage extends PageObject {

  public By dashboardText = By.cssSelector("h1[class='page-title']");

  public boolean isDashboardPresent(){
     try { return waitForExpectedElement(dashboardText,30).isDisplayed();}
     catch (Exception e) {return false; }
  }
}
