package com.coffeeSale.coffeeSaleEcoBean.shop.domain;

import com.coffeeSale.coffeeSaleEcoBean.ingredient.domain.Ingredient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShopIngredientInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Ingredient ingredient;

    @Column(nullable = false)
    private Integer inventory;
}
