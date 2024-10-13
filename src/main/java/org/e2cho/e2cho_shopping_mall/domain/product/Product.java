package org.e2cho.e2cho_shopping_mall.domain.product;

import jakarta.persistence.*;
import lombok.*;
import org.e2cho.e2cho_shopping_mall.constant.ProductType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private ProductType productType;

    @Column(name = "product_image", nullable = false)
    private String productImage;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(columnDefinition = "TEXT", name = "description", nullable = false)
    private String description;

    @CreatedDate
    private LocalDateTime createdAt;


    public static Product of(
            ProductType productType,
            String productImageName,
            int price,
            String description)
    {
        return Product.builder()
                .productType(productType)
                .productImage(productImageName)
                .price(price)
                .description(description)
                .build();
    }
}
