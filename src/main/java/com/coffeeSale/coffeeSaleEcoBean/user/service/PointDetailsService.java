package com.coffeeSale.coffeeSaleEcoBean.user.service;

import com.coffeeSale.coffeeSaleEcoBean.user.repository.PointDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointDetailsService {
    private final PointDetailsRepository pointDetailsRepository;


}
