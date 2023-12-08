package com.coffeeSale.coffeeSaleEcoBean.user.service;

import com.coffeeSale.coffeeSaleEcoBean.user.common.dto.UserJoinDto;
import com.coffeeSale.coffeeSaleEcoBean.user.common.dto.UserLoginDto;
import com.coffeeSale.coffeeSaleEcoBean.user.domain.Authority;
import com.coffeeSale.coffeeSaleEcoBean.user.domain.ShoppingCart;
import com.coffeeSale.coffeeSaleEcoBean.user.domain.User;
import com.coffeeSale.coffeeSaleEcoBean.user.repository.AuthorityRepository;
import com.coffeeSale.coffeeSaleEcoBean.user.repository.ShoppingCartRepository;
import com.coffeeSale.coffeeSaleEcoBean.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    @Transactional
    public void join (UserJoinDto userJoinDto){
        User user = User.createdUser(userJoinDto);
        validateDuplicateUser(user);
        user = userRepository.save(user);

        Authority authority = new Authority(null, user, "ROLE_USER");
        authorityRepository.save(authority);

        shoppingCartRepository.save(new ShoppingCart(null,user,0,0));
    }
    // 존재 하는 user 인지 확인.
    private void validateDuplicateUser(User user){
        userRepository.findByUserId(user.getUserId())
                .ifPresent(m -> {throw new IllegalStateException("이미 존재하는 회원입니다."); //이미 가입된 회원의 경우 IllegalStateException 예외를 발생시킴
                });
    }
    //회원 가져오기
    public User getUser(String userId) {
        return userRepository.findByUserId(userId).orElseThrow();
    }

    public boolean login(UserLoginDto userLoginDto){
        User user = getUser(userLoginDto.getUserId());
        return user.getPassword().equals(userLoginDto.getPassword());
    }
}
