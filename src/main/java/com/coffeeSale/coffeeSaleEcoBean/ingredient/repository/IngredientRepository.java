package com.coffeeSale.coffeeSaleEcoBean.ingredient.repository;

import com.coffeeSale.coffeeSaleEcoBean.ingredient.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient,Long> {
}
