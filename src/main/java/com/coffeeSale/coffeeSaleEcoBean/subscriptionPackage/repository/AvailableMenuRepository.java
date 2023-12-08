package com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.repository;

import com.coffeeSale.coffeeSaleEcoBean.menu.domain.Menu;
import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.domain.AvailableMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvailableMenuRepository extends JpaRepository<AvailableMenu,Long> {
    Optional<AvailableMenu> findByMenu(Menu menu);
}
