package com.coffeeSale.coffeeSaleEcoBean.orders.domain;

import com.coffeeSale.coffeeSaleEcoBean.common.BaseEntity;
import com.coffeeSale.coffeeSaleEcoBean.orders.dto.OrdersDto;
import com.coffeeSale.coffeeSaleEcoBean.user.domain.Coupon;
import com.coffeeSale.coffeeSaleEcoBean.reusableCup.domain.ReusableCup;
import com.coffeeSale.coffeeSaleEcoBean.shop.domain.Shop;
import com.coffeeSale.coffeeSaleEcoBean.user.domain.PointDetails;
import com.coffeeSale.coffeeSaleEcoBean.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Orders extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private ReusableCup reusableCup;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    private Coupon coupon;

    @ToString.Exclude
    @OneToOne(mappedBy = "point_details",fetch = FetchType.LAZY)
    private PointDetails pointDetails;

    @ToString.Exclude
    @OneToMany(mappedBy = "order_details",fetch = FetchType.LAZY)
    private List<OrderDetails> orderDetails = new ArrayList<>();

    @Column(nullable = false)
    private String payMethod;

    @Column(nullable = false)
    private Integer usedPoint;

    @Column(nullable = false)
    private Integer deductedAmount;  //차감 금액

    @Column(nullable = false)
    private Integer totalPrice;

    @Column(nullable = false)
    private Integer integratedPrice; // 통합 가격

    @Column(nullable = false)
    private boolean purchaseReusable;

    public static Orders createOrder(User user, Shop shop, ReusableCup reusableCup, Coupon coupon,OrdersDto ordersDto) {
        return  Orders.builder()
                .user(user)
                .shop(shop)
                .reusableCup(reusableCup)
                .coupon(coupon)
                .payMethod(ordersDto.getPayMethod())
                .usedPoint(ordersDto.getUsedPoint())
                .purchaseReusable(ordersDto.isPurchaseReusable())
                .build();
    }
}
