package com.example.productservice.config;

import com.example.productservice.dto.AddressDto;
import com.example.productservice.entity.AddressEntity;
import com.example.productservice.model.Gender;
import com.example.productservice.model.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class UserContext {
    private Long id;

    private String username;

    private String email;

    private Role roles;

    private String firstName;

    private String lastName;

    private String phone;

    private AddressDto address;

    private Gender gender;

    private String avatarUrl;
}
