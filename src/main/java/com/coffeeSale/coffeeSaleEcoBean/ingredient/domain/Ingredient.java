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

    @ToString.Exclude
    @OneToMany(mappedBy = "ingredient_supply_details",fetch = FetchType.LAZY)
    private List<IngredientSupplyDetails> ingredientSupplyDetails = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "shop_ingredient_inventory",fetch = FetchType.LAZY)
    private List<ShopIngredientInventory> shopIngredientInventories = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "menu_recipe",fetch = FetchType.LAZY)
    private List<MenuRecipe> menuRecipes = new ArrayList<>();

    @Column(nullable = false)
    private String ingredientName;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private Integer inventory;
}
