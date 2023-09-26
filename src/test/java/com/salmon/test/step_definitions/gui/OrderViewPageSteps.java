package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.OrderViewPage;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.gui.cucumberContext.TestContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.salmon.test.enums.ServiceEndPoints.GET_SALES_ORDER;
import static com.salmon.test.framework.helpers.ApiHelper.givenBatApiConfig;
import static com.salmon.test.page_objects.gui.constants.Context.ORDER_NUMBER;
import static java.util.stream.Collectors.toCollection;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderViewPageSteps {
    private static final Logger LOG = LoggerFactory.getLogger(OrderViewPageSteps.class);

    private ScenarioContext scenarioContext;
    private OrderViewPage orderViewPage;

    public OrderViewPageSteps(TestContext testContext) {
        scenarioContext = testContext.getScenarioContext();
        orderViewPage = testContext.getPageObjectManager().getOrderViewPage();
    }

    @Then("^assert free (\\d+) trail packs of the product are added to the order with sku's (.*)$")
    public void assertFreeTrailPacksOfTheProductAreAddedToTheOrderWithSkuS(int numberOfFreeProducts, String expectedSkuListKey) {
        LinkedList<String> expectedSkuList = Stream.of(UrlBuilder.getMessage(expectedSkuListKey).split(","))
                .collect(toCollection(LinkedList::new));
        orderViewPage.assertFreeTrialProductsInOrderView(numberOfFreeProducts, expectedSkuList);
    }

    @Then("^assert delivery address is the selected parcel shop address in order view page$")
    public void assertDeliveryAddressIsTheSelectedParcelShopAddress() {
        String deliveryAddressInOrderView = orderViewPage.getDeliveryAddressInOrderView();
        String expectedParcelShopAddress = (String) scenarioContext.getContext(Context.PARCEL_SHOP_ADDRESS);
        assertThat(deliveryAddressInOrderView.replace("\n", " "))
                .contains((expectedParcelShopAddress.replace("- ", "")));
    }

    @And("^assert status of the order is \"([^\"]*)\" via api$")
    public void assertStatusOfTheOrderIsViaApi(String expectedOrderStatus) {
        Response salesOrderApiResponse = getSalesOrderApiResponseForTheOrder();
        String orderIdFromFE = (String) scenarioContext.getContext(ORDER_NUMBER);
        assertThat((String) salesOrderApiResponse.path("increment_id")).isEqualTo(orderIdFromFE);
        assertThat((String) salesOrderApiResponse.path("state")).isEqualTo(expectedOrderStatus);
    }

    private Response getSalesOrderApiResponseForTheOrder() {
        String orderId = orderViewPage.getCurrentUrl().split("order_id/")[1].split("/")[0];
        scenarioContext.setContext(Context.ORDER_ID, orderId);
        Response response = givenBatApiConfig().log().all().get(GET_SALES_ORDER.getUrl(), orderId);
        LOG.info(response.getBody().asString());
        assertThat(response.getStatusCode()).isEqualTo(200);
        List<Map> items = response.path("items");
        HashMap<Object, Object> newMap = items.stream()
                .collect(Collectors.toMap(map -> map.get("item_id"), map -> map.get("qty_ordered"), (a, b) -> b, HashMap::new));
        scenarioContext.setContext(Context.ORDER_ITEM_ID_AND_QTY_MAP, newMap);
        return response;
    }

    @Then("^assert engraving details is displayed on order view page$")
    public void assertCheckOutPageDisplaysTheEngravingDetails() {
        orderViewPage.assertEngravingDetailsOnOrderViewPage();
    }
}
