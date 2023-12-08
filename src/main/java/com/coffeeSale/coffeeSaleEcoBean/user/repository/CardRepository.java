package com.coffeeSale.coffeeSaleEcoBean.user.repository;

import com.coffeeSale.coffeeSaleEcoBean.user.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
