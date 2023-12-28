package com.example.productservice.service;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.dto.ProductSaveDto;
import com.example.productservice.dto.SubProductDto;
import com.example.productservice.dto.SubProductSaveDto;
import com.example.productservice.entity.ProductEntity;
import com.example.productservice.model.SearchProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface SubProductService {
  SubProductDto create(SubProductSaveDto productSaveDto);
  SubProductDto update(Long id, SubProductSaveDto productSaveDto);
  void delete(Long id);
  Page<SubProductDto> findAll(Pageable pageable);
  SubProductDto findById(Long id);
}
