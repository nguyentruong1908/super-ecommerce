package com.example.authenticateservce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserSearch {
    private String searchQuery;
    private String username;
    private String email;
    private String phone;
    private String nationalName;
    private String province;
    private String district;
    private String wards;
    private String role;
    private String gender;
    private String status;
}
