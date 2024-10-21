package org.e2cho.e2cho_shopping_mall.domain.order;

import jakarta.persistence.*;
import lombok.*;
import org.e2cho.e2cho_shopping_mall.constant.ProductSize;
import org.e2cho.e2cho_shopping_mall.domain.product.Product;
import org.e2cho.e2cho_shopping_mall.domain.user.User;
import org.e2cho.e2cho_shopping_mall.dto.order.orderSheet.OrderSheetEnroll;
import org.e2cho.e2cho_shopping_mall.dto.order.orderSheet.OrderSheetRecipientUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "orderSheet")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class OrderSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "recipient")
    private String recipient;

    @Column(nullable = false, name = "address")
    private String address;

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

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public static OrderSheet of(
            Product foundProduct,
            OrderSheetEnroll.Request request,
            User user)
    {
        return OrderSheet.builder()
                .recipient(user.getName())
                .address(user.getAddress())
                .quantity(request.getQuantity())
                .totalPrice(foundProduct.getPrice() * request.getQuantity())
                .phrase(request.getPhrase() == null ? "default" : request.getPhrase())
                .color(request.getColor() == null ? "default" : request.getColor())
                .font(request.getFont() == null ? "default" : request.getFont())
                .productSize(request.getProductSize() == null ? ProductSize.DEFAULT : ProductSize.valueOf(request.getProductSize()))
                .paymentStatus(false)
                .user(user)
                .product(foundProduct)
                .build();
    }

    public void updateRecipient(OrderSheetRecipientUpdate.Request request) {

        this.recipient = request.getName();
        this.address = request.getAddress();
        this.updatedAt = LocalDateTime.now();

    }

    public void updateQuantity(Long quantity) {
        this.quantity = quantity;
        this.updatedAt = LocalDateTime.now();
    }
}
