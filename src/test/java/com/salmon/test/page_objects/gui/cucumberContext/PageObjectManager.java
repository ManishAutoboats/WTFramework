package com.salmon.test.page_objects.gui.cucumberContext;

import com.salmon.test.page_objects.gui.MailinatorPage;
import com.salmon.test.page_objects.gui.OrderViewPage;
import com.salmon.test.page_objects.gui.gloIt.GloItLoginPage;
import com.salmon.test.page_objects.gui.gloIt.OrderHistoryPage;
import com.salmon.test.page_objects.gui.newsLetter.NewsLetterPage;
import lombok.Getter;
import org.testng.asserts.SoftAssert;

@Getter
public class PageObjectManager {

    private NewsLetterPage newsLetterPage;
    private OrderViewPage orderViewPage;
    private OrderHistoryPage odrHistPage;
    private MailinatorPage mailinatorPage;
    private SoftAssert softAssert;
    private GloItLoginPage gloItLoginPage;

    public PageObjectManager(OrderViewPage orderViewPage, OrderHistoryPage odrHistPage,MailinatorPage mailinatorPage,SoftAssert softAssert, GloItLoginPage gloItLoginPage) {
        this.newsLetterPage = NewsLetterPage.getInstance();
        this.orderViewPage = orderViewPage;
        this.odrHistPage = odrHistPage;
        this.mailinatorPage = mailinatorPage;
        this.softAssert = softAssert;
        this.gloItLoginPage = gloItLoginPage;
    }
}
