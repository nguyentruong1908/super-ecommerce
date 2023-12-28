package com.example.authenticateservce.dto;

import com.example.authenticateservce.entity.AddressEntity;
import com.example.authenticateservce.model.Gender;
import com.example.authenticateservce.model.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSaveDto {
    private String username;
    private String email;
    private String password;
    private Role roles;
    private String firstName;
    private String lastName;
    private String phone;
    private AddressSaveDto address;
    private Gender gender;
    private String avatarUrl;
}
