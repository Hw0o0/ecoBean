package com.coffeeSale.coffeeSaleEcoBean.user.domain;

import com.coffeeSale.coffeeSaleEcoBean.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Coupon extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false)
    private String discountDetails;

    @Column(nullable = false)
    private Integer limitAmount;

    @Column(nullable = false)
    private String expirationPeriod;

    private Date usedDate;

    private boolean state;
}
