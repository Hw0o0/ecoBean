package com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.domain;

import com.coffeeSale.coffeeSaleEcoBean.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MenuPackage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @OneToMany(mappedBy = "available_menu",fetch = FetchType.LAZY)
    private List<AvailableMenu> availableMenus = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "subscription",fetch = FetchType.LAZY)
    private List<Subscription> subscriptions = new ArrayList<>();

    @Column(nullable = false)
    private String packageName;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer period;

    @Column(nullable = false)
    private Integer discountPrice;
}
