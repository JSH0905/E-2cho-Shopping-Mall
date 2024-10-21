package org.e2cho.e2cho_shopping_mall.service.order.cart;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.e2cho.e2cho_shopping_mall.domain.order.OrderSheet;
import org.e2cho.e2cho_shopping_mall.domain.user.User;
import org.e2cho.e2cho_shopping_mall.dto.order.cart.CartDelete;
import org.e2cho.e2cho_shopping_mall.dto.order.cart.CartDeleteAll;
import org.e2cho.e2cho_shopping_mall.dto.order.cart.CartInquiry;
import org.e2cho.e2cho_shopping_mall.repository.OrderSheetRepository;
import org.e2cho.e2cho_shopping_mall.service.order.orderSheet.OrderSheetCommonService;
import org.e2cho.e2cho_shopping_mall.service.user.UserCommonService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final UserCommonService userCommonService;
    private final OrderSheetCommonService orderSheetCommonService;

    private final OrderSheetRepository orderSheetRepository;

    @Value("${productImage.path.api}")
    private String productImageUrlPrefix;

    public CartInquiry.Dto getAllOrderSheetInCart(User user){

        userCommonService.validateUser(user);

        List<OrderSheet> foundOrderSheets = orderSheetCommonService.getAllNotPaidOrderSheet(user.getId());

        return CartInquiry.Dto.of(productImageUrlPrefix, foundOrderSheets);

    }

    @Transactional
    public CartDelete.Dto deleteOrderSheetInCart(User user, Long orderSheetId){

        userCommonService.validateUser(user);

        OrderSheet foundOrderSheet = orderSheetCommonService.getNotPaidOrderSheet(orderSheetId);

        Long deletedOrderSheetId = foundOrderSheet.getId();

        orderSheetRepository.delete(foundOrderSheet);

        return CartDelete.Dto.fromEntity(deletedOrderSheetId);

    }

    @Transactional
    public void deleteAllOrderSheetInCart(User user){

        userCommonService.validateUser(user);

        List<OrderSheet> foundOrderSheets = orderSheetCommonService.getAllNotPaidOrderSheet(user.getId());

        orderSheetRepository.deleteAll(foundOrderSheets);

    }

}
