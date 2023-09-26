package com.salmon.test.step_definitions.api;

import com.salmon.test.framework.helpers.ApiHelper;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.models.api.customer.Customer;
import com.salmon.test.models.api.customer.UpdateCustomer;
import com.salmon.test.models.api.customer.YourChoiceCustomer;
import com.salmon.test.models.api.yourchoice.CustomerPasswordToken;
import com.salmon.test.models.api.yourchoice.ResetCustomerPassword;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.services.YourChoiceService;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import io.restassured.response.Response;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.DoubleStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertTrue;

public class YourChoiceSteps {

    private YourChoiceService yourChoiceService;
    private ScenarioContext scenarioContext;
    private Response response;

    public YourChoiceSteps(ScenarioContext scenarioContext, YourChoiceService yourChoiceService){
        this.scenarioContext = scenarioContext;
        this.yourChoiceService = yourChoiceService;
    }

    @And("^create your choice token via api$")
    public void createYourChoiceToken() {
        YourChoiceCustomer yourChoiceCustomer = YourChoiceCustomer.builder()
                .username(UrlBuilder.getMessage("loginValidEmail.key"))
                .password(UrlBuilder.getMessage("loginValidPassword.key"))
                .yourChoiceLogin(true)
                .build();
        response = yourChoiceService.createYourChoiceToken(yourChoiceCustomer);
        response.then().log().status().log().body();
        scenarioContext.setContext(Context.RESPONSE_STATUS_CODE, response.getStatusCode());
        String yourChoiceToken = response.body().asString().contains(",")?response.body().asString().split(",")[1].split("]")[0].replace("\"",""):response.getBody().asString().replace("\"","");
        scenarioContext.setContext(Context.YOUR_CHOICE_TOKEN, yourChoiceToken);
    }

    @And("^create customer token via api$")
    public void createCustomerToken() {
        YourChoiceCustomer yourChoiceCustomer = YourChoiceCustomer.builder()
                .username(UrlBuilder.getMessage("loginValidEmail.key"))
                .password(UrlBuilder.getMessage("loginValidPassword.key"))
                .yourChoiceLogin(true)
                .build();
        response = yourChoiceService.createCustomerToken(yourChoiceCustomer);
        response.then().log().status().log().body();
        scenarioContext.setContext(Context.RESPONSE_STATUS_CODE, response.getStatusCode());
    }

    @And("^create customer token response should return token$")
    public void createCustomerTokenResponseShouldReturnToken(){
        String token = response.getBody().asString().replace("\"","");
        assertThat(token).containsPattern(Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{32,32}$"));
    }

    @And("^get customer password token response should return token$")
    public String getCustomerPasswordTokenResponseShouldReturnToken(){
        CustomerPasswordToken customerPasswordToken= new CustomerPasswordToken();
        customerPasswordToken=ApiHelper.convertResponseToPojo(response, CustomerPasswordToken.class);
        String token = customerPasswordToken.getToken();
        assertThat(token).containsPattern(Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{32,32}$"));
        return token;
    }

    @And("^reset customer password response should return true$")
    public void resetCustomerPasswordResponseShouldReturn(){
        assertTrue(Boolean.parseBoolean(response.getBody().asString()));
    }

    @And("^get customer password token by given email$")
    public void getCustomerPasswordToken() {
        YourChoiceCustomer yourChoiceCustomer = YourChoiceCustomer.builder()
                .username(UrlBuilder.getMessage("loginValidEmail.key"))
                .password(UrlBuilder.getMessage("loginValidPassword.key"))
                .yourChoiceLogin(true)
                .build();
        response = yourChoiceService.getCustomerPasswordToken(UrlBuilder.getMessage("loginValidEmail.key"));
        response.then().log().status().log().body();
        scenarioContext.setContext(Context.RESPONSE_STATUS_CODE, response.getStatusCode());
    }

    @And("^reset customer password by valid customer token$")
    public void resetCustomerPasswordByValidCustomerToken() {
        getCustomerPasswordToken();
        ResetCustomerPassword resetCustomerPassword = ResetCustomerPassword.builder()
                .email(UrlBuilder.getMessage("loginValidEmail.key"))
                .resetToken(getCustomerPasswordTokenResponseShouldReturnToken())
                .newPassword(UrlBuilder.getMessage("loginValidPassword.key"))
                .build();
        response = yourChoiceService.resetCustomerPassword(resetCustomerPassword);
        response.then().log().status().log().body();
        scenarioContext.setContext(Context.RESPONSE_STATUS_CODE, response.getStatusCode());
    }

    @And("^get customer by token$")
    public void getCustomerByToken() {
        getCustomerPasswordToken();
        CustomerAccountSteps.response = yourChoiceService.getCustomerByYourChoiceToken(getCustomerPasswordTokenResponseShouldReturnToken());
        CustomerAccountSteps.response.then().log().status().log().body();
        scenarioContext.setContext(Context.RESPONSE_STATUS_CODE, CustomerAccountSteps.response.getStatusCode());
    }

    @And("^get customer details by the customer token$")
    public void getCustomerByTheCustomerToken() {
        getCustomerPasswordToken();
        CustomerAccountSteps.response = yourChoiceService.getCustomerByYourChoiceToken(getCustomerPasswordTokenResponseShouldReturnToken());
        CustomerAccountSteps.response.then().log().status().log().body();
        scenarioContext.setContext(Context.RESPONSE_STATUS_CODE, CustomerAccountSteps.response.getStatusCode());
        CustomerAccountSteps.customer = ApiHelper.convertResponseToPojo(CustomerAccountSteps.response, Customer.class);
    }
}
