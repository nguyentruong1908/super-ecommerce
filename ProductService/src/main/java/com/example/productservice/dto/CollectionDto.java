package com.example.productservice.dto;

import com.example.productservice.entity.ProductEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionDto extends BaseDto{
    private Integer id;
    private String collectionName;
    private Double discount;
}
