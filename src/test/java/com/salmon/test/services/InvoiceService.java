package com.salmon.test.services;

import com.google.gson.Gson;
import com.salmon.test.enums.ServiceEndPoints;
import com.salmon.test.framework.helpers.ApiHelper;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.models.api.salesorder.InvoiceOptions;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;

public class InvoiceService extends ApiHelper {

  public InvoiceService(){
    RestAssured.baseURI = UrlBuilder.getStandardAPIPathURI();
  }

  public Response createInvoice(String orderId, InvoiceOptions invoiceOptions) {
    return givenBatApiConfig()
        .when()
        .log().all()
        .body(new Gson().toJson(invoiceOptions))
        .post(ServiceEndPoints.CREATE_INVOICE_BY_ORDERID.getUrl(), orderId);
  }

  public Response emailInvoice(String invoiceId) {
    return givenBatApiConfig()
        .when()
        .log().all()
        .post(ServiceEndPoints.EMAIL_INVOICE_BY_INVOICE_ID.getUrl(), invoiceId);
  }


  public Response createInvoiceForTheOrder(int orderId, InvoiceOptions invoiceOptions) {
    return createInvoice(String.valueOf(orderId), invoiceOptions);
  }
}
