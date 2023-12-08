package com.coffeeSale.coffeeSaleEcoBean.reusableCup.domain;

import com.coffeeSale.coffeeSaleEcoBean.user.domain.User;
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
public class ReusableCupSupply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private ReusableCupSupply reusableCupSupply;

    @ToString.Exclude
    @OneToMany(mappedBy = "reusable_cup",fetch = FetchType.LAZY)
    private List<ReusableCup> reusableCupList = new ArrayList<>();

    @Column(nullable = false)
    private Integer numberUses;


    private Date purchaseDate;

    private boolean returned;
}
