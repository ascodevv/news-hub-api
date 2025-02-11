package com.ascodev.newshubbackend.security;

import com.ascodev.newshubbackend.entity.User;
import com.ascodev.newshubbackend.exception.ResourceNotFoundException;
import com.ascodev.newshubbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow( () -> new ResourceNotFoundException(
                String.format("User with name %s not found", username)
        ));
        return new org.springframework.security.core.userdetails.User (
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities().stream().map(role -> new SimpleGrantedAuthority(role.getAuthority())).collect(Collectors.toList())
        );
    }
}
