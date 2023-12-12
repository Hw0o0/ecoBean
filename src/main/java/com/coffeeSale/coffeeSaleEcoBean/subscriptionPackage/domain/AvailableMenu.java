package com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.domain;

import com.coffeeSale.coffeeSaleEcoBean.menu.domain.Menu;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AvailableMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private MenuPackage menuPackage;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menu;

    @Column(nullable = false)
    private Integer deductedAmount;

    public static AvailableMenu createAvailMenu(MenuPackage menuPackage, Menu menu,Integer deductedAmount){
        return AvailableMenu.builder()
                .menuPackage(menuPackage)
                .menu(menu)
                .deductedAmount(deductedAmount)
                .build();
    }

}
