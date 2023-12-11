package com.coffeeSale.coffeeSaleEcoBean.common.controller;

import com.coffeeSale.coffeeSaleEcoBean.user.common.dto.UserResDto;
import com.coffeeSale.coffeeSaleEcoBean.user.domain.User;
import com.coffeeSale.coffeeSaleEcoBean.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {
    private final UserService userService;

    @GetMapping(value = "{id}")
    public ResponseEntity<?> getHomeView(@PathVariable("id") Long id){
        User user = userService.getUser(id);
        return new ResponseEntity<>(UserResDto.of(user),HttpStatus.OK);
    }
}
