package com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.dto;

import com.coffeeSale.coffeeSaleEcoBean.subscriptionPackage.domain.MenuPackage;
import lombok.Data;

@Data
public class CreateMenuPackageDto {
    private String packageName;

    private Integer price;

    private Integer Period;

    private Integer discountPrice;

    public MenuPackage toEntity(CreateMenuPackageDto createMenuPackageDto){
        return MenuPackage.builder().packageName(createMenuPackageDto.packageName)
                .price(createMenuPackageDto.getPrice())
                .period(createMenuPackageDto.getPeriod())
                .discountPrice(createMenuPackageDto.getDiscountPrice())
                .build();
    }
}
