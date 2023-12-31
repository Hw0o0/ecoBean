package com.coffeeSale.coffeeSaleEcoBean.suppliers.domain;

import com.coffeeSale.coffeeSaleEcoBean.ingredient.domain.IngredientSupplyDetails;
import com.coffeeSale.coffeeSaleEcoBean.reusableCup.domain.ReusableCupSupply;
import com.coffeeSale.coffeeSaleEcoBean.shop.domain.Shop;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Supplys {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Suppliers supplier;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    private Integer totalPrice;

    private Date dueDate;

    private boolean chooseTwoWay;
}
