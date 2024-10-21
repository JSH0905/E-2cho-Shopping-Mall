package org.e2cho.e2cho_shopping_mall.repository;

import org.e2cho.e2cho_shopping_mall.domain.order.OrderSheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderSheetRepository extends JpaRepository<OrderSheet, Long> {

    Optional<OrderSheet> findByPaymentStatusAndId(boolean paymentStatus, Long id);

    List<OrderSheet> findByPaymentStatusAndUserId(boolean paymentStatus, Long userId);

}
