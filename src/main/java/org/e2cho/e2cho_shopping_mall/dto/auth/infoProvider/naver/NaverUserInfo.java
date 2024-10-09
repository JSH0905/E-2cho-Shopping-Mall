package org.e2cho.e2cho_shopping_mall.dto.auth.infoProvider.naver;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

public class NaverUserInfo {

    @Data
    public static class Response{

        @JsonProperty("resultcode")
        private String resultCode;

        @JsonProperty("message")
        private String message;

        @JsonProperty("response")
        private ResponseDetails responseDetails;
    }

    @Data
    public static class ResponseDetails {

        @JsonProperty("id")
        private String id;

        @JsonProperty("nickname")
        private String nickname;

        @JsonProperty("name")
        private String name;

        @JsonProperty("email")
        private String email;

        @JsonProperty("gender")
        private String gender;

        @JsonProperty("age")
        private String age;

        @JsonProperty("birthday")
        private String birthday;

        @JsonProperty("profile_image")
        private String profileImage;

        @JsonProperty("birthyear")
        private String birthyear;

        @JsonProperty("mobile")
        private String mobile;

    }
}
