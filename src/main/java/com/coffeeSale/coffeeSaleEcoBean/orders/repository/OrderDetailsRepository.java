package com.coffeeSale.coffeeSaleEcoBean.orders.repository;

import com.coffeeSale.coffeeSaleEcoBean.orders.domain.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
}
