package com.coffeeSale.coffeeSaleEcoBean.user.repository;

import com.coffeeSale.coffeeSaleEcoBean.user.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
