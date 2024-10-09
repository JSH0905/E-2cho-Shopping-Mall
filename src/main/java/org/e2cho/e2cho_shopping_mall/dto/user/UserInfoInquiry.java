package org.e2cho.e2cho_shopping_mall.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.e2cho.e2cho_shopping_mall.constant.SnsType;
import org.e2cho.e2cho_shopping_mall.domain.user.User;

public class UserInfoInquiry {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Dto{

        private Long id;
        private String snsId;
        private SnsType snsType;
        private String name;
        private String email;
        private String profileImage;
        private String telephone;
        private String address;

        public static Dto fromEntity(User validatedUser){
            return Dto.builder()
                    .id(validatedUser.getId())
                    .snsId(validatedUser.getSnsId())
                    .snsType(validatedUser.getSnsType())
                    .name(validatedUser.getName())
                    .email(validatedUser.getEmail())
                    .profileImage(validatedUser.getProfileImage())
                    .telephone(validatedUser.getTelephone() == null ? "정보 없음" : validatedUser.getTelephone())
                    .address(validatedUser.getAddress() == null ? "정보 없음" : validatedUser.getAddress())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class UserInfoDetails{

        private Long id;
        private String snsId;
        private SnsType snsType;
        private String name;
        private String email;
        private String profileImage;
        private String telephone;
        private String address;

        public static UserInfoDetails fromDto(Dto dto){
            return UserInfoDetails.builder()
                    .id(dto.getId())
                    .snsId(dto.getSnsId())
                    .snsType(dto.getSnsType())
                    .name(dto.getName())
                    .email(dto.getEmail())
                    .profileImage(dto.getProfileImage())
                    .telephone(dto.getTelephone())
                    .address(dto.getAddress())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response{

        private String message;
        private UserInfoDetails userInfoDetails;

        public static Response fromDto(Dto dto){
            return Response.builder()
                    .message("회원정보를 정상적으로 불러왔습니다.")
                    .userInfoDetails(UserInfoDetails.fromDto(dto))
                    .build();
        }

    }

}
