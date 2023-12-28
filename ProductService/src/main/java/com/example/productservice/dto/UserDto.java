package com.example.productservice.dto;

import com.example.productservice.model.Gender;
import com.example.productservice.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseDto {
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
