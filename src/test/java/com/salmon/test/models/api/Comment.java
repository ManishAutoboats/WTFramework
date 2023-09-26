package com.salmon.test.models.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

/**
 * Created by Ramesh Reddy on 18/10/2018.
 */

@FieldDefaults(level = AccessLevel.PUBLIC)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Comment {
    String id;
    String body;
    String postId;
}


