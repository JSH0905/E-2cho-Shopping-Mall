package org.e2cho.e2cho_shopping_mall.service.order.orderSheet;

import lombok.RequiredArgsConstructor;
import org.e2cho.e2cho_shopping_mall.constant.ErrorType;
import org.e2cho.e2cho_shopping_mall.domain.order.OrderSheet;
import org.e2cho.e2cho_shopping_mall.expection.CustomErrorException;
import org.e2cho.e2cho_shopping_mall.repository.OrderSheetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderSheetCommonService {

    private final OrderSheetRepository orderSheetRepository;

    public OrderSheet getOrderSheet(Long orderSheetId) {

        return orderSheetRepository.findById(orderSheetId)
                .orElseThrow(() -> new CustomErrorException(ErrorType.OrderSheetNotFoundError));
    }

    public OrderSheet getNotPaidOrderSheet(Long orderSheetId) {

        return orderSheetRepository.findByPaymentStatusAndId(false, orderSheetId)
                .orElseThrow(() -> new CustomErrorException(ErrorType.OrderSheetNotFoundError));
    }

    public List<OrderSheet> getAllNotPaidOrderSheet(Long userId){

        List<OrderSheet> foundOrderSheets = orderSheetRepository.findByPaymentStatusAndUserId(false, userId);

        if (foundOrderSheets.isEmpty()){
            throw new CustomErrorException(ErrorType.OrderSheetNotFoundError);
        }

        return foundOrderSheets;
    }

    public OrderSheet getPaidOrderSheet(Long orderSheetId) {

        return orderSheetRepository.findByPaymentStatusAndId(true, orderSheetId)
                .orElseThrow(()-> new CustomErrorException(ErrorType.OrderSheetNotFoundError));
    }

    public List<OrderSheet> getAllPaidOrderSheet(Long userId){

        List<OrderSheet> foundOrderSheets = orderSheetRepository.findByPaymentStatusAndUserId(true, userId);
        if (foundOrderSheets.isEmpty()){

            throw new CustomErrorException(ErrorType.OrderSheetNotFoundError);
        }

        return foundOrderSheets;
    }
}

