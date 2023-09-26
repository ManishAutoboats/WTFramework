package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class InsiderClubPage extends PageObject
{
    public static final By REGISTER_NOW_BUTTON = By.cssSelector(".column.main > div:nth-child(5) .pagebuilder-column:nth-child(2) a[href*=\"insiders-club/benefit\"]");
    public static final By TERMS_AND_CONDITIONS_LABEL = By.cssSelector(".row-full-width-inner > div:nth-child(2) h2");
    public static final By TERMS_AND_CONDITIONS_LINK = By.cssSelector("a[href*='vuse-insidersclub/nutzungsbedingungen']");
    public static final By LOYALTY_SIGN_UP_CHECKBOX = By.cssSelector("#global_loyalty_optin");
    private static final By TERMS_AND_CONDITIONS_LINK_IT = By.xpath("//p/a[contains(text(),'Termini e Condizioni')]");


    public void clickOnRegisterNowButton() {
        waitForExpectedElement(REGISTER_NOW_BUTTON, 10);
        getWebDriver().findElement(REGISTER_NOW_BUTTON).click();
    }

    public String getTermsAndConditionsLabelText() {
        return waitForExpectedElement(TERMS_AND_CONDITIONS_LABEL).getText();
    }

    public void clickLearnMoreToNavigateToTermsAndConditionPage() {
        waitForExpectedElement(TERMS_AND_CONDITIONS_LINK, 10);
        clickByElementByQueryJSExecutor(TERMS_AND_CONDITIONS_LINK);
    }

    public void clickOnTermsAndConditionsLinkFromFooter() {
        if ("vuseit".equals(UrlBuilder.getLocale())) {
            isElementDisplayedBySeconds(TERMS_AND_CONDITIONS_LINK_IT, 10);
            clickByElementByQueryJSExecutor(TERMS_AND_CONDITIONS_LINK_IT);
        } else {
            isElementDisplayedBySeconds(TERMS_AND_CONDITIONS_LINK, 10);
            if (getWebDriver().findElements(TERMS_AND_CONDITIONS_LINK).size() > 1)
                clickUsingJS(getWebDriver().findElements(TERMS_AND_CONDITIONS_LINK).get(1));
            else
                clickByElementByQueryJSExecutor(TERMS_AND_CONDITIONS_LINK);
        }
    }
}
