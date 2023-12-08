package com.coffeeSale.coffeeSaleEcoBean.reusableCup.domain;

import com.coffeeSale.coffeeSaleEcoBean.shop.domain.Shop;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReusableCupReturnedDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private ReusableCup reusableCup;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    private Integer cumulativeUseCount;

    private Date returnedDate;
}
