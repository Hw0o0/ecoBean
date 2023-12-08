package com.coffeeSale.coffeeSaleEcoBean.user.domain;

import com.coffeeSale.coffeeSaleEcoBean.common.BaseEntity;
import com.coffeeSale.coffeeSaleEcoBean.user.common.Level;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EnvironmentTree extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private Level level;
}
