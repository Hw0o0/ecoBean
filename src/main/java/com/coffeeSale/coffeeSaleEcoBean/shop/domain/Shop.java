package com.coffeeSale.coffeeSaleEcoBean.shop.domain;

import com.coffeeSale.coffeeSaleEcoBean.common.BaseEntity;
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

    @ToString.Exclude
    @OneToMany(mappedBy = "supplys",fetch = FetchType.LAZY)
    private List<Supplys> supplys = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "shop_ingredient_inventory",fetch = FetchType.LAZY)
    private List<ShopIngredientInventory> shopIngredientInventories = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "orders",fetch = FetchType.LAZY)
    private List<Orders> orders = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "subscription_order",fetch = FetchType.LAZY)
    private List<SubscriptionOrder> subscriptionOrders = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "reusable_cup_returned_details",fetch = FetchType.LAZY)
    private List<ReusableCupReturnedDetails> reusableCupReturnedDetails = new ArrayList<>();

    @Column(nullable = false)
    private String shopName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private Integer reusableCupInventory;
}
