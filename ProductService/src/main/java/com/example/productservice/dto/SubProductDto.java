package com.example.productservice.dto;

import com.example.productservice.entity.AttributeValueEntity;
import com.example.productservice.entity.ImageEntity;
import com.example.productservice.entity.InventoryEntity;
import com.example.productservice.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubProductDto extends BaseDto {
    private Long id;

    private List<ImageEntity> image;

    private List<AttributeValueDto> attributeValue;

    private InventoryDto inventory;

//    private ProductEntity product;
}
