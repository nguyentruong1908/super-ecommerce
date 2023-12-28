package com.example.productservice.dto;

import com.example.productservice.entity.AttributeValueEntity;
import com.example.productservice.entity.ImageEntity;
import com.example.productservice.entity.InventoryEntity;
import com.example.productservice.entity.ProductEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubProductSaveDto extends BaseDto {
    private List<ImageEntity> image;

    @NotNull(message = "Attribute value must not be null")
    private List<AttributeValueEntity> attributeValue;

    @NotNull(message = "Product must not be null")
    private ProductEntity product;
}
