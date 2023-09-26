package com.salmon.test.services.posts;

import io.restassured.response.Response;
import com.salmon.test.enums.ServiceEndPoints;
import com.salmon.test.framework.helpers.ApiHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by Ramesh Reddy on 18/10/2018.
 */
public class CommentsApi extends ApiHelper {
    private static final Logger LOG = LoggerFactory.getLogger(CommentsApi.class);
    public static Response getComments(Map<String, String> queryParamMap) {
        Response response = null;
        try {
            String url = ServiceEndPoints.COMMENTS.getUrl();

            response = givenConfig().queryParams(queryParamMap)
                    .log().all()
                    .when()
                    .get(url);

        } catch (Exception e) {
            LOG.info(e.getMessage());
        }
        return response;
    }


    public static Response getCommentById(String id) {
        Response response = null;
        try {
            String url = ServiceEndPoints.GET_COMMENT_BY_ID.getUrl();

            response = givenConfig()
                    .log().all()
                    .when()
                    .get(url,id);

        } catch (Exception e) {
            LOG.info(e.getMessage());
        }
        return response;
    }



    public static Response createNewComment(String jsonComment) {
        Response response = null;
        try {
            String url = ServiceEndPoints.COMMENTS.getUrl();

            response = givenConfig().body(jsonComment)
                    .log().all()
                    .when()
                    .post(url);

        } catch (Exception e) {
            LOG.info(e.getMessage());
        }
        return response;
    }


}
