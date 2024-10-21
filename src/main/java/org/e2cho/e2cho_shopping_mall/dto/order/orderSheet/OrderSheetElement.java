package org.e2cho.e2cho_shopping_mall.dto.order.orderSheet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.e2cho.e2cho_shopping_mall.constant.ProductSize;
import org.e2cho.e2cho_shopping_mall.constant.ProductType;
import org.e2cho.e2cho_shopping_mall.domain.order.OrderSheet;

@Getter
@AllArgsConstructor
@Builder
public class OrderSheetElement {

    private Long orderId;
    private ProductType productType;
    private Long quantity;
    private Long totalPrice;
    private ProductOption productOption;

    public static OrderSheetElement fromEntity(OrderSheet foundOrderSheet){

        return OrderSheetElement.builder()
                .orderId(foundOrderSheet.getId())
                .productType(foundOrderSheet.getProduct().getProductType())
                .quantity(foundOrderSheet.getQuantity())
                .totalPrice(foundOrderSheet.getTotalPrice())
                .productOption(ProductOption.fromEntity(foundOrderSheet))
                .build();
    }



    @Getter
    @AllArgsConstructor
    @Builder
    public static class ProductOption{
        private String phrase;
        private String color;
        private String font;
        private ProductSize productSize;

        public static ProductOption fromEntity(OrderSheet foundOrderSheet){
            return ProductOption.builder()
                    .phrase(foundOrderSheet.getPhrase())
                    .color(foundOrderSheet.getColor())
                    .font(foundOrderSheet.getFont())
                    .productSize(foundOrderSheet.getProductSize())
                    .build();
        }
    }
}


