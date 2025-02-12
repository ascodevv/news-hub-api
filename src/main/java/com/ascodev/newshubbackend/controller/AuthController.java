package com.ascodev.newshubbackend.controller;

import com.ascodev.newshubbackend.dto.JwtRequest;
import com.ascodev.newshubbackend.security.MyUserDetails;
import com.ascodev.newshubbackend.security.MyUserDetailsService;
import com.ascodev.newshubbackend.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtTokenUtils jwtTokenUtils;
    private final MyUserDetailsService myUserDetailsService;
    private final AuthenticationManager authenticationManager;

    @PostMapping()
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }

        MyUserDetails myUserDetails = (MyUserDetails) myUserDetailsService.loadUserByUsername(authRequest.getUsername());

        String token = jwtTokenUtils.generateToken(myUserDetails);

        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

}
