package com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.service;

import com.coffeeSale.coffeeSaleEcoBean.reusableCup.domain.ReusableCup;
import com.coffeeSale.coffeeSaleEcoBean.reusableCup.repository.ReusableCupRepository;
import com.coffeeSale.coffeeSaleEcoBean.shop.domain.Shop;
import com.coffeeSale.coffeeSaleEcoBean.shop.repository.ShopRepository;
import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.domain.AvailableMenu;
import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.domain.Subscription;
import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.domain.SubscriptionOrder;
import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.repository.AvailableMenuRepository;
import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class SubscriptionOrderService {
    private final SubscriptionRepository subscriptionRepository;
    private final ShopRepository shopRepository;
    private final ReusableCupRepository reusableCupRepository;
    private final AvailableMenuRepository availableMenuRepository;

    //구독 주문할 경우
    @Transactional
    public SubscriptionOrder orderMenu(Long subscriptionId, Long shopId, Long reusableCupId,Long availableMenuId,boolean temperatureControl){
        Subscription subscription = subscriptionRepository.findById(subscriptionId).orElseThrow();
        Shop shop = shopRepository.findById(shopId).orElseThrow();
        ReusableCup reusableCup = reusableCupRepository.findById(reusableCupId).orElseThrow();
        AvailableMenu availableMenu = availableMenuRepository.findById(availableMenuId).orElseThrow();
        return SubscriptionOrder.builder()
                .subscription(subscription)
                .shop(shop)
                .reusableCup(reusableCup)
                .availableMenu(availableMenu)
                .temperatureControl(temperatureControl)
                .build();
    }
    //구독 주문하는데 리유저블 컵으로 바로 메뉴 주문하는 메서드
    @Transactional
    public SubscriptionOrder reusableMenuOrder(Long subscriptionId, Long shopId, Long reusableCupId,boolean temperatureControl){
        Subscription subscription = subscriptionRepository.findById(subscriptionId).orElseThrow();
        Shop shop = shopRepository.findById(shopId).orElseThrow();
        ReusableCup reusableCup = reusableCupRepository.findById(reusableCupId).orElseThrow();
        AvailableMenu availableMenu = availableMenuRepository.findByMenu(reusableCup.getMenu()).orElseThrow();
        return SubscriptionOrder.builder()
                .subscription(subscription)
                .shop(shop)
                .reusableCup(reusableCup)
                .availableMenu(availableMenu)
                .temperatureControl(temperatureControl)
                .build();
    }
}
