package com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.repository;

import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.domain.MenuPackage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuPackageRepository extends JpaRepository<MenuPackage,Long> {
    Optional<MenuPackage> findMenuPackageByPeriod(Integer period);
}
