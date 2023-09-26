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
public class PostsApi extends ApiHelper {
    private static final Logger LOG = LoggerFactory.getLogger(PostsApi.class);
    public static Response getPosts(Map<String, String> queryParamMap) {
        Response response = null;
        try {
            String url = ServiceEndPoints.POSTS.getUrl();

            response = givenConfig().queryParams(queryParamMap)
                    .log().all()
                    .when()
                    .get(url);

        } catch (Exception e) {
            LOG.info(e.getMessage());
        }
        return response;
    }


    public static Response getPostById(String id) {
        Response response = null;
        try {
            String url = ServiceEndPoints.GET_POSTS_BY_ID.getUrl();

            response = givenConfig()
                    .log().all()
                    .when()
                    .get(url,id);

        } catch (Exception e) {
            LOG.info(e.getMessage());
        }
        return response;
    }


    public static Response createNewPosts(String jsonPost) {
        Response response = null;
        try {
            String url = ServiceEndPoints.POSTS.getUrl();

            response = givenConfig().body(jsonPost)
                    .log().all()
                    .when()
                    .post(url);

        } catch (Exception e) {
            LOG.info(e.getMessage());
        }
        return response;
    }

}
