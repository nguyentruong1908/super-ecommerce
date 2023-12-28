package com.example.productservice.dto;

import com.example.productservice.model.AttributeType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttributeSaveDto {
    @NotNull(message = "Attribute name must not be null")
    private String name;
    @NotNull(message = "Attribute type must not be null")
    private AttributeType attributeType;
}
