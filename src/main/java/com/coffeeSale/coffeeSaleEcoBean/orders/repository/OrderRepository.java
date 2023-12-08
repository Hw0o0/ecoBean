package com.coffeeSale.coffeeSaleEcoBean.orders.repository;

import com.coffeeSale.coffeeSaleEcoBean.orders.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders,Long> {
}
