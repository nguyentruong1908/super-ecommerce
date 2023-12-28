package com.example.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchProduct {
    private String searchQuery;
//    private Map<String> searchs;
    private String category;
    private String categoryParent;
    private int priceFrom;
    private int priceTo;
    private Double discount;
}
