package com.salmon.test.page_objects.gui.admin;

import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ContentPage extends PageObject {

    StorePage storePage = new StorePage();
    private final static By DASHBOARD_CONTENT_LINK = By.xpath("//li[@id='menu-magento-backend-content']//span[text()='Content']");

    public int assertEnabledStatusForBannerAndReturnCountForLyftStore() {
        int intBannerCount = 0;
        if (getWebDriver().findElements(storePage.SEARCH_RESULTS_ROWS).size() > 0) {
            List<WebElement> rowData = getWebDriver().findElements(storePage.SEARCH_RESULTS_ROWS);
            for (WebElement row : rowData) {
                if (row.getText().contains("Enabled")) {
                    intBannerCount++;
                } else {
                    LOG.info("No Action Needed.");
                }
            }
        }
        return intBannerCount;
    }

    public void performBackEndStepsToAssertFreeDeliveryBannerConfiguration() throws Throwable {
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        storePage.clickMenuLinkFromAdminDashboard(DASHBOARD_CONTENT_LINK);
        storePage.clickLinkFromMenuWindow(storePage.BLOCKS_LINK);
        storePage.removeAnyDefaultSearchFilters();
        storePage.performSearch("LYFTLAB / deliveryBanner / Top");
    }
}


