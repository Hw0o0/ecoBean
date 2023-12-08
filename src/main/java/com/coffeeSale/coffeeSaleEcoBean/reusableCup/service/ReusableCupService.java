package com.coffeeSale.coffeeSaleEcoBean.reusableCup.service;

import com.coffeeSale.coffeeSaleEcoBean.menu.domain.Menu;
import com.coffeeSale.coffeeSaleEcoBean.menu.repository.MenuRepository;
import com.coffeeSale.coffeeSaleEcoBean.reusableCup.domain.ReusableCup;
import com.coffeeSale.coffeeSaleEcoBean.reusableCup.domain.ReusableCupReturnedDetails;
import com.coffeeSale.coffeeSaleEcoBean.reusableCup.repository.ReusableCupRepository;
import com.coffeeSale.coffeeSaleEcoBean.reusableCup.repository.ReusableCupReturnedDetailsRepository;
import com.coffeeSale.coffeeSaleEcoBean.shop.domain.Shop;
import com.coffeeSale.coffeeSaleEcoBean.shop.repository.ShopRepository;
import com.coffeeSale.coffeeSaleEcoBean.user.domain.User;
import com.coffeeSale.coffeeSaleEcoBean.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReusableCupService {
    private final ReusableCupRepository reusableCupRepository;
    private final ReusableCupReturnedDetailsRepository reusableCupReturnedDetailsRepository;
    private final UserRepository userRepository;
    private final ShopRepository shopRepository;
    private final MenuRepository menuRepository;

    public ReusableCup createReusable(Long userId,Long reusableId){
        User user = userRepository.findById(userId).orElseThrow();
        ReusableCup reusableCup =getReusableCup(reusableId);
        reusableCup.setUser(user);
        reusableCup.setPurchaseDate(new Date());
        reusableCup.setReturned(false);
        return reusableCupRepository.save(reusableCup);
    }
    public ReusableCup getReusableCup(Long reusableCupId){
        return reusableCupRepository.findById(reusableCupId).orElseThrow();
    }

    // 나의 리유저블 컵 Get 하는 메서드
    public List<ReusableCup> getReusableCupList(Long userId){
        User user = userRepository.findById(userId).orElseThrow();
        return reusableCupRepository.findAll().stream()
                .filter(reusableCup -> reusableCup.getUser().equals(user))
                .toList();
    }
    @Transactional
    public void reusableCupReturn(Long userId, Long reusableCupId,Long shopId){
        User user = userRepository.findById(userId).orElseThrow();
        user.setReusableUsed(user.getReusableUsed()+3); //유저의 리유저블 컵 사용횟수를 올려준다.
        ReusableCup reusableCup = reusableCupRepository.findById(reusableCupId).orElseThrow();
        Shop shop = shopRepository.findById(shopId).orElseThrow();
        shop.setReusableCupInventory(shop.getReusableCupInventory()+1);
        shopRepository.save(shop);
        reusableCupReturnedDetailsRepository.save(new ReusableCupReturnedDetails(null,reusableCup,shop,reusableCup.getNumberUses(),new Date()));
        reusableCup.setUser(null);
        reusableCup.setMenu(null);
        reusableCup.setNumberUses(0);
        reusableCup.setPurchaseDate(null);
        reusableCup.setReturned(true);
        reusableCupRepository.save(reusableCup);
    }

    public void reusableCupMenuRegistration(Long reusableCupId,Long menuId){
        ReusableCup reusableCup = getReusableCup(reusableCupId);
        Menu menu = menuRepository.findById(menuId).orElseThrow();
        reusableCup.setMenu(menu);
        reusableCupRepository.save(reusableCup);
    }
}
