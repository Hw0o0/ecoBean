package com.coffeeSale.coffeeSaleEcoBean.user.repository;

import com.coffeeSale.coffeeSaleEcoBean.user.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {
}
