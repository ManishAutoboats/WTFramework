package com.salmon.test.step_definitions.gui.iss;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.admin.LoginPage;
import com.salmon.test.page_objects.gui.iss.IssBasketOverlayPage;
import com.salmon.test.page_objects.gui.iss.IssChooseYourFlavoursPage;
import com.salmon.test.page_objects.gui.iss.IssHomePage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import oracle.jdbc.util.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class IssSignInSteps {
private IssHomePage issHomePage;
private IssChooseYourFlavoursPage issChooseYourFlavoursPage;
private IssBasketOverlayPage issBasketOverlay;
private int basketCount;
private String username = UrlBuilder.getMessage("inStoreSubUser");
private List<WebElement> storeList = new ArrayList<>();
private List<String> actualStores = new ArrayList<>();
private String confirmPasswordStatus;
private String newPasswordStatus;

public IssSignInSteps(IssHomePage issHomePage, IssChooseYourFlavoursPage issChooseYourFlavoursPage, IssBasketOverlayPage issBasketOverlay) {
    this.issHomePage = issHomePage;
    this.issChooseYourFlavoursPage = issChooseYourFlavoursPage;
    this.issBasketOverlay = issBasketOverlay;
}

    @When("^he clicks on STORE drop down with locations$")
    public void heClicksOnSTOREDropDownWithLocations() {
        issHomePage.selectStoreDropdownButton();
    }

    @Then("^he sees list of stores which are enabled for In-Store Subs$")
    public void heSeesListOfStoresWhichAreEnabledForInStoreSubs() {
    storeList = issHomePage.getStoreLists();
    for (WebElement webElement: storeList) {
        actualStores.add(webElement.getText());
    }
       assertThat(storeList.size() > 0)
               .as("ERROR: no stores were displayed in the dropdwon list").isTrue();
    }

    @And("^he can select the store from the drop down where he is sitting$")
    public void heCanSelectTheStoreFromTheDropDownWhereHeIsSitting() {
        issHomePage.selectStoreFromDropdown();
    }

    @When("^he enters incorrect USERNAME and PASSWORD$")
    public void heEntersIncorrectUSERNAMEAndPASSWORD() {
        String invalidUsername = "xxxxxxxxxxxxxx";
        String invalidPassword = "yyyyyyyyyyyyyy";
        issHomePage.enterUsername(invalidUsername);
        issHomePage.enterPassword(invalidPassword);
    }

    @And("^clicks on SIGN IN CTA$")
    public void clicksOnSIGNINCTA() {
        issHomePage.clickSignInCTA();
    }

    @Then("^he sees a message to enter correct details$")
    public void heSeesAMessageToEnterCorrectDetails() {
    String expectedErrorMessage = UrlBuilder.getMessage("invalidLoginError");
    String actualErrorMessage = issHomePage.getLoginErrorMessage();
    assertThat(expectedErrorMessage.equalsIgnoreCase(actualErrorMessage))
            .as("ERROR: expected error message was "+expectedErrorMessage+" but I got "+actualErrorMessage).isTrue();
    }

    @When("^he enters a valid USERNAME and PASSWORD$")
    public void heEntersAValidUSERNAMEAndPASSWORD() {
        issHomePage.enterUsername(username);
        issHomePage.enterPassword(UrlBuilder.getMessage("inStoreSubPassword"));
    }


    @Then("^he is signed into the InStore Subs$")
    public void heIsSignedIntoTheInStoreSubs() {
    String actualUsername = issHomePage.getLoggedInUsername(username);
    assertThat(actualUsername.equalsIgnoreCase(username))
            .as("ERROR: expected logged in user was "+username+" but got "+actualUsername).isTrue();
    }

    @Then("^he can see the Vuse logo on the header$")
    public void heCanSeeTheVuseLogoOnTheHeader() {
        assertThat(issHomePage.isLogoVisible()).as("ERROR: Could not find site logo").isTrue();
    }

    @Given("^the user is signed in$")
    public void theUserIsSignedIn() {
        issHomePage.enterUsername(username);
        issHomePage.enterPassword(UrlBuilder.getMessage("inStoreSubPassword"));
        issHomePage.clickSignInCTA();
    }

    @And("^is on the Challenge screen$")
    public void isOnTheChallengeScreen() {
        assertThat(issHomePage.isOnChallengeScreen())
                .as("ERROR: user is not on the Challenge screen").isTrue();
    }

    @Then("^he sees Vuse logo, sign in as staff member name$")
    public void heSeesVuseLogoSignInAsStaffMemberName() {
        heIsSignedIntoTheInStoreSubs();
    }

    @And("^a CTA link for sign out$")
    public void aCTALinkForSignOut() {
    assertThat(issHomePage.isSignOutLinkVisible()).as("ERROR: sign out link is not visible").isTrue();
    }

    @When("^he clicks on the Sign Out CTA in the header$")
    public void heClicksOnTheSignOutCTAInTheHeader() throws InterruptedException {
        issHomePage.clickOnSignOut();
    }

    @Then("^he should navigate back to the Staff Sign In screen$")
    public void heShouldNavigateBackToTheStaffSignInScreen() {
    assertThat(issHomePage.pageHeadingIs("signInText")).as("ERROR: was not returned to sign in page").isTrue();
    }

    @And("^accepts the challenge screen$")
    public void acceptsTheChallengeScreen() {
    issHomePage.acceptAgeChallenge();
    }

    @And("^click on Start Subscription button$")
    public void clickOnStartSubscriptionButton() {
    issHomePage.clickStartSubscription();
    }

    @And("^the store list is in alphabetical order$")
    public void theStoreListIsInAlphabeticalOrder() {
        List<String> expectedStores = new ArrayList<>(actualStores);
        Collections.sort(expectedStores, String.CASE_INSENSITIVE_ORDER);
        assertThat(actualStores).as("ERROR: dropdown list of stores is not sorted").isEqualTo(expectedStores);
    }

    @And("^he adds item (\\d+) to the basket$")
    public void heAddsItemToTheBasket(int item) {
        issChooseYourFlavoursPage.clickOnPlusCTAForItem(item);
        issChooseYourFlavoursPage.chooseStrength();
        issChooseYourFlavoursPage.addToBasket();
    }

    @When("^he clicks on the basket CTA$")
    public void heClicksOnTheBasketCTA() {
        issChooseYourFlavoursPage.clickBasketButton();
    }

    @Then("^an overlay opens with item details$")
    public void anOverlayOpensWithItemDetails() {
        assertThat(issBasketOverlay.overlayIsDisplayed()).as("ERROR: basket overlay was not displayed").isTrue();
    }

    @And("^he can see \"([^\"]*)\" details$")
    public void heCanSeeDetails(String detail) {
        issBasketOverlay.basketOverlayContains(detail);
    }

    @When("^he clicks the remove icon in front of the item$")
    public void heClicksTheRemoveIconInFrontOfTheItem() throws InterruptedException {
        basketCount = issBasketOverlay.basketCount();
        issBasketOverlay.clickRemoveIcon();
}

    @Then("^the item gets removed from the basket$")
    public void theItemGetsRemovedFromTheBasket() throws InterruptedException {
    assertThat(basketCount > issBasketOverlay.basketCount()).as("ERROR: item was not removed from the basket").isTrue();
    }

    @When("^he clicks the increase quantity icon \"([^\"]*)\" times$")
    public void heClicksTheIncreaseQuantityIconTimes(int increaseBy)  {
        issBasketOverlay.increaseItemQty(increaseBy);
    }

    @And("^he clicks the decrease quantity item \"([^\"]*)\" times$")
    public void heClicksTheDecreaseQuantityItemTimes(int decreaseBy) throws InterruptedException {
        issBasketOverlay.decreaseItemQty(decreaseBy);
    }

    @Then("^the final total quantity for this item is \"([^\"]*)\"$")
    public void theFinalTotalQuantityForThisItemIs(int expectedFinalQty) throws Throwable {
        int actualFinalQty = issBasketOverlay.getItemQty();
        assertThat(expectedFinalQty == actualFinalQty )
                .as("ERROR expected final quantity was "+expectedFinalQty+" but got "+actualFinalQty).isTrue();
    }

    @Given("^user navigates to the Choose Your Flavours screen$")
    public void userNavigatesToTheChooseYourFlavoursScreen() {
        issHomePage.enterUsername(username);
        issHomePage.enterPassword(UrlBuilder.getMessage("inStoreSubPassword"));
        issHomePage.clickSignInCTA();
        issHomePage.acceptAgeChallenge();
        issHomePage.clickStartSubscription();
    }

    @And("^the Staff ID filed is labelled correctly$")
    public void theStaffIDFiledIsLabelledCorrectly() {
    String expectedStaffIdlabel = UrlBuilder.getMessage("loginStaffIdLabel");
    String actualStaffIdLabel = issHomePage.getStaffIdLabel();
        assertThat(expectedStaffIdlabel.equalsIgnoreCase(actualStaffIdLabel))
                .as("ERROR: expected Staff ID label was "+expectedStaffIdlabel+" but got "+actualStaffIdLabel).isTrue();
    }

    @And("^can see the scan code under the retrieve a previous quote section$")
    public void canSeeTheScanCodeUnderTheRetrieveAPreviousQuoteSection() {
        assertThat(issHomePage.isScanCodeCtaDisplayed())
                .as("ERROR: Scan code CTA not displayed").isTrue();
    }

    @When("^he clicks on the Scan Code CTA$")
    public void heClicksOnTheScanCodeCTA() {
        issHomePage.clicksOnScanCodeCta();
    }

    @Then("^he gets an overlay to scan the code$")
    public void heGetsAnOverlayToScanTheCode() {
        assertThat(issHomePage.isScanCodeOverlayIsDisplayed())
                .as("ERROR: Scan code overlay not displayed").isTrue();
    }

    @When("^he clicks on Cancel Scan CTA$")
    public void heClicksOnCancelScanCTA() {
        issHomePage.clickCancelScan();
    }

    @And("^user returns to the main site$")
    public void userReturnsToTheMainSiite() {
        UrlBuilder.revisitBATHomePage();
    }

    @Given("^returns to the Choose Your Flavours screen$")
    public void returnsToTheChooseYourFlavoursScreen() {
        issHomePage.acceptAgeChallenge();
        issHomePage.clickStartSubscription();
    }

    @And("^the user clicks on the Change Password link$")
    public void theUserClicksOnTheChangePasswordLink() {
            issHomePage.clickChangePassword();
        }

    @Then("^The change password page is displayed with correct components$")
    public void theChangePasswordPageIsDisplayedWithCorrectComponents() {
        assertThat(issHomePage.validChangePasswordPage()).as("ERROR: invalid change password page").isTrue();
    }

    @When("^I enter a \"([^\"]*)\" current password$")
    public void iEnterACurrentPassword(String currentPasswordStatus) {
        issHomePage.enterChangePassword("current", currentPasswordStatus);
    }

    @And("^I enter a \"([^\"]*)\" confirm password$")
    public void iEnterAConfirmStatus(String confirmPwdStatus) throws Throwable {
        confirmPasswordStatus = confirmPwdStatus;
        issHomePage.enterChangePassword("confirm", confirmPwdStatus);
    }

    @And("^I enter a \"([^\"]*)\" new password$")
    public void iEnterANewPassword(String newPwdStatus) throws Throwable {
        newPasswordStatus = newPwdStatus;
        issHomePage.enterChangePassword("new", newPwdStatus);
    }

    @When("^I submit the password change$")
    public void iSubmitThePasswordChange() {
        issHomePage.submitChangePassword();
    }

    @Then("^I see the correct error message$")
    public void iSeeTheCorrectErrorMessage() {
        String expectedErrorMessage;
        if (newPasswordStatus.equals("null") && confirmPasswordStatus.equals("null")) {
            expectedErrorMessage = UrlBuilder.getMessage("passwordTooShortError");
        } else if (newPasswordStatus.equals(confirmPasswordStatus)) {
            expectedErrorMessage = UrlBuilder.getMessage("invalidLoginError");
        } else {
            expectedErrorMessage = UrlBuilder.getMessage("paaswordsNotSameError");
        }
        issHomePage.validateErrorMessage(expectedErrorMessage);
    }

    @And("^the user signs in with a \"([^\"]*)\" password$")
    public void theUserSignsInWithAPassword(String passwordStatus) throws Throwable {
    if (passwordStatus.equalsIgnoreCase("valid")) {
        issHomePage.enterUsername(username);
        issHomePage.enterPassword(UrlBuilder.getMessage("loginValidPassword.key"));
        issHomePage.clickSignInCTA();
    }
    }
}
