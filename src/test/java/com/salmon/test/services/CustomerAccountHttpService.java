package com.salmon.test.services;

import com.google.gson.Gson;
import com.salmon.test.enums.DatabaseQueries;
import com.salmon.test.enums.ServiceEndPoints;
import com.salmon.test.framework.helpers.ApiHelper;
import com.salmon.test.framework.helpers.BatDBHelper;
import com.salmon.test.framework.helpers.HttpRequestHelper;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.models.api.customer.CustomAttributes;
import com.salmon.test.models.api.customer.CustomerBuilder;
import com.salmon.test.models.api.customer.CustomerBuilderMultipleLocale;
import com.salmon.test.models.api.customer.Region;
import com.salmon.test.models.api.customer.UpdateCustomer;
import com.salmon.test.models.database.AddressesDB;
import com.salmon.test.models.database.CustomerDB;
import com.salmon.test.models.database.ExtensionAttributesDB;
import io.restassured.RestAssured;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CustomerAccountHttpService extends HttpRequestHelper {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerAccountHttpService.class);
    private BatDBHelper batDBHelper;
    public CookieFilter filter = new CookieFilter();

    public CustomerAccountHttpService(BatDBHelper batDBHelper) {
        this.batDBHelper = batDBHelper;
    }


    public Response getCookies() {
        return givenHttpRequestConfig()
            .when()
            .log().all()
//            .body()
            .filter(filter)
            .get(ServiceEndPoints.GET_COOKIES.getUrl())
            .andReturn();
    }

    public Response createCustomerAccount(Map<String, String> formData, String CookieKey, String CookieValue) {
        RequestSpecification req =  givenHttpMultipartRequestConfig();
        for (Map.Entry<String,String> entry : formData.entrySet())
        {
            String key = entry.getKey();
            String value = entry.getValue();
            req.multiPart(key, value);
        }
        req.multiPart("street[]", "");
        req.multiPart("street[]", "");
        return req
            .when()
            .log().all()
            .filter(filter)
//            .body()
            .cookies(CookieKey,CookieValue)
            .post(ServiceEndPoints.CREATE_CUSTOMER.getUrl());
    }

    public String getWebsiteId(String code) {
        List<Map<String, Object>> lst=batDBHelper.executeQueryToMap(DatabaseQueries.GET_WEBSITE_ID.getUrl().replace("?", code));
        return lst.get(0).get("website_id").toString();
    }

    public List<Map<String, Object>> getConfirmUserInfo(String email, String code) {
        List<Map<String, Object>> userList=batDBHelper.executeQueryToMap(DatabaseQueries.GET_CUSTOMER_ID_BY_EMAIL_AND_WEBSITE_ID.getUrl().replace("{website_id}", getWebsiteId(code)).replace("{email}", email));
        if (userList.size()!=0) {
            LOG.info("customerID is " + userList.get(0).toString());
        }
        return userList;
    }

    public Response getUserConfirm(List<Map<String, Object>> lst) {
        return givenHttpRequestConfig()
                .when()
                .log().all()
//            .body()
                .filter(filter)
                .get(ServiceEndPoints.CONFIRM_CUSTOMER.getUrl(), lst.get(0).get("entity_id").toString(),lst.get(0).get("confirmation").toString());
    }


}
