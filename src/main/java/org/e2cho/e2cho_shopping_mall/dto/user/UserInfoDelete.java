package org.e2cho.e2cho_shopping_mall.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.e2cho.e2cho_shopping_mall.constant.SnsType;

public class UserInfoDelete {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Dto {

        private Long id;
        private SnsType snsType;
        private String snsId;
        private String name;

        public static Dto fromEntity(Long id, SnsType deletedUserSnsType, String deletedUserSnsId, String deletedUserName){
            return Dto.builder()
                    .id(id)
                    .snsType(deletedUserSnsType)
                    .snsId(deletedUserSnsId)
                    .name(deletedUserName)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response{

        private String message;
        private Long id;
        private SnsType snsType;
        private String snsId;
        private String name;

        public static Response fromDto(Dto dto){
            return Response.builder()
                    .message("회원 탈퇴가 정상적으로 이루어졌습니다.")
                    .id(dto.getId())
                    .snsType(dto.getSnsType())
                    .snsId(dto.getSnsId())
                    .name(dto.getName())
                    .build();
        }
    }
}
