package com.coffeeSale.coffeeSaleEcoBean.orders.domain;

import com.coffeeSale.coffeeSaleEcoBean.menu.domain.Menu;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Orders orders;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menu;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private boolean temperatureControl;

    @Column(nullable = false)
    private boolean usedReusable;
}
