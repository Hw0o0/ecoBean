package com.coffeeSale.coffeeSaleEcoBean.user.common.dto;

import lombok.Data;

@Data
public class UserJoinDto {
    private String userId;
    private String password;
    private String phoneNumber;
    private String email;
    private String address;
}
