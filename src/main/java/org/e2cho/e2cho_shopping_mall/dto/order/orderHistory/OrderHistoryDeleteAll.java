package org.e2cho.e2cho_shopping_mall.dto.order.orderHistory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class OrderHistoryDeleteAll {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response{

        public String message;

        public static Response createNewResponse(){

            return Response.builder()
                    .message("전체 주문내역이 삭제되었습니다.")
                    .build();
        }
    }
}
