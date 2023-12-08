package com.coffeeSale.coffeeSaleEcoBean.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false)
    private Integer totalPrice;

    @Column(nullable = false)
    private Integer totalAmount;
}
