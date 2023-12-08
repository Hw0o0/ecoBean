package com.coffeeSale.coffeeSaleEcoBean.user.repository;

import com.coffeeSale.coffeeSaleEcoBean.user.domain.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
}
