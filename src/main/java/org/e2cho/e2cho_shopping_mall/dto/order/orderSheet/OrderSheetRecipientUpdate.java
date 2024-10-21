package org.e2cho.e2cho_shopping_mall.dto.order.orderSheet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.e2cho.e2cho_shopping_mall.domain.order.OrderSheet;

import java.time.LocalDateTime;

public class OrderSheetRecipientUpdate {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request{

        @NotNull(message = "받는분 이름은 필수 값입니다.")
        @NotBlank(message = "이름은 공백일 수 없습니다.")
        @Size(min = 2, max = 20)
        private String name;

        @NotNull(message = "주소는 필수 값입니다.")
        @NotBlank(message = "주소는 공백일 수 없습니다.")
        private String address;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Dto{

        private String updatedName;
        private String updatedAddress;
        private LocalDateTime updatedAt;

        public static Dto fromEntity(OrderSheet updatedOrderSheet) {
            return Dto.builder()
                    .updatedName(updatedOrderSheet.getRecipient())
                    .updatedAddress(updatedOrderSheet.getAddress())
                    .updatedAt(updatedOrderSheet.getUpdatedAt())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response{

        private String message;
        private String updatedName;
        private String updatedAddress;
        private LocalDateTime updatedAt;


        public static Response fromDto(Dto dto){

            return Response.builder()
                    .message("정보가 정상적으로 업데이트 되었습니다.")
                    .updatedName(dto.getUpdatedName())
                    .updatedAddress(dto.getUpdatedAddress())
                    .updatedAt(dto.getUpdatedAt())
                    .build();
        }
    }
}
