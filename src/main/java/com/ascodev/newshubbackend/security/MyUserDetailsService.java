package com.ascodev.newshubbackend.security;

import com.ascodev.newshubbackend.entity.User;
import com.ascodev.newshubbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
            if(user != null) {
                return user;
            }
        throw new UsernameNotFoundException("user " + username +" not found" );
    }
}
