package com.example.productservice.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventorySaveDto {
    @NotEmpty(message = "Quantity is required")
    private int quantity;

    @NotEmpty(message = "SubProduct is required")
    private SubProductDto subProduct;
}
