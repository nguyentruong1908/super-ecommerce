package com.example.productservice.dto;

import com.example.productservice.entity.AttributeEntity;
import com.example.productservice.entity.ProductEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttributeValueSaveDto {
    @NotNull(message = "Attribute must not be null")
    private AttributeDto attribute;

    @NotNull(message = "Product must not be null")
    private SubProductDto subProduct;

    @NotNull(message = "Value must not be null")
    private String value;
}
