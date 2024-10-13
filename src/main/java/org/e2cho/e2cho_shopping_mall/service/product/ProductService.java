package org.e2cho.e2cho_shopping_mall.service.product;

import lombok.RequiredArgsConstructor;
import org.e2cho.e2cho_shopping_mall.constant.ErrorType;
import org.e2cho.e2cho_shopping_mall.constant.ProductType;
import org.e2cho.e2cho_shopping_mall.domain.product.Product;
import org.e2cho.e2cho_shopping_mall.dto.product.ProductDetailInfoInquiry;
import org.e2cho.e2cho_shopping_mall.dto.product.ProductSimpleInfoInquiry;
import org.e2cho.e2cho_shopping_mall.expection.CustomErrorException;
import org.e2cho.e2cho_shopping_mall.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductSimpleInfoInquiry.Dto getProductSimpleInfo(String productType){

        Product foundProduct;

        foundProduct = findProduct(productType);

        return ProductSimpleInfoInquiry.Dto.fromEntity(foundProduct);

    }


    public ProductDetailInfoInquiry.Dto getProductDetailInfo(String productType){

        Product foundProduct;

        foundProduct = findProduct(productType);

        return ProductDetailInfoInquiry.Dto.fromEntity(foundProduct);

    }

    private Product findProduct(String productType) {
        Product foundProduct;
        if (productType.equals("basic")){
             foundProduct = productRepository.findByProductType(ProductType.BASIC)
                    .orElseThrow(() -> new CustomErrorException(ErrorType.ProductNotFoundError));

        }else if (productType.equals("custom")){

            foundProduct = productRepository.findByProductType(ProductType.CUSTOM)
                    .orElseThrow(() -> new CustomErrorException(ErrorType.ProductNotFoundError));

        }else {
            throw new CustomErrorException(ErrorType.ProductNotFoundError);
        }
        return foundProduct;
    }

}
