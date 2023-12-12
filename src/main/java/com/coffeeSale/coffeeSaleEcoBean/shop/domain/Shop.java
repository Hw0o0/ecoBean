package com.coffeeSale.coffeeSaleEcoBean.shop.domain;

import com.coffeeSale.coffeeSaleEcoBean.common.entity.BaseEntity;
import com.coffeeSale.coffeeSaleEcoBean.orders.domain.Orders;
import com.coffeeSale.coffeeSaleEcoBean.reusableCup.domain.ReusableCupReturnedDetails;
import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.domain.SubscriptionOrder;
import com.coffeeSale.coffeeSaleEcoBean.suppliers.domain.Supplys;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Shop extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String shopName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private Integer reusableCupInventory;
}
