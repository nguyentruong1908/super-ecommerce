package com.example.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto extends BaseDto {
    private Long id;
    private String nationalName;
    private String province;
    private String district;
    private String wards;
    private String addressDetail;
}
