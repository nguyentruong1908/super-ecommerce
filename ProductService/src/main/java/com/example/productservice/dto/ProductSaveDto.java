package com.example.productservice.dto;

import com.example.productservice.entity.AttributeValueEntity;
import com.example.productservice.entity.CategoryEntity;
import com.example.productservice.entity.ImageEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductSaveDto {
    @NotNull(message = "productName must not be null")
    private String productName;

    @NotNull(message = "category must not be null")
    private CategoryDto category;

    @NotNull(message = "productName must not be null")
    private int price;

    private String description;

    private Double discount;

    private List<CollectionDto> collection;
}
