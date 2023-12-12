package com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.domain;

import com.coffeeSale.coffeeSaleEcoBean.common.entity.BaseEntity;
import com.coffeeSale.coffeeSaleEcoBean.user.domain.Coupon;
import com.coffeeSale.coffeeSaleEcoBean.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Subscription extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    private Coupon coupon;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private MenuPackage menuPackage;

    @Column(nullable = false)
    private String recommendedUserId;

    @Column(nullable = false)
    private String paymentMethod;

    @Column(nullable = false)
    private Integer deductedAmount; //차감 금액

    @Column(nullable = false)
    private Integer totalPrice;

    @Column(nullable = false)
    private Integer integratedPrice; // 통합 가격

    @Column(nullable = false)
    private Date DeadlineDate;
}
