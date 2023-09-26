package com.salmon.test.step_definitions.api;

import com.salmon.test.framework.helpers.ApiHelper;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.models.api.customer.Customer;
import com.salmon.test.models.api.customer.CustomerBuilder;
import com.salmon.test.models.api.orderfulfilment.*;
import com.salmon.test.models.api.salesorder.Items;
import com.salmon.test.models.api.salesorder.SalesOrder;
import com.salmon.test.models.api.salesorder.SalesOrderBuilder;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.services.CustomerAccountService;
import com.salmon.test.services.SalesOrderService;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.path.json.JsonPath.from;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class SalesOrderSteps {

    private static final Logger LOG = LoggerFactory.getLogger(SalesOrderSteps.class);
    private SalesOrderService salesOrderService;
    private Response response;
    private String orderId;
    private SalesOrder salesOrder;
    private List<Integer> itemIds;
    private String shipId;
    private String shipmentTrackId;
    private String trackingUrl;
    private String sku;
    private Track expectedTrackDetails;
    private StockUpdate stockUpdate;
    private String quoteId;
    private CustomerAccountService customerAccountService;
    private Customer customer;
    private Customer actualCustomer;
    private ScenarioContext scenarioContext;

    public SalesOrderSteps(ScenarioContext scenarioContext, SalesOrderService salesOrderService, CustomerAccountService customerAccountService) {
        this.salesOrderService = salesOrderService;
        this.customerAccountService=customerAccountService;
        this.scenarioContext = scenarioContext;
    }

    @When("^I get sales order details with the order id (.*)$")
    public void i_get_sales_order_details_with_the_order_id(String orderId) {
        this.orderId = orderId;
        response = salesOrderService.getSalesOrderDetails(orderId);
        LOG.info(response.getBody().asString());
    }

    @When("^I get sales order details with the given order id$")
    public void i_get_sales_order_details_with_the_given_order_id() {
        this.orderId = orderId;
        response = salesOrderService.getSalesOrderDetails(orderId);
    }

    @Then("^Sales order response status code is (\\d+)$")
    public void sales_order_response_status_code_is(int status) {
       assertThat(response.getStatusCode()).isEqualTo(status);
    }

    @Then("^Sales order response should contain valid user details$")
    public void sales_order_response_should_contain_valid_user_details() {
        SalesOrder expectedSalesOrder =  salesOrderService.getSalesOrderFromDB(orderId);
        SalesOrder actualSalesOrder = ApiHelper.convertResponseToPojo(response, SalesOrder.class);
        Optional.ofNullable(actualSalesOrder).ifPresent(
                order -> {
                    assertThat(order.getCustomer_firstname()).isEqualTo(expectedSalesOrder.getCustomer_firstname());
                    assertThat(order.getCustomer_lastname()).isEqualTo(expectedSalesOrder.getCustomer_lastname());
                    assertThat(order.getCustomer_dob()).isEqualTo(expectedSalesOrder.getCustomer_dob());
                    assertThat(order.getCustomer_email()).isEqualTo(expectedSalesOrder.getCustomer_email());

                }
        );
    }

    @Then("^Sales order response should contain created order details$")
    public void sales_order_response_should_contain_created_order_details() {
        SalesOrder expectedSalesOrder =  salesOrder;
        SalesOrder actualSalesOrder = ApiHelper.convertResponseToPojo(response, SalesOrder.class);
        Optional.ofNullable(actualSalesOrder).ifPresent(
                order -> {
                    assertThat(order.getCustomer_firstname()).isEqualTo(expectedSalesOrder.getCustomer_firstname());
                    assertThat(order.getCustomer_lastname()).isEqualTo(expectedSalesOrder.getCustomer_lastname());
                    assertThat(order.getCustomer_dob()).isEqualTo(expectedSalesOrder.getCustomer_dob());
                    assertThat(order.getCustomer_email()).isEqualTo(expectedSalesOrder.getCustomer_email());

                   orderId = String.valueOf(order.getItems().stream().map(Items::getOrder_id).findFirst().orElse(0));
                   scenarioContext.setContext(Context.ORDER_ID, orderId);
                   itemIds = order.getItems().stream().map(Items::getItem_id).collect(Collectors.toList());
                   Map<Integer, Integer> map = order.getItems().stream().collect(Collectors.toMap(Items::getItem_id, Items::getQty_ordered));
                   scenarioContext.setContext(Context.ORDER_ITEM_ID_AND_QTY_MAP, map);
                   sku = String.valueOf(order.getItems().stream().map(Items::getSku).findFirst().orElse("0"));
                }
        );
    }

    @And("^Sales order error message is (.*)$")
    public void salesOrderErrorMessageIs(String errorMsg) {
        errorMsg= Props.getLocalMessage(errorMsg);
        assertThat(from(response.asString()).get("message").toString()).isEqualTo(errorMsg);
    }

    @When("^I create sales order$")
    public void iCreateSalesOrder() {
        Customer newCustomer = getNewCustomer();
        SalesOrderBuilder salesOrderBuilder = salesOrderService.createNewSalesOrder(newCustomer.getId(), newCustomer.getEmail());
        salesOrder = salesOrderBuilder.getEntity();
        response = salesOrderService.createSalesOrderDetails(salesOrderBuilder);
        LOG.info(response.getBody().prettyPrint());
    }

    public Customer getNewCustomer() {
        CustomerBuilder customerBuilder = customerAccountService.createNewCustomer();
        customer = customerBuilder.getCustomer();
        response = customerAccountService.createCustomerAccount(customerBuilder);
        LOG.info(response.getBody().asString());
        actualCustomer = ApiHelper.convertResponseToPojo(response, Customer.class);
        return actualCustomer;
    }

    @When("^I create a shipment for the given order$")
    public void iCreateAShipmentForTheGivenOrder() {
        OrderFulfilment orderFulfilment = salesOrderService.createNewShipment();
        com.salmon.test.models.api.orderfulfilment.Items items = com.salmon.test.models.api.orderfulfilment.Items.builder()
                .order_item_id(String.valueOf(itemIds.stream().findFirst().get())).qty(1).build();
        orderFulfilment.getItems().set(0, items);
        response = salesOrderService.createShipmentForTheSalesOrder(orderId, orderFulfilment);
        LOG.info(response.getBody().asString());
        
    }

    @When("^the user create a quote$")
    public void iCreateQuote() {
        response = salesOrderService.createQuote();
        LOG.info(response.getBody().asString());
    }

//    @When("^I get cart items for the given order$")
//    public void iGetCartItemsForTheGivenOrder() {
//        OrderFulfilment orderFulfilment = salesOrderService.createNewShipment();
//        com.salmon.test.models.api.orderfulfilment.Items items = com.salmon.test.models.api.orderfulfilment.Items.builder()
//                .order_item_id(String.valueOf(itemIds.stream().findFirst().get())).qty(1).build();
//        orderFulfilment.getItems().set(0, items);
//
//        //TODO: comment below line once the below line is fixed
//        //response = salesOrderService.getCarts("10334", orderFulfilment);
//        //TODO: Uncomment below when dynamic order id works
//        response = salesOrderService.getCarts(quoteId, orderFulfilment);
//        LOG.info(response.getBody().asString());
//
//    }

    @Then("^Shipment response status code is (\\d+)$")
    public void shipmentResponseStatusCodeIs(int status) {
        assertThat(response.getStatusCode()).isEqualTo(status);
    }


    @Then("^Carts response status code is (\\d+)$")
    public void cartsResponseStatusCodeIs(int status) {
        assertThat(response.getStatusCode()).isEqualTo(status);
    }

    @And("^Shipment response should contain valid user details$")
    public void shipmentResponseShouldContainValidUserDetails() {
        
    }

    @When("^I create a shipment with invalid order id (.*)$")
    public void iCreateAShipmentWithInvalidOrderId(String orderId) {
        OrderFulfilment orderFulfilment = salesOrderService.createNewShipment();
        response = salesOrderService.createShipmentForTheSalesOrder(orderId, orderFulfilment);
        response.prettyPrint();
    }

    @When("^I get cart items with invalid order id (.*)$")
    public void iGetCartItemsWithInvalidOrderId(String orderId) {
        OrderFulfilment orderFulfilment = salesOrderService.createNewShipment();
        response = salesOrderService.getCarts(orderId);
    }

    @When("^I create a shipment with invalid order item id (.*)$")
    public void iCreateAShipmentWithInvalidOrderItemId(String orderItemId) {
        OrderFulfilment orderFulfilment = salesOrderService.createNewShipment();
        com.salmon.test.models.api.orderfulfilment.Items items = com.salmon.test.models.api.orderfulfilment.Items.builder()
                .order_item_id(orderItemId).qty(1).build();
        orderFulfilment.getItems().set(0, items);
        response = salesOrderService.createShipmentForTheSalesOrder(orderId, orderFulfilment);
    }

    @And("^Shipment error message contains (.*)$")
    public void shipmentErrorMessageContains(String errorMsg) {
        errorMsg= Props.getLocalMessage(errorMsg);
        assertThat(from(response.asString()).get("message").toString()).contains(errorMsg);
    }

    @And("^Carts error message contains (.*)$")
    public void cartsErrorMessageContains(String errorMsg) {
        errorMsg= Props.getLocalMessage(errorMsg);
        assertThat(from(response.asString()).get("message").toString()).contains(errorMsg);
    }

    @When("^I create a shipment with invalid tracking details (.*)$")
    public void iCreateAShipmentWithInvalidTrackingDetails(String trackType) {
        OrderFulfilment orderFulfilment = salesOrderService.createNewShipment();
        com.salmon.test.models.api.orderfulfilment.Items items = com.salmon.test.models.api.orderfulfilment.Items.builder()
                .order_item_id(String.valueOf(itemIds.stream().findFirst().get())).qty(1).build();
        orderFulfilment.getItems().set(0, items);
        switch (trackType) {
            case "invalidTrackNumber":
                orderFulfilment.getTracks().get(0).setTrack_number(null);
                break;
            case "invalidCarrierCode":
                orderFulfilment.getTracks().get(0).setCarrier_code(null);
                break;
        }
        response = salesOrderService.createShipmentForTheSalesOrder(orderId, orderFulfilment);
    }

    @And("^Shipment response should return shipment id$")
    public void shipmentResponseShouldReturnShipmentId() {
        shipId = response.getBody().prettyPrint().replace("\"", "");
        assertThat(shipId).containsOnlyDigits();
    }

    @And("^Carts response should return cart items$")
    public void CartsResponseShouldReturnCartItems() {
        //String actualOrderId = from(response.asString()).get("id").toString();
        String actualQuoteId = from(response.asString()).get("id").toString();
        String items = from(response.asString()).get("items").toString();
        //TODO: comment below line once the below line is fixed
        //assertThat(actualOrderId).isEqualTo("10334");
        //TODO: Uncomment below when dynamic order id works
        //assertThat(actualOrderId).isEqualTo(orderId);
        assertThat(actualQuoteId).isEqualTo(quoteId);
        assertThat(items).isNotNull();
        //TODO: comment below line once the below line is fixed
        //assertThat(items).hasSize(392);
        //TODO: Uncomment below when dynamic order id works
        assertThat(items).hasSize(2);
    }

    @When("^I get shipment tracking details for the given order$")
    public void iGetShipmentTrackingDetailsForTheGivenOrder() {
        response = salesOrderService.getShipmentTrackingDetails(shipId);
        LOG.info(response.prettyPrint());
    }

    @When("^I get shipment tracking details with invalid ship id (.*)$")
    public void iGetShipmentTrackingDetailsWithInvalidShipIdShip_id(String shipId) {
        response = salesOrderService.getShipmentTrackingDetails(shipId);
    }

    @And("^Shipment response should return tracking details$")
    public void shipmentResponseShouldReturnTrackingDetails() {
        ShipmentTracking shipmentTracking = ApiHelper.convertResponseToPojo(response, ShipmentTracking.class);
        log.info("Order id:> "+shipmentTracking.getOrder_id());
        log.info("Shipment Tracking Url:> "+shipmentTracking.getTracks().stream().findFirst().get());
        //trackingUrl = shipmentTracking.getTracks().stream().findFirst().get().getTrack_number();
        shipmentTrackId = shipmentTracking.getTracks().stream().findFirst().get().getEntity_id();
        sku = shipmentTracking.getItems().stream().findFirst().get().getSku();
    }

    @When("^I update shipment tracking details for the given order$")
    public void iUpdateShipmentTrackingDetailsForTheGivenOrder() {
        expectedTrackDetails = Track.builder().parent_id(shipmentTrackId).tracking_url("http://yodel.co.uk/tracking/"+orderId).build();
        ShipmentTrackUpdate shipmentTrackUpdate = ShipmentTrackUpdate.builder().track(expectedTrackDetails).build();
        response = salesOrderService.updateShipmentTrackingUrl(shipmentTrackUpdate);
        response.prettyPrint();
    }

    @When("^I update shipment tracking details with invalid ship id (.*)$")
    public void iUpdateShipmentTrackingDetailsWithInvalidShipId(String shipId) {
        Track track = Track.builder().parent_id(orderId).tracking_url("http://yodel.co.uk/tracking/"+orderId).build();
        track.setParent_id(shipId);
        ShipmentTrackUpdate shipmentTrackUpdate = ShipmentTrackUpdate.builder().track(track).build();
        response = salesOrderService.updateShipmentTrackingUrl(shipmentTrackUpdate);
    }

    @When("^I get stock status details for the given sku$")
    public void iGetStockStatusDetailsForTheGivenSku() {
        response = salesOrderService.getStockStatus(sku);
    }

    @Then("^Stock status response status code is (\\d+)$")
    public void stockStatusResponseStatusCodeIs(int status) {
        assertThat(response.getStatusCode()).isEqualTo(status);
    }

    @And("^Stock status response should return stock details$")
    public void stockStatusResponseShouldReturnStockDetails() {
        StockCheck stockCheck = ApiHelper.convertResponseToPojo(response, StockCheck.class);
        InventoryItems actualInventory = stockCheck.getItems().stream().findFirst().get();
        log.info("Actual stock status check>>>>>>>>>"+stockCheck.toString());
        InventoryItems expectedInventory = salesOrderService.getInventoryFromDB(sku);
        //Asserting Stock status details
        assertThat(actualInventory.getSku()).isEqualTo(expectedInventory.getSku());
        assertThat(Double.valueOf(actualInventory.getQuantity())).isEqualTo(Double.valueOf(expectedInventory.getQuantity()));
        assertThat(actualInventory.getSource_code()).isEqualTo(expectedInventory.getSource_code());
        assertThat(actualInventory.getStatus()).isEqualTo(expectedInventory.getStatus());
    }

    @And("^Update Shipment tracking response should return tracking details$")
    public void updateShipmentTrackingResponseShouldReturnTrackingDetails() {
        Track actualTrackDetails = ApiHelper.convertResponseToPojo(response, Track.class);
        assertThat(actualTrackDetails.getParent_id()).isEqualTo(expectedTrackDetails.getParent_id());
        assertThat(actualTrackDetails.getTracking_url()).isEqualTo(expectedTrackDetails.getTracking_url());
    }

    @When("^I get stock status details for the sku (.*)$")
    public void iGetStockStatusDetailsForTheSku(String sku) {
        response = salesOrderService.getStockStatus(sku);
    }

    @And("^Stock status response should return empty status$")
    public void stockStatusResponseShouldReturnEmptyStatus() {
        StockCheck stockCheck = ApiHelper.convertResponseToPojo(response, StockCheck.class);
        assertThat(stockCheck.getItems()).isEmpty();
    }

    @When("^I update stock status details for the given sku$")
    public void iUpdateStockStatusDetailsForTheGivenSku(List<InventoryItems> inventoryItems) {
        InventoryItems inventoryItem = inventoryItems.stream().findFirst().get();
        inventoryItem.setSku(sku);
        inventoryItem.setSource_code("default");
        stockUpdate = StockUpdate.builder().sourceItems(Arrays.asList(inventoryItem)).build();
        response = salesOrderService.updateStockStatus(stockUpdate);
    }

    @And("^Stock status update response should return empty response$")
    public void stockStatusUpdateResponseShouldReturnEmptyResponse() {
        assertThat(response.getBody().prettyPrint()).hasSize(0);
    }

    @And("^Stock status update response should return stock details$")
    public void stockStatusUpdateResponseShouldReturnStockDetails() {
        StockCheck stockCheck = ApiHelper.convertResponseToPojo(response, StockCheck.class);
        InventoryItems expectedStock = stockUpdate.getSourceItems().stream().findFirst().get();
        InventoryItems actualStock = stockCheck.getItems().stream().findFirst().get();
        //Assertions
        assertThat(actualStock.getSku()).isEqualTo(expectedStock.getSku());
        assertThat(actualStock.getQuantity()).isEqualTo(expectedStock.getQuantity());
        assertThat(actualStock.getSource_code()).isEqualTo(expectedStock.getSource_code());
        assertThat(actualStock.getStatus()).isEqualTo(expectedStock.getStatus());
    }

    @When("^I update stock status details for the sku (.*)$")
    public void iUpdateStockStatusDetailsForTheSku(String sku, List<InventoryItems> inventoryItems) {
        InventoryItems inventoryItem = inventoryItems.stream().findFirst().get();
        inventoryItem.setSku(sku);
        inventoryItem.setSource_code("default");
        stockUpdate = StockUpdate.builder().sourceItems(Arrays.asList(inventoryItem)).build();
        response = salesOrderService.updateStockStatus(stockUpdate);
    }

    @And("^Shipment response should return shipment id as empty$")
    public void shipmentResponseShouldReturnShipmentIdAsZero() {
        shipId = response.getBody().prettyPrint();
        assertThat(shipId).isEmpty();
    }

    @Then("^the quote response status code is (\\d+)$")
    public void theQuoteResponseStatusCodeIs(int status) {
        assertThat(response.getStatusCode()).isEqualTo(status);
    }

    @And("^the quote response should contain the created cart id$")
    public void theQuoteResponseShouldContainTheCreatedCartId() {
        quoteId=response.getBody().asString().replace("\"", "");
    }

    @When("^I get cart items for the given quote$")
    public void iGetCartItemsForTheGivenQuote() {
        response = salesOrderService.getCarts(quoteId);
        LOG.info(response.getBody().asString());
    }
}
