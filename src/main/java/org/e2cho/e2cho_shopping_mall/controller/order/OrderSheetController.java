package org.e2cho.e2cho_shopping_mall.controller.order;

import  jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.e2cho.e2cho_shopping_mall.domain.user.User;
import org.e2cho.e2cho_shopping_mall.dto.order.orderSheet.OrderSheetEnroll;
import org.e2cho.e2cho_shopping_mall.dto.order.orderSheet.OrderSheetQuantityUpdate;
import org.e2cho.e2cho_shopping_mall.dto.order.orderSheet.OrderSheetRecipientUpdate;
import org.e2cho.e2cho_shopping_mall.service.order.orderSheet.OrderSheetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orderSheet")
public class OrderSheetController {

    private final OrderSheetService orderSheetService;

    // 상품 구매 OR 장바구니 담기(결제전)
    @PostMapping()
    public ResponseEntity<OrderSheetEnroll.Response> enrollOrderSheet(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody OrderSheetEnroll.Request request
    ){
        OrderSheetEnroll.Dto dto = orderSheetService.enrollOrderSheet(user, request);

        return new ResponseEntity<>(OrderSheetEnroll.Response.fromDto(dto), HttpStatus.CREATED);
    }

    // 장바구니에서 수량 변경할 때 사용.
    @PatchMapping("/quantity")
    public ResponseEntity<OrderSheetQuantityUpdate.Response> updateQuantityOfOrderSheet(
            @AuthenticationPrincipal User user,
            @RequestParam(name = "orderId") Long orderSheetId,
            @Valid @RequestBody OrderSheetQuantityUpdate.Request request
    ) {

        OrderSheetQuantityUpdate.Dto dto = orderSheetService.updateQuantityOfOrderSheet(user, orderSheetId, request);

        return new ResponseEntity<>(OrderSheetQuantityUpdate.Response.fromDto(dto), HttpStatus.CREATED);
    }

    @PatchMapping("/recipient")
    public ResponseEntity<OrderSheetRecipientUpdate.Response> updateRecipientOfOrderSheet(
            @AuthenticationPrincipal User user,
            @RequestParam(name = "orderId") Long orderSheetId,
            @Valid @RequestBody OrderSheetRecipientUpdate.Request request
    ) {

        OrderSheetRecipientUpdate.Dto dto = orderSheetService.updateRecipientOfOrderSheet(user, orderSheetId, request);

        return new ResponseEntity<>(OrderSheetRecipientUpdate.Response.fromDto(dto), HttpStatus.CREATED);

    }

}
