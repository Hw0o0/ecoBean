package com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.dto.response;

import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.domain.MenuPackage;
import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.domain.Subscription;
import com.coffeeSale.coffeeSaleEcoBean.user.domain.Coupon;
import com.coffeeSale.coffeeSaleEcoBean.user.domain.User;
import lombok.Builder;
import java.util.Date;

@Builder
public class SubscriptionRegistrationResDto {
    private User user;

    private Coupon coupon;

    private MenuPackage menuPackage;

    private String recommendedUserId;

    private String paymentMethod;

    private Integer deductedAmount; //차감 금액

    private Integer totalPrice;

    private Integer integratedPrice; // 통합 가격

    private Date deadlineDate;

    public static SubscriptionRegistrationResDto of(Subscription subscription){
        return SubscriptionRegistrationResDto.builder()
                .user(subscription.getUser())
                .coupon(subscription.getCoupon())
                .menuPackage(subscription.getMenuPackage())
                .recommendedUserId(subscription.getRecommendedUserId())
                .paymentMethod(subscription.getPaymentMethod())
                .deductedAmount(subscription.getDeductedAmount())
                .totalPrice(subscription.getTotalPrice())
                .integratedPrice(subscription.getIntegratedPrice())
                .deadlineDate(subscription.getDeadlineDate())
                .build();
    }
}
