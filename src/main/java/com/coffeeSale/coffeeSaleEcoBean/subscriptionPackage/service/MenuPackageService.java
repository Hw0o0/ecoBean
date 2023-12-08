package com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.service;

import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.domain.MenuPackage;
import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.dto.CreateMenuPackageDto;
import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.repository.AvailableMenuRepository;
import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.repository.MenuPackageRepository;
import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.repository.SubscriptionOrderRepository;
import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuPackageService {
    private final SubscriptionRepository subscriptionRepository;
    private final MenuPackageRepository menuPackageRepository;
    private final SubscriptionOrderRepository subscriptionOrderRepository;
    private final AvailableMenuRepository availableMenuRepository;

    public MenuPackage createMenuPackage(CreateMenuPackageDto createMenuPackage){
       return menuPackageRepository.save(createMenuPackage.toEntity(createMenuPackage));
    }
}
