package com.ascodev.newshubbackend.security;


import com.ascodev.newshubbackend.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class MyUserDetails implements UserDetails {

    private final User user;

    public Long getUserId() {
        return user.getId();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public String getEmail() {
        return user.getEmail();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return user.getRole().stream().map(role -> new SimpleGrantedAuthority(role.getAuthority()))
            .collect(Collectors.toList()
        );

    }

    public List<User.UserStatus> getUserStatus() {
        return user.getStatus();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
