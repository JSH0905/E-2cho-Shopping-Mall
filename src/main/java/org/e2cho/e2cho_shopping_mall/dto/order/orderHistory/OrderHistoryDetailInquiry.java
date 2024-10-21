package org.e2cho.e2cho_shopping_mall.dto.order.orderHistory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.e2cho.e2cho_shopping_mall.constant.ProductSize;
import org.e2cho.e2cho_shopping_mall.domain.order.OrderSheet;

import java.time.LocalDateTime;

public class OrderHistoryDetailInquiry {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Dto{

        private String productImageUrlPrefix;
        private OrderInfo orderInfo;
        private ProductInfo productInfo;
        private DeliveryAddressInfo deliveryAddressInfo;
//        private PaymentInfo paymentInfo; 추후에 추가

        public static Dto of(String productImageUrlPrefix, OrderSheet foundOrderSheet){

            return Dto.builder()
                    .productImageUrlPrefix(productImageUrlPrefix)
                    .orderInfo(OrderInfo.fromEntity(foundOrderSheet))
                    .productInfo(ProductInfo.fromEntity(foundOrderSheet))
                    .deliveryAddressInfo(DeliveryAddressInfo.fromEntity(foundOrderSheet))
                    .build();
        }

    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class OrderInfo{

        private Long orderSheetId;
        private LocalDateTime paidAt;

        public static OrderInfo fromEntity(OrderSheet foundOrderSheet){

            return OrderInfo.builder()
                    .orderSheetId(foundOrderSheet.getId())
                    .paidAt(foundOrderSheet.getUpdatedAt())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class ProductInfo{

        private String productName;
        private String productImageName;
        private ProductOption productOption;
        private Long totalPrice;

        public static ProductInfo fromEntity(OrderSheet foundOrderSheet){

            return ProductInfo.builder()
                    .productName(foundOrderSheet.getProduct().getProductName())
                    .productImageName(foundOrderSheet.getProduct().getProductImage())
                    .productOption(ProductOption.fromEntity(foundOrderSheet))
                    .totalPrice(foundOrderSheet.getTotalPrice())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class ProductOption{

        private String phrase;
        private String color;
        private String font;
        private ProductSize productSize;
        private Long quantity;

        public static ProductOption fromEntity(OrderSheet foundOrderSheet){

            return ProductOption.builder()
                    .phrase(foundOrderSheet.getPhrase())
                    .color(foundOrderSheet.getColor())
                    .font(foundOrderSheet.getFont())
                    .productSize(foundOrderSheet.getProductSize())
                    .quantity(foundOrderSheet.getQuantity())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class DeliveryAddressInfo{

        private String recipientName;
        private String telephone;
        private String address;

        public static DeliveryAddressInfo fromEntity(OrderSheet foundOrderSheet){

            return DeliveryAddressInfo.builder()
                    .recipientName(foundOrderSheet.getRecipient())
                    .telephone(foundOrderSheet.getUser().getTelephone())
                    .address(foundOrderSheet.getAddress())
                    .build();
        }
    }

//    @Getter
//    @AllArgsConstructor
//    @Builder
//    public static class PaymentInfo{
//
//
//    }


    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response{

        private String message;
        private String productImageUrlPrefix;
        private OrderInfo orderInfo;
        private ProductInfo productInfo;
        private DeliveryAddressInfo deliveryAddressInfo;

        public static Response fromDto(Dto dto){

            return Response.builder()
                    .message("주문내역 상세정보를 불러왔습니다.")
                    .productImageUrlPrefix(dto.getProductImageUrlPrefix())
                    .orderInfo(dto.getOrderInfo())
                    .productInfo(dto.getProductInfo())
                    .deliveryAddressInfo(dto.getDeliveryAddressInfo())
                    .build();
        }
    }




}


