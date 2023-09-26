package com.salmon.test.step_definitions.gui.gloIt;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.BATHelper;
import com.salmon.test.page_objects.gui.HomePage;
import com.salmon.test.page_objects.gui.gloIt.GloItCheckoutPage;
import com.salmon.test.page_objects.gui.gloIt.GloItProductListPage;
import com.salmon.test.page_objects.gui.gloIt.OrderHistoryPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.testng.Assert;

import static com.salmon.test.framework.helpers.WebDriverHelper.getWebDriver;
import static com.salmon.test.page_objects.gui.gloIt.OrderHistoryPage.M_MINI_CART_PROD_QTY_GLOIT;
import static org.testng.AssertJUnit.assertTrue;

public class OrderHistorySteps extends PageObject {

    private final OrderHistoryPage orderHistoryPage;
    private final HomePage homePage;
    private final GloItCheckoutPage gloItCheckoutPage;
    private final GloItProductListPage gloItProductListPage;
    private final BATHelper batHelper;
    public OrderHistorySteps(OrderHistoryPage orderHistoryPage, HomePage homePage, GloItCheckoutPage gloItCheckoutPage, GloItProductListPage gloItProductListPage,BATHelper batHelper) {
        this.orderHistoryPage = orderHistoryPage;
        this.homePage=homePage;
        this.gloItCheckoutPage=gloItCheckoutPage;
        this.gloItProductListPage=gloItProductListPage;
        this.batHelper=batHelper;
    }

    @And("^verify My Orders Table headings are as expected$")
    public void verifyMyOrdersTableHeadngsAreAsExpected() {
        Assert.assertTrue(orderHistoryPage.assertMyOrderTableHeadingsAreCorrect());

    }

    @And("^assert Generic message is displayed$")
    public void assertGenericMessageIsDisplayed() {
        Assert.assertEquals(orderHistoryPage.noHistoryOrders(), UrlBuilder.getMessage("expectedNohistorderMsg.key"));
    }

    @Then("^user is redirected to chosen order detail page$")
    public void userIsRedirectedToChosenOrderDetailPage() {
        System.out.println("User navigated to order page");
    }

    @And("^verify My Order table data is as expected$")
    public void verifyMyOrderTableDataIsAsExpected() {
        assertTrue("Expected contents or Order links are not present in the order table contents", orderHistoryPage
            .loopThurOrderTableContentsAndReturnifOrderLinksPresent());
    }

    @And("^user selects over 18 years age confirmation option$")
    public void userSelectsOveryearsAgeConfirmationOption() {
        orderHistoryPage.tryClickIAmOver18JP();
    }

    @And("^User is logged in using '(.*)' cookie$")
    public void userIsLoggedInUsingCookie(String strExpectedTitle) {
        Cookie name = new Cookie(UrlBuilder.getMessage("cookieName.key"), UrlBuilder.getMessage(strExpectedTitle));
        getWebDriver().manage().addCookie(name);
        getWebDriver().navigate().refresh();
        getWebDriver().navigate().to(UrlBuilder.getMessage("test.magentojp.url.key") + "home");
    }

    @And("^assert order date on order history$")
    public void assertOrderDateOnOrderHistory() {
        orderHistoryPage.assertOrderDateOnOrderHistory();

    }

    @And("^On next page click user is navigated to next page$")
    public void onNextPageClickUserIsNavigatedToNextPage() {
        orderHistoryPage.navigateToNextPage();
    }

    @And("^On previous page click user is navigated to previous page$")
    public void onPreviousPageClickUserIsNavigatedToPreviousPage() {
        orderHistoryPage.navigateToPreviousPage();
    }

    @And("^list is updated with further orders$")
    public void listIsUpdatedWithFurtherOrders() {
        orderHistoryPage.previousOrderlistUpdated();
    }

    @And("^verify My Order date is displayed$")
    public void verifyMyOrderDateIsDisplayed() {
        Assert.assertTrue(orderHistoryPage.isOrderDateDisplayed());
    }

    @And("^user navigates to cart page$")
    public void navigateToCartPage() throws InterruptedException {
        homePage.clickOnBasketIcon();
        if (UrlBuilder.getLocale().equalsIgnoreCase("glojp")) {
            homePage.eyesCheckBasketPage();
        }
    }

    @And("^user is redirected order success page$")
    public void userIsRedirectedOrderSuccesPage() {
        orderHistoryPage.getGeneratedOrderNumber();
    }

    @And("^verify Order is displayed in Order history page$")
    public void verifyOrderIsDisplayedInOrderHistoryPage() {
        orderHistoryPage.verifyOrderInOrderHistoryPage();
    }

    @Then("^user clicks View historical orders button$")
    public void userCanClicksViewHistoricalOrdersButton() {
        Assert.assertTrue(orderHistoryPage.historyButtonIsPresent());
        orderHistoryPage.clickOnHistoricOrders();
    }

    @And("^user can see the icon$")
    public void userCanSeeTheIcon() {
        Assert.assertTrue(orderHistoryPage.historyIconIsPresent());
    }

    @And("^user can see the Historical orders heading$")
    public void userCanSeeTheHistoricalOrdersHeading() {
        orderHistoryPage.verifyHistoricOrdersTitle();
    }

    @And("^user can see the explaining message$")
    public void userCanSeeTheExplainingMessage() {
        Assert.assertTrue(orderHistoryPage.isHistoricOrdersMessageIsDisplayed());
        orderHistoryPage.verifyHistoricOrdersMessage();
    }

    @Then("^navigate to cart and increase cart qty$")
    public void navigateToCartAndIncreaseCartQty() {
        orderHistoryPage.navigateToCart();
        orderHistoryPage.updateBasketByIncreasingQty();
    }

    @Then("^navigate to cart and decrease cart qty$")
    public void navigateToCartAndDecreaseCartQty() {
        orderHistoryPage.navigateToCart();
        orderHistoryPage.updateBasketByDecreasingQty();
    }

    public boolean isBasketEmpty(){
        By basketCount = By.cssSelector("body > div.bat-wrapper.visible-true > div > div > div:nth-child(1) > div > div.header.aem-GridColumn.aem-GridColumn--default--12 > bat-header-glo > div.bat-header > div > header > div.bat-header--wrapper > div.bat-header--right.d-flex > div > div.bat-header-cart.bat-header-icon.right-icon > a > span.bat-header-cart-count.small.active");
        try {
            if (UrlBuilder.getLocale().equals("it") && UrlBuilder.isMobile())
            {
                LOG.info("\n ****** BAKSET COUNT : " + orderHistoryPage.waitForExpectedElement(M_MINI_CART_PROD_QTY_GLOIT).getText());
                LOG.info("\nBasket needs emptying");
                return false;
            }
            else
            {
                LOG.info("\n ****** BAKSET COUNT : " + orderHistoryPage.waitForExpectedElement(basketCount).getText());
                LOG.info("\nBasket needs emptying");
                return false;
            }
        } catch (Exception e) {
            LOG.info("\n Basket is empty!");
            return true;
        }
    }

    @And("^user navigates to Cart page and empty basket$")
    public void userNavigatesToCartPageAndEmptyBasket() throws InterruptedException {
        if(UrlBuilder.getLocale().equalsIgnoreCase("glojp")) {
            homePage.eyesCheckHomePage();
        }
        homePage.emptyBasket();
    }

    @And("^verify view order link is present in historic orders table$")
    public void verifyViewOrderLinkIsPresentInHistoricOrdersTable() {
        assertTrue("Expected contents or Order links are not present in the order table contents", orderHistoryPage
            .loopThurOrderTableContentsAndReturnifOrderLinksPresent());

    }

    @And("^verify Historic Orders Table headings are as expected$")
    public void verifyHistoricOrdersTableHeadingsAreAsExpected() {
        Assert.assertTrue(orderHistoryPage.assertHistoricOrdersTableHeadingsAreCorrect());
    }

    @And("^user can navigate back to the historic order list via breadcrumb$")
    public void userCanNavigateBackToTheHistoricOrderListViaBreadcrumb() {
        orderHistoryPage.clickHistoricOrdersBreadcrumb();
    }

    @And("^users clicks on the View Order link for historic order$")
    public void usersClicksOnTheViewOrderLinkForHistoricOrder() {
        orderHistoryPage.usersClicksOnTheViewOrderLinkForHistoricOrder();
    }

    @And("^user clicks on person icon$")
    public void userClicksOnPersonIcon() {
        orderHistoryPage.clickOnPersonIcon();
    }

    @And("^user clicks on My Orders$")
    public void userClicksOnMyOrders() {
        orderHistoryPage.clickOnMyOrders();
    }

    @And("^assert page title equals '(.*)'$")
    public void assertPageTitleEqualsYourOrdersKey(String expectedPageTitle) {
        homePage.waitForPage(20);
        String pageTitle = orderHistoryPage.getCurrentPageTitle();
        homePage.assertTrueWithCustomError(UrlBuilder.getMessage(expectedPageTitle), pageTitle);

    }

    @And("^user adds product to cart and navigates to checkout$")
    public void userAddsProductToCartAndNavigatesToCheckout() throws Throwable {
        switch (UrlBuilder.getLocale()) {
            case "it":
                if (!isBasketEmpty()){
                    homePage.emptyBasket();
                }
                batHelper.selectGLOProductAndViewBasket();
                homePage.proceedToCheckoutButton();
                break;
            default:
                if (isBasketEmpty()){
                    LOG.info("Basket is empty.. adding products to basket");
                    batHelper.selectGLOProductAndViewBasket();
                }
                homePage.clickOnBasketIcon();
                homePage.proceedToCheckoutButton();
                break;
        }

    }
}


