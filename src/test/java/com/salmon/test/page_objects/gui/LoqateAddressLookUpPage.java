package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.models.RegistrationPageModel.Address;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static com.salmon.test.framework.helpers.UrlBuilder.getLocale;
import static com.salmon.test.page_objects.gui.constants.Locale.PL;

public class LoqateAddressLookUpPage extends PageObject {

    private static final By STREET_ADDRESS_LINE_1 = By.cssSelector("#street_1");
    private static final By STREET_ADDRESS_LINE_2 = By.cssSelector("input#street_2");
    private static final By CITY = By.cssSelector("input#city");
    private static final By POSTCODE = By.cssSelector("input#zip");
    private static final By COUNTRY = By.cssSelector("select#country");
    private static final By LST_LOQATE_ADDRESSES = By.cssSelector("div.pcaautocomplete.pcatext:nth-child(1) div.pca.pcalist>div");
    private static final By ELE_AUTO_ADDRESS_ITEMS = By.cssSelector("div.pcaautocomplete.pcatext:nth-child(1) div.pca.pcalist > div.pcaitem");
    private static final By STREET_ADDRESS_LINE_1_CHECKOUT_PAGE = By.xpath("//div[@id='shipping-new-address-form']/fieldset/div/div/div/input");
    private static final By STREET_ADDRESS_LINE_2_CHECKOUT_PAGE_PL = By.xpath("//div[@id='shipping-new-address-form']/fieldset/div/div[2]/div/input");
    private static final By STREET_ADDRESS_LINE_1_STORE_LOCATOR_PAGE = By.cssSelector("input#pac-input.input-field.controls");
    private static final By COUNTRY_CHECKOUT_PAGE = By.xpath("//div[@id='shipping-new-address-form']/div[8]/div/select");
    private static final By COUNTRY_CHECKOUT_PAGE_PL = By.xpath("//div[@id='shipping-new-address-form']/div[9]/div/select");
    private static final By LST_LOQATE_ADDRESSES_CHECKOUT_PAGE_PL = By.cssSelector("div.pca > div:nth-child(6) div.pca.pcalist");
    private static final By AVALANCHE_LST_LOQATE_ADDRESSES_CHECKOUT_PAGE_BE = By.cssSelector("#html-body > div.pca > div:nth-child(1) > div.pca.pcalist ");
    private static final By ELE_AUTO_ADDRESS_ITEMS_CHECKOUT_PAGE_PL = By.cssSelector("div.pca > div:nth-child(14) div.pca.pcalist div.pcaitem");
    private static final By LST_LOQATE_ADDRESSES_CHECKOUT_PAGE = By.cssSelector("div.pca > div:nth-child(6) > div.pca.pcalist");
    private static final By ELE_AUTO_ADDRESS_ITEMS_CHECKOUT_PAGE = By.cssSelector("div.pca > div:nth-child(6) > div.pca.pcalist > div.pcaitem");
    private static final By LST_LOQATE_ADDRESSES_CHECKOUT_PAGE_IT = By.cssSelector("div.pcaautocomplete.pcatext:nth-child(6) > div.pca.pcalist");
    private static final By LST_LOQATE_ADDRESSES_CHECKOUT_PAGE_VYPEIT = By.cssSelector("div.pca > div:nth-child(4) > div.pca.pcalist");
    private static final By ELE_AUTO_ADDRESS_ITEMS_CHECKOUT_PAGE_IT = By.cssSelector("div.pcaautocomplete.pcatext:nth-child(6) > div.pca.pcalist> div");
    private static final By ELE_AUTO_ADDRESS_ITEMS_CHECKOUT_PAGE_VYPEIT = By.cssSelector("div.pca > div:nth-child(4) > div.pca.pcalist > div");
    private static final By LST_LOQATE_ADDRESSES_STORE_LOCATOR_PAGE = By.cssSelector("div.pac-container.pac-logo.hdpi");
    private static final By ELE_AUTO_ADDRESS_ITEMS_STORE_LOCATOR_PAGE = By.cssSelector("div.pac-container.pac-logo.hdpi > div.pac-item");
    private static final By CITY_CHECKOUT_PAGE = By.xpath("//div[@id='shipping-new-address-form']/div[4]/div/input");
    private static final By CITY_CHECKOUT_PAGE_PL = By.xpath("//div[@id='shipping-new-address-form']/div[5]/div/input");
    private static final By POSTCODE_CHECKOUT_PAGE = By.xpath("//div[@id='shipping-new-address-form']/div[7]/div/input");
    private static final By POSTCODE_CHECKOUT_PAGE_PL = By.xpath("//div[@id='shipping-new-address-form']/div[8]/div/input");
    public static final By ADDRESS_SEARCH_FIELD = By.cssSelector("#address-search");
    private static final String CHECKOUT_PAGE = "checkoutOverlay";
    private static final String REGISTRATION_PAGE = "registrationPage";
    private static final String STORE_LOCATOR_PAGE = "storeLocatorPage";
    private static final By STREET_ADDRESS_LINE_1_ZA = By.cssSelector("#address-search");
    private static final By STREET_ADDRESS_LINE_1_CHECKOUT_PAGE_ZA = By.cssSelector("#new-address-search");
    public static final By ADDRESS_SEARCH_VUSEDE = By.cssSelector("input[name='address-search'],input[name='address_search']");

    public void startEnteringAddressWith(String strEnterAddress, String page) {
        By streetAddressLine1;
        if ( UrlBuilder.getLocale().equals("velobe")) {
            streetAddressLine1 = ADDRESS_SEARCH_VUSEDE;
        } else {
            streetAddressLine1 = getStreetLocator(page);
        }
        waitForExpectedElement(streetAddressLine1, 10).clear();
        enterDataAndWait(streetAddressLine1, strEnterAddress);
        getWebDriver().findElement(streetAddressLine1).sendKeys(Keys.SPACE);
    }

    public List<WebElement> getLoqateAutoCompleteAddressesList(String page) {
        List<WebElement> lstAutoAddresses = null;
        try {
            Thread.sleep(3000);
            if (waitForExpectedElement(getLstLoqateAddressesLocator(page), 10).isDisplayed()) {
                lstAutoAddresses = getWebDriver().findElements(getEleAutoAddressItemsLocator(page));
            }
        } catch (Exception ex) {
            throw new RuntimeException("Failed to retrieve Address look up Locate due to exception: " + ex.getMessage());
        }
        return lstAutoAddresses;
    }

    public boolean isLoqateAutoCompleteDisplayed(String page) {
        try {
            Thread.sleep(5000);
            getWebDriver().findElement(getLstLoqateAddressesLocator(page));
        } catch (Exception e) {
            LOG.info(e.getMessage());
            return false;
        }
        return true;
    }

    public Address getSelectedAddress(String page) {
        Address address = Address.builder()
                .streetAndHouseNumber(getValue(getStreetLocator(page)))
                .city(getValue(getCityLocator(page)))
                .postcode(getValue(getPostcodeLocator(page)))
                .country(getSelectedCountryText(getCountryLocator(page)))
                .build();

        if (getLocale().equals(PL.getName())) {
            address.setHouseNumber(getValue(getHouseNumberLocator(page)));
        }

        return address;
    }

    private String getSelectedCountryText(By country) {
        return isElementDisplayedBySeconds(country, 5) ?
                new Select(waitForExpectedElement(country)).getFirstSelectedOption().getText() : null;
    }

    private By getEleAutoAddressItemsLocator(String page) {
        if (page.equalsIgnoreCase(CHECKOUT_PAGE)) {
            switch (getLocale().toLowerCase()) {
                case "it":
                    return ELE_AUTO_ADDRESS_ITEMS_CHECKOUT_PAGE_IT;
                case "vypeit":
                case "vuseit":
                    return ELE_AUTO_ADDRESS_ITEMS_CHECKOUT_PAGE_VYPEIT;
                case "pl":
                    return ELE_AUTO_ADDRESS_ITEMS_CHECKOUT_PAGE_PL;
                case "velobe":
                    return ELE_AUTO_ADDRESS_ITEMS;
                default:
                    return ELE_AUTO_ADDRESS_ITEMS_CHECKOUT_PAGE;
            }
        } else if (page.equalsIgnoreCase(REGISTRATION_PAGE)) {
            switch (getLocale().toLowerCase()) {
                case "it":
                    return ELE_AUTO_ADDRESS_ITEMS_CHECKOUT_PAGE_VYPEIT;
                default:
                    return ELE_AUTO_ADDRESS_ITEMS;
            }
        } else if (page.equalsIgnoreCase(STORE_LOCATOR_PAGE)) {
            return ELE_AUTO_ADDRESS_ITEMS_STORE_LOCATOR_PAGE;
        }
        return ELE_AUTO_ADDRESS_ITEMS;
    }

    private By getLstLoqateAddressesLocator(String page) {
        if (page.equalsIgnoreCase(CHECKOUT_PAGE)) {
            switch (getLocale().toLowerCase()) {
                case "it":
                    return LST_LOQATE_ADDRESSES_CHECKOUT_PAGE_IT;
                case "vypeit":
                case "vuseit":
                    return LST_LOQATE_ADDRESSES_CHECKOUT_PAGE_VYPEIT;
                case "pl":
                    return LST_LOQATE_ADDRESSES_CHECKOUT_PAGE_PL;
                case "velobe":
                    return AVALANCHE_LST_LOQATE_ADDRESSES_CHECKOUT_PAGE_BE;
                default:
                    return LST_LOQATE_ADDRESSES_CHECKOUT_PAGE;
            }
        } else if (page.equalsIgnoreCase(REGISTRATION_PAGE)) {
            switch (getLocale().toLowerCase()) {
                case "it":
                    return LST_LOQATE_ADDRESSES_CHECKOUT_PAGE_VYPEIT;
                default:
                    return LST_LOQATE_ADDRESSES;
            }
        } else if (page.equalsIgnoreCase(STORE_LOCATOR_PAGE)) {
            return LST_LOQATE_ADDRESSES_STORE_LOCATOR_PAGE;
        }
        return LST_LOQATE_ADDRESSES;
    }

    public void clickFirstOptionIfPresent(String text){
        By streetListOptions=By.cssSelector("div[class='pca pcalist'] div[title*='"+text+"']");
        int attampt=0;
        while(isElementClickable(streetListOptions)&&attampt<2) {
            waitForExpectedElement(streetListOptions,5).click();
            waitForAjaxElementNotToBePresent(getWebDriver(),5);
            attampt++;
        }
    }

    private By getStreetLocator(String page) {
        if (page.equalsIgnoreCase(CHECKOUT_PAGE)) return STREET_ADDRESS_LINE_1_CHECKOUT_PAGE;
        else if (page.equalsIgnoreCase(STORE_LOCATOR_PAGE)) return STREET_ADDRESS_LINE_1_STORE_LOCATOR_PAGE;
        return STREET_ADDRESS_LINE_1;
    }

    private By getHouseNumberLocator(String page) {
        return page.equalsIgnoreCase(CHECKOUT_PAGE) ? STREET_ADDRESS_LINE_2_CHECKOUT_PAGE_PL : STREET_ADDRESS_LINE_2;
    }

    private By getCountryLocator(String page) {
        return getLocale().equals(PL.getName()) ?
                page.equalsIgnoreCase(CHECKOUT_PAGE) ? COUNTRY_CHECKOUT_PAGE_PL : COUNTRY :
                page.equalsIgnoreCase(CHECKOUT_PAGE) ? COUNTRY_CHECKOUT_PAGE : COUNTRY;
    }

    private By getPostcodeLocator(String page) {
        return getLocale().equals(PL.getName()) ?
                page.equalsIgnoreCase(CHECKOUT_PAGE) ? POSTCODE_CHECKOUT_PAGE_PL : POSTCODE :
                page.equalsIgnoreCase(CHECKOUT_PAGE) ? POSTCODE_CHECKOUT_PAGE : POSTCODE;
    }

    private By getCityLocator(String page) {
        return getLocale().equals(PL.getName()) ?
            page.equalsIgnoreCase(CHECKOUT_PAGE) ? CITY_CHECKOUT_PAGE_PL : CITY :
            page.equalsIgnoreCase(CHECKOUT_PAGE) ? CITY_CHECKOUT_PAGE : CITY;
    }

    public void startEnteringAddressWithZA(String strEnterAddress, String page) {
        By streetAddressLine1 = getStreetLocatorZA(page);
        waitForExpectedElement(streetAddressLine1, 10).clear();
        enterDataAndWait(streetAddressLine1, strEnterAddress);
        getWebDriver().findElement(streetAddressLine1).sendKeys(Keys.SPACE);
    }

    private By getStreetLocatorZA(String page) {
        if (page.equalsIgnoreCase(CHECKOUT_PAGE)) return STREET_ADDRESS_LINE_1_CHECKOUT_PAGE_ZA;
        else if (page.equalsIgnoreCase(STORE_LOCATOR_PAGE)) return STREET_ADDRESS_LINE_1_STORE_LOCATOR_PAGE;
        return STREET_ADDRESS_LINE_1_ZA;
    }

    public void startEnteringAddressFR(String strEnterAddress, String page) {
        By streetAddressLine1 = null;
        if (page.equalsIgnoreCase("Checkoutpage")) {
            streetAddressLine1 = STREET_ADDRESS_LINE_1_CHECKOUT_PAGE_ZA;
        } else {
            streetAddressLine1 = STREET_ADDRESS_LINE_1_ZA;
        }
        waitForExpectedElement(streetAddressLine1, 10).clear();
        enterDataAndWait(streetAddressLine1, strEnterAddress);
        getWebDriver().findElement(streetAddressLine1).sendKeys(Keys.SPACE);
    }
}
