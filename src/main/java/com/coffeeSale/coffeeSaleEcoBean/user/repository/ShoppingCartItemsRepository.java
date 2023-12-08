package com.coffeeSale.coffeeSaleEcoBean.user.repository;

import com.coffeeSale.coffeeSaleEcoBean.menu.domain.Menu;
import com.coffeeSale.coffeeSaleEcoBean.user.domain.ShoppingCartItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCartItemsRepository extends JpaRepository<ShoppingCartItems,Long> {
    Optional<ShoppingCartItems> findByMenu(Menu menu);
}
