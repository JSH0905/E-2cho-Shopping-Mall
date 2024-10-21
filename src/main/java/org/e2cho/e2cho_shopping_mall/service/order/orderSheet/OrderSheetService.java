package org.e2cho.e2cho_shopping_mall.service.order.orderSheet;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.e2cho.e2cho_shopping_mall.constant.ErrorType;
import org.e2cho.e2cho_shopping_mall.domain.order.OrderSheet;
import org.e2cho.e2cho_shopping_mall.domain.product.Product;
import org.e2cho.e2cho_shopping_mall.domain.user.User;
import org.e2cho.e2cho_shopping_mall.dto.order.orderSheet.OrderSheetEnroll;
import org.e2cho.e2cho_shopping_mall.dto.order.orderSheet.OrderSheetQuantityUpdate;
import org.e2cho.e2cho_shopping_mall.dto.order.orderSheet.OrderSheetRecipientUpdate;
import org.e2cho.e2cho_shopping_mall.expection.CustomErrorException;
import org.e2cho.e2cho_shopping_mall.repository.OrderSheetRepository;
import org.e2cho.e2cho_shopping_mall.service.product.ProductService;
import org.e2cho.e2cho_shopping_mall.service.user.UserCommonService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderSheetService {

    private final UserCommonService userCommonService;
    private final ProductService productService;
    private final OrderSheetCommonService orderSheetCommonService;

    private final OrderSheetRepository orderSheetRepository;

    @Transactional
    public OrderSheetEnroll.Dto enrollOrderSheet(User user, OrderSheetEnroll.Request request){

        userCommonService.validateUser(user);

        OrderSheet newOrderSheet;

        if (request.getProductType().equals("기본")){

            Product foundBasicProduct = productService.findProduct("basic");

            newOrderSheet = orderSheetRepository.save(
                    OrderSheet.of(foundBasicProduct, request, user)
            );

        }else if (request.getProductType().equals("커스텀")){

            Product foundCustomProduct = productService.findProduct("custom");

            newOrderSheet = orderSheetRepository.save(
                    OrderSheet.of(foundCustomProduct, request, user)
            );

        } else {
            throw new CustomErrorException(ErrorType.NotValidRequestError);
        }

        return OrderSheetEnroll.Dto.fromEntity(newOrderSheet);
    }

    @Transactional
    public OrderSheetQuantityUpdate.Dto updateQuantityOfOrderSheet(User user, Long orderSheetId, OrderSheetQuantityUpdate.Request request){

        userCommonService.validateUser(user);

        OrderSheet foundOrderSheet = orderSheetCommonService.getOrderSheet(orderSheetId);

        foundOrderSheet.updateQuantity(request.getQuantity());

        return OrderSheetQuantityUpdate.Dto.fromEntity(foundOrderSheet);

    }

    @Transactional
    public OrderSheetRecipientUpdate.Dto updateRecipientOfOrderSheet(User user, Long orderSheetId, OrderSheetRecipientUpdate.Request request){
        userCommonService.validateUser(user);

        OrderSheet foundOrderSheet = orderSheetCommonService.getOrderSheet(orderSheetId);

        foundOrderSheet.updateRecipient(request);

        return OrderSheetRecipientUpdate.Dto.fromEntity(foundOrderSheet);

    }

}
