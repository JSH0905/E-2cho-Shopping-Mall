package org.e2cho.e2cho_shopping_mall.controller.order;

import lombok.RequiredArgsConstructor;
import org.e2cho.e2cho_shopping_mall.domain.user.User;
import org.e2cho.e2cho_shopping_mall.dto.order.orderHistory.OrderHistoryDelete;
import org.e2cho.e2cho_shopping_mall.dto.order.orderHistory.OrderHistoryDeleteAll;
import org.e2cho.e2cho_shopping_mall.dto.order.orderHistory.OrderHistoryDetailInquiry;
import org.e2cho.e2cho_shopping_mall.dto.order.orderHistory.OrderHistoryInquiry;
import org.e2cho.e2cho_shopping_mall.service.order.orderHistory.OrderHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orderHistory")
public class OrderHistoryController {

    private final OrderHistoryService orderHistoryService;

    // 주문목록조회(결제 된 것들)
    @GetMapping()
    public ResponseEntity<OrderHistoryInquiry.Response> getAllOrderHistory(
            @AuthenticationPrincipal User user
    ){

        OrderHistoryInquiry.Dto dto = orderHistoryService.getAllOrderHistory(user);

        return new ResponseEntity<>(OrderHistoryInquiry.Response.fromDto(dto), HttpStatus.OK);

    }

    @GetMapping("/details")
    public ResponseEntity<OrderHistoryDetailInquiry.Response> getOrderHistoryDetail(
            @AuthenticationPrincipal User user,
            @RequestParam(name = "orderId") Long orderSheetId
    ) {

        OrderHistoryDetailInquiry.Dto dto = orderHistoryService.getDetailOrderHistory(user, orderSheetId);

        return new ResponseEntity<>(OrderHistoryDetailInquiry.Response.fromDto(dto), HttpStatus.OK);
    }

    // 단일 주문내역 삭제
    @DeleteMapping()
    public ResponseEntity<OrderHistoryDelete.Response> deleteOrderHistory(
            @AuthenticationPrincipal User user,
            @RequestParam(name = "orderId") Long orderSheetId
    ){

        OrderHistoryDelete.Dto dto = orderHistoryService.deleteSelectedOrderHistory(user, orderSheetId);

        return new ResponseEntity<>(OrderHistoryDelete.Response.fromDto(dto),HttpStatus.CREATED);
    }

    // 주문내역 전체 삭제
    @DeleteMapping("/all")
    public ResponseEntity<OrderHistoryDeleteAll.Response> deleteAllOrderHistory(
            @AuthenticationPrincipal User user
    ){

        orderHistoryService.deleteAllOrderHistory(user);

        return new ResponseEntity<>(OrderHistoryDeleteAll.Response.createNewResponse(), HttpStatus.CREATED);
    }
}
