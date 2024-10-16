package org.e2cho.e2cho_shopping_mall.domain.order;

import jakarta.persistence.*;
import lombok.*;
import org.e2cho.e2cho_shopping_mall.constant.ProductSize;
import org.e2cho.e2cho_shopping_mall.constant.ProductType;
import org.e2cho.e2cho_shopping_mall.domain.product.Product;
import org.e2cho.e2cho_shopping_mall.domain.user.User;
import org.e2cho.e2cho_shopping_mall.dto.order.PurchaseOrderEnroll;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "purchaseOrder")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "product_type")
    private ProductType productType;

    @Column(nullable = false, name = "product_image")
    private String productImageName;

    @Column(nullable = false, name = "quantity")
    private Long quantity;

    @Column(nullable = false, name = "total_price")
    private Long totalPrice;

    @Column(nullable = false, name = "phrase")
    private String phrase;

    @Column(nullable = false, name = "color")
    private String color;

    @Column(nullable = false, name = "font")
    private String font;

    @Column(nullable = false, name = "size")
    private ProductSize productSize;

    @Column(nullable = false, name = "payment_status")
    private boolean paymentStatus;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public static PurchaseOrder of(
            Product foundProduct,
            PurchaseOrderEnroll.Request request,
            User user)
    {
        return PurchaseOrder.builder()
                .productType(foundProduct.getProductType())
                .productImageName(foundProduct.getProductImage())
                .quantity(request.getQuantity())
                .totalPrice(foundProduct.getPrice() * request.getQuantity())
                .phrase(request.getPhrase() == null ? "default" : request.getPhrase())
                .color(request.getColor() == null ? "default" : request.getColor())
                .font(request.getFont() == null ? "default" : request.getFont())
                .productSize(request.getProductSize() == null ? ProductSize.DEFAULT : ProductSize.valueOf(request.getProductSize()))
                .paymentStatus(false)
                .user(user)
                .build();
    }
}
