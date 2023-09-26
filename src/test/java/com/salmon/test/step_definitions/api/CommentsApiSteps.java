package com.salmon.test.step_definitions.api;

import com.salmon.test.models.api.Comment;
import com.salmon.test.services.posts.CommentsApi;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.salmon.test.framework.helpers.ApiHelper.*;
import static org.testng.AssertJUnit.assertEquals;


/**
 * Created by Ramesh Reddy on 18/10/2018.
 */
public class CommentsApiSteps {
    String id;
    Map<String, String> queryParamMap;
    Response response = null;
    Comment comment;
    String jsonStr;
    private static String RESOURCE_LOCATION = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "data" + File.separator;

    @Given("^I have comment details to get comments$")
    public void iHaveCommentDetailsToGetComments(List<List<String>> queryParams) throws Throwable {
        queryParamMap = new HashMap<>();
        if (!queryParams.get(1).get(0).equalsIgnoreCase(StringUtils.EMPTY)) {
            id = queryParams.get(1).get(0);
        }
        if (!queryParams.get(1).get(1).equalsIgnoreCase(StringUtils.EMPTY)) {
            queryParamMap.put("body", queryParams.get(1).get(1));
        }

        if (!queryParams.get(1).get(2).equalsIgnoreCase(StringUtils.EMPTY)) {
            queryParamMap.put("postId", queryParams.get(1).get(2));
        }
    }

    @When("^I send get comment request$")
    public void iSendGetCommentRequest() throws Throwable {
        response = CommentsApi.getComments(queryParamMap);
    }

    @Then("^I should get comment json response with status code (\\d+)$")
    public void iShouldGetCommentJsonResponseWithStatusCode(int statusCode) throws Throwable {
        assertEquals(statusCode, response.getStatusCode());
    }

    @And("^comments should match with \"([^\"]*)\"$")
    public void commentsShouldMatchWith(String fileName) throws Throwable {

        List<Comment> comments = convertResponseToCommentList(response);
        List<Comment> expComments = convertJsonFileToCommentList(fileName, RESOURCE_LOCATION);

        assertEquals(expComments.get(0).id, comments.get(0).id);
        assertEquals(expComments.get(0).body, comments.get(0).body);
        assertEquals(expComments.get(0).postId, comments.get(0).postId);
    }

    @And("^comment should match with \"([^\"]*)\"$")
    public void commentShouldMatchWith(String fileName) throws Throwable {

        Comment actComment = convertResponseToComment(response);
        Comment expComment = convertJsonFileToComment(fileName, RESOURCE_LOCATION);

        assertEquals(expComment.id, actComment.id);
        assertEquals(expComment.body, actComment.body);
        assertEquals(expComment.postId, actComment.postId);
    }

    @When("^I send get comment request by id$")
    public void iSendGetCommentRequestById() throws Throwable {
        response = CommentsApi.getCommentById(id);
    }


    @Given("^I have required data to create a comment$")
    public void iHaveRequiredDataToCreateAComment(List<Comment> comments) throws Throwable {
        comment = comments.get(0);
        jsonStr = convertObjectToJsonStr(comment);

    }

    @When("^I create a new comment$")
    public void iCreateANewComment() throws Throwable {
     response= CommentsApi.createNewComment(jsonStr);
    }

    @Then("^new comment should be created with status code (\\d+)$")
    public void newCommentShouldBeCreatedWithStatusCode(int statusCode) throws Throwable {
        assertEquals(statusCode, response.getStatusCode());
    }

    @And("^new comment response should be returned$")
    public void newCommentResponseShouldBeReturned() throws Throwable {
        Comment actComment = convertResponseToComment(response);

        assertEquals(comment.body,actComment.body);
        assertEquals(comment.postId,actComment.postId);

    }



}
