package com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.domain;

import com.coffeeSale.coffeeSaleEcoBean.common.entity.BaseEntity;
import com.coffeeSale.coffeeSaleEcoBean.reusableCup.domain.ReusableCup;
import com.coffeeSale.coffeeSaleEcoBean.shop.domain.Shop;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SubscriptionOrder extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Subscription subscription;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private ReusableCup reusableCup;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private AvailableMenu availableMenu;

    @Column(nullable = false)
    private boolean temperatureControl;

    @Column(nullable = false)
    private String paymentMethod;
}
