package com.coffeeSale.coffeeSaleEcoBean.user.common.dto;

import com.coffeeSale.coffeeSaleEcoBean.user.common.Rating;
import com.coffeeSale.coffeeSaleEcoBean.user.domain.User;
import lombok.Builder;

@Builder
public class UserResDto {
    private String userId;

    private String phoneNumber;

    private String email;

    private String address;

    private Integer point;

    private Integer recommendations;

    private Integer reusableUsed;

    private Integer reusableReturned;

    private Rating rating;

    public static UserResDto of(User user){
        return UserResDto.builder()
                .userId(user.getUserId())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .point(user.getPoint())
                .recommendations(user.getRecommendations())
                .reusableUsed(user.getReusableUsed())
                .reusableReturned(user.getReusableReturned())
                .rating(user.getRating())
                .build();
    }
}
