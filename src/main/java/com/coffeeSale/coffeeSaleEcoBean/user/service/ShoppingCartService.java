package com.coffeeSale.coffeeSaleEcoBean.user.service;

import com.coffeeSale.coffeeSaleEcoBean.menu.domain.Menu;
import com.coffeeSale.coffeeSaleEcoBean.menu.repository.MenuRepository;
import com.coffeeSale.coffeeSaleEcoBean.user.domain.ShoppingCart;
import com.coffeeSale.coffeeSaleEcoBean.user.domain.ShoppingCartItems;
import com.coffeeSale.coffeeSaleEcoBean.user.repository.ShoppingCartItemsRepository;
import com.coffeeSale.coffeeSaleEcoBean.user.repository.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ShoppingCartService {

    private final MenuRepository menuRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartItemsRepository shoppingCartItemsRepository;

    public void createShoppingCartItem(Long menuId){
        Menu menu = menuRepository.findById(menuId).orElseThrow();
        // 이미 동일한 메뉴가 장바구니에 있는지 확인
        Optional<ShoppingCartItems> shoppingCartItemCheck = shoppingCartItemsRepository.findByMenu(menu);

        //장바구니 여러개 구매
        if (shoppingCartItemCheck.isPresent()) {
            // 동일한 메뉴가 이미 장바구니에 있으면 수량만 증가시킴
            ShoppingCartItems shoppingCartItemInfo = shoppingCartItemCheck.get();
            shoppingCartItemInfo.setAmount(shoppingCartItemInfo.getAmount() + 1);
            shoppingCartItemsRepository.save(shoppingCartItemInfo);
            return;
        }

        ShoppingCart shoppingCart = shoppingCartRepository.findById(1L).orElseThrow();
        // 동일한 메뉴가 없으면 새로운 ShoppingCartItems를 생성함.
        shoppingCartItemsRepository.save(ShoppingCartItems.builder().shoppingCart(shoppingCart).menu(menu).amount(1).build());
    }
}
