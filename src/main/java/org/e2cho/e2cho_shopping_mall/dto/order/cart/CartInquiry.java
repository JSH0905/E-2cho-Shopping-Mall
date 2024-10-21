package org.e2cho.e2cho_shopping_mall.dto.order.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.e2cho.e2cho_shopping_mall.domain.order.OrderSheet;
import org.e2cho.e2cho_shopping_mall.dto.order.orderSheet.OrderSheetElement;
import org.e2cho.e2cho_shopping_mall.dto.product.ProductElement;

import java.util.List;

public class CartInquiry {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class CartElement{

        private OrderSheetElement orderSheetElement;
        private ProductElement productElement;

        public static CartElement of(OrderSheet foundOrderSheet){

            return CartElement.builder()
                    .orderSheetElement(OrderSheetElement.fromEntity(foundOrderSheet))
                    .productElement(ProductElement.fromEntity(foundOrderSheet))
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Dto{

        private String productImageUrlPrefix;
        private List<CartElement> cart;

        public static Dto of(String productImageUrlPrefix, List<OrderSheet> foundOrderSheets){

            return Dto.builder()
                    .productImageUrlPrefix(productImageUrlPrefix)
                    .cart(foundOrderSheets.stream()
                            .map(CartElement::of)
                            .toList())
                    .build();

        }
    }


    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response{

        private String message;
        private String productImageUrlPrefix;
        private List<CartElement> orderSheets;

        public static Response fromDto(Dto dto) {
            return Response.builder()
                    .message("장바구니 정보를 정상적으로 불러왔습니다.")
                    .productImageUrlPrefix(dto.getProductImageUrlPrefix())
                    .orderSheets(dto.getCart())
                    .build();
        }

    }
}
