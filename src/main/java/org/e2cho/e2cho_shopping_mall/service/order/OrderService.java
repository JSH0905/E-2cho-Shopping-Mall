package org.e2cho.e2cho_shopping_mall.service.order;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.e2cho.e2cho_shopping_mall.constant.ErrorType;
import org.e2cho.e2cho_shopping_mall.constant.ProductType;
import org.e2cho.e2cho_shopping_mall.domain.order.PurchaseOrder;
import org.e2cho.e2cho_shopping_mall.domain.product.Product;
import org.e2cho.e2cho_shopping_mall.domain.user.User;
import org.e2cho.e2cho_shopping_mall.dto.order.PurchaseOrderEnroll;
import org.e2cho.e2cho_shopping_mall.expection.CustomErrorException;
import org.e2cho.e2cho_shopping_mall.repository.PurchaseOrderRepository;
import org.e2cho.e2cho_shopping_mall.service.product.ProductService;
import org.e2cho.e2cho_shopping_mall.service.user.UserCommonService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final UserCommonService userCommonService;
    private final ProductService productService;

    private final PurchaseOrderRepository purchaseOrderRepository;

    @Transactional
    public PurchaseOrderEnroll.Dto enrollPurchaseOrder(User user, PurchaseOrderEnroll.Request request){

        userCommonService.validateUser(user);

        PurchaseOrder newPurchaseOrder;

        if (request.getProductType().equals("기본")){

            Product foundBasicProduct = productService.findProduct("basic");

            newPurchaseOrder = purchaseOrderRepository.save(
                    PurchaseOrder.of(foundBasicProduct, request, user)
            );

        }else if (request.getProductType().equals("커스텀")){

            Product foundCustomProduct = productService.findProduct("custom");

            newPurchaseOrder =purchaseOrderRepository.save(
                    PurchaseOrder.of(foundCustomProduct, request, user)
            );

        } else {
            throw new CustomErrorException(ErrorType.NotValidRequestError);
        }

        return PurchaseOrderEnroll.Dto.fromEntity(newPurchaseOrder);
    }
}
