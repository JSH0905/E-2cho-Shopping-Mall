package org.e2cho.e2cho_shopping_mall.dto.product;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.e2cho.e2cho_shopping_mall.constant.ProductType;
import org.e2cho.e2cho_shopping_mall.domain.order.OrderSheet;

@Getter
@AllArgsConstructor
@Builder
public class ProductElement {

    private Long productId;
    private String productName;
    private ProductType productType;
    private String productImageName;
    private int productPrice;

    public static ProductElement fromEntity(OrderSheet foundOrderSheet){
        return ProductElement.builder()
                .productId(foundOrderSheet.getProduct().getId())
                .productName(foundOrderSheet.getProduct().getProductName())
                .productType(foundOrderSheet.getProduct().getProductType())
                .productImageName(foundOrderSheet.getProduct().getProductImage())
                .productPrice(foundOrderSheet.getProduct().getPrice())
                .build();
    }

}
