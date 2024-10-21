package org.e2cho.e2cho_shopping_mall.dto.order.orderHistory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.e2cho.e2cho_shopping_mall.domain.order.OrderSheet;

import java.time.LocalDateTime;
import java.util.List;

public class OrderHistoryInquiry {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class OrderHistoryElement{

        private Long orderSheetId;
        private String productImageName;
        private String productName;
        private Long quantity;
        private Long totalPrice;
        private LocalDateTime paidAt;

        public static OrderHistoryElement fromEntity(OrderSheet foundOrderSheet){

            return OrderHistoryElement.builder()
                    .orderSheetId(foundOrderSheet.getId())
                    .productImageName(foundOrderSheet.getProduct().getProductImage())
                    .productName(foundOrderSheet.getProduct().getProductName())
                    .quantity(foundOrderSheet.getQuantity())
                    .totalPrice(foundOrderSheet.getTotalPrice())
                    .paidAt(foundOrderSheet.getUpdatedAt())
                    .build();
        }
    }


    @Getter
    @AllArgsConstructor
    @Builder
    public static class Dto{

        private String productImageUrlPrefix;
        private List<OrderHistoryElement> orderHistory;

        public static Dto of(String productImageUrlPrefix, List<OrderSheet> foundOrderSheets){

            return Dto.builder()
                    .productImageUrlPrefix(productImageUrlPrefix)
                    .orderHistory(foundOrderSheets.stream()
                            .map(OrderHistoryElement::fromEntity)
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
        private List<OrderHistoryElement> orderHistory;

        public static Response fromDto(Dto dto){
            return Response.builder()
                    .message("주문내역을 성공적으로 불러왔습니다.")
                    .productImageUrlPrefix(dto.getProductImageUrlPrefix())
                    .orderHistory(dto.getOrderHistory())
                    .build();

        }

    }
}
