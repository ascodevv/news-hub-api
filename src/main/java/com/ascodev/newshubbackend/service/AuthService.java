package com.ascodev.newshubbackend.service;

import com.ascodev.newshubbackend.dto.JwtRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthService {
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest);
}
