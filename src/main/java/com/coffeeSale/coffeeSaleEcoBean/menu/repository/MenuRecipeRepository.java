package com.coffeeSale.coffeeSaleEcoBean.menu.repository;

import com.coffeeSale.coffeeSaleEcoBean.menu.domain.MenuRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRecipeRepository extends JpaRepository<MenuRecipe,Long> {
}
