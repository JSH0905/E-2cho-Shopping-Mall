package org.e2cho.e2cho_shopping_mall.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.e2cho.e2cho_shopping_mall.constant.ProductType;
import org.e2cho.e2cho_shopping_mall.domain.product.Product;

public class ProductDetailInfoInquiry {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Dto{

        private ProductType productType;
        private String productImageName;
        private int price;
        private String description;

        public static Dto fromEntity(Product foundProduct){
            return Dto.builder()
                    .productType(foundProduct.getProductType())
                    .productImageName(foundProduct.getProductImage())
                    .price(foundProduct.getPrice())
                    .description(foundProduct.getDescription())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response{

        private String message;
        private ProductType productType;
        private String productImageName;
        private int price;
        private String description;

        public static Response fromDto(Dto dto){
            return Response.builder()
                    .message("상품 상세정보를 정상적으로 불러왔습니다.")
                    .productType(dto.getProductType())
                    .productImageName(dto.getProductImageName())
                    .price(dto.getPrice())
                    .description(dto.getDescription())
                    .build();
        }
    }
}
