package com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.repository;

import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
