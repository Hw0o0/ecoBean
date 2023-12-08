package com.coffeeSale.coffeeSaleEcoBean.ingredient.domain;

import com.coffeeSale.coffeeSaleEcoBean.suppliers.domain.Supplys;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class IngredientSupplyDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Supplys supply;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Ingredient ingredient;

    @Column(nullable = false)
    private Integer supplyAmount;

    @Column(nullable = false)
    private Integer price;
}
