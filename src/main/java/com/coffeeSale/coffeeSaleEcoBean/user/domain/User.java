package com.coffeeSale.coffeeSaleEcoBean.user.domain;

import com.coffeeSale.coffeeSaleEcoBean.orders.domain.Orders;
import com.coffeeSale.coffeeSaleEcoBean.reusableCup.domain.ReusableCup;
import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.domain.Subscription;
import com.coffeeSale.coffeeSaleEcoBean.user.common.Rating;
import com.coffeeSale.coffeeSaleEcoBean.user.common.dto.UserJoinDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Integer point;

    @Column(nullable = false)
    private Integer recommendations;

    @Column(nullable = false)
    private Integer reusableUsed;

    @Column(nullable = false)
    private Integer reusableReturned;

    @Column(nullable = false)
    private Rating rating;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Authority> authorities = new HashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Coupon> coupon = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Card> card = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<PointDetails> pointDetails = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<ReusableCup> reusableCups = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Orders> orders = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Subscription> subscriptions = new ArrayList<>();

    @ToString.Exclude
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private ShoppingCart shoppingCart;

    @ToString.Exclude
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private EnvironmentTree environmentTree;

    private boolean admin;
    public static User createdUser(UserJoinDto userJoinDto){
        return User.builder()
                .userId(userJoinDto.getUserId())
                .password(userJoinDto.getPassword())
                .phoneNumber(userJoinDto.getPhoneNumber())
                .email(userJoinDto.getEmail())
                .address(userJoinDto.getAddress())
                .point(0)
                .recommendations(0)
                .reusableUsed(0)
                .reusableReturned(0)
                .rating(Rating.BRONZE)
                .build();
    }
}
