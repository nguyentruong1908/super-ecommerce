package com.example.authenticateservce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressSaveDto {
    private String nationalName;
    private String province;
    private String district;
    private String wards;
    private String addressDetail;
}
