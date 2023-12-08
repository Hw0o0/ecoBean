package com.coffeeSale.coffeeSaleEcoBean.user.domain;

import com.coffeeSale.coffeeSaleEcoBean.menu.domain.Menu;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ShoppingCartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private ShoppingCart shoppingCart;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menu;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private boolean temperatureControl;

    @Column(nullable = false)
    private boolean usedReusable;
}
