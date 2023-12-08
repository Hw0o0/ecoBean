package com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.repository;

import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.domain.SubscriptionOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionOrderRepository extends JpaRepository<SubscriptionOrder,Long> {
}
