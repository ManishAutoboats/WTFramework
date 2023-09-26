package com.salmon.test.framework.helpers;

import com.salmon.test.enums.ServiceEndPoints;
import com.salmon.test.framework.helpers.utils.EncryptUtils;
import com.salmon.test.models.api.UserDetails;
import io.restassured.response.Response;

import static com.salmon.test.framework.helpers.ApiHelper.*;

public class AuthenticationHelper {

    public static String getAuthToken() {
        UserDetails userDetails = new UserDetails(UrlBuilder.getUserName(), EncryptUtils.decrypt(UrlBuilder.getPassWord(), EncryptUtils.generateSecretKey(UrlBuilder.getSecret())));
        Response response = givenConfig().when().log().all().body(gson().toJson(userDetails)).post(ServiceEndPoints.GET_BEARER_TOKEN.getUrl());
        return "Bearer ".concat(response.getBody().prettyPrint().replace("\"", ""));
        //hardcoded token as permission issue
//        return "Bearer vvjffvbj6h2yleucmyycvok8ss8jwnqv";
    }

    public static String getCustomerAuthToken() {
        UserDetails userDetails = new UserDetails(UrlBuilder.getMessage("loginValidEmail.key"), UrlBuilder.getMessage("loginValidPassword.key"));
        Response response = givenConfig().when().log().all().body(gson().toJson(userDetails)).post(ServiceEndPoints.GET_CUSTOMER_AUTH_BEARER_TOKEN.getUrl());
        return "Bearer ".concat(response.getBody().prettyPrint().replace("\"", ""));
    }

    public static String getCustomerAuthToken(String account, String password) {
        UserDetails userDetails = new UserDetails(account, password);
        Response response = givenBatApiConfig().when().log().all().body(gson().toJson(userDetails)).post(ServiceEndPoints.GET_CUSTOMER_AUTH_BEARER_TOKEN.getUrl());
        return "Bearer ".concat(response.getBody().prettyPrint().replace("\"", ""));
    }
}
