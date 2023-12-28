package com.example.productservice.dto;

import com.example.productservice.entity.InventoryEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto extends BaseDto{
    private Long id;
    private String imgURL;
    private InventoryDto inventory;
}
