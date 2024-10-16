package org.e2cho.e2cho_shopping_mall.controller.order;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.e2cho.e2cho_shopping_mall.domain.user.User;
import org.e2cho.e2cho_shopping_mall.dto.order.PurchaseOrderEnroll;
import org.e2cho.e2cho_shopping_mall.service.order.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    // 상품 구매 OR 장바구니 담기(결제전)
    @PostMapping("/add-to-cart")
    public ResponseEntity<PurchaseOrderEnroll.Response> enrollPurchaseOrder(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody PurchaseOrderEnroll.Request request
    ){
        PurchaseOrderEnroll.Dto dto = orderService.enrollPurchaseOrder(user, request);

        return new ResponseEntity<>(PurchaseOrderEnroll.Response.fromDto(dto), HttpStatus.CREATED);
    }
}
