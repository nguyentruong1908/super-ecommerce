package com.example.productservice.service;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.dto.ProductSaveDto;
import com.example.productservice.entity.ProductEntity;
import com.example.productservice.model.SearchProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ProductService {
  ProductDto create(ProductSaveDto productSaveDto);
  ProductDto update(Long id, ProductSaveDto productSaveDto);
  void delete(Long id);
  Page<ProductDto> findAll(Map<String, String> attributeFilter, Pageable pageable);
  ProductDto findById(Long id);
}
