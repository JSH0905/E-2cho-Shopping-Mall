package org.e2cho.e2cho_shopping_mall.service.order.orderHistory;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.e2cho.e2cho_shopping_mall.domain.order.OrderSheet;
import org.e2cho.e2cho_shopping_mall.domain.user.User;
import org.e2cho.e2cho_shopping_mall.dto.order.orderHistory.OrderHistoryDelete;
import org.e2cho.e2cho_shopping_mall.dto.order.orderHistory.OrderHistoryDetailInquiry;
import org.e2cho.e2cho_shopping_mall.dto.order.orderHistory.OrderHistoryInquiry;
import org.e2cho.e2cho_shopping_mall.repository.OrderSheetRepository;
import org.e2cho.e2cho_shopping_mall.service.order.orderSheet.OrderSheetCommonService;
import org.e2cho.e2cho_shopping_mall.service.user.UserCommonService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderHistoryService {


    private final UserCommonService userCommonService;
    private final OrderSheetCommonService orderSheetCommonService;

    private final OrderSheetRepository orderSheetRepository;

    @Value("${productImage.path.api}")
    private String productImageUrlPrefix;

    public OrderHistoryDetailInquiry.Dto getDetailOrderHistory(User user, Long orderSheetId){

        userCommonService.validateUser(user);

        OrderSheet foundOrdersheet = orderSheetCommonService.getPaidOrderSheet(orderSheetId);

        return OrderHistoryDetailInquiry.Dto.of(productImageUrlPrefix, foundOrdersheet);
    }


    public OrderHistoryInquiry.Dto getAllOrderHistory(User user){

        userCommonService.validateUser(user);

        List<OrderSheet> foundOrderSheets = orderSheetCommonService.getAllPaidOrderSheet(user.getId());

        return OrderHistoryInquiry.Dto.of(productImageUrlPrefix, foundOrderSheets);
    }

    @Transactional
    public OrderHistoryDelete.Dto deleteSelectedOrderHistory(User user, Long orderSheetId){

        userCommonService.validateUser(user);

        OrderSheet foundOrderHistory = orderSheetCommonService.getPaidOrderSheet(orderSheetId);

        Long deletedOrderSheetId = foundOrderHistory.getId();

        orderSheetRepository.delete(foundOrderHistory);

        return OrderHistoryDelete.Dto.fromEntity(deletedOrderSheetId);
    }

    @Transactional
    public void deleteAllOrderHistory(User user){

        userCommonService.validateUser(user);

        List<OrderSheet> foundAllOrderHistory = orderSheetCommonService.getAllPaidOrderSheet(user.getId());

        orderSheetRepository.deleteAll(foundAllOrderHistory);
    }


}
