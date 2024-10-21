package org.e2cho.e2cho_shopping_mall.dto.order.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class CartDeleteAll {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response{

        private String message;

        public static Response createNewResponse(){

            return Response.builder()
                    .message("전체 상품이 장바구니에서 삭제되었습니다.")
                    .build();
        }
    }
}
