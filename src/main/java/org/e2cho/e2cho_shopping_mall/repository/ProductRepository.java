package org.e2cho.e2cho_shopping_mall.repository;

import org.e2cho.e2cho_shopping_mall.constant.ProductType;
import org.e2cho.e2cho_shopping_mall.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByProductType(ProductType productType);

}
