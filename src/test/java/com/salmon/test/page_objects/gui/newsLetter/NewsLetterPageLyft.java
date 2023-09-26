package com.salmon.test.page_objects.gui.newsLetter;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.models.NewsLetterPageModel;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class NewsLetterPageLyft extends NewsLetterPage {

    private static final By NEWSLETTER_EMAIL = By.cssSelector("#newsletter");
    private static final By TERMS_AND_CONDITIONS_CHK_BOX = By.cssSelector("#terms-and-conditions");
    private static final By EMAIL_CONSENT_CHK_BOX = By.cssSelector("#user-consent");
    private static final By BANK_ID = By.cssSelector("#bank_id");
    private static final By DOB = By.cssSelector("input#newsletter_dob");

    @SneakyThrows
    public void completeFormAndSubmit(NewsLetterPageModel newsLetterPageModel) {
        enterNameFields(newsLetterPageModel);
        waitClearAndEnterText(NEWSLETTER_EMAIL, newsLetterPageModel.getEmail());
        waitClearAndEnterText(BANK_ID, newsLetterPageModel.getBankId());
        enterDataAndWait(DOB, UrlBuilder.getMessage("ValidDOB.key"));
        waitForExpectedElement(DOB, 10).sendKeys(Keys.TAB);
        clickUsingJS(TERMS_AND_CONDITIONS_CHK_BOX);
        clickUsingJS(EMAIL_CONSENT_CHK_BOX);
        waitForExpectedElement(SUBSCRIBE_BUTTON).click();
    }

    private void enterNameFields(NewsLetterPageModel newsLetterPageModel) {
        String firstName = newsLetterPageModel.getFirstName();
        String lastName = newsLetterPageModel.getLastName();
        waitForExpectedElement(NEWSLETTER_FIRSTNAME,10);
        if (getValue(NEWSLETTER_FIRSTNAME).isEmpty() || firstName.isEmpty())
            enterText(NEWSLETTER_FIRSTNAME, firstName);
        if (getValue(NEWSLETTER_SURNAME).isEmpty() || lastName.isEmpty())
            enterText(NEWSLETTER_SURNAME, lastName);
    }
}
