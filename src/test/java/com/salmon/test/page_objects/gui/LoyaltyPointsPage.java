package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoyaltyPointsPage extends PageObject {

    private final static By LOYALITY_POINTS_SECTION = By.cssSelector("table.data.table.table-reward-history");
    private final static By ORDER_NUMBER_LOYALITY_POINTS = By.xpath("//td[@class='col reason']");
    public static final By LOYALTY_POINTS_REWARD_PLACER = By.cssSelector("#reward_placer");
    public static final By LOYALTY_POINTS_REWARD_TEXT_CSS = By.cssSelector(".payment-option-inner  > span");
    public static final By LOYALTY_POINTS_REWARD_REDEEM_BUTTON = By.cssSelector(".action.action-use");
    private static final By LOYALTY_POINTS_TD = By.xpath("//following::td[@class='col points'][1]");

    private OrderViewPage orderViewPage;
    private PaymentPage paymentPage;
    private ScenarioContext scenarioContext;

    public LoyaltyPointsPage(OrderViewPage orderViewPage, OrderSuccessPage orderSuccessPage, PaymentPage paymentPage, ScenarioContext scenarioContext) {
        this.orderViewPage = orderViewPage;
        this.paymentPage = paymentPage;
        this.scenarioContext = scenarioContext;
    }

    public void clickLoyaltyPointsLink() {
        clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("loyaltyPointsLinkText.key")));
    }

    public void verifyLoyaltyPointsForOrder() {
        double subTotal = Double.parseDouble(orderViewPage.getSubTotalOrItemPrice().split(" ")[0].replace(",", "."));
        double discount = Double.parseDouble(orderViewPage.getDiscountPrice().split(" ")[0].replace(",", "."));
        int orderPrice = (int)(subTotal - discount);
        String orderNumber = (String) scenarioContext.getContext(Context.ORDER_NUMBER);
        clickLoyaltyPointsLink();
        waitForExpectedElement(LOYALITY_POINTS_SECTION, 10);
        for (WebElement rowData : getTableCols(LOYALITY_POINTS_SECTION)) {
            if (rowData.findElement(ORDER_NUMBER_LOYALITY_POINTS).getText().contains(orderNumber)) {
                String actualLoyaltyPoints = rowData.findElement(By.xpath("//*[contains(text(),'" + orderNumber + "')]//following::td[@class='col points'][1]")).getText();
                //According to #727496, 1€ spent on vuse.com/fr/fr = 10 loyalty points instead of 5loyalty points only in September.
                String expectedLoyaltyPoints = String.valueOf(orderPrice * 5);
                assertThat(actualLoyaltyPoints).as("expected Loyalty points are : " + expectedLoyaltyPoints + " and actual loyalty points are" + actualLoyaltyPoints).contains(expectedLoyaltyPoints);
            }
            break;
        }
    }

    public void verifyLoyaltyPointsRedeemOptionInCheckoutPage() {
        assertTrue(waitForExpectedElement(LOYALTY_POINTS_REWARD_PLACER).isDisplayed());
        assertTrue(waitForExpectedElement(LOYALTY_POINTS_REWARD_TEXT_CSS).getText()
                .contains(UrlBuilder.getMessage("loyaltyPointsRedeemLimitText.key")));
        assertTrue(waitForExpectedElement(LOYALTY_POINTS_REWARD_REDEEM_BUTTON).isDisplayed());
        assertTrue(waitForExpectedElement(LOYALTY_POINTS_REWARD_REDEEM_BUTTON).getText()
                .contains(UrlBuilder.getMessage("loyaltyPointsRedeemButtonText.key")));
    }

    public void verifyTotalPriceAfterRedeemingLoyaltyPoints() {
        Double totalPriceBefore = Double.valueOf(paymentPage.getTotalPriceText().split(" ")[0].replace(",", "."));
        String loyaltyPointsText = waitForExpectedElement(LOYALTY_POINTS_REWARD_TEXT_CSS).getText();
        Double redeemingAmount = Double.valueOf(loyaltyPointsText.substring(loyaltyPointsText.indexOf("(") + 1, loyaltyPointsText.indexOf(")"))
                .split(" ")[0].replace(",", "."));
        getWebDriver().findElement(LOYALTY_POINTS_REWARD_REDEEM_BUTTON).click();
        waitForAjaxElementNotToBePresent(getWebDriver(), 20);
        paymentPage.paymentPageDetailsConfirmed();
        Double totalPriceAfter = Double.valueOf(paymentPage.getTotalPriceText().split(" ")[0].replace(",", "."));
        assertEquals(totalPriceAfter, totalPriceBefore - redeemingAmount);
    }

    public void verifyLoyaltyPointsAwardedForReferringAFriend() {
        waitForExpectedElement(LOYALITY_POINTS_SECTION, 10);
        for (WebElement colData : getTableCols(LOYALITY_POINTS_SECTION)) {
            if (colData.findElement(ORDER_NUMBER_LOYALITY_POINTS).getText().contains(UrlBuilder.getMessage("rafInvitationText.key"))) {
                assertTrue(colData.findElement(LOYALTY_POINTS_TD).getText()
                        .contains("500"));
            }
            break;
        }
    }
}
