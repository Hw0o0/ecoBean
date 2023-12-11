package com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.controller;

import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.domain.AvailableMenu;
import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.domain.Subscription;
import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.dto.request.SubscriptionRegistrationReqDto;
import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.dto.response.SubscriptionDetailsResDto;
import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.dto.response.SubscriptionRegistrationResDto;
import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.service.AvailableMenuService;
import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.service.SubscriptionService;
import com.coffeeSale.coffeeSaleEcoBean.user.domain.User;
import com.coffeeSale.coffeeSaleEcoBean.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subscription")
public class SubscriptionPackageController {

    private final UserService userService;

    private final SubscriptionService subscriptionService;

    private final AvailableMenuService availableMenuService;
    //정기구독 상세보기 페이지
    @GetMapping("/subscriptionDetails")
    public ResponseEntity<?> subscriptionDetails(@RequestParam("userId") Long id){
        User user = userService.getUser(id);
        List<AvailableMenu> availableMenuList = availableMenuService.menuFilter(subscriptionService.getSubscriptionMenuPackageId(user));
        return new ResponseEntity<>(SubscriptionDetailsResDto.of(user,availableMenuList),HttpStatus.OK);
    }
    //정기구독 신청하기 눌렀을때
    @PostMapping("/registration")
    public ResponseEntity<?> registrationSubscription(@RequestParam("userId") Long userId,
                                                      @RequestParam("couponId") Long couponId,
                                                      @RequestParam("menuPackageId") Long menuPackageId,
                                                      @RequestBody SubscriptionRegistrationReqDto subscriptionRegistrationRequestDto){
        Subscription subscription = subscriptionService.registrationSubscription(userId,couponId,menuPackageId,subscriptionRegistrationRequestDto);
        return new ResponseEntity<>(SubscriptionRegistrationResDto.of(subscription), HttpStatus.OK);
    }
}
