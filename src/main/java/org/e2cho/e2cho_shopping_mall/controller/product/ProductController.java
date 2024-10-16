package org.e2cho.e2cho_shopping_mall.controller.product;

import lombok.RequiredArgsConstructor;
import org.e2cho.e2cho_shopping_mall.dto.product.ProductDetailInfoInquiry;
import org.e2cho.e2cho_shopping_mall.dto.product.ProductSimpleInfoInquiry;
import org.e2cho.e2cho_shopping_mall.service.product.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    // 간략 조회
    @GetMapping("/preview")
    public ResponseEntity<ProductSimpleInfoInquiry.Response> getProductSimpleInfo(
            @RequestParam(name = "type") String productType
    ) {
        ProductSimpleInfoInquiry.Dto dto = productService.getProductSimpleInfo(productType);

        return new ResponseEntity<>(ProductSimpleInfoInquiry.Response.fromDto(dto), HttpStatus.OK);
    }

    // 상세 조회
    @GetMapping("/details")
    public ResponseEntity<ProductDetailInfoInquiry.Response> getProductDetailInfo(
            @RequestParam(name = "type") String productType
    ){
        ProductDetailInfoInquiry.Dto dto = productService.getProductDetailInfo(productType);

        return new ResponseEntity<>(ProductDetailInfoInquiry.Response.fromDto(dto), HttpStatus.OK);
    }
}
