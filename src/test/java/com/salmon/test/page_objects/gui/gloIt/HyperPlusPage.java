package com.salmon.test.page_objects.gui.gloIt;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HyperPlusPage extends PageObject {
    private final GloItCheckoutPage gloItCheckoutPage;
    private final GloItProductListPage gloItProductListPage;

    public HyperPlusPage(GloItCheckoutPage gloItCheckoutPage, GloItProductListPage gloItProductListPage) {

        this.gloItCheckoutPage = gloItCheckoutPage;
        this.gloItProductListPage=gloItProductListPage;
    }
    private static final By PDP_PRODUCT_DESCRIPTION = By.cssSelector("div.widget.block.block-static-block>div:nth-child(3)");
    private final static By PDP_HYPER_PLUS_CONTENTS_BOX = By.cssSelector("div.widget.block.block-static-block>div:nth-child(6)>div>div>div");
    private final static By PDP_HYPER_PLUS_ADD_TO_CART = By.cssSelector("button#product-addtocart-button");
    private final static By PDP_HYPER_PLUS_MAIN_COLOURS = By.cssSelector("div.sliding-panel-sections.desktop-only>div>div.configurator-section.main>div.colours-swatches.main>div.colour-swatch.main.is-available");
    private final static By PDP_HYPER_PLUS_MAIN_COLOUR = By.cssSelector("div.sliding-panel-sections.desktop-only>div>div.configurator-section.main>div.colours-swatches.main>div.colour-swatch.main.is-available>span");
    private final static By PDP_HYPER_PLUS_SIDE_COLOURS = By.cssSelector("div.sliding-panel-sections.desktop-only>div>div.configurator-section.side>div.colours-swatches.side>div.colour-swatch.side.is-available");
    private final static By PDP_HYPER_PLUS_SIDE_COLOUR = By.cssSelector("div.sliding-panel-sections.desktop-only>div>div.configurator-section.side>div.colours-swatches.side>div.colour-swatch.side.is-available>span");
    private final static By PDP_HYPER_PLUS_SPINNER = By.cssSelector("div#hyper-plus-loader>div.loader");
    public static String main_colour = null;
    public static String side_colour = null;
    public static String[] hyper_plus_price;
    public static float pdp_hyper_plus_price;
    private final static By PDP_HYPER_PLUS_PRICE = By.cssSelector("span.normal-price>span>span>span");
    private final static By HERO_BANNER = By.cssSelector("div.bat-hero-content-container>a");
    public final static By HYPER_PLUS_LOADER = By.cssSelector("div#hyper-plus-loader");

    public boolean productDescriptionIsDisplayed() {
        return waitForExpectedElement(PDP_PRODUCT_DESCRIPTION).isDisplayed();
    }

    public boolean boxContentsIsDisplayed() {
        return waitForExpectedElement(PDP_HYPER_PLUS_CONTENTS_BOX).isDisplayed();
    }

    public boolean addToCartButtonIsDisplayed() {
        return waitForExpectedElement(PDP_HYPER_PLUS_ADD_TO_CART).isDisplayed();
    }

    public void selectMainBodyColour() {
        List<WebElement> main_colours = waitForExpectedElements(PDP_HYPER_PLUS_MAIN_COLOURS);
        if (main_colours.size() > 0) {
            for (WebElement ele : main_colours) {
                try {
                    if (ele.findElement(PDP_HYPER_PLUS_MAIN_COLOUR).isEnabled()) {
                        main_colour = ele.findElement(PDP_HYPER_PLUS_MAIN_COLOUR).getText();
                        ele.findElement(PDP_HYPER_PLUS_MAIN_COLOUR).click();
                        break;
                    }
                } catch (NoSuchElementException e) {
                    LOG.info("Colour not available so moving to next");
                }
            }
        } else {
            LOG.info("No colours available in main");
        }
    }

    public void selectSideBodyColour() {
        List<WebElement> main_colours = waitForExpectedElements(PDP_HYPER_PLUS_SIDE_COLOURS);
        if (main_colours.size() > 0) {
            for (WebElement ele : main_colours) {
                try {
                    if (ele.findElement(PDP_HYPER_PLUS_SIDE_COLOUR).isEnabled()) {
                        side_colour = ele.findElement(PDP_HYPER_PLUS_SIDE_COLOUR).getText();
                        ele.findElement(PDP_HYPER_PLUS_SIDE_COLOUR).click();
                        break;
                    }
                } catch (NoSuchElementException e) {
                    LOG.info("Colour not available so moving to next");
                }
            }
        } else {
            LOG.info("No colours available in side");
        }
    }

    public void navigateToHyperPlusPDP() {
        waitForExpectedElement(gloItProductListPage.PLP_GLO_DROPDOWN);
        clickByElementByQueryJSExecutor(gloItProductListPage.PLP_GLO_DROPDOWN);
        waitForExpectedElement(By.linkText(UrlBuilder.getMessage("hyperPlus.key")));
        clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("hyperPlus.key")));
        waitForAjaxElementNotToBePresent(getWebDriver(),40);
        waitForExpectedElement(HERO_BANNER);
        clickByElementByQueryJSExecutor(HERO_BANNER);
    }

    public void clickOnAddToCart() {
        hyper_plus_price = waitForExpectedElement(PDP_HYPER_PLUS_PRICE).getText().split(UrlBuilder.getMessage("JapaneseCurrencySymbol.key"));
        pdp_hyper_plus_price = Float.parseFloat(hyper_plus_price[0].replaceAll(",", ""));
        if (main_colour != null && side_colour != null) {
            waitForItemToBeClickableAndClick(PDP_HYPER_PLUS_ADD_TO_CART);
            waitForElementToAppearAndDisappear(GloItProductListPage.SUCCESS_MESSAGE, 1, 1);
            waitForElementToDisappear(HYPER_PLUS_LOADER,30);
        }
    }

    public void addToCartMultipleTimes(int numOfClicksOnCTA) {
        for (int i = 1;i <= numOfClicksOnCTA; i++) {
            waitForExpectedElement(PDP_HYPER_PLUS_ADD_TO_CART,20);
            hoverOnElement(PDP_HYPER_PLUS_ADD_TO_CART);
            clickUsingJS(PDP_HYPER_PLUS_ADD_TO_CART);
            waitForAjaxElementNotToBePresent(getWebDriver(), 70);
        }
    }

    public void assertHyperPlusProductNameOnCheckout() {
        assertTrueExpectedTextContainsActualTextWithCustomError(waitForExpectedElement(GloItCheckoutPage.PRODUCT_NAME).getText(),side_colour);
        assertTrueExpectedTextContainsActualTextWithCustomError(waitForExpectedElement(GloItCheckoutPage.PRODUCT_NAME).getText(),UrlBuilder.getMessage("hyperPlusName.key"));
    }

}