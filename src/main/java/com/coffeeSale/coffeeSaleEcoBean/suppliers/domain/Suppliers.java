package com.coffeeSale.coffeeSaleEcoBean.suppliers.domain;

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
public class Suppliers extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @OneToMany(mappedBy = "supplys",fetch = FetchType.LAZY)
    private List<Supplys> supplys = new ArrayList<>();

    @Column(nullable = false)
    private String supplierName;

    @Column(nullable = false)
    private String address;
}
