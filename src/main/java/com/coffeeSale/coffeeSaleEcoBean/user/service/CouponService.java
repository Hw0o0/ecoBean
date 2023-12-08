package com.coffeeSale.coffeeSaleEcoBean.user.service;

import com.coffeeSale.coffeeSaleEcoBean.user.domain.Coupon;
import com.coffeeSale.coffeeSaleEcoBean.user.domain.User;
import com.coffeeSale.coffeeSaleEcoBean.user.repository.CouponRepository;
import com.coffeeSale.coffeeSaleEcoBean.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;
    private final UserRepository userRepository;

    public List<Coupon> couponListAssist(Long userId){
        User user = userRepository.findById(userId).orElseThrow();
        return couponRepository.findAll().stream()
                .filter(coupon -> coupon.getUser().equals(user))
                .toList();
    }
    public List<Coupon> getCouponList(Integer checkNum,Long userId){
        List<Coupon> couponList = new ArrayList<>();
        if(checkNum == 1){  //coupon 사용 가능한 것
            couponList = couponListAssist(userId).stream()
                    .filter(coupon -> coupon.getUsedDate()==null)
                    .toList();
        }else if(checkNum == 0){ // coupon 사용한 것
            couponList = couponListAssist(userId).stream()
                    .filter(coupon -> coupon.getUsedDate()!=null)
                    .toList();
        }
        return couponList;
    }
}
