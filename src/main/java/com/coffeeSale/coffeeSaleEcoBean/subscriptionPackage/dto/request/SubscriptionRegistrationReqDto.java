package com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.dto.request;

import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.domain.MenuPackage;
import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.domain.Subscription;
import com.coffeeSale.coffeeSaleEcoBean.user.domain.Coupon;
import com.coffeeSale.coffeeSaleEcoBean.user.domain.User;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;

@Data
public class SubscriptionRegistrationReqDto {

    private String recommendedUserId;

    private String paymentMethod;

    public Subscription toEntity(User user, Coupon coupon, MenuPackage menuPackage, SubscriptionRegistrationReqDto subscriptionRegistrationRequestDto) {
        int period = menuPackage.getPeriod(); // 메뉴 패키지의 개월 수
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, period); // 개월 수를 더함

        return Subscription.builder()
                .user(user)
                .coupon(coupon)
                .menuPackage(menuPackage)
                .recommendedUserId(subscriptionRegistrationRequestDto.getRecommendedUserId())
                .paymentMethod(subscriptionRegistrationRequestDto.getPaymentMethod())
                .DeadlineDate(calendar.getTime())
                .build();
    }
}
