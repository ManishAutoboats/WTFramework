package com.salmon.test.services;

import com.google.gson.Gson;
import com.salmon.test.enums.DatabaseQueries;
import com.salmon.test.enums.ServiceEndPoints;
import com.salmon.test.framework.helpers.ApiHelper;
import com.salmon.test.framework.helpers.BatDBHelper;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.models.api.customer.CustomAttributes;
import com.salmon.test.models.api.customer.CustomerBuilder;
import com.salmon.test.models.api.customer.CustomerBuilderMultipleLocale;
import com.salmon.test.models.api.customer.Region;
import com.salmon.test.models.api.customer.UpdateCustomer;
import com.salmon.test.models.database.AddressesDB;
import com.salmon.test.models.database.CustomerDB;
import com.salmon.test.models.database.ExtensionAttributesDB;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CustomerAccountService extends ApiHelper {

    private BatDBHelper batDBHelper;

    public CustomerAccountService(BatDBHelper batDBHelper) {
        this.batDBHelper = batDBHelper;
    }

    public Response createCustomerAccount(CustomerBuilder customerBuilder) {
        return givenBatApiConfig()
            .when()
            .log().all()
            .body(new Gson().toJson(customerBuilder))
            .post(ServiceEndPoints.CUSTOMER_ACCOUNT_CREATE.getUrl());
    }

    public Response createCustomerAccount(CustomerBuilderMultipleLocale customerBuilder) {
        return givenBatApiConfig()
            .when()
            .log().all()
            .body(new Gson().toJson(customerBuilder))
            .post(ServiceEndPoints.CUSTOMER_ACCOUNT_CREATE.getUrl());
    }

    public Response getCustomerDetailsByID(String customerId) {
        return givenBatApiConfig()
                .when()
                .log().all()
                .get(ServiceEndPoints.CUSTOMER_ACCOUNT.getUrl(), customerId);
    }

    public Response getCustomerDetailsByME() {
        return givenCustomerToken()
                .when()
                .log().all()
                .get(ServiceEndPoints.CUSTOMER_ME.getUrl());
    }

    public Response getCustomerDetailsByME(String account, String password) {
        return givenCustomerToken(account, password)
                .when()
                .log().all()
                .get(ServiceEndPoints.CUSTOMER_ME.getUrl());
    }

    public Response getCustomerDetailsByMEWithoutToken() {
        return givenConfig()
                .when()
                .log().all()
                .get(ServiceEndPoints.CUSTOMER_ME.getUrl());
    }

    public Response updateCustomerAccount(String customerId, UpdateCustomer updateCustomer) {
        return givenBatApiConfig()
                .when()
                .log().all()
                .body(new Gson().toJson(updateCustomer))
                //.put(ServiceEndPoints.CUSTOMER_ACCOUNT.getUrl(), customerId);
                .put(ServiceEndPoints.CUSTOMER_ACCOUNT.getUrl(), customerId);
    }

    public Response deleteCustomerAccount(String customerId) {
        return givenBatApiConfig()
                .when()
                .log().all()
                .delete(ServiceEndPoints.CUSTOMER_ACCOUNT.getUrl(), customerId);
    }

    public CustomerBuilder createNewCustomer() {
        return new CustomerBuilder().newCustomer();
    }

    public CustomerBuilderMultipleLocale createNewCustomerMultipleLocale(Map<String, String> userMap) {
        return new CustomerBuilderMultipleLocale().newCustomer(userMap);
    }

    public CustomerDB getCustomerDetailsFromDB(String customerId) {
        List<CustomerDB> customerList = batDBHelper.executeQueryToObject(CustomerDB.class, DatabaseQueries.GET_CUSTOMER_BY_ID.getUrl().replace("?", customerId));
        List<AddressesDB> addressesList = batDBHelper.executeQueryToObject(AddressesDB.class, DatabaseQueries.GET_CUSTOMER_ADDRESS_BY_ID.getUrl().replace("?", customerId));
        Optional.ofNullable(addressesList).get().stream().filter(address -> address.getRegion_id() != 0&& UrlBuilder.storeCode.toLowerCase().contains("de")).forEach(address -> {
            List<Region> regions = batDBHelper.executeQueryToObject(Region.class, DatabaseQueries.GET_REGION_BY_ID.getUrl().replace("?", customerId).replace("$", String.valueOf(address.getRegion_id())));
            address.setRegionInfo(Optional.ofNullable(regions).get().stream().findFirst().get());
        });


        List<CustomAttributes> customAttributesList = batDBHelper.executeQueryToObject(CustomAttributes.class, DatabaseQueries.GET_CUSTOMER_ATTRIBUTES_BY_ID.getUrl().replace("?", customerId));
        List<ExtensionAttributesDB> extensionAttributesList = batDBHelper.executeQueryToObject(ExtensionAttributesDB.class, DatabaseQueries.GET_EXTENSION_ATTRIBUTES_BY_ID.getUrl().replace("?", customerId));
        CustomerDB customer = Optional.ofNullable(customerList).get().stream().findFirst().get();
        customer.setAddresses(addressesList);
        customer.setCustom_attributes(customAttributesList);
        customer.setExtension_attributes(Optional.ofNullable(extensionAttributesList).get().stream().findFirst().orElseGet(ExtensionAttributesDB::new));
        return customer;
    }

    public boolean isCustomerExistInDB(String customerId) {
        List<CustomerDB> customerList = batDBHelper.executeQueryToObject(CustomerDB.class, DatabaseQueries.GET_CUSTOMER_BY_ID.getUrl().replace("?", customerId));
        if (customerList.size() > 0) return true;
        else return false;
    }
}
