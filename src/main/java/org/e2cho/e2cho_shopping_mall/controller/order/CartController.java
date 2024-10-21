package org.e2cho.e2cho_shopping_mall.controller.order;

import lombok.RequiredArgsConstructor;
import org.e2cho.e2cho_shopping_mall.domain.user.User;
import org.e2cho.e2cho_shopping_mall.dto.order.cart.CartDelete;
import org.e2cho.e2cho_shopping_mall.dto.order.cart.CartDeleteAll;
import org.e2cho.e2cho_shopping_mall.dto.order.cart.CartInquiry;
import org.e2cho.e2cho_shopping_mall.service.order.cart.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    // 장바구니 조회(결제 안된 것들)
    @GetMapping()
    public ResponseEntity<CartInquiry.Response> getAllOrderSheetInCart(
            @AuthenticationPrincipal User user
    ) {

        CartInquiry.Dto dto = cartService.getAllOrderSheetInCart(user);

        return new ResponseEntity<>(CartInquiry.Response.fromDto(dto), HttpStatus.OK);

    }

    // 하나의 주문 삭제
    @DeleteMapping()
    public ResponseEntity<CartDelete.Response> deleteOrderSheetInCart(
            @AuthenticationPrincipal User user,
            @RequestParam(name = "orderId") Long orderSheetId
    ){

        CartDelete.Dto dto = cartService.deleteOrderSheetInCart(user, orderSheetId);

        return new ResponseEntity<>(CartDelete.Response.fromDto(dto), HttpStatus.CREATED);
    }

    // 장바구니 전체 삭제
    @DeleteMapping("/all")
    public ResponseEntity<CartDeleteAll.Response> deleteAllOrderSheetInCart(
            @AuthenticationPrincipal User user
    ){

        cartService.deleteAllOrderSheetInCart(user);

        return new ResponseEntity<>(CartDeleteAll.Response.createNewResponse(),HttpStatus.CREATED);

    }

}
