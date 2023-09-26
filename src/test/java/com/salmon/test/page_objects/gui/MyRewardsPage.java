package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import cucumber.api.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class MyRewardsPage extends PageObject
{

    private RegistrationPage registrationPage;
    public MyRewardsPage(RegistrationPage registrationPage) {
        this.registrationPage = registrationPage;
    }

    public static final By DEFAULT_STATUS_EXPLORER = By.cssSelector("div[class*='ac-user-details'] > div:nth-child(1) div[class='ac-status-text']");
    public static final By MY_REWARDS_DASHBOARD_FRAME = By.id("s15-dashboard-iframe");
    private static final By IMG_LOADER=By.cssSelector("div#socialannex_dashboard img");
    public static final By LOYALTY_SIGNUP_CHKBOX = By.cssSelector(".email-mark .emailsquared");
    public static final By LOYALTY_SIGNUP_BUTTON = By.cssSelector("button#btn_join_now");
    public static final By CLUB_VORTEILE_HEADER = By.cssSelector("div.ac-reards-header");
    public static final By VIP_VORTEILE_HEADER = By.cssSelector("#benefit > div:nth-child(3) > div div");
    public static final By EPEN_EPOD_LABEL = By.cssSelector("#acBadgeHref-100 div.bottom-content.acColor-100");
    public static final By BESTELLEN_ENABLED = By.cssSelector("div#acCompleteFlavour2");
    public static final By PROBIEREN_ENABLED = By.cssSelector("div#acCompleteFlavour5");
    public static final By BESTELLEN_ENABLED_TEXT = By.cssSelector("div#acCompleteFlavour2 a div.bottom-content");
    public static final By PROBIEREN_ENABLED_TEXT = By.cssSelector("div#acCompleteFlavour5 a div.bottom-content");
    public final static By DISCOUNT_RULE_APPLIED = By.cssSelector("span.rule-name");
    public final static By DISCOUNT_AMOUNT = By.cssSelector("span.rule-amount");
    public final static By FREE_ITEMS_BLOCK= By.cssSelector("div.widget.block.block-banners");
    public final static By DISCOUNTS_SUMMARY_SECTION=By.cssSelector("table.data.table.totals");
    public final static By CUSTOMER_TIER_STATUS = By.cssSelector("div.ac-status-text");
    public final static By CUSTOMER_TIER_FRAME = By.cssSelector("iframe#s15-dashboard-iframe");
    private final static By REQUIRED_EMAIL_CHECK_INSIDERSCLUB=By.cssSelector("input#emailsquared");
    private final static By REGISTER_NOW_LINK_INSIDERSCLUB=By.cssSelector("button#btn_join_now");
    public final static By TO_DISCOUNT_CODE_POP_OUT_BUTTON=By.cssSelector("button.popout-button-text");
    private final List<WebElement> LOYALTY_TIRE = getWebDriver().findElements(By.cssSelector("#sub > div.container_element > div > a"));
    private final static By ENTER_CLUB = By.cssSelector("#btn_join_now");
    private final static By INSIDE_CLUB_CHECKOUT = By.cssSelector("#partial-customerbalance-available-amount");
    private final static By INSIDE_CLUB_AMOUNT = By.cssSelector("#partial-spend-amount");
    private final static By APPLY_INSIDE_CLUB = By.cssSelector("#partial-spend");
    private final static By CREDIT_APPLY_CONFIRM = By.cssSelector("#checkout > div.messages > div > div");
    private final static By CREDIT_DEDUCT_SUMMARY = By.cssSelector("div.opc-block-summary > table > tbody > tr.totals.balance > td > span");
    private final static By DELETE_CREDIT = By.cssSelector("#delete-customer-balance");
    private final static By TOTAL_IN_SUMMARY = By.cssSelector("#opc-sidebar > div.opc-block-summary > table > tbody > tr.grand.totals.incl > td > strong > span");
    private final static By LOYALTY_ERROR = By.cssSelector("#checkout > div.messages > div > div");
    private final static By CREDIT_IN_DASHBOARD = By.cssSelector("#acWalletBalance");
    private static float CREDIT_CART;
    private final static By CREDIT_DEDUCT_SUMMARY_CART = By.cssSelector("#cart-totals > div > table > tbody > tr.totals.balance > td");
    public final static By TOTAL_IN_CART = By.cssSelector("#cart-totals > div > table > tbody > tr.grand.totals.incl > td > strong > span");
    private final static By DELETE_CREDIT_CART = By.cssSelector("#cart-totals > div > table > tbody > tr.totals.balance > th > a");
    private final static By LOYALTY_ERROR_CART = By.cssSelector("#maincontent > div.page.messages > div.messages > div");


    public String fetchClubVorteileHeaderText() {
        frameToBeAvailableAndSwitchToIt(MY_REWARDS_DASHBOARD_FRAME);
        String clubName = waitForExpectedElement(CLUB_VORTEILE_HEADER).getText();
        getWebDriver().switchTo().defaultContent();
        return clubName;
    }

    public void validateMainDashboardOptions(DataTable dataTable) {
        isElementDisplayedBySeconds(MY_REWARDS_DASHBOARD_FRAME,20);
        frameToBeAvailableAndSwitchToIt(MY_REWARDS_DASHBOARD_FRAME);
        for (List<String> elementItem : dataTable.raw()) {
            for(WebElement element : LOYALTY_TIRE) {
                assertThat(element.getText().equals(elementItem));
                assertThat(isElementClickable((By) element));
            }
        }
    }

    public boolean validateEnterClubButton() {
        return isElementDisplayedBySeconds(ENTER_CLUB,10);
    }

    public void switchToLoyaltyiFrame() {
        waitForAjaxElementNotToBePresent(webDriver,10);
        waitForExpectedElement(MY_REWARDS_DASHBOARD_FRAME, 20);
        frameToBeAvailableAndSwitchToIt(MY_REWARDS_DASHBOARD_FRAME);
    }

    public float validateInsiderClubInCheckout() {
        waitForExpectedElement(INSIDE_CLUB_CHECKOUT, 10);
        CREDIT_CART = Float.parseFloat(waitForExpectedElement(INSIDE_CLUB_CHECKOUT).getText().split(" ")[0].replace(",", ".").replace("€", "").replace("0","00"));
        return CREDIT_CART;
    }

    public void enterClubCredit(String amount){
        try{
            waitForExpectedElement(INSIDE_CLUB_CHECKOUT, 10);
        }catch (Exception e){
            refreshBrowser();
            waitForExpectedElement(INSIDE_CLUB_CHECKOUT, 10);
        }
        enterText(INSIDE_CLUB_AMOUNT,amount);
        waitAndClickByElementByJSExecutor(APPLY_INSIDE_CLUB,5);
        assertThat(waitForExpectedElement(CREDIT_APPLY_CONFIRM).getText().equals(UrlBuilder.getMessage("successMsgInsider.key")));
    }

    public String verifyTotalInSummary(){
        return waitForExpectedElement(TOTAL_IN_SUMMARY,10).getText();
    }

    public float verifyDeductedAmountOfPrice(){
        float amount = - (Float.valueOf(UrlBuilder.getMessage("availableCoin.key")) - Float.valueOf(waitForExpectedElement(INSIDE_CLUB_CHECKOUT, 10).getText().split(" ")[0].replace(",", ".").replace("€", "").replace("0","00")));
        return amount;
    }

    public float verifyDeductedWholeAmountOfPrice(){
        float amount = Float.valueOf(waitForExpectedElement(TOTAL_IN_SUMMARY, 10).getText().split(" ")[0].replace(",", ".").replace("€", "").replace("0","00"));
        return amount;
    }

    public float verifyDeductAmountInSummary(){
        float amo = Float.parseFloat(waitForExpectedElement(CREDIT_DEDUCT_SUMMARY).getText().split(" ")[0].replace(",", ".").replace("€", ""));
        return Float.parseFloat(waitForExpectedElement(CREDIT_DEDUCT_SUMMARY).getText().split(" ")[0].replace(",", ".").replace("€", "").replace("0","00"));
    }

    public void deleteDeductedCredit(){
        if(isElementDisplayedBySeconds(DELETE_CREDIT,5)){
            waitAndClickByElementByJSExecutor(DELETE_CREDIT,5);
            refreshBrowser();
        }
    }

    public void deleteDeductedCreditCart(){
        waitAndClickByElementByJSExecutor(DELETE_CREDIT_CART,5);
        refreshBrowser();
    }

    public void verifySuccessMsgCartRemoveCredit(){
        assertThat(getWebDriver().getPageSource().contains(UrlBuilder.getMessage("successMsgLoyaltyCart.key")));
    }

    public void verifyInsiderClubPageLoaded(){
        assertThat(webDriver.getCurrentUrl().contains("/insiders-club-info"))
                .as("ERROR: user is not on the insider club page").isTrue();
        assertThat(getWebDriver().getPageSource().contains(UrlBuilder.getMessage("signUpOptions.key")));
    }

    public void verifyInsiderClubTCPageLoaded(){
        if(!UrlBuilder.isIPhone()) {
            assertThat(webDriver.getCurrentUrl().contains("/terms-conditions-for-insiders-club"))
                    .as("ERROR: user is not on the insider club T&C page").isTrue();
        }
        assertThat(getWebDriver().getPageSource().contains(UrlBuilder.getMessage("loyaltyTandCTitle.key")));
    }

    public void verifyDeductAmountInCartSummary(String amount){
        assertTrue(getWebDriver().getPageSource().contains((UrlBuilder.getMessage("creditSummary.key"))));
        assertThat(waitForExpectedElement(CREDIT_DEDUCT_SUMMARY_CART).getText().contains(amount));
    }

    public void verifyErrorLoyaltyCredit(){
        assertThat(waitForExpectedElement(LOYALTY_ERROR).getText().equals(UrlBuilder.getMessage("errorMsgLoyalty.key")));
    }

    public String fetchVipVorteileHeaderText() {
        frameToBeAvailableAndSwitchToIt(MY_REWARDS_DASHBOARD_FRAME);
        String clubName = waitForExpectedElement(VIP_VORTEILE_HEADER).getText();
        getWebDriver().switchTo().defaultContent();
        return clubName;
    }

    public String getDefaultStatusTextForMyRewards() {
        return waitForExpectedElement(DEFAULT_STATUS_EXPLORER).getText();
    }

    public void ValidateDefaultStatusIsExplorer(String expectedText){
        waitForExpectedElement(MY_REWARDS_DASHBOARD_FRAME, 20);
        frameToBeAvailableAndSwitchToIt(MY_REWARDS_DASHBOARD_FRAME);
        assertThat(getDefaultStatusTextForMyRewards().equals(UrlBuilder.getMessage(expectedText)));
        getWebDriver().switchTo().defaultContent();
    }

    public void SignUpForLoyalty() throws InterruptedException {
        waitForExpectedElement(MY_REWARDS_DASHBOARD_FRAME, 20);
        frameToBeAvailableAndSwitchToIt(MY_REWARDS_DASHBOARD_FRAME);
        waitForExpectedElement(LOYALTY_SIGNUP_CHKBOX);
        getWebDriver().findElement(LOYALTY_SIGNUP_CHKBOX).click();
        waitForExpectedElement(LOYALTY_SIGNUP_BUTTON);
        getWebDriver().findElement(LOYALTY_SIGNUP_BUTTON).click();
        getWebDriver().switchTo().defaultContent();
    }

    public void clickOnBadgesButton() {
        waitForElementToAppearAndDisappear(IMG_LOADER,10,20);
        waitForExpectedElement(MY_REWARDS_DASHBOARD_FRAME, 20);
        frameToBeAvailableAndSwitchToIt(MY_REWARDS_DASHBOARD_FRAME);
        waitForExpectedElement(By.linkText(UrlBuilder.getMessage("Badges.key")),5);
        clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("Badges.key")));
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        getWebDriver().switchTo().defaultContent();
    }

    public String fetchEpodEpenTileLabel(){
        frameToBeAvailableAndSwitchToIt(MY_REWARDS_DASHBOARD_FRAME);
        String tileLabel = waitForExpectedElement(EPEN_EPOD_LABEL).getText();
        getWebDriver().switchTo().defaultContent();
        return tileLabel;
    }


    public String fetch2ItemTileStatus(){
        frameToBeAvailableAndSwitchToIt(MY_REWARDS_DASHBOARD_FRAME);
        String tileLabel = waitForExpectedElement(BESTELLEN_ENABLED,10).getCssValue("display");
        getWebDriver().switchTo().defaultContent();
        return tileLabel;
    }

    public String fetch2ItemTileText(){
        frameToBeAvailableAndSwitchToIt(MY_REWARDS_DASHBOARD_FRAME);
        String tileLabel = waitForExpectedElement(BESTELLEN_ENABLED_TEXT,10).getText();
        getWebDriver().switchTo().defaultContent();
        return tileLabel;
    }

    public String fetch5ItemTileStatus(){
        frameToBeAvailableAndSwitchToIt(MY_REWARDS_DASHBOARD_FRAME);
        String tileLabel = waitForExpectedElement(PROBIEREN_ENABLED,10).getCssValue("display");
        getWebDriver().switchTo().defaultContent();
        return tileLabel;
    }

    public String fetch5ItemTileText(){
        frameToBeAvailableAndSwitchToIt(MY_REWARDS_DASHBOARD_FRAME);
        String tileLabel = waitForExpectedElement(PROBIEREN_ENABLED_TEXT,10).getText();
        getWebDriver().switchTo().defaultContent();
        return tileLabel;
    }

    public String getAvailableCreditInDashboard(){
        String credit = waitForExpectedElement(CREDIT_IN_DASHBOARD,10).getText();
        return credit;
    }

    public void nonLoyaltyUserRegisterForLoyaltyOnSelectingRequiredCheckAndClickingRegisterNow() {
        waitAndClickByElementByJSExecutor(REQUIRED_EMAIL_CHECK_INSIDERSCLUB,10);
        waitAndClickByElementByJSExecutor(REGISTER_NOW_LINK_INSIDERSCLUB,5);
    }

    public void assertFirstVoucherLoyaltyPopUpNotDisplayedForNonLoyaltyUser(){
        assertEquals(0, getWebDriver().findElements(registrationPage.LOYALTY_POP_UP_CLOSE_BUTTON).size());
    }

    public void assertFirstVoucherLoyaltyPopUpDisplayedForLoyaltyUser(){
        assertTrue(getWebDriver().findElements(TO_DISCOUNT_CODE_POP_OUT_BUTTON).size()>0);
    }
}
