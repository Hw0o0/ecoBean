package com.coffeeSale.coffeeSaleEcoBean.orders.dto;

import lombok.Data;

@Data
public class OrdersDto {
    private String payMethod;

    private Integer usedPoint;

    private boolean purchaseReusable;
}
