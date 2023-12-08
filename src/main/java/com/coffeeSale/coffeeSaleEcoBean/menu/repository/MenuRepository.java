package com.coffeeSale.coffeeSaleEcoBean.menu.repository;

import com.coffeeSale.coffeeSaleEcoBean.menu.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu,Long> {
}
