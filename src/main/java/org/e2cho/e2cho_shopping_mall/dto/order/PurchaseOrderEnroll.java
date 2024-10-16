package org.e2cho.e2cho_shopping_mall.dto.order;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.e2cho.e2cho_shopping_mall.constant.ProductSize;
import org.e2cho.e2cho_shopping_mall.constant.ProductType;
import org.e2cho.e2cho_shopping_mall.domain.order.PurchaseOrder;

import java.time.LocalDateTime;

public class PurchaseOrderEnroll {

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class Request{

        @NotNull(message = "상품 타입은 필수 값입니다.")
        @NotBlank(message = "상품 타입은 공백일 수 없습니다.")
        private String productType;

        @NotNull(message = "수량은 필수 값입니다.")
        @Min(value = 1, message = "최소 수량은 1개입니다.")
        @Max(value = 10, message = "최대 수량은 10개입니다.")
        private Long quantity;

        @Size(min = 3, max = 8, message = "문구는 최소 3글자에서 10글자까지 가능합니다.")
        private String phrase;
        private String color;
        private String font;
        private String productSize;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Dto{

        private String buyerName;
        private String address;
        private String productImageName;
        private ProductType productType;
        private Long quantity;
        private Long totalPrice;
        private String phrase;
        private String color;
        private String font;
        private ProductSize productSize;
        private boolean paymentStatus;
        private LocalDateTime createdAt;

        public static Dto fromEntity(PurchaseOrder newPurchaseOrder){

            return Dto.builder()
                    .buyerName(newPurchaseOrder.getUser().getName())
                    .address(newPurchaseOrder.getUser().getAddress())
                    .productImageName(newPurchaseOrder.getProductImageName())
                    .productType(newPurchaseOrder.getProductType())
                    .quantity(newPurchaseOrder.getQuantity())
                    .totalPrice(newPurchaseOrder.getTotalPrice())
                    .phrase(newPurchaseOrder.getPhrase())
                    .color(newPurchaseOrder.getColor())
                    .font(newPurchaseOrder.getFont())
                    .productSize(newPurchaseOrder.getProductSize())
                    .paymentStatus(newPurchaseOrder.isPaymentStatus())
                    .createdAt(newPurchaseOrder.getCreatedAt())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response{
        private String message;
        private String buyerName;
        private String address;
        private String productImageName;
        private ProductType productType;
        private Long quantity;
        private Long totalPrice;
        private String phrase;
        private String color;
        private String font;
        private ProductSize productSize;
        private boolean paymentStatus;
        private LocalDateTime createdAt;

        public static Response fromDto(Dto dto){

            return Response.builder()
                    .message("상품이 장바구니에 성공적으로 담겼습니다.")
                    .buyerName(dto.getBuyerName())
                    .address(dto.getAddress())
                    .productType(dto.getProductType())
                    .quantity(dto.getQuantity())
                    .totalPrice(dto.getTotalPrice())
                    .phrase(dto.getPhrase())
                    .color(dto.getColor())
                    .font(dto.getFont())
                    .productSize(dto.getProductSize())
                    .paymentStatus(dto.isPaymentStatus())
                    .createdAt(dto.getCreatedAt())
                    .build();
        }
    }
}
