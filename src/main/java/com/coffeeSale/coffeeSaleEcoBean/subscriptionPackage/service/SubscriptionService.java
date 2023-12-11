package com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.service;

import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.domain.MenuPackage;
import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.domain.Subscription;
import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.dto.request.SubscriptionRegistrationReqDto;
import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.repository.MenuPackageRepository;
import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.repository.SubscriptionRepository;
import com.coffeeSale.coffeeSaleEcoBean.user.domain.Coupon;
import com.coffeeSale.coffeeSaleEcoBean.user.domain.User;
import com.coffeeSale.coffeeSaleEcoBean.user.repository.CouponRepository;
import com.coffeeSale.coffeeSaleEcoBean.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final MenuPackageRepository menuPackageRepository;
    private final CouponRepository couponRepository;
    private final UserRepository userRepository;

    public Long getSubscriptionMenuPackageId(User user){
        Subscription subscription = subscriptionRepository.findByUser(user);
        return subscription.getMenuPackage().getId();
    }

    //정기 구독 신청하기.
    @Transactional
    public Subscription registrationSubscription(Long menuPackageId, Long couponId, Long userId, SubscriptionRegistrationReqDto subscriptionRegistrationRequestDto){
        MenuPackage menuPackage = menuPackageRepository.findById(menuPackageId).orElseThrow();
        Coupon coupon = couponRepository.findById(couponId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();
        Subscription subscription = subscriptionRegistrationRequestDto.toEntity(user,coupon,menuPackage,subscriptionRegistrationRequestDto);
        useCoupon(coupon,subscription,menuPackage);
        return subscription;
    }

    //정기 구독 연장해주는 메서드
    public Subscription extendSubscription(Long subscriptionId, Integer period) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId).orElseThrow();
        MenuPackage menuPackage = menuPackageRepository.findMenuPackageByPeriod(subscription.getMenuPackage().getPeriod() + period).orElseThrow();
        subscription.setMenuPackage(menuPackage);
        subscription.setTotalPrice(menuPackage.getPrice());
        subscription.setIntegratedPrice(subscription.getTotalPrice() - subscription.getDeductedAmount());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(subscription.getDeadlineDate()); // 기존 DeadlineDate를 설정
        calendar.add(Calendar.MONTH, period); // 개월 수를 더함

        subscription.setDeadlineDate(calendar.getTime()); // 연장된 기간을 subscription에 설정
        return subscriptionRepository.save(subscription);
    }
    public void useCoupon(Coupon coupon, Subscription subscription, MenuPackage menuPackage){
        switch (coupon.getDiscountDetails()) {
            case "아이스 아메리카노 1+1 쿠폰" -> {
                coupon.setLimitAmount(4000); // 할인 가격 설정

                coupon.setState(false);
                coupon.setUsedDate(new Date()); // 쿠폰 사용 일자 설정
                couponRepository.save(coupon);

                subscription.setDeductedAmount(coupon.getLimitAmount()); // 쿠폰 할인 가격
                subscription.setTotalPrice(menuPackage.getPrice()); // 총 가격
                subscription.setIntegratedPrice(menuPackage.getPrice() - coupon.getLimitAmount()); // 통합 가격
                subscriptionRepository.save(subscription);
            }
            case "정기구독 10% 할인 쿠폰" -> {
                coupon.setLimitAmount(menuPackage.getPrice() / 10); // 할인 가격 설정

                coupon.setUsedDate(new Date()); // 쿠폰 사용 일자 설정
                coupon.setState(false);
                couponRepository.save(coupon);

                subscription.setDeductedAmount(coupon.getLimitAmount()); // 쿠폰 할인 가격
                subscription.setTotalPrice(menuPackage.getPrice()); // 총 가격
                subscription.setIntegratedPrice(menuPackage.getPrice() - coupon.getLimitAmount()); // 통합 가격
                subscriptionRepository.save(subscription);
            }
            case "정기구독 15% 할인 쿠폰" -> {
                coupon.setLimitAmount(menuPackage.getPrice() /100 * 15); // 할인 가격 설정

                coupon.setUsedDate(new Date()); // 쿠폰 사용 일자 설정
                coupon.setState(false);
                couponRepository.save(coupon);

                subscription.setDeductedAmount(coupon.getLimitAmount()); // 쿠폰 할인 가격
                subscription.setTotalPrice(menuPackage.getPrice()); // 총 가격
                subscription.setIntegratedPrice(menuPackage.getPrice() - coupon.getLimitAmount()); // 통합 가격
                subscriptionRepository.save(subscription);
            }
            case "정기구독 20% 할인 쿠폰" -> {
                coupon.setLimitAmount(menuPackage.getPrice() /100 * 20); // 할인 가격 설정

                coupon.setUsedDate(new Date()); // 쿠폰 사용 일자 설정
                coupon.setState(false);
                couponRepository.save(coupon);

                subscription.setDeductedAmount(coupon.getLimitAmount()); // 쿠폰 할인 가격
                subscription.setTotalPrice(menuPackage.getPrice()); // 총 가격
                subscription.setIntegratedPrice(menuPackage.getPrice() - coupon.getLimitAmount()); // 통합 가격
                subscriptionRepository.save(subscription);
            }
            default -> {
                subscription.setDeductedAmount(0);
                subscription.setTotalPrice(menuPackage.getPrice());
                subscription.setIntegratedPrice(subscription.getTotalPrice());
                subscriptionRepository.save(subscription);
            }
        }
    }
}
