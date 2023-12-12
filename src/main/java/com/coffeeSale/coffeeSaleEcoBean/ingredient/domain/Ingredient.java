package com.coffeeSale.coffeeSaleEcoBean.ingredient.domain;

import com.coffeeSale.coffeeSaleEcoBean.menu.domain.MenuRecipe;
import com.coffeeSale.coffeeSaleEcoBean.shop.domain.ShopIngredientInventory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ingredientName;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private Integer inventory;
}
