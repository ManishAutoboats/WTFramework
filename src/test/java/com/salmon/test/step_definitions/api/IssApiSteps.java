package com.salmon.test.step_definitions.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.salmon.test.enums.TestConstants;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.models.api.ArrayAdapterFactory;
import com.salmon.test.models.api.iss.requests.*;
import com.salmon.test.models.api.iss.responses.*;
import com.salmon.test.page_objects.gui.LogininPage;
import com.salmon.test.page_objects.gui.MailinatorPage;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.services.iss.InStoreSubscriptionServices;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

public class IssApiSteps {
    private LogininPage logininPage;
    private MailinatorPage mailinatorPage;
    private ScenarioContext scenarioContext;
    private FetchStoresResponse fetchStoresResponse;
    private InStoreSubscriptionServices inStoreSubscriptionServices;
    private List<StaffLoginResponse> staffLoginResponse;
    private StaffLoginResponse staffLoginResponseError;
    private List<GenerateQRCodeResponse> generatedQRCode;
    private StaffLoginRequest staffLoginRequest;
    private Response response;
    private GenerateQRCodeRequest generateQRCodeRequest;
    private HandoverEmailResponse handoverEmailResponse;
    private List<HandoverEmailResponse> handoverEmailResponseList;
    private List<SendToCustomerResponse> sendToCustomerResponse;
    private List<RetrieveQuoteResponse> retrieveQuoteResponse;
    private RetrieveQuoteResponse retrieveQuoteResponseError;
    private List<String> addedProducts = new ArrayList<>();
    private AddToCartResponse addToCartResponse;

    public IssApiSteps(InStoreSubscriptionServices inStoreSubscriptionServices, ScenarioContext scenarioContext, MailinatorPage mailinatorPage, LogininPage logininPage  )  {
        this.inStoreSubscriptionServices = inStoreSubscriptionServices;
        this.scenarioContext = scenarioContext;
        this.mailinatorPage = mailinatorPage;
        this.logininPage = logininPage;
    }

    @When("^I can retrieve a list of stores$")
    public void iCanRetrieveAListOfStores() {
        fetchStoresResponse = inStoreSubscriptionServices.fetchStores();
    }

    @And("^I login as a staff user \"([^\"]*)\" with password \"([^\"]*)\"$")
    public void iLoginAsAStaffUserWithPassword(String user, String pass) {
        String outletId = fetchStoresResponse.getItems().get(2).getOutlet_id();
        String username = UrlBuilder.getMessage(user);
        String password = UrlBuilder.getMessage(pass);
        staffLoginRequest = new StaffLoginRequest();
        staffLoginRequest.setUsername(username);
        staffLoginRequest.setPassword(password);
        staffLoginRequest.setOutlet_id(outletId);
        staffLoginResponse = inStoreSubscriptionServices.staffLogin(staffLoginRequest);
        scenarioContext.setContext(Context.ISS_BEARER_TOKEN,"BEARER "+staffLoginResponse.get(0).getToken());
        scenarioContext.setContext(Context.ISS_QUOTE_ID, staffLoginResponse.get(0).getQuote_id());
    }

    @And("^I generate a QR code for type \"([^\"]*)\" and site \"([^\"]*)\"$")
    public void iGenerateAQRCodeForTypeAndSite(String type, String site) {
        generateQRCodeRequest = new GenerateQRCodeRequest();
        generateQRCodeRequest.setType(type);
        generateQRCodeRequest.setData(site);
        generatedQRCode = inStoreSubscriptionServices.generateQR(generateQRCodeRequest);
    }

    @When("^I attempt to login with the error condition \"([^\"]*)\"$")
    public void iAttemptToLoginWithTheErrorCondition(String errCondition) {
        staffLoginRequest = new StaffLoginRequest();
        String username = UrlBuilder.getMessage("inStoreSubUser");
        String password = UrlBuilder.getMessage("inStoreSubPassword");
        String outletId = fetchStoresResponse.getItems().get(2).getOutlet_id();
        switch (errCondition.toLowerCase()) {
            case "no outlet id":
                staffLoginRequest.setUsername(username);
                staffLoginRequest.setPassword(password);
                break;
            case "no username":
                staffLoginRequest.setPassword(password);
                staffLoginRequest.setOutlet_id(outletId);
                break;
            case "no password":
                staffLoginRequest.setUsername(username);
                staffLoginRequest.setOutlet_id(outletId);
                break;
            case "invalid user":
                staffLoginRequest.setUsername(username + "X");
                staffLoginRequest.setPassword(password);
                staffLoginRequest.setOutlet_id(outletId);
                break;
            case "invalid password":
                staffLoginRequest.setUsername(username);
                staffLoginRequest.setPassword(password + "X");
                staffLoginRequest.setOutlet_id(outletId);
                break;
            case "invalid outlet id":
                staffLoginRequest.setUsername(username);
                staffLoginRequest.setPassword(password);
                staffLoginRequest.setOutlet_id("1234567890");
                break;
            default:
                assertThat(true).as("ERROR invalid test condition "+errCondition+" supplied").isFalse();
        }
        response = inStoreSubscriptionServices.staffLoginRawResponse(staffLoginRequest);
    }

    @Then("^I am returned the following \"([^\"]*)\"$")
    public void iAmReturnedTheFollowing(String expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        assertThat(actualStatusCode == Integer.parseInt(expectedStatusCode))
                .as("ERROR: expected status code was "+expectedStatusCode+" but got "+actualStatusCode).isTrue();
    }

    @And("^indicating the \"([^\"]*)\"$")
    public void indicatingThe(String expectedFieldName)  {
        String actualFieldName = staffLoginResponseError.getParameters().getFieldName();
        assertThat(expectedFieldName.equalsIgnoreCase(actualFieldName))
                .as("ERROR: expected field name was "+expectedFieldName+" but got "+actualFieldName).isTrue();
    }

    @And("^error message \"([^\"]*)\" is returned$")
    public void errorMessageIsReturned(String expectedErrorMessage) {
        String actualErrorMessage;
        staffLoginResponseError = null;
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new ArrayAdapterFactory()).create();
        staffLoginResponseError = gson.fromJson(response.asString(), StaffLoginResponse.class);
        actualErrorMessage = staffLoginResponseError.getMessage();
        assertThat(actualErrorMessage.toLowerCase().contains(expectedErrorMessage.toLowerCase()))
                .as("ERROR: expected error message was "+expectedErrorMessage+" but got "+actualErrorMessage).isTrue();
    }

    @And("^I attempt to generate a QR code with \"([^\"]*)\"$")
    public void iAttemptToGenerateAQRCodeWith(String errCondition) throws Throwable {
        generateQRCodeRequest = new GenerateQRCodeRequest();
        String type = "";
        String data = "https://www.vuse.com/gb/en/";
        String token = "";
        switch (errCondition) {
            case "no type":
                generateQRCodeRequest.setData(data);
                break;
            case "no data":
                generateQRCodeRequest.setType(type);
                break;
            case "invalid data":
                generateQRCodeRequest.setData("X");
                generateQRCodeRequest.setType(type);
                break;
            case "invalid type":
                generateQRCodeRequest.setData(data);
                generateQRCodeRequest.setType("X");
                break;
            case "invalid token":
                token = scenarioContext.getContext(Context.ISS_BEARER_TOKEN).toString();
                token = token + "X";
                scenarioContext.setContext(Context.ISS_BEARER_TOKEN, token);
                break;
            default:
                assertThat(true).as("ERROR invalid test condition "+errCondition+" supplied").isFalse();
        }
        response = inStoreSubscriptionServices.generateQRRawResponse(generateQRCodeRequest);
    }

    @And("^where \"([^\"]*)\" is for missing parameters the \"([^\"]*)\" is reported$")
    public void whereIfForMissingParametersTheIsReported(String errorCondition, String expectedFieldName) {
        if (errorCondition.toLowerCase().contains("no")) {
            String actualFieldName = staffLoginResponseError.getParameters().getFieldName();
            assertThat(expectedFieldName.equalsIgnoreCase(actualFieldName))
                    .as("ERROR: expected field name was "+expectedFieldName+" but got "+actualFieldName).isTrue();
        }
    }

    @And("^I call the handover email service$")
    public void iCallTheHandoverEmailService() {
        HandoverEmailRequest handoverEmailRequest = new HandoverEmailRequest();
        handoverEmailRequest.setEmail(UrlBuilder.getMessage("issCustomer"));
        handoverEmailRequest.setQuote_id(scenarioContext.getContext(Context.ISS_QUOTE_ID).toString());
        handoverEmailRequest.setOutlet_id(fetchStoresResponse.getItems().get(0).getOutlet_id());
        handoverEmailRequest.setStaff_id(UrlBuilder.getMessage("inStoreSubUser"));
        handoverEmailResponseList = inStoreSubscriptionServices.emailHandover(handoverEmailRequest);
    }

    @And("^add a product to the cart$")
    public void addAProductToTheCart() {
        AddToCartRequest addToCartRequest = new AddToCartRequest();
        CartItem cartItem = new CartItem();
        cartItem.setQty("1");
        cartItem.setSku("VUSE-EPOD-CART-TM-UK");
        cartItem.setQuote_id(scenarioContext.getContext(Context.ISS_QUOTE_ID).toString());
        Product_option productOption = new Product_option();
        Extension_attributes extension_attributes = new Extension_attributes();
        Configurable_item_option configurable_item_option = new Configurable_item_option();
        configurable_item_option.setOption_id("373");
        configurable_item_option.setOption_value("6285");
        List<Configurable_item_option> configurableItemOptions = new ArrayList<>();
        configurableItemOptions.add(configurable_item_option);
        extension_attributes.setConfigurable_item_options(configurableItemOptions);
        productOption.setExtension_attributes(extension_attributes);
        cartItem.setProduct_option(productOption);
        addToCartRequest.setCartItem(cartItem);
        response = inStoreSubscriptionServices.addToCartRawResponse(addToCartRequest);
    }

    @When("^I call the send to customer service$")
    public void iCallTheSendToCustomerService() {
        SendToCustomerRequest sendToCustomerRequest = new SendToCustomerRequest();
        sendToCustomerRequest.setEmail(UrlBuilder.getMessage("issCustomer"));
        sendToCustomerRequest.setQuote_id(scenarioContext.getContext(Context.ISS_QUOTE_ID).toString());
        sendToCustomerResponse = inStoreSubscriptionServices.sendToCustomer(sendToCustomerRequest);
    }

    @And("^I call the handover email service with \"([^\"]*)\" error condition$")
    public void iCallTheHandoverEmailServiceWithCerrorCondition(String errorCondition) throws Throwable {
        HandoverEmailRequest handoverEmailRequest = new HandoverEmailRequest();
        String expectedHttpStatus = "";
        switch (errorCondition) {
            case "invalid email address":
                expectedHttpStatus = String.valueOf(HttpStatus.SC_BAD_REQUEST);
                handoverEmailRequest.setEmail(UrlBuilder.getMessage("loginInvalidEmail.key"));
                handoverEmailRequest.setQuote_id(scenarioContext.getContext(Context.ISS_QUOTE_ID).toString());
                handoverEmailRequest.setStaff_id("jelsen");
                handoverEmailRequest.setOutlet_id("12345");
                break;
            case "token not found":
                scenarioContext.setContext(Context.ISS_BEARER_TOKEN, "");
                expectedHttpStatus = String.valueOf(HttpStatus.SC_UNAUTHORIZED);
                handoverEmailRequest.setQuote_id(scenarioContext.getContext(Context.ISS_QUOTE_ID).toString());
                handoverEmailRequest.setStaff_id("jelsen");
                handoverEmailRequest.setOutlet_id("12345");
                break;
            case "token invalid":
                scenarioContext.setContext(Context.ISS_BEARER_TOKEN, "invalid");
                handoverEmailRequest.setQuote_id(scenarioContext.getContext(Context.ISS_QUOTE_ID).toString());
                handoverEmailRequest.setStaff_id("jelsen");
                handoverEmailRequest.setOutlet_id("12345");
                expectedHttpStatus = String.valueOf(HttpStatus.SC_UNAUTHORIZED);
                break;
            default:
                assertThat(true).as("ERROR: invalid error condition "+errorCondition+" supplied").isFalse();
        }
        handoverEmailResponse = inStoreSubscriptionServices.emailHandoverError(handoverEmailRequest, expectedHttpStatus);
    }

    @Then("^I get the correct value in the \"([^\"]*)\" response parameter$")
    public void iGetTheCorrectValueInTheResponseParameter(String expectedMessage) throws Throwable {
        String actualMessage = handoverEmailResponse.getMessage();
        assertThat(expectedMessage.equalsIgnoreCase(actualMessage.trim()))
                .as("ERROR: expected message was "+expectedMessage+" but got "+actualMessage).isTrue();
    }

    @Then("^the handover email service response is correct$")
    public void theHandoverEmailServiceResponseIsCorrect() {
        assertThat(handoverEmailResponseList.get(0).getStatus().equals("1")).as("ERROR: expected status = 1 but got "+handoverEmailResponseList.get(0).getStatus()).isTrue();
    }

    @Then("^the user receives an email containing \"([^\"]*)\"$")
    public void theUserReceivesAnEmailContaining(String expectedText) throws Throwable {
        mailinatorPage.clickLatestEmailReceivedAndSwitchToMessageBodyIframe(UrlBuilder.getMessage("issCustomer"));
        logininPage.assertTextOfRegisteredCustomersIsDisplayed(expectedText);
    }

    @When("^I call the retrieve a quote service$")
    public void iCallTheRetrieveAQuoteService() {
        RetrieveQuoteRequest retrieveQuoteRequest = new RetrieveQuoteRequest();
        retrieveQuoteRequest.setQuote_id(scenarioContext.getContext(Context.ISS_QUOTE_ID).toString());
        retrieveQuoteRequest.setHash(sendToCustomerResponse.get(0).getId());
        retrieveQuoteResponse = inStoreSubscriptionServices.retrieveQuote(retrieveQuoteRequest);
    }

    @When("^an empty array is returned in the response$")
    public void anemptyArrayisReturnedInTheResponse() {
        assertThat(retrieveQuoteResponse.size() == 0)
                .as("ERROR: expected empty response but got "+retrieveQuoteResponse).isTrue();
    }

    @When("^I call the retrieve a quote service with an invalid hash$")
    public void iCallTheRetrieveAquoteServiceWithAnInvalidHash() {
        RetrieveQuoteRequest retrieveQuoteRequest = new RetrieveQuoteRequest();
        retrieveQuoteRequest.setQuote_id(scenarioContext.getContext(Context.ISS_QUOTE_ID).toString());
        retrieveQuoteRequest.setHash("invalidHash");
        retrieveQuoteResponseError = inStoreSubscriptionServices.retrieveQuoteError(retrieveQuoteRequest, "403");
    }

    @And("^add a \"([^\"]*)\" strength \"([^\"]*)\" product to the cart$")
    public void addAStrengthProductToTheCart(String requiredStrength, String sku) throws Throwable {
        String strengthOptionValue = "";
        if (requiredStrength.contains("12")) {
            strengthOptionValue = "6286";
        }
        AddToCartRequest addToCartRequest = new AddToCartRequest();
        CartItem cartItem = new CartItem();
        cartItem.setQty("1");
        cartItem.setSku(sku);
        cartItem.setQuote_id(scenarioContext.getContext(Context.ISS_QUOTE_ID).toString());
        Product_option productOption = new Product_option();
        Extension_attributes extension_attributes = new Extension_attributes();
        Configurable_item_option configurable_item_option = new Configurable_item_option();
        configurable_item_option.setOption_id(UrlBuilder.getMessage("strengthOptionId"));
        configurable_item_option.setOption_value(strengthOptionValue);
        List<Configurable_item_option> configurableItemOptions = new ArrayList<>();
        configurableItemOptions.add(configurable_item_option);
        extension_attributes.setConfigurable_item_options(configurableItemOptions);
        productOption.setExtension_attributes(extension_attributes);
        cartItem.setProduct_option(productOption);
        addToCartRequest.setCartItem(cartItem);
        addToCartResponse = inStoreSubscriptionServices.addToCartResponse(addToCartRequest);
        addedProducts.add(addToCartResponse.getName());
    }

    @Then("^the out of stock product is returned in the response$")
    public void theOutOfStockProductIsReturnedInTheResponse() {
        assertThat(retrieveQuoteResponse.size() > 0)
                .as("ERROR: product was not retuned as missing in response").isTrue();
        for (RetrieveQuoteResponse product: retrieveQuoteResponse) {
            assertThat(addedProducts.contains(product.getName())).as("ERROR: product "+product.getName()+" not reported as missing").isTrue();
        }
    }

    @When("^I call the retrieve a quote service with an invalid quote id$")
    public void iCallTheRetrieveAQuoteServiceWithAnInvalidQuoteId() {
        RetrieveQuoteRequest retrieveQuoteRequest = new RetrieveQuoteRequest();
        retrieveQuoteRequest.setQuote_id("invalidquoteId");
        retrieveQuoteRequest.setHash(sendToCustomerResponse.get(0).getId());
        retrieveQuoteResponseError = inStoreSubscriptionServices.retrieveQuoteError(retrieveQuoteRequest, "400");
    }

    @Then("^an invalid hash is indicated in the error response$")
    public void anInvalidHashIsIndicatedInTheErrorResponse() {
        assertThat(retrieveQuoteResponseError.getMessage().contains("Invalid has")).isTrue();
    }
}
