package com.salmon.test.services;

import com.google.gson.Gson;
import com.salmon.test.enums.ServiceEndPoints;
import com.salmon.test.framework.helpers.ApiHelper;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.models.api.customer.UpdateCustomer;
import com.salmon.test.models.api.customer.YourChoiceCustomer;
import com.salmon.test.models.api.yourchoice.ResetCustomerPassword;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class YourChoiceService extends ApiHelper {

    public YourChoiceService(){
        RestAssured.baseURI = UrlBuilder.getStandardAPIPathURI();
    }

    public Response createYourChoiceToken(YourChoiceCustomer yourChoiceCustomer) {
        return givenConfig()
                .when()
                .log().all()
                .body(new Gson().toJson(yourChoiceCustomer))
                .post(ServiceEndPoints.CREATE_YOUR_CHOICE_TOKEN.getUrl());
    }

    public Response createCustomerToken(YourChoiceCustomer yourChoiceCustomer) {
        return givenConfig()
                .when()
                .log().all()
                .body(new Gson().toJson(yourChoiceCustomer))
                .post(ServiceEndPoints.GET_CUSTOMER_AUTH_BEARER_TOKEN.getUrl());
    }

    public Response getCustomerPasswordToken(String email) {
        return givenBatApiConfig()
                .when()
                .log().all()
                .get(ServiceEndPoints.GET_CUSTOMER_PASSWORD_TOKEN.getUrl(),email);
    }

    public Response resetCustomerPassword(ResetCustomerPassword resetCustomerPassword) {
        return givenConfig()
                .when()
                .log().all()
                .body(new Gson().toJson(resetCustomerPassword))
                .post(ServiceEndPoints.RESET_CUSTOMER_PASSWORD.getUrl());
    }

    public Response getCustomerByYourChoiceToken(String token) {
        return givenBatApiConfig()
                .when()
                .log().all()
                .get(ServiceEndPoints.GET_CUSTOMER_BY_YOUR_CHOICE_TOKEN.getUrl(),token);
    }

    public static Response updateCustomerAccount(UpdateCustomer updateCustomer, String email, String password) {
        return givenCustomerToken(email, password)
                .when()
                .log().all()
                .body(new Gson().toJson(updateCustomer))
                .put(ServiceEndPoints.CUSTOMER_ME.getUrl());
    }
}
