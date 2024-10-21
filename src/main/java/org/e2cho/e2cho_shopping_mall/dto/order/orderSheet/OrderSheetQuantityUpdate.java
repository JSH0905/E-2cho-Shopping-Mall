package org.e2cho.e2cho_shopping_mall.dto.order.orderSheet;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.e2cho.e2cho_shopping_mall.domain.order.OrderSheet;

import java.time.LocalDateTime;

public class OrderSheetQuantityUpdate {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request{

        @NotNull(message = "수량은 필수 값입니다.")
        private Long quantity;

    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Dto{

        private Long updatedQuantity;
        private LocalDateTime updatedAt;

        public static Dto fromEntity(OrderSheet updatedOrderSheet){
            return Dto.builder()
                    .updatedQuantity(updatedOrderSheet.getQuantity())
                    .updatedAt(updatedOrderSheet.getUpdatedAt())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response{

        private String message;
        private Long updatedQuantity;
        private LocalDateTime updatedAt;

        public static Response fromDto(Dto dto){

            return Response.builder()
                    .message("수량이 정상적으로 업데이트 되었습니다.")
                    .updatedQuantity(dto.getUpdatedQuantity())
                    .updatedAt(dto.getUpdatedAt())
                    .build();
        }
    }
}
