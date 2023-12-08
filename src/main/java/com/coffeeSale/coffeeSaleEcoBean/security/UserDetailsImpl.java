package com.coffeeSale.coffeeSaleEcoBean.security;

import com.coffeeSale.coffeeSaleEcoBean.user.domain.Authority;
import com.coffeeSale.coffeeSaleEcoBean.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private final User user;
    private final Set<Authority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities.stream()
                .map(authority -> (GrantedAuthority) authority::getRole)
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
