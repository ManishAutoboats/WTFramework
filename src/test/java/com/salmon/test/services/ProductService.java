package com.salmon.test.services;

import com.google.gson.Gson;
import com.salmon.test.enums.DatabaseQueries;
import com.salmon.test.enums.ServiceEndPoints;
import com.salmon.test.framework.helpers.ApiHelper;
import com.salmon.test.framework.helpers.BatDBHelper;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.models.api.newsletter.Subscriber;
import com.salmon.test.models.api.product.ProductBuilder;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService extends ApiHelper {

    private BatDBHelper batDBHelper;

    public ProductService(BatDBHelper batDBHelper) {
        this.batDBHelper = batDBHelper;
    }

    public Response createProduct(ProductBuilder createProduct) {
        return givenBatApiConfig()
                .when()
                .log().all()
                .body(new Gson().toJson(createProduct))
                .post(ServiceEndPoints.PRODUCTS.getUrl());
    }

    public Response getProducts(Map<String, String> productQueryMap) {
        return givenBatApiConfig()
                .when()
                .log().all()
                .queryParams(productQueryMap)
                .get(ServiceEndPoints.PRODUCTS.getUrl());
    }

    public Response getMedia(String attributeSetName) {
        return givenBatApiConfig()
                .when()
                .log().all()
                .get(ServiceEndPoints.GET_MEDIA.getUrl(),attributeSetName);
    }

    public Response getMediaCatalog(String productId) {
        return givenBatApiConfig()
                .when()
                .log().all()
                .get(ServiceEndPoints.GET_MEDIA_CATALOG.getUrl(), productId);
    }


//    public List<Subscriber> getSubscriberDetails(String subscriberId) {
//        return batDBHelper.executeQueryToObject(Subscriber.class,
//                DatabaseQueries.GET_SUBSCRIBER_BY_ID.getUrl().replace("?", subscriberId));
//    }

    public String getWebsiteId() {
        List<Map<String, Object>> lst=batDBHelper.executeQueryToMap(DatabaseQueries.GET_WEBSITE_ID.getUrl().replace("?", UrlBuilder.storeCode));
        return lst.get(0).get("website_id").toString();
    }

}
