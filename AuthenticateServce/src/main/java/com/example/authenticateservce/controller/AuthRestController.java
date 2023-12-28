package com.example.authenticateservce.controller;

import com.example.authenticateservce.config.authorize.CustomUserDetails;
import com.example.authenticateservce.config.jwt.JwtAuthenticationFilter;
import com.example.authenticateservce.config.jwt.JwtTokenProvider;
import com.example.authenticateservce.dto.UserDto;
import com.example.authenticateservce.dto.UserSaveDto;
import com.example.authenticateservce.entity.UserEntity;
import com.example.authenticateservce.model.LoginRequest;
import com.example.authenticateservce.service.AuthRestService;
import com.example.authenticateservce.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthRestController {
    private final AuthRestService authRestService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(authRestService.login(loginRequest), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserSaveDto dto) {
        return new ResponseEntity<>(authRestService.register(dto), HttpStatus.OK);
    }

    @GetMapping("/usercontext")
    public ResponseEntity<UserDto> getCurrentUser(HttpServletRequest request) {
        return new ResponseEntity<>(authRestService.getCurrentUser(request), HttpStatus.OK);
    }

}