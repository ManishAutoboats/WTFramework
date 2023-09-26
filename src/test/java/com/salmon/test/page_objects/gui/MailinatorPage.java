package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.toCollection;
import static org.testng.Assert.assertTrue;

public class MailinatorPage extends PageObject {

    private static final By EMAIL_RECEIVED_VELO = By.cssSelector("#inbox_pane > div.wrapper-primary-table.scrollbar > div > div.os-padding > div > div > table > tbody>tr");
    private static final By EMAIL_MESSAGE_BODY_IFRAME = By.cssSelector("iframe#html_msg_body");
    private static final By EMAIL_CONFIRMATION_MESSAGE = By.cssSelector("table.table-striped.jambo_table tbody tr");
    private static final By M_EMAIL_CONFIRMATION_MESSAGE_GLO_IT = By.cssSelector("div.d-flex.w-100.row-table > div.block-subject.w-100 > a");
    private static final By EMAIL_RECEIVED = By.cssSelector("table.table-striped.jambo_table>tbody>tr>td:nth-child(3)");
    private static final By EMAIL_LINKS_LIST = By.cssSelector("div#pills-links-content a");
    private static final By EMAIL_LINKS_TAB = By.cssSelector("a#pills-links-tab");

    public List<WebElement> getReceivedEmailElements(String emailAddressData) {
        LOG.info("email substring " + emailAddressData.substring(0, emailAddressData.indexOf("@")));
        //Redirect to Mailinator
        getInbox(emailAddressData);
        if(UrlBuilder.getLocale().equals("it") && UrlBuilder.isMobile())
        {
            waitForExpectedElement(M_EMAIL_CONFIRMATION_MESSAGE_GLO_IT, 10).isDisplayed();
            return getWebDriver().findElements(M_EMAIL_CONFIRMATION_MESSAGE_GLO_IT);
        }
        if(UrlBuilder.getLocale().equals("velobe") || UrlBuilder.getLocale().equals("velopl")) {
            return getWebDriver().findElements(EMAIL_RECEIVED_VELO);
        }  else {
            try {
                waitForExpectedElement(EMAIL_CONFIRMATION_MESSAGE, 10);
                waitForExpectedElement(EMAIL_RECEIVED, 10);
                return presenceOfAllElementsLocatedBy(EMAIL_RECEIVED);
            }catch(Exception e){
                LOG.info("Exception when waitForExpectedElement EMAIL_CONFIRMATION_MESSAGE: "+e);
                return presenceOfAllElementsLocatedBy(EMAIL_RECEIVED);
            }
        }
    }

    public void getInbox(String emailAddressData) {
        getWebDriver().get("https://www.mailinator.com/v4/public/inboxes.jsp?to=" + emailAddressData
                .substring(0, emailAddressData.indexOf("@")) + "#/#inboxpane");
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
    }
    public void clickOrOpenLatestEmailReceived(String emailAddressData) {
        getReceivedEmailElements(emailAddressData).get(0).click();
    }

    public void clickLatestEmailReceivedAndSwitchToMessageBodyIframe(String emailAddress) {
        clickOrOpenLatestEmailReceived(emailAddress);
        frameToBeAvailableAndSwitchToIt(EMAIL_MESSAGE_BODY_IFRAME);
    }

    public void switchToMessageBodyIframe() {
        frameToBeAvailableAndSwitchToIt(EMAIL_MESSAGE_BODY_IFRAME);
    }

    public void clickOnEmailWithSubject(List<WebElement> receivedEmailElements, String subject) {
        receivedEmailElements.stream()
                .filter(webElement -> webElement.getText().contains(subject))
                .findFirst().get().click();
    }

    public void clickOnEmailWithSubject(String subject) {
        waitForExpectedElement(By.xpath("//td[contains(text(),'"+ UrlBuilder.getMessage(subject)+"')]")).click();
    }

    public List<String> getAllLinkOnEmail(){
        webDriver.switchTo().defaultContent();
        waitForExpectedElement(EMAIL_LINKS_TAB).click();
        List<WebElement> emailLinkElements = getWebDriver().findElements(EMAIL_LINKS_LIST);
        return emailLinkElements.stream()
                .map(webElement -> webElement.getAttribute("href"))
                .filter(s -> !s.isEmpty())
                .collect(toCollection(LinkedList::new));
    }
}
