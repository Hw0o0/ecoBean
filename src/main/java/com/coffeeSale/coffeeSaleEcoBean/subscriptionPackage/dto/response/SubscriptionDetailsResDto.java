package com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.dto.response;

import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.domain.AvailableMenu;
import com.coffeeSale.coffeeSaleEcoBean.user.common.Rating;
import com.coffeeSale.coffeeSaleEcoBean.user.domain.User;
import lombok.Builder;

import java.util.List;
@Builder
public class SubscriptionDetailsResDto {
    private String userId;

    private String phoneNumber;

    private String email;

    private String address;

    private Integer point;

    private Integer recommendations;

    private Integer reusableUsed;

    private Integer reusableReturned;

    private Rating rating;

    private List<AvailableMenu> availableMenuList;

    public static SubscriptionDetailsResDto of(User user,List<AvailableMenu> availableMenuList){
        return SubscriptionDetailsResDto.builder()
                .userId(user.getUserId())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .point(user.getPoint())
                .recommendations(user.getRecommendations())
                .reusableUsed(user.getReusableUsed())
                .reusableReturned(user.getReusableReturned())
                .rating(user.getRating())
                .availableMenuList(availableMenuList)
                .build();
    }
}
