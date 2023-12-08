package com.coffeeSale.coffeeSaleEcoBean.menu.domain;

import com.coffeeSale.coffeeSaleEcoBean.common.BaseEntity;
import com.coffeeSale.coffeeSaleEcoBean.ingredient.domain.Ingredient;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MenuRecipe extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menu;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Ingredient ingredient;

    @Column(nullable = false)
    private Integer usedAmount;
}
