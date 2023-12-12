package com.coffeeSale.coffeeSaleEcoBean.reusableCup.domain;

import com.coffeeSale.coffeeSaleEcoBean.menu.domain.Menu;
import com.coffeeSale.coffeeSaleEcoBean.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ReusableCup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menu;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private ReusableCupSupply reusableCupSupply;

    @Column(nullable = false)
    private Integer numberUses;


    private Date purchaseDate;

    private boolean returned;

}
