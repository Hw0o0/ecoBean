package com.coffeeSale.coffeeSaleEcoBean.reusableCup.dto;

import com.coffeeSale.coffeeSaleEcoBean.menu.domain.Menu;
import com.coffeeSale.coffeeSaleEcoBean.reusableCup.domain.ReusableCupSupply;
import com.coffeeSale.coffeeSaleEcoBean.user.domain.User;

import java.util.Date;

public class ReusableReturnReqDto {
    private User user;

    private Menu menu;

    private ReusableCupSupply reusableCupSupply;

    private Integer numberUses;

    private Date purchaseDate;

    private boolean returned;
}
