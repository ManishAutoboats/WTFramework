package com.salmon.test.step_definitions.api;

import com.salmon.test.enums.Store;
import com.salmon.test.framework.helpers.ApiHelper;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.models.api.orderfulfilment.ShipmentTracking;
import com.salmon.test.models.api.product.Product;
import com.salmon.test.models.api.product.ProductBuilder;
import com.salmon.test.services.ProductService;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.path.json.JsonPath.from;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductSteps {

    private static final Logger LOG = LoggerFactory.getLogger(ProductSteps.class);
    private ProductService productService;
    private Response response;
    private String productId;

    public ProductSteps(ProductService productService) {
        this.productService = productService;
    }

    @When("^I create a product$")
    public void iCreateAProduct() {
        ProductBuilder createProduct = new ProductBuilder().newProduct();
        response = productService.createProduct(createProduct);
        LOG.info(response.getBody().asString());
    }

    @Then("^Create product response status code is (\\d+)$")
    public void createProductResponseStatusCodeIs(int status) {
        assertThat(response.getStatusCode()).isEqualTo(status);
    }

    @And("^Create product response should contain created product details$")
    public void createProductResponseShouldContainCreatedProductDetails() {
    }

    @And("^Create product error message is (.*)$")
    public void createProductErrorMessageIsError_msg(String errorMsg) {
    }

    @When("^I get media for the given product$")
    public void iGetMediaForTheGivenProduct() {
       response = productService.getMediaCatalog(productId);
       response.prettyPrint();
    }

    @Then("^Media response status code is (\\d+)$")
    public void mediaResponseStatusCodeIs(int status) {
        assertThat(response.getStatusCode()).isEqualTo(status);
    }

    @And("^Media catalog response should return media items$")
    public void mediaCatalogResponseShouldReturnMediaItems() {
        String items = from(response.asString()).get("items").toString();
        assertThat(items).isNotNull();
    }

    @When("^I get media items with invalid product id (.*)$")
    public void iGetMediaItemsWithInvalidProductIdOrder(String productId) {
        response = productService.getMediaCatalog(productId);
        response.prettyPrint();
    }

    @Then("^Media catalog response status code is (.*)$")
    public void mediaCatalogResponseStatusCodeIs(int status) {
        assertThat(response.getStatusCode()).isEqualTo(status);
    }

    @And("^Media catalog error message contains (.*)$")
    public void mediaCatalogErrorMessageContains(String errorMsg) {
        errorMsg= Props.getLocalMessage(errorMsg);
        assertThat(from(response.asString()).get("message").toString()).contains(errorMsg);
    }

    @When("^I get products for the given search criteria$")
    public void iGetProductsForTheGivenSearchCriteria() {
        response = productService.getProducts(productQueryMap("website_id", Store.valueOf(UrlBuilder.storeCode).getWebsiteID()));
        //response = productService.getProducts(productQueryMap("website_id", productService.getWebsiteId()));
        LOG.info(response.getBody().asString());
    }

    @Then("^Product response status code is (\\d+)$")
    public void productResponseStatusCodeIs(int status) {
        assertThat(response.getStatusCode()).isEqualTo(status);
    }

    @And("^Product response should contain created product details$")
    public void productResponseShouldContainCreatedProductDetails() {
        String items = from(response.asString()).get("items").toString();
        assertThat(items).isNotNull();
    }

    @When("^I get products for the invalid (.*) and (.*)$")
    public void iGetProductsForTheInvalidSearchCriteria(String field, String value) {
        response = productService.getProducts(productQueryMap(field, value));
    }

    @And("^Product error message is (.*)$")
    public void productErrorMessageIs(String errorMsg) {
        errorMsg= Props.getLocalMessage(errorMsg);
        assertThat(from(response.asString()).get("message").toString()).isEqualTo(errorMsg);
    }

    private Map<String, String> productQueryMap(String field, String value) {
        Map<String, String> stockStatusMap = new HashMap<>();
        stockStatusMap.put("searchCriteria[filter_groups][0][filters][0][field]", field);
        stockStatusMap.put("searchCriteria[filter_groups][0][filters][0][value]", value);
        stockStatusMap.put("searchCriteria[filter_groups][0][filters][0][condition_type]", "eq");
        stockStatusMap.put("fields", "items[sku,source_code,quantity,status]");
        return stockStatusMap;
    }

    @When("^I get media for the given attributeSet name$")
    public void iGetMediaForTheGivenAttributeSetName() {
        String attributeSetName= UrlBuilder.storeCode.split("_")[0];
        response = productService.getMedia(attributeSetName);
        response.prettyPrint();
    }

    @And("^Media response should return media items$")
    public void mediaResponseShouldReturnMediaItems() {
        String items = from(response.asString()).get("items").toString();
        assertThat(items).isNotNull();
    }

    @And("^Product response should contain the product id$")
    public void productResponseShouldContainTheProductId() {
        productId = from(response.asString()).get("items.sku").toString().split(",")[0].substring(1);
    }
}
