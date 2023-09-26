package com.salmon.test.services.iss;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.salmon.test.enums.ServiceEndPoints;
import com.salmon.test.framework.helpers.ApiHelper;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.models.api.ArrayAdapterFactory;
import com.salmon.test.models.api.iss.requests.*;
import com.salmon.test.models.api.iss.responses.*;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.gui.iss.IssHomePage;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.*;


public class InStoreSubscriptionServices extends ApiHelper {
    private ScenarioContext scenarioContext;

    public InStoreSubscriptionServices(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
        RestAssured.baseURI = UrlBuilder.getISSAPIPathURI();
    }

    private static Logger logger = LoggerFactory.getLogger(InStoreSubscriptionServices.class);

    public List<StaffLoginResponse> staffLogin(StaffLoginRequest payload) {
        Response response = null;
        Gson gson;
        List<StaffLoginResponse> enums = null;
        try {
            String url = ServiceEndPoints.ISS_STAFF_LOGIN.getUrl();
            response = givenConfig().log().all().body(gson().toJson(payload)).post(url);
            response.then().log().all().spec(getResponseSpecBuilder());
            gson = new GsonBuilder().registerTypeAdapterFactory(new ArrayAdapterFactory()).create();
            Type collectionType = new TypeToken<Collection<StaffLoginResponse>>(){}.getType();
            enums = gson.fromJson(response.asString(), collectionType);
        } catch (Exception e) {
            logger.info("Exception while invoking the request: " + e);
            assertThat(false).as("Exception while invoking the request: " + e).isTrue();
        }
        return enums;
    }

    public Response staffLoginRawResponse(StaffLoginRequest payload) {
        StaffLoginResponse staffLoginResponse = new StaffLoginResponse();
        Response response = null;
        try {
            String url = ServiceEndPoints.ISS_STAFF_LOGIN.getUrl();
            response = givenConfig().log().all().body(gson().toJson(payload)).post(url);
            response.then().log().all();
        } catch (Exception e) {
            logger.info("Exception while invoking the request: " + e);
            assertThat(false).as("Exception while invoking the request: " + e).isTrue();
        }
        return response;
    }

    public FetchStoresResponse fetchStores() {
        FetchStoresResponse fetchStoresResponse = new FetchStoresResponse();
        Response response;
        Gson gson;
        try {
            String url = ServiceEndPoints.ISS_FETCH_STORES.getUrl();
            response = givenConfig().log().all().get(url);
            response.then().log().all().spec(getResponseSpecBuilder());
            gson = new GsonBuilder().registerTypeAdapterFactory(new ArrayAdapterFactory()).create();
            fetchStoresResponse = gson.fromJson(response.asString(), FetchStoresResponse.class);
        } catch (Exception e) {
            logger.info("Exception while invoking the request: " + e);
            assertThat(false).as("Exception while invoking the request: " + e).isTrue();
        }
        return fetchStoresResponse;
    }

    public List<GenerateQRCodeResponse> generateQR(GenerateQRCodeRequest payload) {
        String token = scenarioContext.getContext(Context.ISS_BEARER_TOKEN).toString();
        Response response;
        Gson gson;
        List<GenerateQRCodeResponse> enums = null;
        try {
            String url = ServiceEndPoints.ISS_GENERATE_QR_CODE.getUrl();
            response = givenConfig().log().all().body(gson().toJson(payload)).header("Authorization",token).post(url);
            response.then().log().all().spec(getResponseSpecBuilder());
            gson = new GsonBuilder().registerTypeAdapterFactory(new ArrayAdapterFactory()).create();
            Type collectionType = new TypeToken<Collection<GenerateQRCodeResponse>>(){}.getType();
            enums = gson.fromJson(response.asString(), collectionType);
        } catch (Exception e) {
            logger.info("Exception while invoking the request: " + e);
            assertThat(false).as("Exception while invoking the request: " + e).isTrue();
        }
        return enums;
    }

    public Response generateQRRawResponse(GenerateQRCodeRequest payload) {
        String token = scenarioContext.getContext(Context.ISS_BEARER_TOKEN).toString();
        Response response = null;
        try {
            String url = ServiceEndPoints.ISS_GENERATE_QR_CODE.getUrl();
            response = givenConfig().log().all().body(gson().toJson(payload)).header("Authorization",token).post(url);
            response.then().log().all();
        } catch (Exception e) {
            logger.info("Exception while invoking the request: " + e);
            assertThat(false).as("Exception while invoking the request: " + e).isTrue();
        }
        return response;
    }

    public List<HandoverEmailResponse> emailHandover(HandoverEmailRequest payload) {
        String token = scenarioContext.getContext(Context.ISS_BEARER_TOKEN).toString();
        List<HandoverEmailResponse> enums = new ArrayList<>();
        Response response;
        Gson gson;
        try {
            String url = ServiceEndPoints.ISS_EMAIL_HANDOVER.getUrl();
            response = givenConfig().log().all().body(gson().toJson(payload)).header("Authorization",token).post(url);
            response.then().log().all().spec(getResponseSpecBuilder());
            gson = new GsonBuilder().registerTypeAdapterFactory(new ArrayAdapterFactory()).create();
            Type collectionType = new TypeToken<Collection<HandoverEmailResponse>>(){}.getType();
            enums = gson.fromJson(response.asString(), collectionType);
        } catch (Exception e) {
            logger.info("Exception while invoking the request: " + e);
            assertThat(false).as("Exception while invoking the request: " + e).isTrue();
        }
        return enums;
    }

    public AddToCartResponse addToCartResponse(AddToCartRequest payload) {
        String token = scenarioContext.getContext(Context.ISS_BEARER_TOKEN).toString();
        AddToCartResponse result = null;
        Response  response;
        Gson gson;
        try {
            String url = ServiceEndPoints.ISS_ADD_TO_CART.getUrl().replace("%QUOTE_ID%",scenarioContext.getContext(Context.ISS_QUOTE_ID).toString());
            response = givenConfig().log().all().body(gson().toJson(payload)).header("Authorization",token).post(url);
            response.then().log().all().spec(getResponseSpecBuilder());
            gson = new GsonBuilder().registerTypeAdapterFactory(new ArrayAdapterFactory()).create();
            result = gson.fromJson(response.asString(), AddToCartResponse.class);
        } catch (Exception e) {
            logger.info("Exception while invoking the request: " + e);
            assertThat(false).as("Exception while invoking the request: " + e).isTrue();
        }
        return result;
    }

    public Response addToCartRawResponse(AddToCartRequest payload) {
        String token = scenarioContext.getContext(Context.ISS_BEARER_TOKEN).toString();
        Response response = null;
        try {
            String url = ServiceEndPoints.ISS_ADD_TO_CART.getUrl().replace("%QUOTE_ID%",scenarioContext.getContext(Context.ISS_QUOTE_ID).toString());
            response = givenConfig().log().all().body(gson().toJson(payload)).header("Authorization",token).post(url);
            response.then().log().all();
        } catch (Exception e) {
            logger.info("Exception while invoking the request: " + e);
            assertThat(false).as("Exception while invoking the request: " + e).isTrue();
        }
        return response;
    }

    public List<SendToCustomerResponse> sendToCustomer(SendToCustomerRequest payload) {
        String token = scenarioContext.getContext(Context.ISS_BEARER_TOKEN).toString();
        List<SendToCustomerResponse> enums = new ArrayList<>();
        Response response;
        Gson gson;
        try {
            String url = ServiceEndPoints.ISS_SEND_TO_CUSTOMER.getUrl();
            response = givenConfig().log().all().body(gson().toJson(payload)).header("Authorization",token).post(url);
            response.then().log().all().spec(getResponseSpecBuilder());
            gson = new GsonBuilder().registerTypeAdapterFactory(new ArrayAdapterFactory()).create();
            Type collectionType = new TypeToken<Collection<SendToCustomerResponse>>(){}.getType();
            enums = gson.fromJson(response.asString(), collectionType);
        } catch (Exception e) {
            logger.info("Exception while invoking the request: " + e);
            assertThat(false).as("Exception while invoking the request: " + e).isTrue();
        }
        return enums;
    }

    public HandoverEmailResponse emailHandoverError(HandoverEmailRequest payload, String expectedStatusCode) {
        String token = scenarioContext.getContext(Context.ISS_BEARER_TOKEN).toString();
        HandoverEmailResponse result = null;
        Response response;
        Gson gson;
        try {
            String url = ServiceEndPoints.ISS_EMAIL_HANDOVER.getUrl();
            response = givenConfig().log().all().body(gson().toJson(payload)).header("Authorization",token).post(url);
            response.then().log().all().spec(errorResponseSpecBuilder(expectedStatusCode));
            gson = new GsonBuilder().registerTypeAdapterFactory(new ArrayAdapterFactory()).create();
            result = gson.fromJson(response.asString(), HandoverEmailResponse.class);
        } catch (Exception e) {
            logger.info("Exception while invoking the request: " + e);
            assertThat(false).as("Exception while invoking the request: " + e).isTrue();
        }
        return result;
    }

    public List<RetrieveQuoteResponse> retrieveQuote(RetrieveQuoteRequest payload) {
        String token = scenarioContext.getContext(Context.ISS_BEARER_TOKEN).toString();
        Response response;
        Gson gson;
        List<RetrieveQuoteResponse> retrieveQuoteResponse = new ArrayList<>();
        try {
            String url = ServiceEndPoints.ISS_RETRIEVE_QUOTE.getUrl();
            response = givenConfig().log().all().body(gson().toJson(payload)).header("Authorization",token).post(url);
            response.then().log().all().spec(getResponseSpecBuilder());
            gson = new GsonBuilder().registerTypeAdapterFactory(new ArrayAdapterFactory()).create();
            Type collectionType = new TypeToken<Collection<RetrieveQuoteResponse>>(){}.getType();
            retrieveQuoteResponse = gson.fromJson(response.asString(), collectionType);
        } catch (Exception e) {
            logger.info("Exception while invoking the request: " + e);
            assertThat(false).as("Exception while invoking the request: " + e).isTrue();
        }
        return retrieveQuoteResponse;
    }

    public RetrieveQuoteResponse retrieveQuoteError(RetrieveQuoteRequest payload, String expectedStatusCode) {
        String token = scenarioContext.getContext(Context.ISS_BEARER_TOKEN).toString();
        RetrieveQuoteResponse result = null;
        Response response;
        Gson gson;
        try {
            String url = ServiceEndPoints.ISS_RETRIEVE_QUOTE.getUrl();
            response = givenConfig().log().all().body(gson().toJson(payload)).header("Authorization",token).post(url);
            response.then().log().all().spec(errorResponseSpecBuilder(expectedStatusCode));
            gson = new GsonBuilder().registerTypeAdapterFactory(new ArrayAdapterFactory()).create();
            result = gson.fromJson(response.asString(), RetrieveQuoteResponse.class);
        } catch (Exception e) {
            logger.info("Exception while invoking the request: " + e);
            assertThat(false).as("Exception while invoking the request: " + e).isTrue();
        }
        return result;
    }

    public void ageVerification(AgeVerifyRequest payload, String token) {
        Response response;
        try {
            String url = ServiceEndPoints.ISS_AGE_VERIFY.getUrl();
            response = givenConfig().log().all().body(gson().toJson(payload)).header("Authorization",token).post(url);
            response.then().log().all().spec(getResponseSpecBuilder());
        } catch (Exception e) {
            logger.info("Exception while invoking the request: " + e);
            assertThat(false).as("Exception while invoking the request: " + e).isTrue();
        }
    }

    public List<GetHandoverResponse> getHandover(String quoteId) {
        List<GetHandoverResponse> result =null;
        Map<String ,String> queryParams = new HashMap<>();
        queryParams.put("quote_id", quoteId);
        String token = scenarioContext.getContext(Context.ISS_BEARER_TOKEN).toString();
        Response response = null;
        Gson gson;
        try {
            String url = ServiceEndPoints.ISS_GET_HANDOVER.getUrl();
            response = givenConfig().log().all().queryParams(queryParams).header("Authorization",token).get(url);
            response.then().log().all().spec(getResponseSpecBuilder());
            gson = new GsonBuilder().registerTypeAdapterFactory(new ArrayAdapterFactory()).create();
            Type collectionType = new TypeToken<Collection<GetHandoverResponse>>(){}.getType();
            result = gson.fromJson(response.asString(), collectionType);
        } catch (Exception e) {
            logger.info("Exception while invoking the request: " + e);
            assertThat(false).as("Exception while invoking the request: " + e).isTrue();
        }
        return result;
    }
}
