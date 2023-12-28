package com.example.productservice.dto;

import com.example.productservice.model.AttributeType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionSaveDto {
    @NotNull(message = "Collection name type must not be null")
    private String collectionName;

    private Double discount;

}
