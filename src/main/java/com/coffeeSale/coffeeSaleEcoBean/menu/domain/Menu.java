package com.coffeeSale.coffeeSaleEcoBean.menu.domain;

import com.coffeeSale.coffeeSaleEcoBean.common.BaseEntity;
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

    @ToString.Exclude
    @OneToMany(mappedBy = "shopping_cart_items",fetch = FetchType.LAZY)
    private List<ShoppingCartItems> shoppingCartItems = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "order_details",fetch = FetchType.LAZY)
    private List<OrderDetails> orderDetails = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "available_menu",fetch = FetchType.LAZY)
    private List<AvailableMenu> availableMenus = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "menu_recipe",fetch = FetchType.LAZY)
    private List<MenuRecipe> menuRecipes = new ArrayList<>();

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
