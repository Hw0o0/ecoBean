package com.coffeeSale.coffeeSaleEcoBean.user.controller;

import com.coffeeSale.coffeeSaleEcoBean.common.ErrorResponse;
import com.coffeeSale.coffeeSaleEcoBean.user.common.dto.UserJoinDto;
import com.coffeeSale.coffeeSaleEcoBean.user.common.dto.UserLoginDto;
import com.coffeeSale.coffeeSaleEcoBean.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody UserJoinDto userJoinDto){
        try{
            userService.join(userJoinDto);
            return ResponseEntity.ok("가입 성공");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto){
        try {
            if(userService.login(userLoginDto)) {
                return ResponseEntity.ok("로그인 성공 :"+userService.getUser(userLoginDto.getUserId()).toString());
            }else {
                return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.NOT_FOUND,"고객 정보가 없습니다"));
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}