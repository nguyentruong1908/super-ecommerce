package com.example.productservice.dto;

import com.example.productservice.entity.AttributeEntity;
import com.example.productservice.entity.InventoryEntity;
import com.example.productservice.entity.ProductEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttributeValueDto {
    private Long id;

    private AttributeDto attribute;

    private String value;
}
