package com.coffeeSale.coffeeSaleEcoBean.shop.repository;

import com.coffeeSale.coffeeSaleEcoBean.shop.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ShopRepository extends JpaRepository<Shop,Long> {
}
