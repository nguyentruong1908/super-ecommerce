package com.example.productservice.dto;

import com.example.productservice.model.AttributeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttributeDto {
    private Long id;
    private String name;
    private AttributeType attributeType;
}
