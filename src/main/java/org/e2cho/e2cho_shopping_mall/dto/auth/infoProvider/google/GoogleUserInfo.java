package org.e2cho.e2cho_shopping_mall.dto.auth.infoProvider.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

public class GoogleUserInfo {

    @Data
    public static class Response{

        @JsonProperty("sub")
        private String sub;

        @JsonProperty("name")
        private String name;

        @JsonProperty("given_name")
        private String givenName;

        @JsonProperty("picture")
        private String picture;

        @JsonProperty("email")
        private String email;

        @JsonProperty("email_verified")
        private Boolean emailVerified;
    }

}
