package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.models.RegistrationPageModel;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class TryItPage extends PageObject {
    private static final By TRY_IT_HREF = By.cssSelector("a[href*='personal-presentation']");
    private static final By FIRSTNAME = By.cssSelector("#firstname");
    private static final By SURNAME = By.cssSelector("#lastname");
    private static final By EMAIL_TXT = By.cssSelector("#email");
    private static final By DOB = By.cssSelector("#dob");
    private static final By TERMS_AND_CONDITIONS_CHK_BOX = By.cssSelector("input#confirm,input#register-input-custom");
    private static final By SEND_MESSAGE_BUTTON = By.cssSelector("button.action.submit.primary");
    public static final By PHONE_NUMBER_TXT_BOX = By.cssSelector("input#telephone");
    private static final By CITY = By.cssSelector("#personal-consultation-form > fieldset > div.field.city.required > div > select");
    public static final By PRODUCT_DROP_DOWN = By.cssSelector("#product");
    public static final By TRY_IT_HREF_KZ = By.cssSelector("li.level0.category-item.sixth > a:nth-child(2)");

    public void clickTryItLink() {
        if ("kz".equals(UrlBuilder.getLocale())) {
            waitForExpectedElement(TRY_IT_HREF_KZ, 20).click();
        } else {
            waitForExpectedElement(TRY_IT_HREF, 20).click();
        }
    }

    @SneakyThrows
    public void completeFormAndSubmit(RegistrationPageModel pageModel) {
        enterNameFields(pageModel);
        waitClearAndEnterText(PHONE_NUMBER_TXT_BOX, pageModel.getAddress().getPhoneNumber());
        waitClearAndEnterText(EMAIL_TXT, pageModel.getSignInInfo().getEmail());
        waitClearAndEnterText(DOB, pageModel.getPersonalInfo().getDob());
        selectValueFromDropDownByby(pageModel.getAddress().getCity(), CITY);
        selectProduct();
        if (!getWebDriver().findElement(TERMS_AND_CONDITIONS_CHK_BOX).isSelected()) {
            clickUsingJS(TERMS_AND_CONDITIONS_CHK_BOX);
        }
        clickByElementByQueryJSExecutor(SEND_MESSAGE_BUTTON);
    }

    private void selectProduct() {
        if ("kz".equals(UrlBuilder.getLocale())) {
            clickByElementByQueryJSExecutor(PRODUCT_DROP_DOWN);
            waitForExpectedElement(PRODUCT_DROP_DOWN).sendKeys(Keys.ARROW_DOWN);
            waitForExpectedElement(PRODUCT_DROP_DOWN).sendKeys(Keys.ENTER);
        } else {
            waitForExpectedElement(PRODUCT_DROP_DOWN).click();
            waitForExpectedElement(PRODUCT_DROP_DOWN).sendKeys(Keys.ARROW_DOWN);
            waitForExpectedElement(PRODUCT_DROP_DOWN).sendKeys(Keys.ENTER);
        }
    }

    private void enterNameFields(RegistrationPageModel pageModel) {
        String firstName = pageModel.getPersonalInfo().getFirstName();
        String lastName = pageModel.getPersonalInfo().getLastName();

        if (getValue(FIRSTNAME).isEmpty() || firstName.isEmpty())
            waitClearAndEnterText(FIRSTNAME, firstName);
        if (getValue(SURNAME).isEmpty() || lastName.isEmpty())
            waitClearAndEnterText(SURNAME, lastName);
    }
}
