package com.coffeeSale.coffeeSaleEcoBean.user.domain;

import com.coffeeSale.coffeeSaleEcoBean.common.entity.BaseEntity;
import com.coffeeSale.coffeeSaleEcoBean.user.common.CardCompany;
import com.coffeeSale.coffeeSaleEcoBean.user.common.CardType;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Card extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private String expirationPeriod;

    @Column(nullable = false)
    private CardCompany cardCompany;

    @Column(nullable = false)
    private String cvc;

    @Column(nullable = false)
    private CardType cardType;
}
