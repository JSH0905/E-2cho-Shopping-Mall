package org.e2cho.e2cho_shopping_mall.dto.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

public class UserInfoUpdate {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {

        @NotNull(message = "이름은 필수 값입니다.")
        @Size(min = 2, max = 20, message = "이름이 너무 짧거나 깁니다.")
        private String name;

        @NotNull(message = "주소는 필수 값입니다.")
        private String address;

        @NotNull(message = "전화번호는 필수 값입니다.")
        @Pattern(regexp = "^01[016789]-\\d{3,4}-\\d{4}$", message = "유효한 형식의 휴대폰 번호를 입력해 주세요. 예: 010-1234-5678")
        private String telephone;

    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {

        private String message;

        public static Response createNewResponse() {
            return Response.builder()
                    .message("회원 정보가 정상적으로 수정되었습니다.")
                    .build();
        }

    }
}
