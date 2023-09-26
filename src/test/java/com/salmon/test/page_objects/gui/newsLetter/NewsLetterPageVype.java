package com.salmon.test.page_objects.gui.newsLetter;

import com.salmon.test.page_objects.gui.models.NewsLetterPageModel;
import lombok.SneakyThrows;
import org.openqa.selenium.By;

public class NewsLetterPageVype extends NewsLetterPage {

    private static final By NEWSLETTER_EMAIL = By.cssSelector("#newsletter");
    private static final By EMAIL_CONSENT_CHK_BOX = By.cssSelector("#agree-to-subscribe");

    @SneakyThrows
    public void completeFormAndSubmit(NewsLetterPageModel newsLetterPageModel) {
        enterNameFields(newsLetterPageModel);
        waitClearAndEnterText(NEWSLETTER_EMAIL, newsLetterPageModel.getEmail());
        clickUsingJS(EMAIL_CONSENT_CHK_BOX);
        waitForExpectedElement(SUBSCRIBE_BUTTON).click();
    }

    private void enterNameFields(NewsLetterPageModel newsLetterPageModel) {
        if (getValue(NEWSLETTER_FIRSTNAME).isEmpty())
            enterText(NEWSLETTER_FIRSTNAME, newsLetterPageModel.getFirstName());
        if (getValue(NEWSLETTER_SURNAME).isEmpty())
            enterText(NEWSLETTER_SURNAME, newsLetterPageModel.getLastName());
    }
}
