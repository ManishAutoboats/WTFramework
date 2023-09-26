package com.salmon.test.step_definitions.gui.admin;

import com.salmon.test.page_objects.gui.admin.DashBoardPage;
import com.salmon.test.page_objects.gui.admin.LoginPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class LoginPageSteps {

  private LoginPage loginPage;
  private DashBoardPage dashboardPage;

  public LoginPageSteps(LoginPage loginPage, DashBoardPage dashboardPage) {
    this.loginPage = loginPage;
    this.dashboardPage = dashboardPage;
  }

  @Given("^user navigates to Magento Admin home page$")
  public void userNavigatesToMagentoAdminHomePage() {
    loginPage.userNavigatesToMagentoAdminHomePage();
  }

  @And("^user submits username and password$")
  public void userSubmitsUsernameAndPassword() {
  loginPage.userLoginsMagentoAdminHomePageSuccessfully();
  }

  @Then("^user is logged in successfully$")
  public void userIsLoggedInSuccessfully() {
    loginPage.userIsLoggedInSuccessfully();
  }

  @Given("^user logins Magento Admin home page successfully$")
  public void userLoginsMagentoAdminHomePageSuccessfully() {
    loginPage.userLoginsMagentoAdminHomePageSuccessfully();
  }
}
