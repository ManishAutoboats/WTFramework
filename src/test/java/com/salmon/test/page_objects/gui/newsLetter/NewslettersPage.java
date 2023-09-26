package com.salmon.test.page_objects.gui.newsLetter;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.HomePage;
import com.salmon.test.page_objects.gui.models.NewsLetterPageModel;
import java.util.List;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import static com.salmon.test.page_objects.gui.AccountDashboardPage.MY_ACCOUNT_DROPDOWN_LINKS;
import static com.salmon.test.page_objects.gui.AccountDashboardPage.MY_ACCOUNT_LINKS;
import static org.testng.Assert.assertTrue;

public class NewslettersPage extends NewsLetterPage{

    private static final By NEWSLETTER_BUTTON = By.cssSelector(".action.subscribe.primary");
    private static final By NEWSLETTER_EMAIL = By.cssSelector("#newsletter");
    private static final By DOB_DATE = By.cssSelector("select.dob_date");
    private static final By DOB_MONTH = By.cssSelector("select.dob_month");
    private static final By DOB_YEAR = By.cssSelector("select.dob_year");
    private static final By TERMS_AND_CONDITIONS_CHK_BOX = By.cssSelector("input[name=store_my_data]");
    private static final By EMAIL_CONSENT_CHK_BOX = By.cssSelector("input[name=email_marketing]");
    private static final By EMAIL_INFO = By.cssSelector(".field.personal-data-info,#newsletter-validate-detail > fieldset > div:nth-child(6) > div:nth-child(1) > div > div> p");
    private static final By MOBILE_OR_SOCIAL_CONSENT_CHK_BOX = By.cssSelector("input#mob_marketing");
    private static final By PHONE_NUMBER_TXT_BOX = By.cssSelector("input#telephone");

  // ELEMENT MAPPING
  public By elePageHeading = By.cssSelector("span.base");
  public By txtFirstName = By.cssSelector("#firstname");
  public By txtLastName = By.cssSelector("#lastname");
  public By btnSubscribeNewsletterPopUpMX = By.cssSelector("#newsletter-validate-detail > fieldset > div.actions > button > span");
  public By chkAuthorizeEmail= By.cssSelector("#newsletter-validate-detail > fieldset > div.newsletter-agree.required > label");
  public By eleNewsletterErrMessage= By.cssSelector("div.message-error.error.message");

  public void isSMSUnchecked() {
    String uncheck =  webDriver.findElement(MOBILE_OR_SOCIAL_CONSENT_CHK_BOX).getAttribute("value");
    assertTrue(uncheck.equalsIgnoreCase("1"));
  }

  public void isSMSTextDisplayed(String text) {
    waitForExpectedElement(EMAIL_INFO).getText().contains(text);
  }
  public void isEmailUnchecked() {
    String uncheck =  webDriver.findElement(EMAIL_CONSENT_CHK_BOX).getAttribute("value");
    assertTrue(uncheck.equalsIgnoreCase("1"));
  }

  public void isEmailTextDisplayed(String text) {
    assertTrue(waitForExpectedElement(EMAIL_INFO).isDisplayed());
    waitForExpectedElement(EMAIL_INFO).getText().contains(text);
  }
  public void clickNewLetterButton()
  {
    List<WebElement> buttons = webDriver.findElements(NEWSLETTER_BUTTON);
    if(buttons.size()>0)
      buttons.get(0).click();
  }

  public void clickNewslettersLink() {
    switch (UrlBuilder.getLocale()) {
        case "mx":
        case "vusemx":
        waitForExpectedElement(NEWSLETTER_BUTTON,10);
        clickByElementByQueryJSExecutor(NEWSLETTER_BUTTON);
        break;
        case "kz":
            hoverOnElement(MY_ACCOUNT_LINKS);
            waitForExpectedElement(MY_ACCOUNT_DROPDOWN_LINKS,10);
            clickByElementByQueryJSExecutor(MY_ACCOUNT_DROPDOWN_LINKS);
            waitForExpectedElement(NEWSLETTER_ON_MYACCOUNT,10);
            clickByElementByQueryJSExecutor(NEWSLETTER_ON_MYACCOUNT);
            break;
        case "vuseco":
            waitForExpectedElement(NEWSLETTER_ON_MYACCOUNT_VUSECO,10);
            clickByElementByQueryJSExecutor(NEWSLETTER_ON_MYACCOUNT_VUSECO);
            break;
      default:
      try {
        waitForExpectedElement(By.linkText(UrlBuilder.getMessage("newsLetterText.key")), 10);
        clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("newsLetterText.key")));
        waitForExpectedElement(elePageHeading, 30);
      } catch (TimeoutException e) {
        waitForExpectedElement(NEWSLETTER_HREF, 20);
        clickByElementByQueryJSExecutor(NEWSLETTER_HREF);
      } catch (Exception ex) {
        clickByElementByQueryJSExecutor(By.linkText(Props.getProp("newsLetterText.key")));
      }
    }
  }

    @SneakyThrows
    public void completeFormAndSubmit(NewsLetterPageModel newsLetterPageModel) {
        enterNameFields(newsLetterPageModel);
        enterDataAndWait(PHONE_NUMBER_TXT_BOX, newsLetterPageModel.getPhoneNumber());
        enterDOBFields(newsLetterPageModel);
        enterDataAndWait(NEWSLETTER_EMAIL, newsLetterPageModel.getEmail());

        if (!getWebDriver().findElement(TERMS_AND_CONDITIONS_CHK_BOX).isSelected()) {
            scrollElementIntoView(TERMS_AND_CONDITIONS_CHK_BOX);
            clickUsingJS(TERMS_AND_CONDITIONS_CHK_BOX);
        }

        clickUsingJS(EMAIL_CONSENT_CHK_BOX);

        if (getWebDriver().findElements(MOBILE_OR_SOCIAL_CONSENT_CHK_BOX).size() > 0) {
            clickUsingJS(MOBILE_OR_SOCIAL_CONSENT_CHK_BOX);
        }

        waitForExpectedElement(SUBSCRIBE_BUTTON).click();
    }

    private void enterNameFields(NewsLetterPageModel newsLetterPageModel) {
        String firstName = newsLetterPageModel.getFirstName();
        String lastName = newsLetterPageModel.getLastName();

        if (getValue(NEWSLETTER_FIRSTNAME).isEmpty() || firstName.isEmpty())
            enterText(NEWSLETTER_FIRSTNAME, firstName);
        if (getValue(NEWSLETTER_SURNAME).isEmpty() || lastName.isEmpty())
            enterText(NEWSLETTER_SURNAME, lastName);
    }

    private void enterDOBFields(NewsLetterPageModel newsLetterPageModel) {
        waitForExpectedElement(DOB_DATE, 10);
        selectValueFromDropDownByby(newsLetterPageModel.getDob().getDate(), DOB_DATE);
        selectValueFromDropDownByby(newsLetterPageModel.getDob().getMonth(), DOB_MONTH);
        selectValueFromDropDownByby(newsLetterPageModel.getDob().getYear(), DOB_YEAR);
    }
}
