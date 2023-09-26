package com.salmon.test.framework.helpers;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.salmon.test.enums.TestConstants;
import com.salmon.test.models.api.Comment;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.apache.commons.io.FileUtils;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import org.apache.commons.*;
import org.apache.http.HttpStatus;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

/**
 * Every Api Step definition class should extend this class
 */

@Slf4j
public class ApiHelper {
    private static Gson gson;

    static {
        RestAssured.baseURI = UrlBuilder.getBasePathURI().toString();
        RestAssured.useRelaxedHTTPSValidation();
    }

    protected static RequestSpecification givenConfig () {
        return given().
                header("Accept-Language", "en")
                .header("Content-Type", "application/json");
    }

    public static RequestSpecification givenBatApiConfig() {
        return given().
                header("Accept-Language", "en")
                .header("Content-Type", "application/json")
                .header("Authorization", AuthenticationHelper.getAuthToken());
    }

    public static RequestSpecification givenCustomerToken() {
        return given().
                header("Accept-Language", "en")
                .header("Content-Type", "application/json")
                .header("Authorization", AuthenticationHelper.getCustomerAuthToken());
    }

    public static RequestSpecification givenCustomerToken(String account, String password) {
        return given().
                header("Accept-Language", "en")
                .header("Content-Type", "application/json")
                .header("Authorization", AuthenticationHelper.getCustomerAuthToken(account,password));
    }

    //Specify all one time default Gson config
    public static Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        // if uncommented will also create Json for fields which are null
        //   gsonBuilder.serializeNulls();
        gson = gson(gsonBuilder);
        return gson;
    }

    //Custom Gson config to override Default Gson  configuration
    public static Gson gson(GsonBuilder gsonBuilder) {
        gson = gsonBuilder.create();
        return gson;
    }
    public static String convertJsonFileToJsonStr(String fileName, String location) {

        String json = null;
        try {
            json = FileUtils.readFileToString(new File(location + fileName));

        } catch (Exception e) {

            e.getMessage();
        }
        return json;
    }

    public static JSONObject convertJsonFileToJsonObject(String fileName, String location) {

        JSONObject jResponse = null;

        try {

            String response = FileUtils.readFileToString(new File(location + fileName));

            StringBuffer sbResponse = new StringBuffer(response);

            JSONParser parser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);

            jResponse = (JSONObject) parser.parse(sbResponse.toString());

        } catch (Exception e) {

            e.getMessage();
        }
        return jResponse;
    }
    public static JSONArray convertJsonFileToJsonArray(String fileName, String location) {

        JSONArray jResponse = null;

        try {

            String response = FileUtils.readFileToString(new File(location + fileName));

            StringBuffer sbResponse = new StringBuffer(response);

            JSONParser parser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);

            jResponse = (JSONArray) parser.parse(sbResponse.toString());

        } catch (Exception e) {

            e.getMessage();
        }
        return jResponse;
    }

    public static JSONObject convertApiResponseToJsonObject(Response apiResponse) {

        JSONObject jResponse = null;
        try {

            StringBuffer sbResponse = new StringBuffer(apiResponse.prettyPrint());

            JSONParser parser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);

            jResponse = (JSONObject) parser.parse(sbResponse.toString());

        } catch (ClassCastException | ParseException e) {

            e.getMessage();
        }
        return jResponse;
    }

    public static JSONArray convertApiResponseToJsonArray(Response apiResponse) {

        JSONArray jResponse = null;
        try {

            StringBuffer sbResponse = new StringBuffer(apiResponse.prettyPrint());

            JSONParser parser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);

            jResponse = (JSONArray) parser.parse(sbResponse.toString());

        } catch (ClassCastException | ParseException e) {

            e.getMessage();
        }
        return jResponse;
    }


    public static List<Comment> convertResponseToCommentList(Response response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Comment> comments = mapper.readValue(response.prettyPrint(), new TypeReference<List<Comment>>() {
        });
        return comments;
    }

    public static Comment convertResponseToComment(Response response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Comment comment = mapper.readValue(response.prettyPrint(), new TypeReference<Comment>() {
        });
        return comment;
    }

    public static String convertObjectToJsonStr(Object obj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(obj);

        return jsonInString;
    }

    public static List<Comment> convertJsonFileToCommentList(String fileName, String location) throws IOException {
        String jsonInString = convertJsonFileToJsonStr(fileName, location);
        ObjectMapper mapper = new ObjectMapper();
        List<Comment> comments = mapper.readValue(jsonInString, new TypeReference<List<Comment>>() {
        });
        return comments;

    }

    public static Comment convertJsonFileToComment(String fileName, String location) throws IOException {
        String jsonInString = convertJsonFileToJsonStr(fileName, location);
        ObjectMapper mapper = new ObjectMapper();
        Comment comment = mapper.readValue(jsonInString, new TypeReference<Comment>() {
        });
        return comment;

    }

    public static <T> T convertResponseToPojo(Response response, Class<T> classType) {
        T returnType = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            returnType = mapper.readValue(response.asString(), classType);
        } catch (IOException e) {
            log.error("Exception occurred while processing response :> "+e.getMessage());
        }
        return returnType;
    }

    public static String toPrettyFormat(String json) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(jsonObject);
    }

    protected ResponseSpecification getResponseSpecBuilder() {
        ResponseSpecBuilder builder = new ResponseSpecBuilder();
        builder.expectStatusCode(HttpStatus.SC_OK);
        builder.expectContentType(String.valueOf(APPLICATION_JSON));
        return builder.build();
    }

    protected ResponseSpecification errorResponseSpecBuilder(String errorCode) {
        ResponseSpecBuilder builder = new ResponseSpecBuilder();
        builder.expectStatusCode(Integer.parseInt(errorCode));
        builder.expectContentType(String.valueOf(APPLICATION_JSON));
        return builder.build();
    }

}