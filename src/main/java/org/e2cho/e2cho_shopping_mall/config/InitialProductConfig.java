package org.e2cho.e2cho_shopping_mall.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.e2cho.e2cho_shopping_mall.constant.ProductType;
import org.e2cho.e2cho_shopping_mall.domain.product.Product;
import org.e2cho.e2cho_shopping_mall.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitialProductConfig implements CommandLineRunner {

    private final ProductRepository productRepository;


    @Override
    public void run(String... args) throws Exception {
        initializeDefaultProduct();
        log.info("초기 상품 데이터 구성완료");
    }

    public void initializeDefaultProduct(){

        Optional<Product> basicProduct = productRepository.findByProductType(ProductType.BASIC);
        Optional<Product> customProduct = productRepository.findByProductType(ProductType.CUSTOM);

        if (basicProduct.isPresent() || customProduct.isPresent()){
            return;
        }

        for (ProductType productType : ProductType.values()) {

            if (productType.toString().equals("BASIC")){

                productRepository.save(
                        Product.of(
                                productType,
                                "echo.jpg",
                                39800,
                                "E^2cho의 기본적인 상품입니다."

                        )
                );
            }
            else {
                productRepository.save(
                        Product.of(
                                productType,
                                "echoCustom.jpg",
                                0,
                                "E^2cho의 커스텀 상품입니다."
                        )
                );
            }

        }

    }
}
