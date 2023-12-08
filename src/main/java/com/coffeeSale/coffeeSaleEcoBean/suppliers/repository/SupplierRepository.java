package com.coffeeSale.coffeeSaleEcoBean.suppliers.repository;

import com.coffeeSale.coffeeSaleEcoBean.suppliers.domain.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Suppliers,Long> {
}
