package org.e2cho.e2cho_shopping_mall.dto.auth.signup;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.e2cho.e2cho_shopping_mall.constant.SnsType;
import org.e2cho.e2cho_shopping_mall.domain.user.User;

public class Signup {

    @Getter
    @AllArgsConstructor
    public static class Request{

        @NotNull(message = "소셜 계정의 타입이 필요합니다.")
        private SnsType snsType;

        @NotNull(message = "accessToken을 기입해주세요")
        private String accessToken;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Dto{

        private Long userId;
        private String snsId;
        private SnsType snsType;
        private String name;
        private String email;
        private String profileImage;

        public static Dto fromEntity(User newUser){
            return Dto.builder()
                    .userId(newUser.getId())
                    .snsId(newUser.getSnsId())
                    .snsType(newUser.getSnsType())
                    .name(newUser.getName())
                    .email(newUser.getEmail())
                    .profileImage(newUser.getProfileImage())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response{

        private String message;
        private UserInfo userInfo;

        public static Response fromDto(Dto dto){
            return Response.builder()
                    .message("회원가입이 정상적으로 완료되었습니다.")
                    .userInfo(UserInfo.fromDto(dto))
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class UserInfo{
        private Long userId;
        private String snsId;
        private SnsType snsType;
        private String name;
        private String email;
        private String profileImage;

        public static UserInfo fromDto(Dto dto){
            return UserInfo.builder()
                    .userId(dto.getUserId())
                    .snsId(dto.getSnsId())
                    .snsType(dto.getSnsType())
                    .name(dto.getName())
                    .email(dto.getEmail())
                    .profileImage(dto.getProfileImage())
                    .build();
        }

    }

}
