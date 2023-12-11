package com.coffeeSale.coffeeSaleEcoBean.user.domain;

import com.coffeeSale.coffeeSaleEcoBean.common.entity.BaseEntity;
import com.coffeeSale.coffeeSaleEcoBean.orders.domain.Orders;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PointDetails extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    private Orders orders;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Integer point;

}
