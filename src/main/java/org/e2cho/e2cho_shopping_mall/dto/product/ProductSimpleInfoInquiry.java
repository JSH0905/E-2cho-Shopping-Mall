package org.e2cho.e2cho_shopping_mall.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.e2cho.e2cho_shopping_mall.constant.ProductType;
import org.e2cho.e2cho_shopping_mall.domain.product.Product;

public class ProductSimpleInfoInquiry {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Dto{

        private String productName;
        private ProductType productType;
        private String productImageName;

        public static Dto fromEntity(Product foundProduct){
            return Dto.builder()
                    .productName(foundProduct.getProductName())
                    .productType(foundProduct.getProductType())
                    .productImageName(foundProduct.getProductImage())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response{

        private String message;
        private String productName;
        private ProductType productType;
        private String productImageName;

        public static Response fromDto(Dto dto){

            return Response.builder()
                    .message("상품 간단 정보를 정상적으로 불러왔습니다.")
                    .productName(dto.getProductName())
                    .productType(dto.getProductType())
                    .productImageName(dto.getProductImageName())
                    .build();
        }
    }
}
