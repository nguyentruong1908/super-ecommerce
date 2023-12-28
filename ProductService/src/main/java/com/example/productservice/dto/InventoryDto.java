package com.example.productservice.dto;

import com.example.productservice.entity.AttributeValueEntity;
import com.example.productservice.entity.ImageEntity;
import com.example.productservice.entity.ProductEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDto extends BaseDto {
    private Long id;

    private int quantity;

}
