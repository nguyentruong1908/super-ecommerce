package com.example.productservice.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategorySaveDto {
    private Long parentId;
    @NotEmpty(message = "categoryName must not be null")
    private String categoryName;
}
