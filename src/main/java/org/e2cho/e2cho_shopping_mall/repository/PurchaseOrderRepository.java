package org.e2cho.e2cho_shopping_mall.repository;

import org.e2cho.e2cho_shopping_mall.domain.order.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

}
