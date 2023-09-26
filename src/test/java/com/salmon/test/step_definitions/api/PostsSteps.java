package com.salmon.test.step_definitions.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salmon.test.framework.helpers.ApiHelper;
import com.salmon.test.services.posts.PostsApi;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.path.json.JsonPath.from;
import static org.testng.AssertJUnit.assertEquals;


/**
 * Created by Ramesh Reddy on 18/10/2018.
 */
public class PostsSteps {

    private static String RESOURCE_LOCATION = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "data" + File.separator;
    Map<String, String> queryParamMap;
    Response response = null;
    String json, id;


    @Given("^I have post details to get posts$")
    public void iHavePostDetailsToGetPosts(List<List<String>> queryParams) throws Throwable {
        queryParamMap = new HashMap<>();
        if (!queryParams.get(1).get(0).equalsIgnoreCase(StringUtils.EMPTY)) {
            id = queryParams.get(1).get(0);
        }
        if (!queryParams.get(1).get(1).equalsIgnoreCase(StringUtils.EMPTY)) {
            queryParamMap.put("title", queryParams.get(1).get(1));
        }

        if (!queryParams.get(1).get(2).equalsIgnoreCase(StringUtils.EMPTY)) {
            queryParamMap.put("author", queryParams.get(1).get(2));
        }
    }

    @When("^I send get posts requests$")
    public void iSendGetPostsRequests() throws Throwable {
        response = PostsApi.getPosts(queryParamMap);
    }

    @Then("^I should get json response with status code (\\d+)$")
    public void iShouldGetJsonResponseWithStatusCode(int statusCode) throws Throwable {
        assertEquals(statusCode, response.getStatusCode());
        assertEquals("application/json; charset=utf-8", response.contentType());
    }


    @Given("^I have required data to create a post \"([^\"]*)\"$")
    public void iHaveRequiredDataToCreateAPost(String fileName) throws Throwable {
        json = ApiHelper.convertJsonFileToJsonStr(fileName, RESOURCE_LOCATION);
    }
    @When("^I create a new post$")
    public void iCreateANewPost() throws Throwable {
        response = PostsApi.createNewPosts(json);
    }
    @Then("^new post should be created with status code (\\d+)$")
    public void newPostShouldBeCreatedWithStatusCode(int statusCode) throws Throwable {
        assertEquals(statusCode, response.getStatusCode());
    }
    @And("^new post response should be returned$")
    public void newPostResponseShouldBeReturned() throws Throwable {
        Map<String, Object> expectedJsonMap = getStringObjectMap(json);
        assertEquals(expectedJsonMap.get("title"), from(response.asString()).get("title"));
        assertEquals(expectedJsonMap.get("author"), from(response.asString()).get("author"));
    }

    private Map<String, Object> getStringObjectMap(String jsonStr) throws java.io.IOException {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        ObjectMapper mapper = new ObjectMapper();
        // convert JSON string to Map
        jsonMap = mapper.readValue(jsonStr, new TypeReference<Map<String, String>>() {
        });
        return jsonMap;
    }

    @When("^I send get posts by id requests$")
    public void iSendGetPostsByIdRequests() throws Throwable {

        response = PostsApi.getPostById(id);
    }

    @And("^post should match with \"([^\"]*)\"$")
    public void postShouldMatchWith(String fileName) throws Throwable {
       JSONObject expJson, actJson;
        expJson = ApiHelper.convertJsonFileToJsonObject(fileName, RESOURCE_LOCATION);
        actJson= ApiHelper.convertApiResponseToJsonObject(response);
        assertEquals(expJson, actJson);
    }

    @And("^post should match with title \"([^\"]*)\"$")
    public void postShouldMatchWithTitle(String title) throws Throwable {

        List<Object> titleFields = from(response.getBody().asString()).getList("title");

        for(Object titleField:titleFields) {
            assertEquals(title, titleField);
        }

    }

    @And("^posts should match with \"([^\"]*)\"$")
    public void postsShouldMatchWith(String fileName) throws Throwable {
        JSONArray expJson, actJson;
        expJson = ApiHelper.convertJsonFileToJsonArray(fileName, RESOURCE_LOCATION);
        actJson= ApiHelper.convertApiResponseToJsonArray(response);
        assertEquals(expJson, actJson);
    }
}
