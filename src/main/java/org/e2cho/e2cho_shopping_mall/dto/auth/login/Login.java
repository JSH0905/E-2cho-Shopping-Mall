package org.e2cho.e2cho_shopping_mall.dto.auth.login;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.e2cho.e2cho_shopping_mall.constant.SnsType;


public class Login {

    @Getter
    @Setter
    public static class Request{
        @NotNull(message = "소셜 계정의 타입이 필요합니다.")
        private SnsType snsType;

        @NotNull(message = "accessToken이 필요합니다.")
        private String accessToken;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Dto {

        private Token token;

        public static Dto of(String accessToken, String refreshToken){
            return Dto.builder()
                    .token(Token.of(accessToken, refreshToken))
                    .build();
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Token{

        private String accessToken;
        private String refreshToken;

        public static Token of(String accessToken, String refreshToken){
            return Token.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response{

        private String message;
        private Token token;

        public static Response fromDto(Dto dto){
            return Response.builder()
                    .message("로그인이 정상적으로 처리되었습니다.")
                    .token(dto.getToken())
                    .build();
        }

    }

}
