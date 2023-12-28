package com.example.productservice.dto;

import com.example.productservice.entity.*;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto extends BaseDto {
    private Long id;

    private String productName;

    private CategoryDto category;

    private int price;

    private String description;

    private Double discount;

    private List<SubProductDto> subProduct;

    private List<CollectionDto> collection;
}
