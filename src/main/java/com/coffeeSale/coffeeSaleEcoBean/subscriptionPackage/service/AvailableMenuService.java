package com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.service;

import com.coffeeSale.coffeeSaleEcoBean.menu.domain.Menu;
import com.coffeeSale.coffeeSaleEcoBean.menu.repository.MenuRepository;
import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.domain.AvailableMenu;
import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.domain.MenuPackage;
import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.repository.AvailableMenuRepository;
import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.repository.MenuPackageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvailableMenuService {
    private final MenuPackageRepository menuPackageRepository;
    private final AvailableMenuRepository availableMenuRepository;
    private final MenuRepository menuRepository;

    public void createPackageMenu(Long menuPackageId,Long menuId){
        Menu menu = menuRepository.findById(menuId).orElseThrow();
        // 이미 동일한 메뉴가 구매 가능 메뉴들 중에  있는지 확인
        Optional<AvailableMenu> availableMenuCheck = availableMenuRepository.findByMenu(menu);
        if (availableMenuCheck.isEmpty()) {
            MenuPackage menuPackage = menuPackageRepository.findById(menuPackageId).orElseThrow();
            AvailableMenu highestPriceMenu = availableMenuRepository.findAll().stream()
                    .filter(availableMenu -> availableMenu.getId().equals(menuPackageId))
                    .max(Comparator.comparing(availableMenu -> availableMenu.getMenu().getPrice())).orElseThrow();
            int deductedAmount = highestPriceMenu.getMenu().getPrice() - menu.getPrice();
            availableMenuRepository.save(AvailableMenu.createAvailMenu(menuPackage, menu,deductedAmount));
        }
    }
    //각 패키지 별 구매 가능 메뉴 모아서 반환해주는 메서드
    public List<AvailableMenu> menuFilter(Long menuPackageId) {
        List<AvailableMenu> availableMenuList = new ArrayList<>();

        if (menuPackageId == 1L) {
            availableMenuList = availableMenuRepository.findAll().stream()
                    .filter(availableMenu -> availableMenu.getMenuPackage().getId() <= menuPackageId)
                    .collect(Collectors.toList());
        } else if (menuPackageId == 2L) {
            availableMenuList = availableMenuRepository.findAll().stream()
                    .filter(availableMenu -> availableMenu.getMenuPackage().getId() <= menuPackageId)
                    .collect(Collectors.toList());
        } else if (menuPackageId == 3L) {
            availableMenuList = availableMenuRepository.findAll().stream()
                    .filter(availableMenu -> availableMenu.getMenuPackage().getId() <= menuPackageId)
                    .collect(Collectors.toList());
        } else if (menuPackageId == 4L) {
            availableMenuList = availableMenuRepository.findAll().stream()
                    .filter(availableMenu -> availableMenu.getMenuPackage().getId() <= menuPackageId)
                    .collect(Collectors.toList());
        }
        return availableMenuList;
    }
}
