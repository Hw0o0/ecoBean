package com.coffeeSale.coffeeSaleEcoBean.menu.domain;

import com.coffeeSale.coffeeSaleEcoBean.common.entity.BaseEntity;
import com.coffeeSale.coffeeSaleEcoBean.orders.domain.OrderDetails;
import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.domain.AvailableMenu;
import com.coffeeSale.coffeeSaleEcoBean.user.domain.ShoppingCartItems;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Menu extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String menuName;

    @Column(nullable = false)
    private String menuDescription;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private boolean specialMenu;
}
