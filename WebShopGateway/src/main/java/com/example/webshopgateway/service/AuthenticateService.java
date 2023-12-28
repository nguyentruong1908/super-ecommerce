package com.example.webshopgateway.service;

import com.example.webshopgateway.entity.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

public interface AuthenticateService {
    @GetMapping("/auth/usercontext")
    ResponseEntity<String> getCurrentUser(@RequestHeader(name = "Authorization") String token);

    @GetMapping("/auth/admin")
    ResponseEntity<String> roleAdmin(@RequestHeader(name = "Authorization") String token);

    @GetMapping("/auth/test")
    ResponseEntity<String> test(@RequestHeader(name = "Authorization") String token);
}