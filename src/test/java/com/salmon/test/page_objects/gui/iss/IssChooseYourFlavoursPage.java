package com.salmon.test.page_objects.gui.iss;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import lombok.*;
import org.openqa.selenium.*;

import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.assertThat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IssChooseYourFlavoursPage extends PageObject {
    public static final By START_AGAIN_LINK = By.cssSelector("span.icon.icon__start-again");
    public static final By ARE_YOU_SURE_OVERLAY_TITLE = By.cssSelector(" div > div > div.title > div.titelText > h1");
    public static final By OVERLAY_YES_BUTTON = By.cssSelector("div.model__content  button.btn.btn__primary");
    public static final By OVERLAY_NO_BUTTON = By.cssSelector("div.model__content  button.btn.btn__secondary");
    public static final By FLAVOUR = By.cssSelector("div.flavour__wrapper");
    public static final By ADD_CTA = By.cssSelector("span.icon.icon__cart");
    public static final By STRENGTH_MODAL = By.cssSelector("div.ReactModal__Content");
    public static final By STICKY_HEADER = By.cssSelector("main > div > div");
    public static final By STICKY_HEADER_TIER = By.cssSelector("span.tier-view-content");
    public static final By STICKY_HEADER_PRICE_PER_MONTH = By.cssSelector("button.btn.btn__dark");
    public static final By STICKY_HEADER_BASKET = By.cssSelector("#app > main > div > div > div > div button.btn.btn__primary");
    public static final By STRENGTH_PLUS_ICON = By.cssSelector("body  div.Z-rJOrm9uESAtW9CQ6eNX span.plus-icon");
    public static final By STRENGTH_MINUS_ICON = By.cssSelector("span.minus-icon");
    public static final By ADD_TO_CART_BUTTON = By.cssSelector("body > div > div > div > div > div > div > div > button");
    public static final By SEARCH_FIELD = By.cssSelector("input#search");
    public static final By SEARCH_SUGGESTION = By.cssSelector("#app > main  div._3eE_W0gb3Kt2gaw-P43oCW > div");
    public static final By SEARCH_SUGGESTION_ITEM = By.cssSelector("div.product-name");
    public static final By SEARCH_SUGGESTIONS_BLOCK = By.cssSelector("#app > main > div.y-WH4txbNtUvL2BO3WyjU > div._1MnS_MgcIswJjjdaqD7PUn > div._3eE_W0gb3Kt2gaw-P43oCW");
    public static final By PLP_PRODUCT_DESCRIPTION = By.cssSelector("div.flavour h3.product-name");
    public static final By PACK_QUANTITY = By.cssSelector("body div._1LWWFq-SDEx2Tog9zYh4vE > p");
    public static final By STRENGTH_ROW = By.cssSelector("body  div.vVnCzG6b_7b8MfqhybpZS > div");
    public static final By STRENGTH_ADD_BUTTON = By.cssSelector(" div._36MYBSWKcHIZxZrl0E7QKf > button");
    public static final By TIER_INFORMATION_ICON = By.cssSelector("span.tier-view-button");
    public static final By TIER_BADGE = By.cssSelector("div[class*='large']");
    public static final By I_ICON = By.cssSelector("span.icon.icon__info");
    public static final By TIER_OVERLAY_TITLE = By.cssSelector("body div > div > div.title > div.titelText > h1");
    public static final By TIER_DESCRIPTION = By.cssSelector("div.membership-content");
    public static final By CLOSE_OVERLAY = By.cssSelector("body  div > div > div.title > div.close_icon > span");
    public static final By STRENGTH_OVERLAY_PRODUCT_DESCRIPTION = By.cssSelector("body div > div > div._1HmO29R1MBDV2RBfvp-m99 > p");
    public static final By STRENGTH_OVERLAY_QUANTITY = By.cssSelector("body div.Z-rJOrm9uESAtW9CQ6eNX > div > span");
    public static final By STRENGTH_OVERLAY_STRENGTH = By.cssSelector("body  div._2VFYNCjPKpzMsdQprmO-MO > span");
    public static final By FILTER_LINK = By.cssSelector("#app > main > div.y-WH4txbNtUvL2BO3WyjU > div._1MnS_MgcIswJjjdaqD7PUn > div.A9HoQC-tZKANlRie2VpkK > span");
    public static final By FILTER_FLYOUT = By.cssSelector("#app > main > div._3v-sbJHHUzqYijADRtUbub._2dJXN-3XxEShH79BID5Bz4 > div");
    public static final By FILTER_FACET_TITLES = By.cssSelector("#app > main > div._3v-sbJHHUzqYijADRtUbub._2dJXN-3XxEShH79BID5Bz4 > div > div > div._3SrxsiCA1OQi_cZAXvUgp1 > h3");
    public static final By FILTER_FACETS_DEVICES = By.cssSelector("#app > main > div._3v-sbJHHUzqYijADRtUbub._2dJXN-3XxEShH79BID5Bz4 > div > div:nth-child(3) > div._2AHVFNzb1W9mZBGXlUzCAr > label");
    public static final By FILTER_FACETS_STRENGTHS = By.cssSelector("#app > main > div._3v-sbJHHUzqYijADRtUbub._2dJXN-3XxEShH79BID5Bz4 > div > div:nth-child(4) > div._2AHVFNzb1W9mZBGXlUzCAr > label");
    public String  SPECIFIC_STRENGTH_SELECTOR = "div.flavour__strength > div > img[title='%STRENGTH%']";
    public static final By ITEM_STRENGTH_SELECTOR = By.cssSelector("div > div.flavour__deails > div.flavour__strength > div");
    public static final By PRODUCT_DESCRIPTION = By.cssSelector("#app > main > div.y-WH4txbNtUvL2BO3WyjU > div._3gKnqCAUJWUfqrvAwrW1rY > div > div > div.flavour__deails > div.flavour__name > h3");
    public static final By FILTER_FACETS_DONE_BUTTON = By.cssSelector("#app > main > div._3v-sbJHHUzqYijADRtUbub._2dJXN-3XxEShH79BID5Bz4 > div > div._2LqO1cvME06bgMAjbuQvdj > button");
    public static final By FILTER_DEVICE_CLEAR = By.cssSelector("#app > main > div._3v-sbJHHUzqYijADRtUbub._2dJXN-3XxEShH79BID5Bz4 > div > div:nth-child(3) > div._3SrxsiCA1OQi_cZAXvUgp1 > span");
    public static final By FILTER_STRENGTH_CLEAR = By.cssSelector("#app > main > div._3v-sbJHHUzqYijADRtUbub._2dJXN-3XxEShH79BID5Bz4 > div > div:nth-child(4) > div._3SrxsiCA1OQi_cZAXvUgp1 > span");
    public static final By PRICE_PER_MONTH_BOX = By.cssSelector("#app > main div._3bmIa_threb-x6Oh5V1aCJ");
    public static final By DESKTOP_PRODUCT_TITLE = By.cssSelector("a.product-item-link");
    public static final By SITE_FLAVOURS_LINK = By.cssSelector("div > a[href$='/e-liquids/']");
    public static final By SITE_STRENGTHS = By.cssSelector("div.swatch-option.text");
    public final static By SITE_PRODUCT_DESCRIPTION = By.cssSelector("div.product.name");
    public static final By SITE_FLAVOURS = By.cssSelector("li.product-item");
    public static final By SUBSCRIPTION_PRODUCT = By.cssSelector("div.subscription");
    public static final By APP_STRENGTHS = By.cssSelector("div.flavour__strength > div > img");
    public static final By APP_FLAVOUR = By.cssSelector("div.flavour__price");

    private static final Logger LOG = LoggerFactory.getLogger(IssChooseYourFlavoursPage.class);

    private ScenarioContext scenarioContext;
    private IssBasketOverlayPage issBasketOverlayPage;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class FlavourPriceAndStrength {
        String price;
        List<String> strengths;
    }

    public IssChooseYourFlavoursPage(IssBasketOverlayPage issBasketOverlayPage, ScenarioContext scenarioContext) {
        this.issBasketOverlayPage = issBasketOverlayPage;
        this.scenarioContext = scenarioContext;
    }

    public boolean isStartAgainLinkDisplayed() {
        return waitForExpectedElement(START_AGAIN_LINK).getText().equalsIgnoreCase(UrlBuilder.getMessage("startAgainText"));
    }

    public void clickOnStartAgain() { waitForExpectedElement(START_AGAIN_LINK).click(); }

    public boolean overlayHeadingIs(String headingKey) {
        String actualHeading = waitForExpectedElement(ARE_YOU_SURE_OVERLAY_TITLE).getText();
        return actualHeading.equalsIgnoreCase(UrlBuilder.getMessage(headingKey));
    }

    public void clickOnOverlay(String yesOrNo) {
        By yesOrNoSelector;
        if (yesOrNo.equalsIgnoreCase("yes")) {
            yesOrNoSelector = OVERLAY_YES_BUTTON;
        } else {
            yesOrNoSelector = OVERLAY_NO_BUTTON;
        }
        waitForExpectedElement(yesOrNoSelector).click();
    }

    public boolean productHasAddToCartCTA() {
        waitForExpectedElement(FLAVOUR);
        List<WebElement> flavours = webDriver.findElements(FLAVOUR);
        for (WebElement flavour: flavours) {
            if (flavour.findElement(ADD_CTA).isDisplayed()) {
                return true;
            }
        }
        return false;
    }

    public void clickOnPlusCTA() {
        waitForExpectedElement(FLAVOUR);
        List<WebElement> flavours = webDriver.findElements(FLAVOUR);
        for (WebElement flavour: flavours) {
            if (flavour.findElement(ADD_CTA).isDisplayed()) {
               clickUsingJS(flavour.findElement(ADD_CTA));
               break;
            }
        }
    }

    public boolean strengthPopUpIsDisplayed() { return waitForExpectedElement(STRENGTH_MODAL).getText().toLowerCase().contains(UrlBuilder.getMessage("nicotineStrengthText")); }

    public boolean stickyHeaderIsDisplayed() {
        WebElement stickyHeader = webDriver.findElements(STICKY_HEADER).get(0);
        return stickyHeader.isDisplayed();
    }

    public boolean isHeaderDetailVisible(String detail) {
        By headerComponentSelector = null;
        switch (detail) {
            case "tier":
                headerComponentSelector = STICKY_HEADER_TIER;
                break;
            case "price per month":
                headerComponentSelector = STICKY_HEADER_PRICE_PER_MONTH;
                break;
            case "basket":
                headerComponentSelector = STICKY_HEADER_BASKET;
                break;
            default:
                assertThat( true).as("ERROR: "+detail+" is an invalid sticky header component").isFalse();
        }
        return waitForExpectedElement(headerComponentSelector).isDisplayed();
    }

    public void clickOnPlusCTAForItem(int itemNumber) {
        waitForExpectedElement(FLAVOUR,10);
        List<WebElement> flavours = webDriver.findElements(FLAVOUR);
        int item = 0;
        for (WebElement flavour: flavours) {
            if (itemNumber == item) {
                waitForExpectedElement(ADD_CTA);
                clickUsingJS(flavour.findElement(ADD_CTA));
                break;
            } else {
                item++;
            }
        }
    }

    public void chooseStrength() {
        waitForElementToAppearAndDisappear(LOADER, 3, 20);
        List<WebElement> plusIcons;
        try {
            plusIcons = waitForExpectedElements(STRENGTH_PLUS_ICON);
            plusIcons.get(0).click();
        } catch (Exception e) {
            waitForExpectedElement(STRENGTH_PLUS_ICON,10);
            plusIcons = waitForExpectedElements(STRENGTH_PLUS_ICON);
            clickUsingJS(plusIcons.get(0));
        }
    }

    public void addToBasket() {
        try {
            waitForExpectedElement(ADD_TO_CART_BUTTON).click();
        } catch (Exception e) {
            clickUsingJS(waitForExpectedElement(ADD_TO_CART_BUTTON));
        }
        waitForElementToAppearAndDisappear(LOADER,5,5);
    }

    public void clickBasketButton() {
        waitForElementToAppearAndDisappear(LOADER, 5, 5);
        try {
            waitForExpectedElement(STICKY_HEADER_BASKET, 30).click();
        } catch (Exception e) {
            clickByElementByQueryJSExecutor(STICKY_HEADER_BASKET);
        }
    }

    public void enterSearchTerm(String searchTermKey) {
        waitForElementToAppearAndDisappear(LOADER,3,3);
        String searchTerm = UrlBuilder.getMessage(searchTermKey);
        waitForExpectedElement(SEARCH_FIELD).click();
        waitForExpectedElement(SEARCH_FIELD).sendKeys(searchTerm);
        waitForExpectedElement(SEARCH_FIELD).sendKeys(Keys.ENTER);
    }

    public void enterSearchTermIntoSearchField(String searchTermKey) {
        String searchTerm = UrlBuilder.getMessage(searchTermKey);
        waitForExpectedElement(SEARCH_FIELD).click();
        waitForExpectedElement(SEARCH_FIELD).sendKeys(searchTerm);
    }

    public boolean listOfSuggestionsDisplayed() {
        waitForElementToAppearAndDisappear(LOADER, 5, 5);
        boolean result = false;
        try {
            result = waitForExpectedElements(SEARCH_SUGGESTION).size() > 0 && webDriver.findElement(SEARCH_SUGGESTIONS_BLOCK).isDisplayed();
        } catch (Exception e ) {
            result = false;
        }
        return result;
    }

    public String selectSearchSuggestion(int index) {
        List<WebElement> suggestions = waitForExpectedElements(SEARCH_SUGGESTION);
        String result = "";
        if (index > suggestions.size()) {
            assertThat(true).as("ERROR: not enough search suggestions ").isFalse();
        } else {
            result = suggestions.get(index).getText();
            suggestions.get(index).click();
        }
        return result;
    }

    public void selectSearchSuggestion(String searchTermKey) {
        String searchTerm = UrlBuilder.getMessage(searchTermKey);
        try {
            waitForExpectedElements(SEARCH_SUGGESTION_ITEM).stream()
                    .forEach(webElement -> {
                        if(webElement.getText().toLowerCase().equals(searchTerm.toLowerCase())) {
                            webElement.click();
                            return;
                        }
                    });
        }catch(StaleElementReferenceException e){
        }
    }

    public boolean correctProductIsDisplayed(String expectedProductDescription) {
        String actualProductDescription = waitForExpectedElement(PLP_PRODUCT_DESCRIPTION).getText();
        return (actualProductDescription.equalsIgnoreCase(expectedProductDescription));
    }

    public boolean packQuantityIsDisplayed(String expectedQuantityTitle) {
        waitForElementToAppearAndDisappear(LOADER,3,3);
        String actualQuantityTitle = waitForExpectedElement(PACK_QUANTITY).getText();
        return actualQuantityTitle.equalsIgnoreCase(expectedQuantityTitle);
    }

    public boolean updateQuantityCtasDisplayed() {
        boolean result = true;
        List<WebElement> strengths;
        try {
            strengths = waitForExpectedElements(STRENGTH_ROW);
        } catch (Exception e) {
            return false;
        }
        for (WebElement strength: strengths) {
            if (!strength.getText().toLowerCase().contains("out of stock") && !strength.getText().toLowerCase().contains("not available")) {
                result = result && strength.findElement(STRENGTH_MINUS_ICON).isDisplayed() && strength.findElement(STRENGTH_PLUS_ICON).isDisplayed();
            }
        }
        return result;
    }

    public boolean strengthAddButtonDisplayed() {
        return waitForExpectedElement(STRENGTH_ADD_BUTTON).isDisplayed();
    }

    public boolean strengthAddButtonClickable() {
        return isElementClickable(STRENGTH_ADD_BUTTON);
    }

    public void addQuantity(int howMany) throws InterruptedException {
        for (int x=0; x<howMany; x++) {
            waitForElementToAppearAndDisappear(LOADER, 5,5);
            waitForExpectedElement(STRENGTH_PLUS_ICON);
            clickUsingJS(STRENGTH_PLUS_ICON);
        }
    }

    public String getTierDescription() {
        return waitForExpectedElement(TIER_INFORMATION_ICON).getText();
    }

    public boolean isTierDisplayed(String expectedTier) {
        waitForElementToAppearAndDisappear(LOADER,3,3);
        return waitForExpectedElement(TIER_BADGE).getText().equalsIgnoreCase(expectedTier);
    }

    public boolean clickableIDisplayed() {
        return isElementClickable(I_ICON);
    }

    public void clickIIcon() {
        waitForExpectedElement(I_ICON).click();
    }

    public boolean tierDescriptionsAreDisplayed() {
        boolean result = true;
        List<WebElement> tierDescriptions = waitForExpectedElements(TIER_DESCRIPTION);
        if (tierDescriptions.size() != 3) {
            waitForElementToAppearAndDisappear(LOADER,3,3);
        }
        result = result && tierDescriptions.get(0).getText().toLowerCase().contains(UrlBuilder.getMessage("bronzeText"));
        result = result && tierDescriptions.get(1).getText().toLowerCase().contains(UrlBuilder.getMessage("silverText"));
        result = result && tierDescriptions.get(2).getText().toLowerCase().contains(UrlBuilder.getMessage("goldText"));
        return result;
    }

    public boolean isTierOverlayDisplayed() {
        return waitForExpectedElement(TIER_OVERLAY_TITLE).getText().equalsIgnoreCase(UrlBuilder.getMessage("subscriptionTiersOverlayHeading"));
    }

    public void clickCloseTierOverlay() {
        waitForExpectedElement(CLOSE_OVERLAY).click();
    }

    public boolean amIOnTheChooseFlavourspage() {
        try {
            waitForExpectedElement(ADD_CTA).click();
            waitForElementToAppearAndDisappear(LOADER, 5, 5);
            waitForExpectedElement(CLOSE_OVERLAY).click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickOnFilter() {
        waitForExpectedElement(FILTER_LINK).click();
    }

    public boolean isFlyoutDisplayed() {
        return waitForExpectedElement(FILTER_FLYOUT).isDisplayed();
    }

    public boolean filterHasSectionFor(String expectedFacetTitle) {
        List<WebElement> facetTitles = waitForExpectedElements(FILTER_FACET_TITLES);
        for (WebElement title: facetTitles) {
            if (title.getText().equalsIgnoreCase(expectedFacetTitle)) {
                return true;
            }
        }
        return false;
    }

    private List<String> getTitleTagsForImages() {
        String strength;
        List<String> result = new ArrayList<>();
        List<WebElement> products = waitForExpectedElements(FLAVOUR);
        List<WebElement> images;
        for (WebElement product: products) {
            images = product.findElements(By.cssSelector("img"));
            for (WebElement image: images) {
                strength = image.getAttribute("title");
                if (!result.contains(strength) && !strength.equals("")) {
                    result.add(strength);
                }
            }
        }
        return result;
    }
    public boolean allFacetsDisplayed(String facetKey) {
        boolean result;
        String facetName;
        List<WebElement> actualDeviceList;
        List<String> expectedDeviceList;
        if (facetKey.toLowerCase().contains("device")) {
            expectedDeviceList = Stream.of(UrlBuilder.getMessage(facetKey).split(",")).collect(Collectors.toList());
            actualDeviceList = waitForExpectedElements(FILTER_FACETS_DEVICES);
        } else {
            expectedDeviceList = getTitleTagsForImages();
            actualDeviceList = waitForExpectedElements(FILTER_FACETS_STRENGTHS);
        }
        result = expectedDeviceList.size() <= actualDeviceList.size();
        if (result) {
            for (int thisDevice=0; thisDevice < expectedDeviceList.size(); thisDevice++) {
                facetName = getFacetValue(actualDeviceList.get(thisDevice).getText());
                if (!expectedDeviceList.contains(facetName)) {
                    return false;
                }
            }
        }
        return result;
    }

    private int getExpectedFacetCount(String facetName, String facetValue) {
        int result = 0;
        if (facetName.equalsIgnoreCase("device")) {
            List<WebElement> products = waitForExpectedElements(PRODUCT_DESCRIPTION);
            for (WebElement item: products) {
                if (item.getText().toLowerCase().contains(facetValue.toLowerCase())) {
                    result++;
                }
            }
            // strengths
        } else {
            List<WebElement> strengths = waitForExpectedElements(By.cssSelector(SPECIFIC_STRENGTH_SELECTOR.replace("%STRENGTH%",facetValue)));
            result = strengths.size();
        }
        return result;
    }

    private List<WebElement> getFacetValues(String facetName) {
        List<WebElement> facetValues = new ArrayList<>();
        waitForElementToAppearAndDisappear(LOADER, 3, 3);
        switch (facetName.toLowerCase()) {
            case "device":
                facetValues = waitForExpectedElements(FILTER_FACETS_DEVICES);
                break;
            case "strength":
                facetValues = waitForExpectedElements(FILTER_FACETS_STRENGTHS);
                break;
            default:
                assertThat(true).as("ERROR getActualFacetCount: ivalid facet name "+facetName+" supplied").isFalse();
        }
        return facetValues;
    }

    private int getActualFacetCount(String facetName, String facetValue) {
        List<WebElement> facetValues = getFacetValues(facetName);
        for (WebElement fv: facetValues) {
            if (fv.getText().split("\\(")[0].trim().equals(facetValue)) {
                return Integer.parseInt(fv.getText().split("\\(")[1].split("\\)")[0]);
            }
        }
        return -1;
    }

    private String getFacetValue(String facetData) {
        return facetData.split("\\(")[0].trim();
    }

    public boolean validateFacetCounts(String facetName) {
        String fv;
        List<WebElement> facetValues = getFacetValues(facetName);
        for (WebElement facetValue: facetValues) {
            fv = getFacetValue(facetValue.getText());
            if (getActualFacetCount(facetName, fv) != getExpectedFacetCount(facetName, fv)) {
                return false;
            };
        }
        return true;
    }

    public void selectFacetValue(String facetName, String facetValue) {
        waitForElementToAppearAndDisappear(LOADER,3,3);
        List<WebElement> facetValues = getFacetValues(facetName);
        for (WebElement fv: facetValues) {
            LOG.info(getFacetValue(fv.getText()) + "< >"+facetValue);
            if (getFacetValue(fv.getText().toLowerCase()).equals(facetValue.toLowerCase())) {
                try {
                    fv.click();
                } catch (Exception e) {
                    clickUsingJS(fv);
                }
                break;
            }
        }
    }

    public void clickDone() {
        waitForExpectedElement(FILTER_FACETS_DONE_BUTTON).click();
        waitForElementToAppearAndDisappear(LOADER, 5, 5);
    }

    public boolean eachResultContainsAttribute(String facetName, String facetValue) {
        waitForElementToAppearAndDisappear(LOADER,3,3);
        boolean found;
        List<WebElement> strengths = new ArrayList<>();
        List<WebElement> flavours = waitForExpectedElements(FLAVOUR);
        WebElement strengthImage;
        for (WebElement flavour : flavours) {
            if (facetName.equals("device")) {
                if (!flavour.getText().toLowerCase().contains(facetValue.toLowerCase())) {
                    return false;
                }
            } else if ( facetName.equals("strength")) {
                found = false;
                strengths = flavour.findElements(ITEM_STRENGTH_SELECTOR);
                for (WebElement strength: strengths) {
                    strengthImage = strength .findElement(By.cssSelector("img"));
                    if (strengthImage.getAttribute("title").equals(facetValue)) {
                        found = true;
                    }
                }
                if (!found) { return false; }
            } else {
                assertThat(true).as("ERROR eachResultContainsAttribute: invalid facet name "+facetName+" supplied").isFalse();
            }
        }
        return true;
    }

    public int numberOfProductsVisible() {
        return waitForExpectedElements(PRODUCT_DESCRIPTION).size();
    }

    public void clearDeviceFilter(String facetName) {
        if (facetName.equalsIgnoreCase("device")) {
            waitForExpectedElement(FILTER_DEVICE_CLEAR).click();
        } else if (facetName.equalsIgnoreCase("strength")) {
            waitForExpectedElement(FILTER_STRENGTH_CLEAR).click();
        } else {
            assertThat(true).as("ERROR: clearDeviceFilter: invalid facet name supplied "+facetName).isFalse();
        }
    }

    public String getActualPricePerMonthMessage() {
        waitForElementToAppearAndDisappear(LOADER, 5, 5);
        return waitForExpectedElement(PRICE_PER_MONTH_BOX).getText();
    }

    private String doubleToString(Double d, int decimalPlaces) {
        return String.format ("%."+decimalPlaces+"f", d);
    }

    public void clickOnStrengthAddButton() {
        waitForExpectedElement(STRENGTH_ADD_BUTTON).click();
    }

    private String getExpectedPricePerMonthMessage(int numberOfItems) {
        double costPerItem = calculateCostPerItem(numberOfItems);
        double totalCost = costPerItem * numberOfItems;
        return UrlBuilder.getMessage("currency.key")
                + doubleToString(totalCost, Integer.parseInt(UrlBuilder.getMessage("priceDecimalPlaces")))
                + " "
                + UrlBuilder.getMessage("perMonthText");
    }

    public boolean validPricePerMonthDisplayed(int numberOfItems) {
        String currency = UrlBuilder.getMessage("currency.key");
        String actualPricePerMonthMessage = currency+getActualPricePerMonthMessage().split(currency)[1];
        LOG.info("actual price per month message: "+actualPricePerMonthMessage);
        String expectedPricePerMonthMessage = getExpectedPricePerMonthMessage(numberOfItems);
        LOG.info("expected price per month message: "+expectedPricePerMonthMessage);
        return expectedPricePerMonthMessage.equalsIgnoreCase(actualPricePerMonthMessage);
    }

    private double calculateCostPerItem(int numberOfItems) {
        if (numberOfItems < Integer.parseInt(UrlBuilder.getMessage("bronzeThreshold"))) {
            return Double.parseDouble(UrlBuilder.getMessage("itemPrice"));
        } else if (numberOfItems < Integer.parseInt(UrlBuilder.getMessage("silverThreshold"))) {
            return Double.parseDouble(UrlBuilder.getMessage("bronzePrice"));
        } else if (numberOfItems < Integer.parseInt(UrlBuilder.getMessage("goldThreshold"))) {
            return Double.parseDouble(UrlBuilder.getMessage("silverPrice"));
        } else {
            return Double.parseDouble(UrlBuilder.getMessage("goldPrice"));
        }
    }

    public void selectProductForSkuDescription(String skuDescription) {
        List<WebElement> flavours = waitForExpectedElements(FLAVOUR);
        for (WebElement flavour: flavours) {
            // for some reason product description displayes e-Liquid isntead of eLiquid
            if (skuDescription.toLowerCase().contains(flavour.findElement(By.cssSelector("h3")).getText().toLowerCase().replace("-",""))) {
                clickUsingJS(flavour.findElement(ADD_CTA));
                waitForElementToAppearAndDisappear(LOADER,3 ,3);
                break;
            }
        }
    }

    public boolean strengthSelectable(String strengthDescription) {
        List<WebElement> strengthsSelectable;
        try {
            strengthsSelectable = waitForExpectedElements(STRENGTH_OVERLAY_STRENGTH);
        } catch (TimeoutException t) {
            // no strengths available
            return false;
        }
        for (WebElement strengthSelectable: strengthsSelectable) {
            if (strengthDescription.toLowerCase().contains(strengthSelectable.toString().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public Map<String, FlavourPriceAndStrength> getIssFlavourData() {
        String price;
        String description;
        String strengthValue;
        List<WebElement> flavours = waitForExpectedElements(FLAVOUR);
        HashMap<String, FlavourPriceAndStrength>  flavourData = new HashMap();
        for (WebElement flavour: flavours) {
            FlavourPriceAndStrength flavourPriceAndStrength = new FlavourPriceAndStrength();
            List<String> strengthValues = new ArrayList<>();
            description = flavour.findElement(PLP_PRODUCT_DESCRIPTION).getText();
            price = flavour.findElement(APP_FLAVOUR).getText();
            List<WebElement> strengths = flavour.findElements(APP_STRENGTHS);
            for (WebElement strength: strengths) {
                strengthValue = strength.getAttribute("title");
                strengthValues.add(strengthValue);
            }
            flavourPriceAndStrength.setPrice(price);
            flavourPriceAndStrength.setStrengths(strengthValues);
            flavourData.put(description,flavourPriceAndStrength);
        }
        return flavourData;
    }

    private boolean isSubscriptionProduct(WebElement webElement) {
        try {
            webElement.findElement(SUBSCRIPTION_PRODUCT);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Map<String, FlavourPriceAndStrength> getSiteFlavourData() {
        String price;
        String description;
        String strengthValue;
        List<WebElement> flavours = waitForExpectedElements(SITE_FLAVOURS);
        HashMap<String, FlavourPriceAndStrength>  flavourData = new HashMap();
        for (WebElement flavour: flavours) {
            if (isSubscriptionProduct(flavour)) {
                FlavourPriceAndStrength flavourPriceAndStrength = new FlavourPriceAndStrength();
                List<String> strengthValues = new ArrayList<>();
                description = flavour.findElement(SITE_PRODUCT_DESCRIPTION).getText();
                price = UrlBuilder.getMessage("currency.key") + flavour.getAttribute("data-price");
                List<WebElement> strengths = flavour.findElements(SITE_STRENGTHS);
                for (WebElement strength : strengths) {
                    strengthValue = strength.getAttribute("aria-label");
                    strengthValues.add(strengthValue);
                }
                flavourPriceAndStrength.setPrice(price);
                flavourPriceAndStrength.setStrengths(strengthValues);
                flavourData.put(description, flavourPriceAndStrength);
            }
        }
        return flavourData;
    }

    public void navigateToSiteFlavoursPage() {
        waitForExpectedElement(SITE_FLAVOURS_LINK);
        clickUsingJS(SITE_FLAVOURS_LINK);
    }

    public boolean sameFlavours(Map<String, FlavourPriceAndStrength> issFlavours, Map<String, FlavourPriceAndStrength> siteFlavours) {
        assertThat(issFlavours.size() == siteFlavours.size())
                .as("ERROR number iss flavours = "+issFlavours.size()+" site number of flavours = "+siteFlavours).isTrue();
        for (Map.Entry<String,FlavourPriceAndStrength> siteEntry: siteFlavours.entrySet()) {
            assertThat(issFlavours.get(siteEntry.getKey()).getPrice().equals(siteEntry.getValue().getPrice()))
                    .as("ERROR " + siteEntry.getKey() + " iss price = " + issFlavours.get(siteEntry.getKey()).getPrice() + " site price = " +  siteEntry.getValue().getPrice()).isTrue();
            assertThat(issFlavours.get(siteEntry.getKey()).getStrengths().equals(siteEntry.getValue().getStrengths()))
                    .as("ERROR " + siteEntry.getKey() + " iss strength = " + issFlavours.get(siteEntry.getKey()).getStrengths() + " site strength = " +  siteEntry.getValue().getStrengths()).isTrue();
        }
        return true;
    }
}