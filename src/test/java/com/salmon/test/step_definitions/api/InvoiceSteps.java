package com.salmon.test.step_definitions.api;

import com.salmon.test.models.api.salesorder.InvoiceOptions;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.services.InvoiceService;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.apache.commons.collections.map.HashedMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvoiceSteps {

    private InvoiceService invoiceService;
    private ScenarioContext scenarioContext;

    public InvoiceSteps(ScenarioContext scenarioContext, InvoiceService invoiceService) {
        this.scenarioContext = scenarioContext;
        this.invoiceService = invoiceService;
    }

    @And("^create invoice for the order via api$")
    public void createInvoiceForTheOrder() {
        int orderId = Integer.parseInt((String) scenarioContext.getContext(Context.ORDER_ID));
        Map<Integer, Integer> orderItemIdAndQtyMap = (Map<Integer, Integer>) scenarioContext.getContext(Context.ORDER_ITEM_ID_AND_QTY_MAP);
        InvoiceOptions invoiceOptions = InvoiceOptions.withDefaultInvoiceOptions(orderItemIdAndQtyMap);
        Response invoiceResponse = invoiceService.createInvoiceForTheOrder(orderId, invoiceOptions);
        invoiceResponse.then().log().status().log().body();
        scenarioContext.setContext(Context.RESPONSE_STATUS_CODE, invoiceResponse.getStatusCode());
        int invoiceIdForTheOrder = Integer.parseInt(invoiceResponse.body().as(String.class));
        scenarioContext.setContext(Context.INVOICE_ID, invoiceIdForTheOrder);
    }

    @When("^invoice is emailed to the customer$")
    public void invoiceIsEmailedToTheCustomer() {
        String invoiceId = String.valueOf(scenarioContext.getContext(Context.INVOICE_ID));
        Response response = invoiceService.emailInvoice(invoiceId);
        response.then().log().status().log().body();
        scenarioContext.setContext(Context.RESPONSE_STATUS_CODE, response.getStatusCode());
    }
}
