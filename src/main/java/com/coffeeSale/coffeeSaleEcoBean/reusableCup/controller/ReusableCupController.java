package com.coffeeSale.coffeeSaleEcoBean.reusableCup.controller;

import com.coffeeSale.coffeeSaleEcoBean.reusableCup.service.ReusableCupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/reusable")
public class ReusableCupController {
    private final ReusableCupService reusableCupService;

    @GetMapping
    public ResponseEntity<?> myReusableCupView(@RequestParam("userId") Long id){
        return new ResponseEntity<>(reusableCupService.getReusableCupList(id),HttpStatus.OK);
    }

    @DeleteMapping("/returned")
    public ResponseEntity<?> reusableReturned(@RequestParam("reusableId") Long reusableId,@RequestParam("shopId") Long shopId){
        return new ResponseEntity<>(reusableCupService.reusableCupReturn(reusableId,shopId), HttpStatus.OK);
    }
}
