package com.coffeeSale.coffeeSaleEcoBean.user.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Level {
    ROOT("뿌리"),
    SPROUT("싹"),
    STEM("줄기"),
    BRANCH("가지"),
    TREE("나무");
    private final String level;
}