package com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.dto;

import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.domain.MenuPackage;
import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.domain.Subscription;
import com.coffeeSale.coffeeSaleEcoBean.user.domain.Coupon;
import com.coffeeSale.coffeeSaleEcoBean.user.domain.User;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;

@Data
public class RegistrationSubscriptionDto {

    private String recommendedUserId;

    private String paymentMethod;

    public Subscription toEntity(User user, Coupon coupon, MenuPackage menuPackage, RegistrationSubscriptionDto registrationSubscriptionDto) {
        int period = menuPackage.getPeriod(); // 메뉴 패키지의 개월 수
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, period); // 개월 수를 더함

        return Subscription.builder()
                .user(user)
                .coupon(coupon)
                .menuPackage(menuPackage)
                .recommendedUserId(registrationSubscriptionDto.getRecommendedUserId())
                .paymentMethod(registrationSubscriptionDto.getPaymentMethod())
                .DeadlineDate(calendar.getTime())
                .build();
    }
}
