package com.salmon.test.framework.helpers;


import static io.restassured.RestAssured.given;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.salmon.test.models.api.Comment;
import io.restassured.RestAssured;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.File;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.apache.commons.io.FileUtils;

/**
 * Every Api Step definition class should extend this class
 */

@Slf4j
public class HttpRequestHelper {
    private static Gson gson;

    static {
        //RestAssured.baseURI = UrlBuilder.getHttpRequestPathURI().toString();
        RestAssured.useRelaxedHTTPSValidation();
    }


    protected static RequestSpecification givenHttpRequestConfig() {
        return given().
            header("Accept-Language", "en");
    }


    protected static RequestSpecification givenHttpMultipartRequestConfig() {
        return given().
            header("Accept-Language", "en")
            .header("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundaryVxid7K86IujTRxEu");
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




}