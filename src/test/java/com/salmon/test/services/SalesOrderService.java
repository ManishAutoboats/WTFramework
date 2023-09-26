package com.salmon.test.services;

import com.google.gson.Gson;
import com.salmon.test.enums.DatabaseQueries;
import com.salmon.test.enums.ServiceEndPoints;
import com.salmon.test.framework.helpers.ApiHelper;
import com.salmon.test.framework.helpers.BatDBHelper;
import com.salmon.test.models.api.orderfulfilment.*;
import com.salmon.test.models.api.product.ProductBuilder;
import com.salmon.test.models.api.salesorder.SalesOrder;
import com.salmon.test.models.api.salesorder.SalesOrderBuilder;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SalesOrderService extends ApiHelper {

    private BatDBHelper batDBHelper;

    public SalesOrderService(BatDBHelper batDBHelper) {
        this.batDBHelper = batDBHelper;
    }

    public Response getSalesOrderDetails(String orderId) {
        return givenBatApiConfig()
                .when()
                .log().all()
                .get(ServiceEndPoints.GET_SALES_ORDER.getUrl(), orderId);
    }

    public Response createSalesOrderDetails(SalesOrderBuilder salesOrderBuilder) {
        return givenBatApiConfig()
                .when()
                .log().all()
                .body(new Gson().toJson(salesOrderBuilder))
                .post(ServiceEndPoints.CREATE_SALES_ORDER.getUrl());
    }

    public Response createQuote() {
        return givenBatApiConfig()
                .when()
                .log().all()
                .post(ServiceEndPoints.CREATE_QUOTE.getUrl());
    }

    public Response createShipmentForTheSalesOrder(String orderId, OrderFulfilment orderFulfilment) {
        return givenBatApiConfig()
                .when()
                .log().all()
                .body(new Gson().toJson(orderFulfilment))
                .post(ServiceEndPoints.CREATE_SHIPMENT.getUrl(), orderId);
    }

    public Response getShipmentTrackingDetails(String shipId) {
        return givenBatApiConfig()
                .when()
                .log().all()
                .get(ServiceEndPoints.GET_TRACKING_INFO_FROM_SHIPMENT.getUrl(), shipId);
    }

    public Response updateShipmentTrackingUrl(ShipmentTrackUpdate shipmentTrackUpdate) {
        return givenBatApiConfig()
                .when()
                .log().all()
                .body(new Gson().toJson(shipmentTrackUpdate))
                .post(ServiceEndPoints.UPDATE_TRACKING_URL.getUrl());
    }

    public Response getStockStatus(String skuValue) {
        return givenBatApiConfig()
                .when()
                .log().all()
                .queryParams(stockStatusQueryMap(skuValue))
                .get(ServiceEndPoints.STOCK_STATUS.getUrl());
    }

    public Response updateStockStatus(StockUpdate stockUpdate) {
        return givenBatApiConfig()
                .when()
                .log().all()
                .body(new Gson().toJson(stockUpdate))
                .post(ServiceEndPoints.STOCK_STATUS.getUrl());
    }

//    public Response getCarts(String orderId, OrderFulfilment orderFulfilment) {
//        return givenBatApiConfig()
//                .when()
//                .log().all()
//                .body(new Gson().toJson(orderFulfilment))
//                .get(ServiceEndPoints.GET_CARTS.getUrl(), orderId);
//    }

    public Response getCarts(String quoteId) {
        return givenBatApiConfig()
                .when()
                .log().all()
                .get(ServiceEndPoints.GET_CARTS.getUrl(),quoteId);
    }

//    TODO: Need to find efficient way to handle below
    private Map<String, String> stockStatusQueryMap(String skuValue) {
        Map<String, String> stockStatusMap = new HashMap<>();
        stockStatusMap.put("searchCriteria[filter_groups][0][filters][0][field]", "sku");
        stockStatusMap.put("searchCriteria[filter_groups][0][filters][0][value]", skuValue);
        stockStatusMap.put("searchCriteria[filter_groups][0][filters][0][condition_type]", "eq");
        return stockStatusMap;
    }

    public SalesOrder getSalesOrderFromDB(String orderId) {
       List<SalesOrder> salesOrders = batDBHelper.executeQueryToObject(SalesOrder.class, DatabaseQueries.GET_SALES_ORDER.getUrl().replace("?", orderId));
       return  Optional.ofNullable(salesOrders).get().stream().findFirst().get();
    }

    public SalesOrderBuilder createNewSalesOrder(int customerID, String email) {
        return new SalesOrderBuilder().createNewSalesOrder(customerID, email);
    }

    public OrderFulfilment createNewShipment() {
        return new ShipmentBuilder().createNewShipmentForTheOrder();
    }

    public InventoryItems getInventoryFromDB(String sku) {
        List<InventoryItems> inventoryItems = batDBHelper.executeQueryToObject(InventoryItems.class, DatabaseQueries.GET_INVENTORY.getUrl().replace("?", sku));
        return  Optional.ofNullable(inventoryItems).get().stream().findFirst().get();
    }
}
